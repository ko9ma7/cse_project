package refactoringProject08;

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
	// 리듬게임 구현 GUI
	// 스크린 이미지
	private Image screenImage;
	private Graphics screenGraphic;
	// 시작, 나가기, 종료 버튼 이미지
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Parameter.PATH + "exitButtonEntered.jpg");
	private ImageIcon exitButtonBasicImage = new ImageIcon(Parameter.PATH + "exitButtonBasic.jpg");
	private ImageIcon startButtonEnteredImage = new ImageIcon(Parameter.PATH + "startButtonEntered.jpg");
	private ImageIcon startButtonBasicImage = new ImageIcon(Parameter.PATH + "startButtonBasic.jpg");
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Parameter.PATH + "quitButtonEntered.jpg");
	private ImageIcon quitButtonBasicImage = new ImageIcon(Parameter.PATH + "quitButtonBasic.jpg");
	// 곡 선택 왼쪽, 오른쪽 버튼 이미지
	private ImageIcon leftButtonImage = new ImageIcon(Parameter.PATH + "leftButton.jpg");
	private ImageIcon rightButtonImage = new ImageIcon(Parameter.PATH + "rightButton.jpg");
	// 난이도 선택 쉬움, 어려움 버튼 이미지
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Parameter.PATH + "easyButtonEntered.jpg");
	private ImageIcon easyButtonBasicImage = new ImageIcon(Parameter.PATH + "easyButtonBasic.jpg");
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Parameter.PATH + "hardButtonEntered.jpg");
	private ImageIcon hardButtonBasicImage = new ImageIcon(Parameter.PATH + "hardButtonBasic.jpg");
	// 게임 시작 후 뒤로 가기 버튼 이미지
	private ImageIcon backButtonEnteredImage = new ImageIcon(Parameter.PATH + "backButtonEntered.jpg");
	private ImageIcon backButtonBasicImage = new ImageIcon(Parameter.PATH + "backButtonBasic.jpg");
	// 게임 시작 후 스크린 이미지
	private Image background = new ImageIcon(Parameter.PATH + "introbackground.jpg").getImage();
	// 맨 위에 메뉴바 이미지(화면 이동 가능)
	private JLabel menuBar = new JLabel(new ImageIcon(Parameter.PATH + "menuBar.jpg"));
	
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonImage);
	private JButton rightButton = new JButton(rightButtonImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);
	
	private int mouseX, mouseY; // 메뉴바에 필요한 변수
	
	private boolean isMusicSelectScreen = false; // 화면이 mainScreen인지 아닌지(곡 선택 화면)
	private boolean isGameStartScreen = false; // 화면이 gameScreen인지 아닌지(노트 입력 화면)
	
	private Image titleImage;
	private Image selectedImage; // 선택된 이미지
	private Music selectedMusic; // 선택된 음악
	Music introMusic = new Music("introMusic.mp3", true); // 리듬게임 처음 실행할 때 음악
	private int nowSelected = 0; // 현재 선택된 트랙 곡 인덱스 값
	
	// "멀티쓰레드 형식에서 - 싱글톤 패턴으로 구현해보기"
	public static Game game; // 게임이 시작했을 때 단 하나의 게임만 실행되도록 하여야 하므로 static을 사용한다
	ArrayList<Track> trackList = new ArrayList<Track>(); // 트랙 리스트 생성
	
	public RhythmGame() { // 객체가 생성되면 화면/사용자 인터페이스, 음악실행, 게임 실행 등등..
		
		// "싱글톤 패턴으로 구현해보기" -> 열거형 사용하기
		// 트랙리스트에 곡을 먼저 넣어주지 않으면 곡을 넣어주기 전까지 계속 로딩이 걸리게 되므로 오류가 발생할 수 있어 먼저 곡을 넣는다
		trackList.add(new Track("LimitlessTitleImage.jpg", "LimitlessStartImage.jpg", 
				"LimitlessGameImage.jpg", "LimitlessSelected.mp3", "Limitless.mp3", "Limitless"));
		trackList.add(new Track("GoTitleImage.jpg", "GoStartImage.jpg", 
				"GoGameImage.jpg", "GoSelected.mp3", "Go.mp3", "Go"));
		trackList.add(new Track("InfectiousTitleImage.jpg", "InfectiousStartImage.jpg", 
				"InfectiousGameImage.jpg", "InfectiousSelected.mp3", "Infectious.mp3", "Infectious"));
		
		// 게임 창 설정
		setUndecorated(true);
		setTitle("Rhythm Game");
		setSize(Parameter.SCREEN_WIDTH.getValue(), Parameter.SCREEN_HEIGHT.getValue());
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		// 키보드 입력 이벤트 리스너
		addKeyListener(new KeyListener());
		
		// introMusic이 생성자 안에서 구현되어 있기 때문에 밖에다가 선언해준다
		introMusic.start();
		
		// 버튼 입력 혹시 Command 패턴 가능?
		// 화면 종료 버튼 설정
		exitButton.setBounds(1230, 0, 50, 50);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override // 마우스가 이미지에 위치해지면
			public void mouseEntered(MouseEvent e) { exitButton.setIcon(exitButtonEnteredImage); }
			@Override // 마우스가 이미지에서 벗어나면
			public void mouseExited(MouseEvent e) { exitButton.setIcon(exitButtonBasicImage); }
			@Override // 화면 종료 버튼을 누르면 게임 화면이 사라짐
			public void mousePressed(MouseEvent e) { System.exit(0); }
		});
		add(exitButton);
		
		// 게임 시작 버튼 설정
		startButton.setBounds(0, 100, 430, 200);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { startButton.setIcon(startButtonEnteredImage); }
			@Override
			public void mouseExited(MouseEvent e) { startButton.setIcon(startButtonBasicImage); }
			@Override // 화면에서 게임 시작 버튼을 누르면 곡 선택 화면으로 들어가짐
			public void mousePressed(MouseEvent e) { selectMusic(); }
		});
		add(startButton);
		
		// 게임 종료 버튼 설정
		quitButton.setBounds(0, 300, 430, 200);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { quitButton.setIcon(quitButtonEnteredImage); }
			@Override
			public void mouseExited(MouseEvent e) { quitButton.setIcon(quitButtonBasicImage); }
			@Override // 화면에서 게임 종료 버튼을 누르면 게임 화면이 사라짐
			public void mousePressed(MouseEvent e) { System.exit(0); }
		});
		add(quitButton);
		
		// 곡 선택 왼쪽 버튼 설정
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) { quitButton.setIcon(leftButtonImage); }
			@Override // 게임 실행 화면으로 들어간 후, 곡 선택 왼쪽 버튼을 누르면 왼쪽 화면으로 넘어가짐
			public void mousePressed(MouseEvent e) { selectLeft(); }
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
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) { quitButton.setIcon(rightButtonImage); }
			@Override // 게임 실행 화면으로 들어간 후, 곡 선택 오른쪽 버튼을 누르면 오쪽 화면으로 넘어가짐
			public void mousePressed(MouseEvent e) { selectRight(); }
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
			public void mouseEntered(MouseEvent e) { easyButton.setIcon(easyButtonEnteredImage); }
			@Override
			public void mouseExited(MouseEvent e) { easyButton.setIcon(easyButtonBasicImage); }
			@Override // 난이도 쉬움 이벤트(현재 선택된 곡, 난이도 쉬움)
			public void mousePressed(MouseEvent e) { gameStart(nowSelected, "Easy"); }
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
			public void mouseEntered(MouseEvent e) { hardButton.setIcon(hardButtonEnteredImage); }
			@Override
			public void mouseExited(MouseEvent e) { hardButton.setIcon(hardButtonBasicImage); }
			@Override // 난이도 어려움 이벤트(현재 선택된 곡, 난이도 어려움)
			public void mousePressed(MouseEvent e) { gameStart(nowSelected, "Hard"); }
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
			public void mouseEntered(MouseEvent e) { backButton.setIcon(backButtonEnteredImage); }
			@Override
			public void mouseExited(MouseEvent e) { backButton.setIcon(backButtonBasicImage); }
			@Override
			public void mousePressed(MouseEvent e) { backMain(); }
		});
		add(backButton);
		
		// 화면을 자유롭게 움직일 수 있는 메뉴바 설정
		menuBar.setBounds(10, 10, 1260, 30);
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
		screenImage = createImage(Parameter.SCREEN_WIDTH.getValue(), Parameter.SCREEN_HEIGHT.getValue());
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic); // 글자 깨짐 현상 해결방법
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) { // Graphics2D로 설정
		g.drawImage(background, 30, 50, null);
		if (isMusicSelectScreen) {
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		
		// gameScreen일 경우 화면을 출력
		if (isGameStartScreen) { game.screenDraw(g); }
		
		paintComponents(g);
		
		try {
			Thread.sleep(5); // 게임 창이 재조정 되었을 때 시간 차
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.repaint();
	}
	
	public void selectTrack(int nowSelected) { // 선택된 곡의 트랙 음악 시작
		if (selectedMusic != null) selectedMusic.close();
		
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start(); // 선택된 음악 시작
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
	
	public void selectMusic() { // startButton 이벤트 메소드
		startButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Parameter.PATH + "mainBackground.jpg").getImage();
		isMusicSelectScreen = true; // 게임에 들어가면 
		
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introMusic.close();
		selectTrack(0);
	}
	
	public void backMain() { // backButton 이벤트 메소드
		isMusicSelectScreen = true; // 뒤로 가기 버튼을 누르면 음악 선택 화면으로 돌아감
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Parameter.PATH + "mainBackground.jpg").getImage();
		backButton.setVisible(false);
		selectTrack(nowSelected); // 현재 선택된 트랙을 보여주고 하이라이트 부분을 재생시키는 코드
		setFocusable(true);
		isGameStartScreen = false; // 뒤로 가기 버튼을 누르면 게임 시작 화면이 사라짐
		
		game.close();
	}
	
	public void gameStart(int nowSelected, String difficulty) { // 게임화면 실행(현재 선택된 곡, 난이도 명)
		if (selectedMusic != null) selectedMusic.close();
		isMusicSelectScreen = false; // 게임 시작 화면으로 들어가므로 곡 선택 화면이 사라짐
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage())).getImage();
		backButton.setVisible(true);
		isGameStartScreen = true; // 게임 시작 화면으로 들어가므로
	
		// 싱글톤 패턴
		game = game.getInstance();
		game.setGame(trackList.get(nowSelected).getTitleName(), difficulty);
		game.start(); // game 클래스의 run() 메소드가 자동으로 실행
		setFocusable(true);
	}
}
