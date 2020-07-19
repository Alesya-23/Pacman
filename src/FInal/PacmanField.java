package Final;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import EnemyAndPacman.Pacman;
import EnemyAndPacman.PacmanEnemyGreen;
import EnemyAndPacman.PacmanEnemyOrange;
import EnemyAndPacman.PacmanEnemyPink;
import EnemyAndPacman.PacmanEnemyRed;

public class PacmanField {

	public static int[][] array;
	public static int lastI;
	public static int lastJ;

	public static int eat;
	public static boolean gameIsOver;

	public static boolean energizer;

	private static int count;

	public static int stateEnergizer;

	public static int countPoint;

	public static int posCherryI;
	public static int posCherryJ;

	public static int posRottenCherryI;
	public static int posRottenCherryJ;

	public static int Level;

	PacmanEnemyRed pacmanEnemyRed;
	PacmanEnemyGreen pacmanEnemyGreen;
	PacmanEnemyPink pacmanEnemyPink;
	PacmanEnemyOrange pacmanEnemyOrange;
	Pacman pacman;

	public PacmanField(int level) {
		Level = level;
		if (Level == 1) {
			pacman = new Pacman(5, 3, Level);
			pacmanEnemyRed = new PacmanEnemyRed(1, 1);
			pacmanEnemyGreen = new PacmanEnemyGreen(12, 19);
			pacmanEnemyPink = new PacmanEnemyPink(12, 1);
			pacmanEnemyOrange = new PacmanEnemyOrange(1, 19);

			lastI = 14;
			lastJ = 21;
			array = new int[lastI][lastJ];
			try (BufferedReader br = new BufferedReader(new FileReader(
					"Assets/LevelFirst.txt"))) {
				for (int i = 0; i < lastI; i++) {
					String[] strArr = br.readLine().trim().split(" ");
					for (int j = 0; j < lastJ; j++) {
						array[i][j] = Integer.parseInt(strArr[j]);
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			eat = 0;
			for (int i = 0; i < lastI; i++) {
				for (int j = 0; j < lastJ; j++) {
					if (array[i][j] == 0)
						eat++;
				}
			}
			stateEnergizer = 0;
			count = 0;
			energizer = false;
			gameIsOver = false;
		}
		if (Level == 2) {
			pacman = new Pacman(10, 1, Level);
			pacmanEnemyRed = new PacmanEnemyRed(16, 27);
			pacmanEnemyGreen = new PacmanEnemyGreen(1, 1);
			pacmanEnemyPink = new PacmanEnemyPink(1, 27);
			pacmanEnemyOrange = new PacmanEnemyOrange(10, 11);

			lastI = 18;
			lastJ = 28;
			array = new int[lastI][lastJ + 1];
			try (BufferedReader br = new BufferedReader(new FileReader(
					"Assets/LevelSecond.txt"))) {
				for (int i = 0; i < lastI; i++) {
					String[] strArr = br.readLine().trim().split(" ");
					for (int j = 0; j < lastJ + 1; j++) {
						array[i][j] = Integer.parseInt(strArr[j]);
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			eat = 0;
			for (int i = 0; i < lastI; i++) {
				for (int j = 0; j < lastJ; j++) {
					if (array[i][j] == 0)
						eat++;
				}
			}
			stateEnergizer = 0;
			count = 0;
			energizer = false;
			gameIsOver = false;
			posCherryI = -1;
			posCherryJ = -2;
		}
		if (Level == 3) {
			pacman = new Pacman(10, 1, Level);
			pacmanEnemyRed = new PacmanEnemyRed(16, 27);
			pacmanEnemyGreen = new PacmanEnemyGreen(1, 1);
			pacmanEnemyPink = new PacmanEnemyPink(1, 27);
			pacmanEnemyOrange = new PacmanEnemyOrange(16, 1);

			lastI = 18;
			lastJ = 28;
			array = new int[lastI][lastJ + 1];
			try (BufferedReader br = new BufferedReader(new FileReader(
					"Assets/LevelTree.txt"))) {
				for (int i = 0; i < lastI; i++) {
					String[] strArr = br.readLine().trim().split(" ");
					for (int j = 0; j < lastJ + 1; j++) {
						array[i][j] = Integer.parseInt(strArr[j]);
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			eat = 0;
			for (int i = 0; i < lastI; i++) {
				for (int j = 0; j < lastJ; j++) {
					if (array[i][j] == 0)
						eat++;
				}
			}
			stateEnergizer = 0;
			count = 0;
			energizer = false;
			gameIsOver = false;
			posCherryI = -1;
			posCherryJ = -2;
			posRottenCherryI = 5;
			posRottenCherryJ = 7;
		}

	}

	public boolean isGameOver() {
		return isGameIsOver();
	}

	public boolean isGameIsOver() {
		return gameIsOver;
	}

	public static void setGameIsOver(boolean gameIsOver) {
		PacmanField.gameIsOver = gameIsOver;
	}

	public boolean isGameWin() {
		if (eat <= 0) {
			return true;
		} else
			return false;
	}

	// считаем количество съеденных клеток
	public static void haveEat() {
		if (array[Pacman.PosHeroI][Pacman.PosHeroJ] == 0) {
			eat--;
			countPoint += 25;
			if (Pacman.PosHeroI == posCherryI && Pacman.PosHeroJ == posCherryJ) {
				posCherryI = -1;
				posCherryJ = -2;
				if (Level == 2) {
					countPoint += 1000;
				}
				if (Level == 3) {
					GameFinal.live++;
				}
			}
		}
		if (Pacman.PosHeroI == posRottenCherryI
				&& Pacman.PosHeroJ == posRottenCherryJ) {
			posRottenCherryI = -1;
			posRottenCherryJ = -2;
			gameIsOver = true;
		}
	}

	public int countEat() {
		return eat;
	}

	public int countPoint() {
		return countPoint;
	}

	public static boolean getEnergizer() {
		return energizer;
	}

	public static void setEnergizer(boolean energizer) {
		PacmanField.energizer = energizer;
	}

	public void stateEnergizer() {
		if (stateEnergizer == 0) {
			stateEnergizer = 1;
		} else
			stateEnergizer = 0;
	}

	public static boolean afraid() {
		if (getEnergizer() == true && count != 7)
			return true;
		else {
			return false;
		}

	}

	public void moveEnemy() {
		if (!afraid()) {
			pacmanEnemyRed.CleverEnemyPursuit(array, Level, lastI, lastJ);
			pacmanEnemyPink.CleverEnemyPursuit(array, Level, lastI, lastJ);
			pacmanEnemyOrange.CleverEnemyPursuit(array, Level, lastI, lastJ);
			pacmanEnemyGreen.CleverEnemyPursuit(array, Level, lastI, lastJ);

			count = -1;
			setEnergizer(false);
		}

	}

	public void moveAfraid() {
		if (afraid()) {
			count++;
			pacmanEnemyRed.afraidEnemy(array, lastI, lastJ);
			pacmanEnemyGreen.afraidEnemy(array, lastI, lastJ);
			pacmanEnemyPink.afraidEnemy(array, lastI, lastJ);
			pacmanEnemyOrange.afraidEnemy(array, lastI, lastJ);
		}
	}
	public void checkHero(){
		PacmanEnemyRed.checkHero();
		PacmanEnemyGreen.checkHero();
		PacmanEnemyPink.checkHero();
		PacmanEnemyOrange.checkHero();
	}

	public int Count() {
		return count;
	}

	public void Cherry() {
		posCherryI = (int) (Math.random() * lastI);
		posCherryJ = (int) (Math.random() * lastJ);
		if (Level == 3) {
			posRottenCherryI = (int) (Math.random() * lastI);
			;
			posRottenCherryJ = (int) (Math.random() * lastI);
			;
		}
	}

	public void drawWalls(Graphics g, int i, int j) {
		g.setColor(Color.BLACK);
		g.fillRect(j * CELL_WIDTH, i * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(5));
		g.setColor(Color.BLUE);
		g2d.drawRoundRect(j * CELL_WIDTH + 2, i * CELL_HEIGHT + 2,
				CELL_WIDTH - 4, CELL_HEIGHT - 4, 13, 15);
		g2d.setStroke(new BasicStroke(1));
	}

	public void drawCherry(Graphics g, int i, int j) {
		g.setColor(Color.RED);
		g.fillOval(j * CELL_WIDTH, i * CELL_HEIGHT + 10, 10, 10);
		g.fillOval(j * CELL_WIDTH + 10, i * CELL_HEIGHT + 14, 10, 10);
		g.setColor(Color.GREEN);
		g.drawLine(j * CELL_WIDTH + 5, i * CELL_HEIGHT + 12,
				j * CELL_WIDTH + 9, i * CELL_HEIGHT + 1);
		g.drawLine(j * CELL_WIDTH + 14, i * CELL_HEIGHT + 15, j * CELL_WIDTH
				+ 9, i * CELL_HEIGHT + 1);
	}

	public void drawRottenCherry(Graphics g, int i, int j) {
		g.setColor(Color.RED);
		g.fillOval(j * CELL_WIDTH, i * CELL_HEIGHT + 10, 10, 10);
		g.fillOval(j * CELL_WIDTH + 10, i * CELL_HEIGHT + 14, 10, 10);
		g.setColor(Color.GRAY);
		g.fillOval(j * CELL_WIDTH + 12, i * CELL_HEIGHT + 15, 7, 9);
		g.fillOval(j * CELL_WIDTH + 4, i * CELL_HEIGHT + 12, 4, 6);
		g.setColor(Color.GREEN);
		g.drawLine(j * CELL_WIDTH + 5, i * CELL_HEIGHT + 12,
				j * CELL_WIDTH + 9, i * CELL_HEIGHT + 1);
		g.drawLine(j * CELL_WIDTH + 14, i * CELL_HEIGHT + 15, j * CELL_WIDTH
				+ 9, i * CELL_HEIGHT + 1);
	}

	final public static int CELL_HEIGHT = 24;
	final public static int CELL_WIDTH = 24;

	public void drawField(Graphics g, int width, int height) {
		for (int i = 0; i < array.length; i++) {
			int top = i * CELL_HEIGHT;
			for (int j = 0; j < array[i].length; j++) {
				int left = j * CELL_WIDTH;
				if (array[i][j] == 0) { // еда
					g.setColor(Color.BLACK);
					g.fillRect(left, top, CELL_WIDTH, CELL_HEIGHT);
					g.setColor(Color.WHITE);
					g.fillOval(left + 10, top + 10, 4, 4);
				} else if (array[i][j] == 1) { // стена
					drawWalls(g, i, j);
				} else if (array[i][j] == 8) { // нет еды
					g.setColor(Color.BLACK);
					g.fillRect(left, top, CELL_WIDTH, CELL_HEIGHT);
				} else if (array[i][j] == 9) { // енерджайзер
					if (stateEnergizer == 0) {
						g.setColor(Color.BLACK);
						g.fillRect(left, top, CELL_WIDTH, CELL_HEIGHT);
						g.setColor(Color.WHITE);
						g.fillOval(left + 5, top + 5, 15, 15);
					} else {
						g.setColor(Color.BLACK);
						g.fillRect(left, top, CELL_WIDTH, CELL_HEIGHT);
						g.setColor(Color.WHITE);
						g.fillOval(left + 8, top + 8, 10, 10);
					}
				}
			}

		}
		if (energizer != true) {
			if (Level == 2)
				drawCherry(g, posCherryI, posCherryJ);
			if (Level == 3) {
				drawCherry(g, posCherryI, posCherryJ);
				drawRottenCherry(g, posRottenCherryI, posRottenCherryJ);
			}
		}
		pacman.drawHero(g, CELL_WIDTH, CELL_HEIGHT);
		pacmanEnemyRed.drawEnemy(g, CELL_WIDTH, CELL_HEIGHT);
		pacmanEnemyGreen.drawEnemy(g, CELL_WIDTH, CELL_HEIGHT);
		pacmanEnemyPink.drawEnemy(g, CELL_WIDTH, CELL_HEIGHT);
		pacmanEnemyOrange.drawEnemy(g, CELL_WIDTH, CELL_HEIGHT);
		
	}
}