package refactoringProject07;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPanel extends RhythmGame {
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.jpg"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.jpg"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.jpg"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.jpg"));

	protected JButton startButton = new JButton(startButtonBasicImage);
	protected JButton quitButton = new JButton(quitButtonBasicImage);
	
	public MainPanel() {
		// 게임 시작 버튼 설정
		startButton.setBounds(0, 100, 430, 200);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
			}

			@Override // 화면에서 게임 시작 버튼을 누르면 곡 선택 화면으로 들어가짐
			public void mousePressed(MouseEvent e) {
				startButton.setVisible(false);
				quitButton.setVisible(false);
				
				selectMusic();
			}
		});
		add(startButton);
		
		// 게임 종료 버튼 설정
		quitButton.setBounds(0, 300, 430, 200);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
			}

			@Override // 화면에서 게임 종료 버튼을 누르면 게임 화면이 사라짐
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(quitButton);
	}
	public JButton getStartButton() {
		return startButton;
	}
	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}
	public JButton getQuitButton() {
		return quitButton;
	}
	public void setQuitButton(JButton quitButton) {
		this.quitButton = quitButton;
	}
}
