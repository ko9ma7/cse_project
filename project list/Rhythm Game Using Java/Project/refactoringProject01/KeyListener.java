package refactoringProject01;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// 사용자가 키보드를 입력했을 때 입력을 감지하여 발생하는 클래스
// 게임 클래스 내에서 구현되있음
public class KeyListener extends KeyAdapter {
	
	@Override
	public void keyPressed(KeyEvent e) { // 키를 눌렀을 경우 이벤트 처리
		
		if (RhythmGame.game == null) { return; } // 현재 게임이 진행되고 있지 않은 상태는 키보드 입력 무효
		
		if (e.getKeyCode() == KeyEvent.VK_S) {
			RhythmGame.game.pressS();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			RhythmGame.game.pressD();
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			RhythmGame.game.pressF();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			RhythmGame.game.pressSpace();
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			RhythmGame.game.pressJ();
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			RhythmGame.game.pressK();
		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			RhythmGame.game.pressL();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) { // 키를 떼었을 경우 이벤트 처리
		
		if (RhythmGame.game == null) { return; } // 현재 게임이 진행되고 있지 않은 상태는 키보드 입력 무효
		
		if (e.getKeyCode() == KeyEvent.VK_S) {
			RhythmGame.game.releaseS();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			RhythmGame.game.releaseD();
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			RhythmGame.game.releaseF();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			RhythmGame.game.releaseSpace();
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			RhythmGame.game.releaseJ();
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			RhythmGame.game.releaseK();
		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			RhythmGame.game.releaseL();
		}

	}
}
