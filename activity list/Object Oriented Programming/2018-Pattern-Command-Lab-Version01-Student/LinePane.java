import java.util.Stack;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기 명령 패턴 실습
 * LinePane
 * 수평, 수직선 그리기
 * @author 김상진
 */
public class LinePane extends Pane {
	private int x = 10;
	private int y = 10;
	private Stack<Line> undoStack = new Stack<>();
	private Stack<Line> redoStack = new Stack<>();
	
	public void drawHorizontalLine() { // 수평선 그리기
		Line line = new Line(0, y, getWidth(), y);
		line.setStroke(Color.RED);
		
		undoStack.push(line);
		redoStack.clear(); // 새로운 수평선이 들어가게 되면 redoStack에 있는 값은 삭제
		
		getChildren().add(line);
		y += 10;
	}
	public void drawVerticalLine() { // 수직선 그리기
		Line line = new Line(x, 0, x, getHeight());
		line.setStroke(Color.BLUE);
		
		undoStack.push(line);
		redoStack.clear(); // 새로운 수직선이 들어가게 되면 redoStack에 있는 값은 삭제
		
		getChildren().add(line);
		x += 10; 
	}
	/*
	 *  undo()와 redo() 스택이 있다. 1번이 실행되고, 2번이 실행되면 undo() 스택에 차례대로 들어간다. 만약, 여기서 undo()를 하게되면
	 *  가장 마지막에 들어간 2번이 취소되고, 1번 상태로 돌아간다. 여기서 빠져나온 2번은 redo() 스택으로 들어간다. undo()를 하면
	 *  redo() 스택으로, redo()를 하면 undo() 스택으로 들어간다. 주의해야 할 점은 만약, redo()를 하다가 아직 redo() 스택에 값이 있더라도
	 *  새로운 번호가 실행되어 undo() 스택에 들어가게 되면, redo() 스택 안의 내용은 없애야 한다.
	 */
	public void undo() {
		
		if (!undoStack.isEmpty()) {
			Line undoLine = undoStack.pop();
			// undoStack에 마지막에 들어있는 값이 수평선인지 수직선인지 확인해야됨
			if (undoLine.getStartY() == undoLine.getEndY()) y -= 10;
			else x -= 10;
			
			getChildren().remove(undoLine);	// 라인을 없애주는 함수(remove)
			redoStack.push(undoLine); // undoStack에서 pop한 값을 redoStack에 넣어준다
		}

	}
	public void redo() { // undo에서 삭제한 라인을 다시 그려주는 기능이므로 +를 해주어야 함
		
		if (!redoStack.isEmpty()) {
			Line redoLine = redoStack.pop();
			// redoStack에 마지막에 들어있는 값이 수평선인지 수직선인지 확인해야됨
			if (redoLine.getStartY() == redoLine.getEndY()) y += 10;
			else x += 10;
			
			getChildren().add(redoLine);
			undoStack.push(redoLine); // redoStack에서 pop한 값을 undoStack에 넣어준다
		}
		
	}
}
