package refactoringProject08;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

// 떨어지는 노트의 애니매이션 클래스
// "싱글톤 패턴으로 구현해보기" -> 열거형 사용하기
public class Note extends Thread { // 각각의 노트도 따로 움직여주어야 하므로 쓰레드로 상속
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.jpg")).getImage(); // noteBasicImage 변수 생성
	private int x;
	private int y = 580 - (1000 / Parameter.SLEEP_NOTE.getValue() * Parameter.NOTE_SPEED.getValue()) * Parameter.REACH_TIME.getValue();
	
	private String noteType;
	private boolean isProceed = true; // 현재 노트의 진행 여부
	
	
	// 점수 전략
	public int score = 0;
	
	// 이 부분도 키 입력과 노트의 값에 관계 다시 생각해보기
	public Note(String noteType) {
		if (noteType.equals("S")) { x = 228; } 
		else if (noteType.equals("D")) { x = 332; }
		else if (noteType.equals("F")) { x = 436; }
		else if (noteType.equals("Space")) { x = 540; }
		else if (noteType.equals("J")) { x = 744; }
		else if (noteType.equals("K")) { x = 848; }
		else if (noteType.equals("L")) { x = 952; }
		
		this.noteType = noteType;
	}
	
	public String getNoteType() { return noteType;}
	public boolean getIsProceed() { return isProceed; }
	// 노트가 입력되면 더이상 노트가 움직이지 않도록
	public void close() { isProceed = false; }

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
		y += Parameter.NOTE_SPEED.getValue();
	}
	@Override
	public void run() {
		try {
			while (true) {
				drop();
			
				if (isProceed) {
					Thread.sleep(Parameter.SLEEP_NOTE.getValue());
				} else {
					interrupt();
					break;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public String judge(String difficulty) { // 노트 판정 함수
		
		if (y >= 800) {
			System.out.println("Miss");
			close();

			score = 0;

			return "Miss";
		} else if (y >= 780) {
			score += 1;
			System.out.println("Too late");
			close();

			return "Too late";
		} else if (y >= 750) {

			score += 20;
			System.out.println("So So");
			close();

			return "So So";
		} else if (y >= 730) {
			score += 30;
			System.out.println("Good");
			close();

			return "Good";
		} else if (y >= 650) {
			score += 50;
			System.out.println("Great");
			close();

			return "Great";
		} else if (y >= 400) {
			score += 1;
			System.out.println("Too fast");
			close();

			return "Too fast";
		}

		return "None";
	
	}
	
	
}
