import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * 2018년도 2학기
 * MVC Pattern
 * @author 김상진
 * TextView 클래스: 카운터 모델에 상태 변화에 따라 그것을 사용자에게 
 *     디스플레이하여 주는 클래스  
 * MVC 패턴에서 View에 해당
 */

public class TextView implements CounterObserver {
	Label counterLabel = new Label();
	private Stage stage;
	public TextView(){
		Label textLabel = new Label("Current Counter:");
		textLabel.setMinWidth(80);
		counterLabel.setMinWidth(40);
		BorderPane mainPane = new BorderPane();		
		HBox mainRow = new HBox();
		mainRow.setPadding(new Insets(10));
		mainRow.setSpacing(10);
		mainRow.getChildren().addAll(textLabel,counterLabel);
		mainPane.setCenter(mainRow);
		stage = new Stage();
		stage.setTitle("Text View");
		stage.setScene(new Scene(mainPane));
		stage.show();
		stage.setX(500);
		stage.setY(100);
	}
	@Override
	public void updateCounter(int currentValue) {
		counterLabel.setText(String.format("%d",currentValue));
	}
}
