package prevProject;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

// 떨어지는 노트의 애니매이션 클래스

public class Note extends Thread { // 각각의 노트도 따로 움직여주어야 하므로 쓰레드로 상속

	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.jpg")).getImage(); // noteBasicImage 변수 생성
	private int x, y = 580 - (1000 / Main.SLEEP_NOTE * Main.NOTE_SPEED) * Main.REACH_TIME; // 노트가 떨어지고선 판정라인에 1초 뒤에 닿을 수 있도록 y 좌표 설정
	private String noteType;
	private boolean proceeded = true; // 현재 노트의 진행 여부
	
	public String getNoteType() {
		
		return noteType;
		
	}
	
	public boolean isProceeded() {
		
		return proceeded;
		
	}
	
	public void close() { // 노트가 입력되면 더이상 노트가 움직이지 않도록
		
		proceeded = false;
		
	}
	
	public Note(String noteType) {
		
		if (noteType.equals("S")) {
			
			x = 228;
			
		} else if (noteType.equals("D")) {
			
			x = 332;
			
		} else if (noteType.equals("F")) {
			
			x = 436;
			
		} else if (noteType.equals("Space")) {
			
			x = 540;
			
		} else if (noteType.equals("J")) {
			
			x = 744;
			
		} else if (noteType.equals("K")) {
			
			x = 848;
			
		} else if (noteType.equals("L")) {
			
			x = 952;
			
		}
		
		this.noteType = noteType;
		
	}
	
	public void screenDraw(Graphics2D g) {
		
		if (!noteType.equals("Space")) { // 스페이스가 아닌 경우
			g.drawImage(noteBasicImage, x, y, null);
		} else {
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);
		}
	}
	
	// 노트가 떨어지는 메소드
	public void drop() {
		y += Main.NOTE_SPEED;
		
		if (y > 650) { // 노트가 너무 아래쪽에 가있으면 노트 미스 판정
			
			System.out.println("Miss");
			close();
			
		}
	}
	
	@Override
	public void run() {
		try {

			while (true) {
				drop();
				
				if (proceeded) {
					Thread.sleep(Main.SLEEP_NOTE);
				} else {
					interrupt();
					break;
				}
				
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void judge() { // 노트 판정 함수
		
		if (y >= 630) {
			System.out.println("Too late");
			close();
		} else if (y >= 620) {
			System.out.println("So So");
			close();
		} else if (y >= 610) {
			System.out.println("Good");
			close();
		} else if (y >= 600) {
			System.out.println("Great");
			close();
		} else if (y >= 580) {
			System.out.println("Exellent");
			close();
		} else if (y >= 570) {
			System.out.println("Perfect");
			close();
		} else if (y >= 550) {
			System.out.println("Too fast");
			close();
		}
		
	}
}
