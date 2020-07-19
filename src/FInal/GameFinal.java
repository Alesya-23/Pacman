package Final;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import EnemyAndPacman.Pacman;
import PacmanListRecord.PacmanListRecord;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Toolkit;
import java.awt.Label;
import java.awt.Window.Type;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class GameFinal implements KeyListener, Updated {

	private JFrame frmLab;
	public javax.swing.Timer timer;
	private JPanel panel;
	private JLabel lblGameOver;
	private JLabel lblGameWin;
	private JLabel lblEat;
	private JLabel lblPoint;
	private JLabel lblLives;
	private JLabel lblLevel;
	static int live = 4;
	public int Level = 1;
	private int time = 800;
	PacmanField pacmanFieldLevel;
	PacmanListRecord list;
	private Label Rez;
	private JButton restart;
	private boolean flagPause = false;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFinal window = new GameFinal();
					window.frmLab.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameFinal() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	/**
	 * 
	 */

	private void initialize() {

		frmLab = new JFrame();
		frmLab.setType(Type.POPUP);
		frmLab.setTitle("Pacman");
		frmLab.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"Assets/Splash.png"));
		frmLab.getContentPane().setBackground(Color.BLACK);
		frmLab.getContentPane().setForeground(new Color(0, 0, 0));
		frmLab.setBounds(100, 100, 822, 661);
		frmLab.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLab.getContentPane().setLayout(null);

		pacmanFieldLevel = new PacmanField(3);
		list = new PacmanListRecord(0);
		checklive();

		panel = new PacmanPanelLevel(pacmanFieldLevel, this);
		panel.setBackground(Color.BLACK);
		panel.setBounds(30, 49, 704, 443);
		frmLab.getContentPane().add(panel);
		panel.addKeyListener(this);
		panel.setFocusable(true);

		lblGameOver = new JLabel("");
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameOver.setForeground(Color.RED);
		lblGameOver.setBackground(Color.RED);
		lblGameOver.setFont(new Font("Arial Black", Font.BOLD, 60));
		lblGameOver.setBounds(-4, 489, 800, 79);
		frmLab.getContentPane().add(lblGameOver);

		lblGameWin = new JLabel("");
		lblGameWin.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameWin.setForeground(Color.GREEN);
		lblGameWin.setBackground(Color.PINK);
		lblGameWin.setFont(new Font("Arial Black", Font.BOLD, 50));
		lblGameWin.setBounds(60, 489, 654, 48);
		frmLab.getContentPane().add(lblGameWin);
		lblGameWin.setText(" ");

		lblEat = new JLabel("");
		lblEat.setForeground(Color.BLUE);
		lblEat.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEat.setBackground(Color.RED);
		lblEat.setBounds(72, 12, 102, 20);
		frmLab.getContentPane().add(lblEat);
		lblEat.setText("EAT: " + pacmanFieldLevel.countEat());

		lblPoint = new JLabel("");
		lblPoint.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblPoint.setForeground(Color.BLUE);
		lblPoint.setBounds(182, 12, 160, 20);
		frmLab.getContentPane().add(lblPoint);
		lblPoint.setText("POINT: " + pacmanFieldLevel.countPoint());

		lblLives = new JLabel("LIVES " + live);
		lblLives.setForeground(Color.BLUE);
		lblLives.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblLives.setBackground(Color.BLACK);
		lblLives.setBounds(350, 12, 102, 20);
		frmLab.getContentPane().add(lblLives);

		Rez = new Label("");
		Rez.setForeground(new Color(0, 153, 51));
		Rez.setFont(new Font("Dialog", Font.BOLD, 20));
		Rez.setBounds(6, 552, 790, 48);
		frmLab.getContentPane().add(Rez);

		restart = new JButton("");
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				restartGame();
			}
		});
		restart.setBackground(Color.DARK_GRAY);
		restart.setIcon(new ImageIcon(
				"C:\\Users\\aleca\\worcspace\\PacmanMy\\Assets\\restart.png"));
		restart.setBounds(469, 10, 35, 35);
		frmLab.getContentPane().add(restart);
		restart.setEnabled(false);

		lblLevel = new JLabel("Level");
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblLevel.setForeground(Color.BLUE);
		lblLevel.setBackground(Color.BLACK);
		lblLevel.setBounds(572, 14, 113, 20);
		frmLab.getContentPane().add(lblLevel);

		timer = new Timer(time, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameStart();
			}
		});
		timer.start();

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (!pacmanFieldLevel.isGameIsOver() && live > 0) {
			switch (arg0.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				Sound.playSound("Assets/Eat.wav").join();
				if (flagPause != true)
					Pacman.moveToLeft();
				UpDateAllHero();
				panel.repaint();
				break;

			case KeyEvent.VK_RIGHT:
				Sound.playSound("Assets/Eat.wav").join();
				if (flagPause != true)
					Pacman.moveToRight();
				UpDateAllHero();
				panel.repaint();
				break;
			case KeyEvent.VK_DOWN:
				Sound.playSound("Assets/Eat.wav").join();
				if (flagPause != true)
					Pacman.moveDown();
				UpDateAllHero();
				panel.repaint();
				break;
			case KeyEvent.VK_UP:
				Sound.playSound("Assets/Eat.wav").join();
				if (flagPause != true)
					Pacman.moveUp();
				UpDateAllHero();
				panel.repaint();
				break;
			case KeyEvent.VK_SPACE:
				if (flagPause == false) {
					timer.stop();
					flagPause = true;
				} else if (flagPause == true) {
					timer.start();
					flagPause = false;
				}
				panel.repaint();
				break;
			default:
				return;
			}
		}
	}

	public void GameStart() {
		while (!pacmanFieldLevel.isGameIsOver()
				&& !pacmanFieldLevel.isGameWin() && live > 0) {
			pacmanFieldLevel.stateEnergizer();
			pacmanFieldLevel.checkHero();
			int timePursuit = 0;
			if (PacmanField.afraid()) {
				timePursuit = 7 * time;
				while (timePursuit > 0) {
					pacmanFieldLevel.moveAfraid();
					pacmanFieldLevel.Cherry();
					panel.repaint();
					break;
				}
				timePursuit -= time;

				while (timePursuit > -4 * time) {
					if (!PacmanField.afraid()) {
						for (int i = 0; i < 2; i++) {
							pacmanFieldLevel.moveEnemy();
						}
						panel.repaint();
						break;
					}
					timePursuit -= time;
				}
			} else {
				pacmanFieldLevel.moveEnemy();
				pacmanFieldLevel.checkHero();
			}
			panel.repaint();
			break;
		}
		if (pacmanFieldLevel.isGameIsOver() && live != 0) {
			lblGameOver.setText("GAME OVER! ");
			list.insertToHead(PacmanField.countPoint);
			lblLives.setText("LIVES " + live);
			lblEat.setText("EAT: ");
			lblPoint.setText("POINT:" + "0");
			panel.repaint();
			actionButton();
			panel.repaint();
			update();

		} else if (pacmanFieldLevel.isGameWin()) {
			lblGameWin.setText("YOU WIN! ");
			list.insertToHead(PacmanField.countPoint);
			if (Level < 3) {
				Level++;
				checklive();
				panel.repaint();
			} else if (Level == 3) {
				Rez.setText("Rezults: " + list.toString());
				actionButton();
				panel.repaint();
			}
		} else if (live == 0) {
			lblGameOver.setFont(new Font("Arial Black", Font.BOLD, 24));
			lblGameOver.setText("Rezults: " + list.toString());
			actionButton();
			panel.repaint();
		}
		lblLives.setText("LIVES " + live);
		lblLevel.setText("Level " + Level);
	}

	public void checklive() {
		if (live >= 1) {
			live--;
			if (Level == 1) {
				if (pacmanFieldLevel.isGameWin())
					live++;
				time = 725;
				pacmanFieldLevel = new PacmanField(1);
				UpDateAllLevel();
				panel.setBounds(30, 49, 706, 439);
			}
			else if (Level == 2) {
				if (pacmanFieldLevel.isGameWin())
					live++;
				time = 550;
				pacmanFieldLevel = new PacmanField(2);
				UpDateAllLevel();
			}
			else  if (Level == 3) {
				if (pacmanFieldLevel.isGameWin())
					live++;
				time = 500;
				pacmanFieldLevel = new PacmanField(3);
				UpDateAllLevel();
			}
			else if (live == 0) {
				list.toString();
				lblLives.setText("LIVES " + live);
				lblGameOver.setText("GAME OVER! ");
				lblGameOver.setFont(new Font("Arial Black", Font.BOLD, 24));
				lblGameOver.setText("Rezults: " + list.toString());
			}
		}
	}

	@Override
	public void update() {
		panel.repaint();
	}

	public void restartGame() {
		if (live <= 0) {
			restart.setEnabled(false);
			live = 4;
			Level = 1;
			pacmanFieldLevel = new PacmanField(1);
			list = new PacmanListRecord(0);
			checklive();
			timer.restart();
			restart.setEnabled(false);
			panel.repaint();

		} else if (pacmanFieldLevel.isGameIsOver() && live>0) {
			timer.restart();
			checklive();
			restart.setEnabled(false);
			panel.repaint();
			lblGameOver.setText("");
		}
	}

	public void actionButton() {
		timer.stop();
		restart.setEnabled(true);
	}

	public void UpDateAllHero() {
		pacmanFieldLevel.checkHero();
		lblGameOver.setText("");
		lblEat.setText("EAT: " + pacmanFieldLevel.countEat());
		lblPoint.setText("POINT " + pacmanFieldLevel.countPoint());
		lblGameWin.setText(" ");
	}

	public void UpDateAllLevel() {
		panel = new PacmanPanelLevel(pacmanFieldLevel, this);
		panel.setBackground(Color.BLACK);
		panel.setBounds(30, 49, 706, 439);
		frmLab.getContentPane().add(panel);
		PacmanField.countPoint = 0;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
