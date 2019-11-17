import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * 2018년도 2학기
 * MVC Pattern
 * @author 김상진
 * ProgressBarView 클래스  
 * MVC 패턴에서 View에 해당
 */
public class ProgressBarView implements CounterObserver {
	private ProgressIndicator pi = new ProgressIndicator(0);
	private ProgressBar pb = new ProgressBar(0);
	private final double MAX;
	public ProgressBarView(int max){
		MAX = max;
		BorderPane mainPane = new BorderPane();	
		VBox mainColumn = new VBox();
		mainColumn.setPadding(new Insets(10));
		mainColumn.setSpacing(10);
		mainColumn.getChildren().addAll(pi, pb);
		mainPane.setCenter(mainColumn);
		Stage stage = new Stage();
		stage.setTitle("ProgressBar View");
		stage.setScene(new Scene(mainPane));
		stage.show();
		stage.setX(100);
		stage.setY(100);
	}
	@Override
	public void updateCounter(int currentValue) {
		pi.setProgress(currentValue/MAX);
		pb.setProgress(currentValue/MAX);
	}
}
