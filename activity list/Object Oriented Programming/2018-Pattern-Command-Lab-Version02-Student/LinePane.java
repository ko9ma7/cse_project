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
	private Stack<Command> undoStack = new Stack<>();
	private Stack<Command> redoStack = new Stack<>();
	
	public void drawHorizontalLine() {
		Command hCommand = new HorizontalLineDrawCommand(this);
		
		hCommand.execute();
		undoStack.push(hCommand);
		redoStack.clear(); // 새로운 수평선을 그리는 객체가 생성되면 redoStack에 있는 값은 없애준다.
	}
	public void drawVerticalLine() {
		Command vCommand = new VerticalLineDrawCommand(this);
		
		vCommand.execute();
		undoStack.push(vCommand);
		redoStack.clear(); // 새로운 수직선을 그리는 객체가 생성되면 redoStack에 있는 값은 없애준다.
	}
	public void undo() {
		if (!undoStack.isEmpty()) {
			Command command = undoStack.pop();
			command.undo();
			redoStack.push(command);
		}
	}
	public void redo() {
		if (!redoStack.isEmpty()) {
			Command command = redoStack.pop();
			command.execute();
			undoStack.push(command);
		}
	}
}

/*
 * Version01과의 차이점은 수평선과 수직선을 그리는 명령을 하나의 객체로 설정해주었다는 것이다.
 * 명령패턴을 이용한 결과인데, 이런 방법은 Version01에서는 undo기능과 redo기능, execute기능을 다 따로 지정해주어야 하지만
 * Version02에서는 하나의 객체 안에서 execute기능과 undo기능이 같이 있기에 코드가 더 간결해진다.
 * 또한, LinePane 클래스에 있는 undo와 redo 메소드에서 Version01의 경우, 단순히 명령 메소드이기 때문에 수평선인지, 수직선인지 구분해주어야 한다.
 * 하지만 Version02에서는 명령어가 명령 객체이기에 때문에 명령을 실행할 경우 굳이 수평선인지, 수직선인지 구분할 필요가 없다. 
 */
