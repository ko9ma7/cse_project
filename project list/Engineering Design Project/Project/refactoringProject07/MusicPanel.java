package refactoringProject07;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MusicPanel extends RhythmGame {
	// 곡 선택 왼쪽, 오른쪽 버튼 이미지
	private ImageIcon leftButtonImage = new ImageIcon(Main.class.getResource("../images/leftButton.jpg"));
	private ImageIcon rightButtonImage = new ImageIcon(Main.class.getResource("../images/rightButton.jpg"));
	// 난이도 선택 쉬움, 어려움 버튼 이미지
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.jpg"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.jpg"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.jpg"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.jpg"));
	// 게임 시작 후 뒤로 가기 버튼 이미지
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.jpg"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.jpg"));
	
	protected JButton leftButton = new JButton(leftButtonImage);
	protected JButton rightButton = new JButton(rightButtonImage);
	protected JButton easyButton = new JButton(easyButtonBasicImage);
	protected JButton hardButton = new JButton(hardButtonBasicImage);
	protected JButton backButton = new JButton(backButtonBasicImage);
	
	public MusicPanel() {
		// 곡 선택 왼쪽 버튼 설정
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonImage);
			} // -> 이미지 추가하자

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonImage);
			}

			@Override // 게임 실행 화면으로 들어간 후, 곡 선택 왼쪽 버튼을 누르면 왼쪽 화면으로 넘어가짐
			public void mousePressed(MouseEvent e) {
				selectLeft();
			}
		});
		add(leftButton);

		// 곡 선택 오른쪽 버튼 설정
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonImage);
			} // -> 이미지 추가하자

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonImage);
			}

			@Override // 게임 실행 화면으로 들어간 후, 곡 선택 오른쪽 버튼을 누르면 오쪽 화면으로 넘어가짐
			public void mousePressed(MouseEvent e) {
				selectRight();
			}
		});
		add(rightButton);
				
		// 난이도 선택 쉬움 버튼 설정
		// 선택을 하면 게임이 시작된다
		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
			}

			@Override // 난이도 쉬움 이벤트(현재 선택된 곡, 난이도 쉬움)
			public void mousePressed(MouseEvent e) {
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);
				
		// 난이도 선택 어려움 버튼 설정
		// 선택을 하면 게임이 시작된다
		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 250, 67);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
			}

			@Override // 난이도 어려움 이벤트(현재 선택된 곡, 난이도 어려움)
			public void mousePressed(MouseEvent e) {
				gameStart(nowSelected, "Hard");
			}
		});

		add(hardButton);

		// 게임 시작 후 뒤로가기 버튼 설정
		// 메인화면으로 돌아가는 이벤트
		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				backMain();
		
			}
		});
		add(backButton);
	}

	public JButton getLeftButton() {
		return leftButton;
	}

	public void setLeftButton(JButton leftButton) {
		this.leftButton = leftButton;
	}

	public JButton getRightButton() {
		return rightButton;
	}

	public void setRightButton(JButton rightButton) {
		this.rightButton = rightButton;
	}

	public JButton getEasyButton() {
		return easyButton;
	}

	public void setEasyButton(JButton easyButton) {
		this.easyButton = easyButton;
	}

	public JButton getHardButton() {
		return hardButton;
	}

	public void setHardButton(JButton hardButton) {
		this.hardButton = hardButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}
}
