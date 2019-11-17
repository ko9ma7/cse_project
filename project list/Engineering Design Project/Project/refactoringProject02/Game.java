package refactoringProject02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.ImageIcon;

public class Game extends Thread { // 하나의 게임은 하나의 단위로 움직여야 되므로 클래스를 생성
	// 객체가 생성되면 게임 인터페이스, 노트 입력, ...
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.jpg")).getImage(); // 노트가 이동하는 라인
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.jpg")).getImage(); // 노트 판정선
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.jpg")).getImage(); 			// 게임 화면 전환 후, 하단에 정보 표시
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage(); 
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage(); 
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage();
	private String tName;      // 선택된 곡 이름
	private String difficulty; // 선택된 난이도 명
	private String mTitle;     // 선택된 앨범
	private Music music;	   // 게임에서 선택된 음악
	// 노트의 집합
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	// 인터페이스 has-a
	NoteFactory factory;
	LimitlessNote n;
	
	
	
	public Game(String t, String d, String m) {
		this.tName = t;
		this.difficulty = d;
		this.mTitle = m;
		this.music = new Music(this.mTitle, false); // "팩토리 메소드로 사용해보기"
	}
	
	public void screenDraw(Graphics2D g) {
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
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			
			if (!note.getIsProceed()) { // 사용되지 않는 노트는 사라짐
				noteList.remove(i);
				i--;
			} 
			note.screenDraw(g);
		}
		
		g.setColor(Color.WHITE);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); // 글자 깨짐 현상을 없애줌
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(mTitle, 20, 702); 		 // 곡명
		g.drawString(difficulty, 1190, 702); // 난이도
		
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
		g.drawString("0", 565, 702); // 점수
	}
	
	public void pressS() { 
		judge("S"); // S에 대한 판정
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.jpg")).getImage();
	}
	public void releaseS() { noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage(); }
	
	public void pressD() { 
		judge("D"); // D에 대한 판정
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.jpg")).getImage();
	}
	public void releaseD() { noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage(); }
	
	public void pressF() {
		judge("F"); // F에 대한 판정
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.jpg")).getImage();
	}
	public void releaseF() { noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage(); }
	
	public void pressSpace() {
		judge("Space"); // Space에 대한 판정
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.jpg")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.jpg")).getImage();
	}
	public void releaseSpace() { 
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage();
	}
	
	public void pressJ() {
		judge("J"); // J에 대한 판정
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.jpg")).getImage();
	}
	public void releaseJ() { noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage(); }
	
	public void pressK() {
		judge("K"); // K에 대한 판정
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.jpg")).getImage();
	}
	public void releaseK() { noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage(); }
	
	public void pressL() { 
		judge("L"); // L에 대한 판정
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.jpg")).getImage();
	}
	public void releaseL() { noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage(); }
	
	@Override
	public void run() { 
		
		dropNote(this.tName); 
//		n.createNote(this.tName);
		
	}
	
	public void close() {
		music.close(); // 게임 창 뒤로 가기를 하였을 때 곡이 재생되는 것을 막는다
		this.interrupt();
	}
	
	// 노트들이 떨어지는 메소드
	// 음악에 따라 떨어지는 속도와 시간을 생각해야 한다 -> 각 곡의 bpm을 살펴보자!
	// "무조건 곡 마다 비트 추가 되는 패턴 필요"
	public void dropNote(String titleName) {
		Beat[] beats = null;
		if (titleName.equals("Limitless") && difficulty.equals("Easy")) {
			int startTime = 1000 - Parameter.REACH_TIME.getValue() * 1000;
			int gap = 250;
			
			beats = new Beat[] {
					new Beat(startTime - gap, "S"),
					new Beat(startTime + gap * 2, "D"),
					new Beat(startTime + gap * 4, "F"),
					new Beat(startTime + gap * 6, "D"),
	
					new Beat(startTime + gap * 8, "S"),
					new Beat(startTime + gap * 10, "D"),
					new Beat(startTime + gap * 12, "F"),
					new Beat(startTime + gap * 14, "D"),
					
					new Beat(startTime + gap * 16, "S"),
					new Beat(startTime + gap * 17, "D"),
					new Beat(startTime + gap * 18, "F"),
					new Beat(startTime + gap * 19, "D"),
					
					new Beat(startTime + gap * 20, "S"),
					new Beat(startTime + gap * 22, "D"),
					new Beat(startTime + gap * 24, "F"),
					
					// 26부터
					new Beat(startTime + gap * 26, "L"),
					new Beat(startTime + gap * 28, "K"),
					new Beat(startTime + gap * 30, "J"),
					new Beat(startTime + gap * 32, "K"),
					
					new Beat(startTime + gap * 33, "L"),
					new Beat(startTime + gap * 34, "K"),
					new Beat(startTime + gap * 35, "J"),
					
					new Beat(startTime + gap * 40, "J"),
					new Beat(startTime + gap * 42, "K"),
					new Beat(startTime + gap * 44, "L"),
					new Beat(startTime + gap * 46, "K"),
					new Beat(startTime + gap * 47, "J"),
					
					new Beat(startTime + gap * 49, "S"),
					new Beat(startTime + gap * 50, "D"),
					new Beat(startTime + gap * 51, "F"),
					new Beat(startTime + gap * 52, "J"),
					new Beat(startTime + gap * 53, "K"),
					new Beat(startTime + gap * 54, "L"),
					
					// 55부터
					new Beat(startTime + gap * 55, "Space"),
					new Beat(startTime + gap * 57, "Space"),
					new Beat(startTime + gap * 59, "Space"),
					new Beat(startTime + gap * 61, "Space"),
					new Beat(startTime + gap * 63, "Space"),
					new Beat(startTime + gap * 65, "Space"),
					new Beat(startTime + gap * 67, "Space"),
					
					new Beat(startTime + gap * 69, "S"),
					new Beat(startTime + gap * 71, "L"),
					new Beat(startTime + gap * 73, "F"),
					new Beat(startTime + gap * 75, "J"),

					
					new Beat(startTime + gap * 78, "F"), new Beat(startTime + gap * 78, "J"),
					new Beat(startTime + gap * 79, "F"), new Beat(startTime + gap * 79, "J"),
					new Beat(startTime + gap * 80, "F"), new Beat(startTime + gap * 80, "J"),
					new Beat(startTime + gap * 81, "F"), new Beat(startTime + gap * 81, "J"),
					new Beat(startTime + gap * 82, "F"), new Beat(startTime + gap * 82, "J"),
					new Beat(startTime + gap * 83, "F"), new Beat(startTime + gap * 83, "J"),
				
					// 84부터
					new Beat(startTime + gap * 84, "Space"),
					new Beat(startTime + gap * 86, "Space"),
					new Beat(startTime + gap * 88, "Space"),
					new Beat(startTime + gap * 90, "Space"),
					new Beat(startTime + gap * 92, "Space"),
					
					new Beat(startTime + gap * 94, "S"), new Beat(startTime + gap * 94, "L"),
					new Beat(startTime + gap * 95, "S"), new Beat(startTime + gap * 95, "L"),
					new Beat(startTime + gap * 96, "S"), new Beat(startTime + gap * 96, "L"),
					new Beat(startTime + gap * 97, "S"), new Beat(startTime + gap * 97, "L"),
					
					new Beat(startTime + gap * 99, "S"), new Beat(startTime + gap * 99, "L"), new Beat(startTime + gap * 99, "F"), new Beat(startTime + gap * 99, "J"),
					
					new Beat(startTime + gap * 102, "S"), new Beat(startTime + gap * 102, "L"),
					new Beat(startTime + gap * 104, "S"), new Beat(startTime + gap * 104, "L"),
					new Beat(startTime + gap * 106, "S"), new Beat(startTime + gap * 106, "L"),
					new Beat(startTime + gap * 108, "S"), new Beat(startTime + gap * 108, "L"),
					new Beat(startTime + gap * 110, "S"), new Beat(startTime + gap * 110, "L"),
					new Beat(startTime + gap * 112, "S"), new Beat(startTime + gap * 112, "L"),
					new Beat(startTime + gap * 114, "S"), new Beat(startTime + gap * 114, "L"),
					new Beat(startTime + gap * 116, "Space"),
					
					// 117부터 시작
					new Beat(startTime + gap * 117, "J"),
					new Beat(startTime + gap * 118, "J"),
					new Beat(startTime + gap * 119, "J"),
					new Beat(startTime + gap * 120, "J"),
					new Beat(startTime + gap * 121, "J"),
					new Beat(startTime + gap * 122, "J"),
					
					new Beat(startTime + gap * 124, "F"),
					new Beat(startTime + gap * 125, "F"),
					new Beat(startTime + gap * 126, "F"),
					new Beat(startTime + gap * 127, "F"),
					new Beat(startTime + gap * 128, "F"),
					new Beat(startTime + gap * 129, "F"),
					
					new Beat(startTime + gap * 131, "K"),
					new Beat(startTime + gap * 132, "K"),
					new Beat(startTime + gap * 133, "K"),
					new Beat(startTime + gap * 134, "K"),
					new Beat(startTime + gap * 135, "K"),
					new Beat(startTime + gap * 136, "K"),
					
					new Beat(startTime + gap * 138, "D"),
					new Beat(startTime + gap * 139, "D"),
					new Beat(startTime + gap * 140, "D"),
					new Beat(startTime + gap * 141, "D"),
					new Beat(startTime + gap * 142, "D"),
					new Beat(startTime + gap * 143, "D"),
					
					// 다시 한번 반복
					new Beat(startTime + gap * 144, "Space"),
					
					new Beat(startTime + gap * 145, "L"),
					new Beat(startTime + gap * 146, "L"),
					new Beat(startTime + gap * 147, "L"),
					new Beat(startTime + gap * 148, "L"),
					new Beat(startTime + gap * 149, "L"),
					new Beat(startTime + gap * 150, "L"),
					
					new Beat(startTime + gap * 152, "Space"),
					
					new Beat(startTime + gap * 154, "S"),
					new Beat(startTime + gap * 155, "S"),
					new Beat(startTime + gap * 156, "S"),
					new Beat(startTime + gap * 157, "S"),
					new Beat(startTime + gap * 158, "S"),
					new Beat(startTime + gap * 159, "S"),
					
					new Beat(startTime + gap * 161, "J"),
					new Beat(startTime + gap * 162, "J"),
					new Beat(startTime + gap * 163, "J"),
					new Beat(startTime + gap * 164, "J"),
					new Beat(startTime + gap * 165, "J"),
					new Beat(startTime + gap * 166, "J"),
					
					new Beat(startTime + gap * 168, "Space"),
					
					new Beat(startTime + gap * 169, "F"),
					new Beat(startTime + gap * 170, "F"),
					new Beat(startTime + gap * 171, "F"),
					new Beat(startTime + gap * 172, "F"),
					new Beat(startTime + gap * 173, "F"),
					new Beat(startTime + gap * 174, "F"),
					
					// 175부터 시작
					new Beat(startTime + gap* 175, "S"),
					new Beat(startTime + gap* 177, "J"),
					new Beat(startTime + gap* 179, "D"),
					new Beat(startTime + gap* 181, "K"),
					new Beat(startTime + gap* 183, "F"),
					new Beat(startTime + gap* 185, "L"),
					new Beat(startTime + gap* 187, "J"),
					new Beat(startTime + gap* 189, "F"),
					new Beat(startTime + gap* 191, "K"),
					new Beat(startTime + gap* 193, "D"),
					new Beat(startTime + gap* 195, "L"),
					new Beat(startTime + gap* 197, "S"),
					new Beat(startTime + gap* 199, "K"),
					new Beat(startTime + gap* 201, "D"),
					new Beat(startTime + gap* 203, "J"),
					new Beat(startTime + gap* 205, "F"),
					new Beat(startTime + gap* 207, "L"),
					new Beat(startTime + gap* 209, "S"),
					
					new Beat(startTime + gap* 210, "S"),
					new Beat(startTime + gap* 211, "J"),
					new Beat(startTime + gap* 213, "D"),
					new Beat(startTime + gap* 215, "K"),
					new Beat(startTime + gap* 217, "F"),
					new Beat(startTime + gap* 219, "L"),
					new Beat(startTime + gap* 221, "J"),
					new Beat(startTime + gap* 223, "F"),
					new Beat(startTime + gap* 225, "K"),
					new Beat(startTime + gap* 227, "D"),
					new Beat(startTime + gap* 229, "L"),
					new Beat(startTime + gap* 231, "S"),
					new Beat(startTime + gap* 233, "K"),
					new Beat(startTime + gap* 235, "D"), new Beat(startTime + gap* 236, "Space"),
					new Beat(startTime + gap* 237, "J"), new Beat(startTime + gap* 238, "Space"),
					new Beat(startTime + gap* 239, "F"), new Beat(startTime + gap* 240, "Space"),
					new Beat(startTime + gap* 241, "L"), new Beat(startTime + gap* 242, "Space"),
					new Beat(startTime + gap* 243, "S"), new Beat(startTime + gap* 244, "Space"),
					
					new Beat(startTime + gap* 245, "S"), new Beat(startTime + gap* 246, "Space"),
					new Beat(startTime + gap* 247, "J"), new Beat(startTime + gap* 248, "Space"),
					new Beat(startTime + gap* 249, "D"), new Beat(startTime + gap* 250, "Space"),
					new Beat(startTime + gap* 251, "K"), new Beat(startTime + gap* 252, "Space"),
					new Beat(startTime + gap* 253, "F"), new Beat(startTime + gap* 254, "Space"),
					new Beat(startTime + gap* 255, "L"), new Beat(startTime + gap* 256, "Space"),
					new Beat(startTime + gap* 257, "J"), new Beat(startTime + gap* 258, "Space"),
					new Beat(startTime + gap* 259, "F"), new Beat(startTime + gap* 260, "Space"),
					new Beat(startTime + gap* 261, "K"), new Beat(startTime + gap* 262, "Space"),
					new Beat(startTime + gap* 263, "D"), new Beat(startTime + gap* 264, "Space"),
					new Beat(startTime + gap* 265, "L"), new Beat(startTime + gap* 266, "Space"),
					new Beat(startTime + gap* 267, "S"), new Beat(startTime + gap* 268, "Space"),
					new Beat(startTime + gap* 269, "K"), new Beat(startTime + gap* 270, "Space"),
					new Beat(startTime + gap* 271, "D"), new Beat(startTime + gap* 272, "Space"),
					new Beat(startTime + gap* 273, "J"), new Beat(startTime + gap* 273, "Space"), new Beat(startTime + gap* 274, "Space"),
					new Beat(startTime + gap* 275, "F"), new Beat(startTime + gap* 275, "Space"), new Beat(startTime + gap* 276, "Space"),
					new Beat(startTime + gap* 277, "L"), new Beat(startTime + gap* 277, "Space"), new Beat(startTime + gap* 278, "Space"),
					new Beat(startTime + gap* 279, "S"), new Beat(startTime + gap* 279, "Space"), new Beat(startTime + gap* 280, "Space"),
					
					new Beat(startTime + gap* 281, "Space"),
					new Beat(startTime + gap* 281 + 125, "Space"),
					new Beat(startTime + gap* 282, "Space"),
					new Beat(startTime + gap* 282 + 125, "Space"),
					new Beat(startTime + gap* 283, "Space"),
					new Beat(startTime + gap* 283 + 125, "Space"),
					new Beat(startTime + gap* 284, "Space"),
					new Beat(startTime + gap* 284 + 125, "Space"),
					new Beat(startTime + gap* 285, "Space"),
					new Beat(startTime + gap* 285 + 125, "Space"),
					new Beat(startTime + gap* 286, "Space"),
					new Beat(startTime + gap* 286 + 125, "Space"),
					new Beat(startTime + gap* 287, "Space"),
					new Beat(startTime + gap* 287 + 125, "Space"),
					new Beat(startTime + gap* 288, "Space"),
					new Beat(startTime + gap* 288 + 125, "Space"),
					
					new Beat(startTime + gap* 290, "S"), new Beat(startTime + gap* 290, "F"), new Beat(startTime + gap* 290, "J"), new Beat(startTime + gap* 290, "L"), 
					new Beat(startTime + gap* 291, "S"), new Beat(startTime + gap* 291, "F"), new Beat(startTime + gap* 291, "J"), new Beat(startTime + gap* 291, "L"),
					new Beat(startTime + gap* 292, "Space"),
					
					// 293부터 시작
					
					/*
					// 다시 한번 반복
					new Beat(startTime + gap * 31, "S"),
					new Beat(startTime + gap * 33, "D"),
					new Beat(startTime + gap * 35, "F"),
					new Beat(startTime + gap * 37, "D"),
					new Beat(startTime + gap * 38, "S"),
					new Beat(startTime + gap * 39, "D"),
					new Beat(startTime + gap * 40, "F"),
					new Beat(startTime + gap * 41, "D"),
					new Beat(startTime + gap * 42, "S"),
					
					new Beat(startTime + gap * 44, "S"),
					new Beat(startTime + gap * 44, "D"),
					new Beat(startTime + gap * 44, "F"),
					
					new Beat(startTime + gap * 46, "L"),
					new Beat(startTime + gap * 48, "K"),
					new Beat(startTime + gap * 50, "J"),
					new Beat(startTime + gap * 52, "K"),
					new Beat(startTime + gap * 53, "L"),
					new Beat(startTime + gap * 55, "K"),
					new Beat(startTime + gap * 56, "J"),
					new Beat(startTime + gap * 57, "K"),
					new Beat(startTime + gap * 58, "L"),
					
					new Beat(startTime + gap * 60, "L"),
					new Beat(startTime + gap * 60, "K"),
					new Beat(startTime + gap * 60, "J"),
					
					
					new Beat(startTime + gap * 32, "Space"),
					new Beat(startTime + gap * 36, "Space"),
					new Beat(startTime + gap * 40, "Space"),
					new Beat(startTime + gap * 44, "Space"),
					new Beat(startTime + gap * 48, "Space"),
					new Beat(startTime + gap * 52, "Space"),
					new Beat(startTime + gap * 56, "Space"),
					new Beat(startTime + gap * 60, "Space"),
					new Beat(startTime + gap * 64, "Space"),
					new Beat(startTime + gap * 68, "Space"),
					new Beat(startTime + gap * 72, "Space"),
					new Beat(startTime + gap * 74, "Space"),
					new Beat(startTime + gap * 78, "Space"),
					new Beat(startTime + gap * 82, "Space"),
					new Beat(startTime + gap * 86, "Space"),
					
					new Beat(startTime + gap * 28, "K"),
					new Beat(startTime + gap * 30, "L"),
					new Beat(startTime + gap * 32, "J"),
					new Beat(startTime + gap * 34, "K"),
					
					new Beat(startTime + gap * 36, "L"),
					new Beat(startTime + gap * 38, "K"),
					new Beat(startTime + gap * 40, "J"),
					new Beat(startTime + gap * 42, "K"),
					new Beat(startTime + gap * 44, "L"),
					new Beat(startTime + gap * 46, "K"),
					new Beat(startTime + gap * 48, "J"),
					new Beat(startTime + gap * 50, "F"),
					new Beat(startTime + gap * 50, "J"),
					new Beat(startTime + gap * 54, "Space"),
					new Beat(startTime + gap * 62, "S"),
					new Beat(startTime + gap * 64, "D"),
					new Beat(startTime + gap * 68, "F"),
					new Beat(startTime + gap * 70, "D"),
					new Beat(startTime + gap * 72, "S"),
					new Beat(startTime + gap * 74, "D"),
					new Beat(startTime + gap * 76, "F"),
					
					
					new Beat(startTime + gap * 78, "Space"),
					new Beat(startTime + gap * 80, "Space"),
					new Beat(startTime + gap * 82, "Space"),
					new Beat(startTime + gap * 84, "Space"),
					new Beat(startTime + gap * 86, "Space"),
					new Beat(startTime + gap * 88, "S"),
					new Beat(startTime + gap * 90, "D"),
					new Beat(startTime + gap * 92, "F"),
					new Beat(startTime + gap * 90, "D"),
					new Beat(startTime + gap * 92, "S"),
					new Beat(startTime + gap * 94, "D"),
					new Beat(startTime + gap * 96, "F"),
					new Beat(startTime + gap * 98, "D"),
					new Beat(startTime + gap * 100, "S"),
					new Beat(startTime + gap * 102, "D"),
					new Beat(startTime + gap * 104, "F"),
					new Beat(startTime + gap * 106, "D"),
					new Beat(startTime + gap * 108, "S"),
					new Beat(startTime + gap * 120, "D"),
					new Beat(startTime + gap * 122, "L"),
					new Beat(startTime + gap * 124, "K"),
					new Beat(startTime + gap * 126, "J"),
					new Beat(startTime + gap * 128, "K"),
					new Beat(startTime + gap * 130, "L"),
					new Beat(startTime + gap * 132, "K"),
					new Beat(startTime + gap * 134, "J"),
					new Beat(startTime + gap * 136, "K"),
					new Beat(startTime + gap * 138, "L"),
					new Beat(startTime + gap * 140, "K"),
					new Beat(startTime + gap * 142, "J"),
					new Beat(startTime + gap * 144, "F"),
					new Beat(startTime + gap * 146, "J"),
					new Beat(startTime + gap * 150, "Space"),
					new Beat(startTime + gap * 154, "S"),
					new Beat(startTime + gap * 156, "D"),
					new Beat(startTime + gap * 158, "F"),
					new Beat(startTime + gap * 160, "D"),
					new Beat(startTime + gap * 162, "S"),
					new Beat(startTime + gap * 164, "D"),
					new Beat(startTime + gap * 168, "F"),
					
					new Beat(startTime + gap * 170, "Space"),
					new Beat(startTime + gap * 171, "Space"),
					new Beat(startTime + gap * 172, "Space"),
					new Beat(startTime + gap * 173, "Space"),
					new Beat(startTime + gap * 174, "Space"),
					new Beat(startTime + gap * 175, "Space"),
					new Beat(startTime + gap * 176, "Space"),
					new Beat(startTime + gap * 177, "Space"),
					new Beat(startTime + gap * 178, "Space"),
					new Beat(startTime + gap * 179, "Space"),
					*/
					
			};
		} else if (tName.equals("Limitless_hard") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
					
			};
		} else if (tName.equals("Go") && difficulty.equals("Easy")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
					
			};
		} else if (tName.equals("Go_hard") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
					
			};
		} else if (tName.equals("Infectious") && difficulty.equals("Easy")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
					
			};
		} else if (tName.equals("Infectious_hard") && difficulty.equals("Hard")) {
			int startTime = 1000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
					
			};
		}
	
		int i = 0;
		music.start(); // 여기다 넣어주어야 비트들이 모두 생성되는 시간과 비트의 시작의 격차를 줄일 수 있다
		
		while (i < beats.length && !isInterrupted()) { // beats 배열에서 얻은 이름을 이용해 노트들을 하나씩 생성해주며 시작하게 한다
			
			boolean dropped = false; // 음악이 무한 재생되지 않도록 하는 플래그
			
			if (beats[i].getTime() <= music.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			
			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void judge(String nType) { // 노트 판정 함수
		for (int i = 0; i < noteList.size(); i++) { // 노트 리스트에서 첫 번째부터 검수한다(큐와 같은 느낌)
			Note note = noteList.get(i);
			
			if (nType.equals(note.getNoteType())) { // 만약 노트 타입이 같으면 실행
				note.judge(); // 노트의 위치에 따른 판정
				break;
			}
		}		
	}
}
