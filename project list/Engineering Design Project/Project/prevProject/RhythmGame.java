package prevProject;

/*
	project - 노트 판정 함수 구현하기
*/

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RhythmGame extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.jpg"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.jpg"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.jpg"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.jpg"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.jpg"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.jpg"));
	
	private ImageIcon leftButtonImage = new ImageIcon(Main.class.getResource("../images/leftButton.jpg"));
	private ImageIcon rightButtonImage = new ImageIcon(Main.class.getResource("../images/rightButton.jpg"));
	
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.jpg"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.jpg"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.jpg"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.jpg"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.jpg"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.jpg"));
	
	private Image background = new ImageIcon(Main.class.getResource("../images/introbackground.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.jpg")));
	
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonImage);
	private JButton rightButton = new JButton(rightButtonImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);
	
	private int mouseX, mouseY;
	
	private boolean isMainScreen = false;
	private boolean isGameScreen = false; // 화면이 gameScreen인지 아닌지
	
	public static Game game; // 게임이 시작했을 때 단 하나의 게임만 실행되도록 하여야 하므로 static을 사용한다
	
	ArrayList<Track> trackList = new ArrayList<Track>();
	
	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;
	Music introMusic = new Music("introMusic.mp3", true);
	private int nowSelected = 0;
	
	public RhythmGame() {
		
		// 트랙리스트에 곡을 먼저 넣어주지 않으면 곡을 넣어주기 전까지 계속 로딩이 걸리게 되므로 오류가 발생할 수 있어 먼저 곡을 넣는다
		trackList.add(new Track("LimitlessTitleImage.jpg", "LimitlessStartImage.jpg", 
				"LimitlessGameImage.jpg", "LimitlessSelected.mp3", "Limitless.mp3","Limitless"));
		trackList.add(new Track("GoTitleImage.jpg", "GoStartImage.jpg", 
				"GoGameImage.jpg", "GoSelected.mp3", "Go.mp3","Go"));
		trackList.add(new Track("InfectiousTitleImage.jpg", "InfectiousStartImage.jpg", 
				"InfectiousGameImage.jpg", "InfectiousSelected.mp3", "Infectious.mp3","Infectious"));
		
		setUndecorated(true);
		setTitle("Rhythm Game");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		addKeyListener(new KeyListener()); // 키보드 입력 이벤트 리스너
		
		// introMusic이 생성자 안에서 구현되어 있기 때문에 밖에다가 선언해준다
		introMusic.start();
		
		exitButton.setBounds(1230, 0, 50, 50);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(exitButton);
		
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
			@Override
			public void mousePressed(MouseEvent e) {
				// refactoring -> 코드를 깔끔하게 다시 정리
				enterMain();
			}
		});
		add(startButton);
		
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
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(quitButton);
		
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { 
				quitButton.setIcon(leftButtonImage);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(leftButtonImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				selectLeft();
			}
		});
		add(leftButton);
		
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(rightButtonImage);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(rightButtonImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				selectRight();
			}
		});
		add(rightButton);
		
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
			@Override
			public void mousePressed(MouseEvent e) {
				// 난이도 쉬움 이벤트
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);
		
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
			@Override
			public void mousePressed(MouseEvent e) {
				// 난이도 어려움 이벤트
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);
		
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
				// 메인화면으로 돌아가는 이벤트
				backMain();
			}
		});
		add(backButton);
		
		menuBar.setBounds(10, 10, 1200, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic); // 글자 깨짐 현상 해결방법
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) { // Graphics2D로 설정
		g.drawImage(background, 30, 50, null);
		if (isMainScreen) {
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		if (isGameScreen) { // gameScreen일 경우 화면을 출력
			
			game.screenDraw(g); // Refactoring
			
		}
		
		paintComponents(g);
		
		try {
			Thread.sleep(5); // 게임 창이 재조정 되었을 때 시간 차
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.repaint();
	}
	
	public void selectTrack(int nowSelected) {
		if (selectedMusic != null) selectedMusic.close();
		
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}
	
	public void selectLeft() { 
		if (nowSelected == 0) nowSelected = trackList.size() - 1;
		else nowSelected--; 
		
		selectTrack(nowSelected);
	}
	
	public void selectRight() { 
		if (nowSelected == trackList.size() - 1) nowSelected = 0; 
		else nowSelected++;
		
		selectTrack(nowSelected);
	}
	
	public void gameStart(int nowSelected, String difficulty) { // 게임화면 실행
		if (selectedMusic != null) selectedMusic.close();
		isMainScreen = false; // screenDraw 메소드의 isMainScreen이 실행 x
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage())).getImage();
		backButton.setVisible(true);

		isGameScreen = true;
	
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());
		game.start(); // game 클래스의 run() 메소드가 자동으로 실행
		setFocusable(true);
	}
	
	public void backMain() { // backButton 이벤트 메소드
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		backButton.setVisible(false);
		selectTrack(nowSelected); // 현재 선택된 트랙을 보여주고 하이라이트 부분을 재생시키는 코드
		setFocusable(true);
		isGameScreen = false;
		game.close();
	}
	
	public void enterMain() { // startButton 이벤트 메소드
		startButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		isMainScreen = true;
		
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introMusic.close();
		selectTrack(0);
	}
}
