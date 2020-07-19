package EnemyAndPacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import Final.PacmanField;

public class Pacman {

	public static int PosHeroI;
	public static int PosHeroJ;
	public static int stateHero;
	public static int Level;

	public Pacman(int posHeroI, int posHeroJ, int level) {
		PosHeroI = posHeroI;
		PosHeroJ = posHeroJ;
		Level = level;
		stateHero = -1;

	}

	public Pacman() {
		// TODO Auto-generated constructor stub
	}

	public static void moveToLeft() {
		HeroEatLeft();
		if (Level == 1) {
			if (PacmanField.array[PosHeroI][PosHeroJ - 1] != 1 && checkEnemy()!=0) {
				PacmanField.haveEat();
				if (PacmanField.array[PosHeroI][PosHeroJ - 1] == 9) {
					PacmanField.setEnergizer(true);
					PacmanField.countPoint += 100;
				}
				PacmanField.array[PosHeroI][PosHeroJ] = 8;
				if (PosHeroJ > 1) { // граница поля
					PosHeroJ--;
				}
			}
		}
		if (Level == 2 || Level == 3) {
			if ((PosHeroI == 10 && PosHeroJ == 0)
					|| PacmanField.array[PosHeroI][PosHeroJ - 1] != 1 && checkEnemy()!=0) {
				PacmanField.haveEat();
				if (PosHeroJ != 0
						&& PacmanField.array[PosHeroI][PosHeroJ - 1] == 9) {
					PacmanField.setEnergizer(true);
					PacmanField.countPoint += 100;
				}
				PacmanField.array[PosHeroI][PosHeroJ] = 8;
				if (PosHeroJ > 0) { // граница поля
					PosHeroJ--;

				} else if (PosHeroJ == 0 && PosHeroI == 10) {
					PosHeroJ = 28;
				}

			}

		}

	}

	public static void moveToRight() {
		HeroEatRight();
		if (Level == 1) {
			if (PacmanField.array[PosHeroI][PosHeroJ + 1] != 1 && checkEnemy()!=0) {
				PacmanField.haveEat();
				if (PacmanField.array[PosHeroI][PosHeroJ + 1] == 9) {
					PacmanField.setEnergizer(true);
					PacmanField.countPoint += 100;
				}
				PacmanField.array[PosHeroI][PosHeroJ] = 8;
				if (PosHeroJ < PacmanField.lastJ) {
					PosHeroJ++;
				}
			}
		}
		if (Level == 2 || Level == 3) {
			if ((PosHeroI == 10 && PosHeroJ == 28)
					|| PacmanField.array[PosHeroI][PosHeroJ + 1] != 1 && checkEnemy()!=0) {
				PacmanField.haveEat();
				if (PosHeroJ != 28
						&& PacmanField.array[PosHeroI][PosHeroJ + 1] == 9) {
					PacmanField.setEnergizer(true);
					PacmanField.countPoint += 100;
				}
				PacmanField.array[PosHeroI][PosHeroJ] = 8;
				if (PosHeroJ < PacmanField.lastJ) {
					PosHeroJ++;
				} else if (PosHeroJ == 28 && PosHeroI == 10) {
					PosHeroJ = 0;
				}
			}
		}

	}

	public static void moveDown() {
		HeroEatDown();
		if (Level == 1) {
			if (PacmanField.array[PosHeroI + 1][PosHeroJ] != 1 && checkEnemy()!=0) {
				PacmanField.haveEat();
				if (PacmanField.array[PosHeroI + 1][PosHeroJ] == 9) {
					PacmanField.setEnergizer(true);
					PacmanField.countPoint += 100;
				}
				PacmanField.array[PosHeroI][PosHeroJ] = 8;
				if (PosHeroI < PacmanField.lastI) {
					PosHeroI++;
				}
			}
		}
		if (Level == 2 || Level == 3) {
			if (PacmanField.array[PosHeroI + 1][PosHeroJ] != 1 && checkEnemy()!=0) {
				PacmanField.haveEat();
				if (PacmanField.array[PosHeroI + 1][PosHeroJ] == 9) {
					PacmanField.setEnergizer(true);
					PacmanField.countPoint += 100;
				}
				PacmanField.array[PosHeroI][PosHeroJ] = 8;
				if (PosHeroI < PacmanField.lastI) {
					PosHeroI++;
				}

			}
		}

	}

	public static void moveUp() {
		HeroEatUp();
		if (Level == 1) {
			if (PacmanField.array[PosHeroI - 1][PosHeroJ] != 1 && checkEnemy()!=0) {
				PacmanField.haveEat();
				if (PacmanField.array[PosHeroI - 1][PosHeroJ] == 9) {
					PacmanField.setEnergizer(true);
					PacmanField.countPoint += 100;
				}
				PacmanField.array[PosHeroI][PosHeroJ] = 8;
				if (PosHeroI > 1) {
					PosHeroI--;
				}
			}
		}
		if (Level == 2 || Level == 3) {
			if (PacmanField.array[PosHeroI - 1][PosHeroJ] != 1 && checkEnemy()!=0) {
				PacmanField.haveEat();
				if (PacmanField.array[PosHeroI - 1][PosHeroJ] == 9) {
					PacmanField.setEnergizer(true);
					PacmanField.countPoint += 100;
				}
				PacmanField.array[PosHeroI][PosHeroJ] = 8;
				if (PosHeroI > 1) {
					PosHeroI--;
				}
			}
		}
	}

	public static void HeroEatRight() {
		if (stateHero == 5)
			stateHero = 2;
		else
			stateHero = 5;
	}

	public static void HeroEatLeft() {
		if (stateHero == 6)
			stateHero = 0;
		else
			stateHero = 6;
	}

	public static void HeroEatUp() {
		if (stateHero == 7)
			stateHero = 3;
		else
			stateHero = 7;
	}

	public static void HeroEatDown() {
		if (stateHero == 8)
			stateHero = 1;
		else
			stateHero = 8;
	}

	public static int checkEnemy() {
		if (PosHeroI != PacmanEnemyGreen.posEnemyI
				&& PosHeroJ != PacmanEnemyGreen.posEnemyJ
				|| PosHeroI != PacmanEnemyOrange.posEnemyI
				&& PosHeroJ != PacmanEnemyOrange.posEnemyJ
				|| PosHeroI != PacmanEnemyRed.posEnemyI
				&& PosHeroJ != PacmanEnemyRed.posEnemyJ
				|| PosHeroI != PacmanEnemyPink.posEnemyI
				&& PosHeroJ != PacmanEnemyPink.posEnemyJ) {
			return 1;
		} else {
			PacmanField.gameIsOver = true;
			return 0;
		}
	}

	public void drawHeroUp(Graphics g) {
		g.setColor(Color.YELLOW);
		int size = 2;
		g.fillOval(CELL_WIDTH * PosHeroJ, CELL_HEIGHT * PosHeroI, CELL_WIDTH,
				CELL_HEIGHT);
		Polygon j = new Polygon();
		j.addPoint(CELL_WIDTH * PosHeroJ + 3 * size, CELL_HEIGHT * PosHeroI);
		j.addPoint(CELL_WIDTH * PosHeroJ + 8 * size, CELL_HEIGHT * PosHeroI);
		j.addPoint(CELL_WIDTH * PosHeroJ + 6 * size + size / 2, CELL_HEIGHT
				* PosHeroI + size * 6);
		g.setColor(Color.BLACK);
		g.fillPolygon(j);
		g.drawPolygon(j);
	}

	public void drawHeroDown(Graphics g) {
		g.setColor(Color.YELLOW);
		int size = 2;
		g.fillOval(CELL_WIDTH * PosHeroJ, CELL_HEIGHT * PosHeroI, CELL_WIDTH,
				CELL_HEIGHT);
		Polygon j = new Polygon();
		j.addPoint(CELL_WIDTH * PosHeroJ + 3 * size, CELL_HEIGHT * PosHeroI
				+ size * 12 + size / 2);
		j.addPoint(CELL_WIDTH * PosHeroJ + 8 * size, CELL_HEIGHT * PosHeroI
				+ size * 12 + size / 2);
		j.addPoint(CELL_WIDTH * PosHeroJ + 6 * size + size / 2, CELL_HEIGHT
				* PosHeroI + size * 6);
		g.setColor(Color.BLACK);
		g.fillPolygon(j);
		g.drawPolygon(j);
	}

	public void drawHeroLeft(Graphics g) {
		g.setColor(Color.YELLOW);
		int size = 2;
		g.fillOval(CELL_WIDTH * PosHeroJ, CELL_HEIGHT * PosHeroI, CELL_WIDTH,
				CELL_HEIGHT);
		Polygon j = new Polygon();
		j.addPoint(CELL_WIDTH * PosHeroJ, CELL_HEIGHT * PosHeroI + size * 5);
		j.addPoint(CELL_WIDTH * PosHeroJ, CELL_HEIGHT * PosHeroI + size * 8);
		j.addPoint(CELL_WIDTH * PosHeroJ + 6 * size + size / 2, CELL_HEIGHT
				* PosHeroI + size * 6);
		g.setColor(Color.BLACK);
		g.fillPolygon(j);
		g.drawPolygon(j);
	}

	public void drawHeroRight(Graphics g) {
		g.setColor(Color.YELLOW);
		int size = 2;
		g.fillOval(CELL_WIDTH * PosHeroJ, CELL_HEIGHT * PosHeroI, CELL_WIDTH,
				CELL_HEIGHT);
		Polygon j = new Polygon();
		j.addPoint(CELL_WIDTH * PosHeroJ + size * 12 + size / 2, CELL_HEIGHT
				* PosHeroI + size * 5);
		j.addPoint(CELL_WIDTH * PosHeroJ + size * 12 + size / 2, CELL_HEIGHT
				* PosHeroI + size * 8);
		j.addPoint(CELL_WIDTH * PosHeroJ + 6 * size + size / 2, CELL_HEIGHT
				* PosHeroI + size * 6);
		g.setColor(Color.BLACK);
		g.fillPolygon(j);
		g.drawPolygon(j);
	}

	public void drawHeroEatRight(Graphics g) {
		g.setColor(Color.YELLOW);
		int size = 2;
		g.fillOval(CELL_WIDTH * PosHeroJ, CELL_HEIGHT * PosHeroI, CELL_WIDTH,
				CELL_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawLine(CELL_WIDTH * PosHeroJ + 6 * size + size / 2, CELL_HEIGHT
				* PosHeroI + size * 6, CELL_WIDTH * PosHeroJ + size * 12 + size
				/ 2, CELL_HEIGHT * PosHeroI + size * 6);
	}

	public void drawHeroEatLeft(Graphics g) {
		g.setColor(Color.YELLOW);
		int size = 2;
		g.fillOval(CELL_WIDTH * PosHeroJ, CELL_HEIGHT * PosHeroI, CELL_WIDTH,
				CELL_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawLine(CELL_WIDTH * PosHeroJ, CELL_HEIGHT * PosHeroI + size * 6,
				CELL_WIDTH * PosHeroJ + size * 6 + size / 2, CELL_HEIGHT
						* PosHeroI + size * 6);
	}

	public void drawHeroEatUp(Graphics g) {
		g.setColor(Color.YELLOW);
		int size = 2;
		g.fillOval(CELL_WIDTH * PosHeroJ, CELL_HEIGHT * PosHeroI, CELL_WIDTH,
				CELL_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawLine(CELL_WIDTH * PosHeroJ + 6 * size + size / 2, CELL_HEIGHT
				* PosHeroI, CELL_WIDTH * PosHeroJ + 6 * size + size / 2,
				CELL_HEIGHT * PosHeroI + size * 6 + size / 2);
	}

	public void drawHeroEatDown(Graphics g) {
		g.setColor(Color.YELLOW);
		int size = 2;
		g.fillOval(CELL_WIDTH * PosHeroJ, CELL_HEIGHT * PosHeroI, CELL_WIDTH,
				CELL_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawLine(CELL_WIDTH * PosHeroJ + 6 * size + size / 2, CELL_HEIGHT
				* PosHeroI + size * 6 + size / 2, CELL_WIDTH * PosHeroJ + size
				* 6 + size / 2, CELL_HEIGHT * PosHeroI + size * 12 + size / 2);
	}

	final public static int CELL_HEIGHT = 24;
	final public static int CELL_WIDTH = 24;

	public void drawHero(Graphics g, int width, int height) {
		if (stateHero == 1)
			drawHeroDown(g);
		else if (stateHero == 0)
			drawHeroLeft(g);
		else if (stateHero == 2 || stateHero == -1)
			drawHeroRight(g);
		else if (stateHero == 3)
			drawHeroUp(g);
		else if (stateHero == 5)
			drawHeroEatRight(g);
		else if (stateHero == 6)
			drawHeroEatLeft(g);
		else if (stateHero == 7)
			drawHeroEatUp(g);
		else if (stateHero == 8)
			drawHeroEatDown(g);
	}

}
