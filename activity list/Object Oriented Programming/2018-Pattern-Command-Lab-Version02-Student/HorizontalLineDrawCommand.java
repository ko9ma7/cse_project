import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class HorizontalLineDrawCommand implements Command {
	private Pane pane;
	private Line line;
	private static int y = 10;
	public HorizontalLineDrawCommand(Pane pane){
		this.pane = pane;
	}
	@Override
	public void execute() {
		line = new Line(0, y, pane.getWidth(), y);
		line.setStroke(Color.RED);
		pane.getChildren().add(line);
		y += 10;
	}

	@Override
	public void undo() {
		pane.getChildren().remove(line);
		y -= 10;
	}
}
