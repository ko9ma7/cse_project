package refactoringProject04;

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
import javax.swing.JPanel;

import refactoringProject02.Main;
import refactoringProject02.Parameter;

public class RhythmGame extends JFrame implements Runnable {
	private Image screenImage;
	private Graphics screenGraphic;
	private String path = "C:/Users/지명화/eclipse-workspace/2018-Pattern-SoftWareEngineering_Project-RhythmGame/src/images/";

	private Image background = new ImageIcon(path + "introbackground.jpg").getImage();
	private ImageIcon exitButtonImage = new ImageIcon(path + "exitButtonBasic.jpg");
	private ImageIcon startButtonImage = new ImageIcon(path + "startButtonBasic.jpg");
	private ImageIcon quitButtonImage = new ImageIcon(path + "quitButtonBasic.jpg");
	private ImageIcon leftButtonImage = new ImageIcon(path + "leftButton.jpg");
	private ImageIcon rightButtonImage = new ImageIcon(path + "rightButton.jpg");
	private ImageIcon easyButtonImage = new ImageIcon(path + "easyButtonBasic.jpg");
	private ImageIcon hardButtonImage = new ImageIcon(path + "hardButtonBasic.jpg");
	private ImageIcon backButtonImage = new ImageIcon(path + "backButtonBasic.jpg");
	private JLabel menuBar = new JLabel(new ImageIcon((path + "menuBar.jpg")));
	
	private JButton exitButton = new JButton(exitButtonImage);
	private JButton startButton = new JButton(startButtonImage);
	private JButton quitButton = new JButton(quitButtonImage);
	private JButton leftButton = new JButton(leftButtonImage);
	private JButton rightButton = new JButton(rightButtonImage);
	private JButton easyButton = new JButton(easyButtonImage);
	private JButton hardButton = new JButton(hardButtonImage);
	private JButton backButton = new JButton(backButtonImage);
	
	private int mouseX, mouseY; // 메뉴바에 필요한 변수
	
	private boolean isMusicSelectScreen = false; // 화면이 mainScreen인지 아닌지(곡 선택 화면)
	private boolean isGameStartScreen = false; // 화면이 gameScreen인지 아닌지(노트 입력 화면)
	
	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic; // 선택된 음악
	private int nowSelected = 0; // 현재 선택된 트랙 곡 인덱스 값
	
	private static Game newGame; // 게임이 시작했을 때 단 하나의 게임만 실행되도록 하여야 하므로 static을 사용한다
	ArrayList<Track> trackList = new ArrayList<Track>(); // 트랙 리스트 생성
	
	private Image gameBackground;
	
	
	private Image noteRouteLineImage = new ImageIcon(path + "noteRouteLine.jpg").getImage(); // 노트가 이동하는 라인
	private Image judgementLineImage = new ImageIcon(path + "judgementLine.jpg").getImage(); // 노트 판정선
	private Image gameInfoImage = new ImageIcon(path + "gameInfo.jpg").getImage(); 			// 게임 화면 전환 후, 하단에 정보 표시
	private Image noteRouteSImage = new ImageIcon(path + "noteRoute.jpg").getImage();
	private Image noteRouteDImage = new ImageIcon(path + "noteRoute.jpg").getImage();
	private Image noteRouteFImage = new ImageIcon(path + "noteRoute.jpg").getImage();
	private Image noteRouteSpace1Image = new ImageIcon(path + "noteRoute.jpg").getImage();
	private Image noteRouteSpace2Image = new ImageIcon(path + "noteRoute.jpg").getImage(); 
	private Image noteRouteJImage = new ImageIcon(path + "noteRoute.jpg").getImage();
	private Image noteRouteKImage = new ImageIcon(path + "noteRoute.jpg").getImage(); 
	private Image noteRouteLImage = new ImageIcon(path + "noteRoute.jpg").getImage();
	
	
	public RhythmGame() { // 객체가 생성되면 화면/사용자 인터페이스, 음악실행, 게임 실행 등등..
		
		
		/*
		ArrayList<Music> playList = new ArrayList<>();
		
		playList.add(new Music("Limitless.mp3", false));
		playList.add(new Music("Go.mp3", false));
		playList.add(new Music("Infectious.mp3", false));
		*/
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
//		addKeyListener(new KeyListener());
		
		// 화면 종료 버튼 설정
		exitButton.setVisible(true);
		exitButton.setBounds(1230, 0, 50, 50);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override // 마우스가 이미지에 위치해지면
			public void mouseEntered(MouseEvent e) {}
			@Override // 마우스가 이미지에서 벗어나면
			public void mouseExited(MouseEvent e) { exitButton.setIcon(exitButtonImage); }
			@Override // 화면 종료 버튼을 누르면 게임 화면이 사라짐
			public void mousePressed(MouseEvent e) { System.exit(0); }
		});
		add(exitButton);
		
		
		// 게임 시작 버튼 설정
		startButton.setVisible(true);
		startButton.setBounds(0, 100, 430, 200);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override // 화면에서 게임 시작 버튼을 누르면 곡 선택 화면으로 들어가짐
			public void mousePressed(MouseEvent e) { 
				isMusicSelectScreen = true; 
				isGameStartScreen = false;
				startButton.setVisible(false);
				quitButton.setVisible(false);
			}
		});
		add(startButton);

		// 게임 종료 버튼 설정
		quitButton.setVisible(true);
		quitButton.setBounds(0, 300, 430, 200);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

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
			public void mouseExited(MouseEvent e) {}
			@Override // 게임 실행 화면으로 들어간 후, 곡 선택 왼쪽 버튼을 누르면 왼쪽 화면으로 넘어가짐
			public void mousePressed(MouseEvent e) { 
				if (nowSelected == 0) nowSelected = trackList.size() - 1;
				else nowSelected--;
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
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override // 게임 실행 화면으로 들어간 후, 곡 선택 오른쪽 버튼을 누르면 오쪽 화면으로 넘어가짐
			public void mousePressed(MouseEvent e) { 
				if (nowSelected == trackList.size() - 1) nowSelected = 0; 
				else nowSelected++;
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
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) { easyButton.setIcon(easyButtonImage); }
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
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) { hardButton.setIcon(hardButtonImage); }
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
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) { backButton.setIcon(backButtonImage); }
			@Override
			public void mousePressed(MouseEvent e) { backMain(); }
		});
		add(backButton);
		
		// 화면을 자유롭게 움직일 수 있는 메뉴바 설정
		menuBar.setVisible(true);
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

	public void screenDraw(Graphics2D g) {
		
		if (isMusicSelectScreen) { musicScreenDraw(g); }
		else if (isGameStartScreen) { gameScreenDraw(g); }
		else { mainScreenDraw(g); }
		
		paintComponents(g);
		
		try {
			Thread.sleep(5); // 게임 창이 재조정 되었을 때 시간 차
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.repaint();
		
	}
	
	public void mainScreenDraw(Graphics2D g) {
		g.drawImage(background, 30, 50, null);
	}

	public void musicScreenDraw(Graphics2D g) { // Graphics2D로 설정
		titleImage = new ImageIcon(path + trackList.get(nowSelected).getTitleImage()).getImage();
		selectedImage = new ImageIcon(path + trackList.get(nowSelected).getStartImage()).getImage();
		g.drawImage(selectedImage, 340, 100, null);
		g.drawImage(titleImage, 340, 70, null);
	}
	
	
	public void gameScreenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage, 228, 30, null);      // 키 S
		g.drawImage(noteRouteDImage, 332, 30, null); 	  // 키 D
		g.drawImage(noteRouteFImage, 436, 30, null); 	  // 키 F
		g.drawImage(noteRouteSpace1Image, 540, 30, null); // 스페이스바
		g.drawImage(noteRouteSpace2Image, 640, 30, null); // 스페이스바
		g.drawImage(noteRouteJImage, 744, 30, null); 	  // 키 J
		g.drawImage(noteRouteKImage, 848, 30, null); 	  // 키 K
		g.drawImage(noteRouteLImage, 952, 30, null); 	  // 키 L
		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementLineImage, 0, 580, null);
		// noteList에 있는 노트들을 하나씩 불러와 그려준다 -> 람다식으로 변환해보자
		/*
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			
			if (!note.getIsProceed()) { // 사용되지 않는 노트는 사라짐
				noteList.remove(i);
				i--;
			} 
			note.screenDraw(g);
		}
		*/
		g.setColor(Color.WHITE);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); // 글자 깨짐 현상을 없애줌
		g.setFont(new Font("Arial", Font.BOLD, 30));
//		g.drawString(mTitle, 20, 702); 		 // 곡명
//		g.drawString(difficulty, 1190, 702); // 난이도
		
		// 조작키
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
//		g.drawString(String.valueOf(score), 565, 702); // 점수
		
//		g.drawImage(judgeImage, 500, 300, null);
	}
	
	
	
/*
	public void selectTrack() { // 선택된 곡의 트랙 음악 시작(미리 듣기)
		if (selectedMusic != null) selectedMusic.close();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start(); // 선택된 음악 시작
	}

	public void selectMusic() { // startButton 이벤트 메소드
//		background = new ImageIcon(path + "mainBackground.jpg").getImage();
		isMusicSelectScreen = true; // 게임에 들어가면 
		
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		selectTrack();
	}
*/
	public void gameStart(int nowSelected, String difficulty) { // 게임화면 실행(현재 선택된 곡, 난이도 명)
		isMusicSelectScreen = false; // 게임 시작 화면으로 들어가므로 곡 선택 화면이 사라짐
		isGameStartScreen = true; // 게임 시작 화면으로 들어가므로
		
		startButton.setVisible(false);
		quitButton.setVisible(false);
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		backButton.setVisible(true);
		gameBackground = new ImageIcon(path + trackList.get(nowSelected).getTitleImage()).getImage();
		
		newGame = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic()); // 게임 생성
//		game.start(); // game 클래스의 run() 메소드가 자동으로 실행
		setFocusable(true);
	}
	
	public void backMain() { // backButton 이벤트 메소드
		isMusicSelectScreen = true; // 뒤로 가기 버튼을 누르면 음악 선택 화면으로 돌아감
		isGameStartScreen = false; // 뒤로 가기 버튼을 누르면 게임 시작 화면이 사라짐
		
		startButton.setVisible(false);
		quitButton.setVisible(false);
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
//		background = new ImageIcon(path + "mainBackground.jpg").getImage();
		backButton.setVisible(false);
//		selectTrack(); // 현재 선택된 트랙을 보여주고 하이라이트 부분을 재생시키는 코드
		setFocusable(true);
		
		
//		game.close();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new RhythmGame();
	}
	
	
}
