package refactoringProject07;
/*
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	// 객체가 생성되면 게임 인터페이스, 노트 입력, ...
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.jpg")).getImage(); // 노트가
																														// 이동하는
																														// 라인
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.jpg")).getImage(); // 노트
																														// 판정선
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.jpg")).getImage();
	
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.jpg")).getImage();
	private String tName; // 선택된 곡 이름
	private String difficulty; // 선택된 난이도 명
	private String mTitle; // 선택된 앨범
	private Music music; // 게임에서 선택된 음악

	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage, 228, 30, null); // 키 S
		g.drawImage(noteRouteDImage, 332, 30, null); // 키 D
		g.drawImage(noteRouteFImage, 436, 30, null); // 키 F
		g.drawImage(noteRouteSpace1Image, 540, 30, null); // 스페이스바
		g.drawImage(noteRouteSpace2Image, 640, 30, null); // 스페이스바
		g.drawImage(noteRouteJImage, 744, 30, null); // 키 J
		g.drawImage(noteRouteKImage, 848, 30, null); // 키 K
		g.drawImage(noteRouteLImage, 952, 30, null); // 키 L
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
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); // 글자 깨짐 현상을
																											// 없애줌
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(mTitle, 20, 702); // 곡명
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
		g.drawString(String.valueOf(score), 565, 702); // 점수

		g.drawImage(judgeImage, 500, 300, null);
	}
}
*/
