import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class VerticalLineDrawCommand implements Command {
	private Pane pane;
	private Line line;
	private static int x = 10;
	public VerticalLineDrawCommand(Pane pane){
		this.pane = pane;
	}
	@Override
	public void execute() {
		line = new Line(x, 0, x, pane.getHeight());
		line.setStroke(Color.BLUE);
		pane.getChildren().add(line);
		x += 10;
	}
	@Override
	public void undo() {
		pane.getChildren().remove(line);
		x -= 10;
	}
}
