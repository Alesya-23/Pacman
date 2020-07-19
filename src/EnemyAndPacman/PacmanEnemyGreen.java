package EnemyAndPacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import Final.PacmanField;

public class PacmanEnemyGreen {

	public static int posEnemyI;
	public static int posEnemyJ;

	private static int[] mass;
	private static int imin;
	private static int prevmin;

	private static int count;
	private static int LevelTec;

	public PacmanEnemyGreen(int posEnemyI1, int posEnemyJ1) {
		posEnemyI = posEnemyI1;
		posEnemyJ = posEnemyJ1;

		imin = 0;
		mass = new int[5];
		prevmin = -1;
		count = 0;
		LevelTec = 0;

	}

	// враги пакмена

	public PacmanEnemyGreen() {
		// TODO Auto-generated constructor stub
	}

	public static void moveToLeftEnemy(int lastJ) {
		if (posEnemyJ >= 1
				|| (posEnemyJ == 1 && posEnemyI == 10 && (LevelTec == 2 || LevelTec == 3))) { // граница
																								// пол€
			posEnemyJ--;
		} else if (posEnemyJ == 0) {
			posEnemyJ = lastJ;
		}

	}

	public static void moveToRightEnemy(int lastJ) {
		if (posEnemyJ < lastJ
				|| (posEnemyJ == 27 && posEnemyI == 10 && (LevelTec == 2 || LevelTec == 3))) { // граница
																								// пол€
			posEnemyJ++;
		} else if (posEnemyJ == 28) {
			posEnemyJ = 0;
		}

	}

	public static void moveDownEnemy(int lastI) {
		if (posEnemyI < lastI) { // граница пол€
			posEnemyI++;
		}

	}

	public static void moveUpEnemy() {
		if (posEnemyI > 1) { // граница пол€
			posEnemyI--;
		}
	}

	// ......”ћЌџ… ѕ–»«–ј ...................06/04/20
	// провер€ем клетки вокруг текущей и считаем рассто€ние от них до пакмена
	public static void checkPosition(int array[][]) {
		if (array[posEnemyI][posEnemyJ - 1] != 1) { // лева€
			mass[0] = (int) Math.sqrt((posEnemyI - Pacman.PosHeroI)
					* (posEnemyI - Pacman.PosHeroI)
					+ (posEnemyJ - 1 - Pacman.PosHeroJ)
					* (posEnemyJ - 1 - Pacman.PosHeroJ));
			count++;
		} else
			mass[0] = -1;
		if (array[posEnemyI + 1][posEnemyJ] != 1) { // нижн€€
			mass[1] = (int) Math.sqrt((posEnemyI + 1 - Pacman.PosHeroI)
					* (posEnemyI + 1 - Pacman.PosHeroI)
					+ (posEnemyJ - Pacman.PosHeroJ)
					* (posEnemyJ - Pacman.PosHeroJ));
			count++;
		} else
			mass[1] = -1;
		if (array[posEnemyI][posEnemyJ + 1] != 1) { // права€
			mass[2] = (int) Math.sqrt((posEnemyI - Pacman.PosHeroI)
					* (posEnemyI - Pacman.PosHeroI)
					+ (posEnemyJ + 1 - Pacman.PosHeroJ)
					* (posEnemyJ + 1 - Pacman.PosHeroJ));
			count++;
		} else
			mass[2] = -1;
		if (array[posEnemyI - 1][posEnemyJ] != 1) { // верхн€€
			mass[3] = (int) Math.sqrt((posEnemyI - 1 - Pacman.PosHeroI)
					* (posEnemyI - 1 - Pacman.PosHeroI)
					+ (posEnemyJ - Pacman.PosHeroJ)
					* (posEnemyJ - Pacman.PosHeroJ));
			count++;
		} else
			mass[3] = -1;

	}

	public static void minRoad() {
		int min = 10000;
		for (int i = 0; i < 4; i++) {
			if (mass[i] < min && mass[i] != -1 && i != prevmin) {
				min = mass[i];
				imin = i;
			}
		}
		if (imin == 0) {
			prevmin = 2;
		}
		if (imin == 1) {
			prevmin = 3;
		}
		if (imin == 3) {
			prevmin = 1;
		}
		if (imin == 2) {
			prevmin = 0;
		}
	}

	public static void runClever(int array[][], int lastI, int lastJ, int Level) {
		while (1 > 0) {
			if (imin == 0) {
				if (posEnemyJ == 0 || array[posEnemyI][posEnemyJ - 1] != 1) {
					if (posEnemyI != 10 && posEnemyJ != 1)
						checkPosition(array);
					moveToLeftEnemy(lastJ);
					break;
				} else
					imin++;

			}
			if (imin == 1) {
				if (array[posEnemyI + 1][posEnemyJ] != 1) {
					checkPosition(array);
					moveDownEnemy(lastI);
					break;
				} else
					imin++;
			}
			if (imin == 2) {
				if (posEnemyJ == 28 || array[posEnemyI][posEnemyJ + 1] != 1) {
					if (posEnemyI != 10 && posEnemyJ != 27)
						checkPosition(array);
					moveToRightEnemy(lastJ);
					break;
				} else
					imin++;
			}
			if (imin == 3) {
				if (array[posEnemyI - 1][posEnemyJ] != 1) {
					checkPosition(array);
					moveUpEnemy();
					break;
				} else
					imin = 0;
			}

		}
	}

	public static void checkHero() {
		if (posEnemyI == Pacman.PosHeroI && posEnemyJ == Pacman.PosHeroJ
				&& PacmanField.energizer != true) {
			PacmanField.setGameIsOver(true);
		}
		if (posEnemyI == Pacman.PosHeroI && posEnemyJ == Pacman.PosHeroJ
				&& PacmanField.energizer == true) {
			PacmanField.countPoint += 200;
		}
	}

	// когда каунт мньше равен 2 то ходим рандомно ( по последнему мнимуму) если
	// больше то выбираем куда идти
	// преследователь-призрак
	public void CleverEnemyPursuit(int array[][], int Level, int lastI,
			int lastJ) {
		LevelTec = Level;
		if (posEnemyJ != 0 && posEnemyJ != 28) {
			checkPosition(array);
			if (count > 2) {
				minRoad();
				count = 0;
			}
		}
		runClever(array, lastI, lastJ, Level);
		checkHero();

	}

	// испуганный
	public void afraidEnemy(int array[][], int lastI, int lastJ) {

		if (posEnemyJ == 28 || array[posEnemyI][posEnemyJ + 1] != 1
				&& posEnemyI != Pacman.PosHeroI
				&& posEnemyJ + 1 != Pacman.PosHeroJ) {
			moveToRightEnemy(lastJ);

		} else if (array[posEnemyI - 1][posEnemyJ] != 1
				&& posEnemyI - 1 != Pacman.PosHeroI
				&& posEnemyJ != Pacman.PosHeroJ) {
			moveUpEnemy();

		} else if (posEnemyJ == 0 || array[posEnemyI][posEnemyJ - 1] != 1
				&& posEnemyI != Pacman.PosHeroI
				&& posEnemyJ - 1 != Pacman.PosHeroJ) {
			moveToLeftEnemy(lastJ);
		} else if (array[posEnemyI + 1][posEnemyJ] != 1
				&& posEnemyI + 1 != Pacman.PosHeroI
				&& posEnemyJ != Pacman.PosHeroJ) {
			moveDownEnemy(lastI);

		}
	}

	final public static int CELL_HEIGHT = 24;
	final public static int CELL_WIDTH = 24;

	public void drawEnemy(Graphics g, int x, int y) {
		int size = 2;
		Polygon j1 = new Polygon();
		j1.addPoint(CELL_HEIGHT * posEnemyJ + 4 * size + size / 2, CELL_WIDTH
				* posEnemyI + size / 2);
		j1.addPoint(CELL_HEIGHT * posEnemyJ + 6 * size + size / 2, CELL_WIDTH
				* posEnemyI + size / 4);
		j1.addPoint(CELL_HEIGHT * posEnemyJ + 8 * size + size / 2, CELL_WIDTH
				* posEnemyI + size / 2);
		j1.addPoint(CELL_HEIGHT * posEnemyJ + 10 * size + size / 2, CELL_WIDTH
				* posEnemyI + size);
		j1.addPoint(CELL_HEIGHT * posEnemyJ + 11 * size, CELL_WIDTH * posEnemyI
				+ 5 * size + size / 2);
		j1.addPoint(CELL_HEIGHT * posEnemyJ + 11 * size + size / 2, CELL_WIDTH
				* posEnemyI + 8 * size + size / 2);
		j1.addPoint(CELL_HEIGHT * posEnemyJ + size * 11, CELL_WIDTH * posEnemyI
				+ 12 * size);
		j1.addPoint(CELL_HEIGHT * posEnemyJ + 8 * size, CELL_WIDTH * posEnemyI
				+ 9 * size);
		j1.addPoint(CELL_HEIGHT * posEnemyJ + 6 * size + size / 2, CELL_WIDTH
				* posEnemyI + size * 12);
		j1.addPoint(CELL_HEIGHT * posEnemyJ + 5 * size, CELL_WIDTH * posEnemyI
				+ 9 * size);
		j1.addPoint(CELL_HEIGHT * posEnemyJ + size + size / 2, CELL_WIDTH
				* posEnemyI + 12 * size);
		j1.addPoint(CELL_HEIGHT * posEnemyJ + size, CELL_WIDTH * posEnemyI + 8
				* size + size / 2);
		j1.addPoint(CELL_HEIGHT * posEnemyJ + size + size / 2, CELL_WIDTH
				* posEnemyI + 5 * size + size / 2);
		j1.addPoint(CELL_HEIGHT * posEnemyJ + size * 2, CELL_WIDTH * posEnemyI
				+ size);

		if (PacmanField.afraid()) {
			g.setColor(Color.GRAY);
		} else
			g.setColor(Color.GREEN);

		if (LevelTec == 0)
			g.setColor(Color.GREEN);
		g.fillPolygon(j1);
		g.drawPolygon(j1);
		g.setColor(Color.WHITE);
		g.fillOval(CELL_HEIGHT * posEnemyJ + 4 * size, CELL_WIDTH * posEnemyI
				+ size * 3 + size / 2, 4, 5);
		g.fillOval(CELL_HEIGHT * posEnemyJ + 7 * size, CELL_WIDTH * posEnemyI
				+ size * 3 + size / 2, 4, 5);
		g.setColor(Color.BLACK);
		g.fillOval(CELL_HEIGHT * posEnemyJ + 4 * size + size / 16, CELL_WIDTH
				* posEnemyI + size * 3 + size / 2 + size / 16, 2, 3);
		g.fillOval(CELL_HEIGHT * posEnemyJ + 7 * size + size / 16, CELL_WIDTH
				* posEnemyI + size * 3 + size / 2 + size / 16, 2, 3);

	}
}
