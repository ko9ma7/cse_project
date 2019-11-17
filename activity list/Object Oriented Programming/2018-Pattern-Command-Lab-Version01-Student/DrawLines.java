import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기 명령 패턴 실습
 * DrawLines
 * 수평, 수직선 그리기
 * @author 김상진
 */
public class DrawLines extends Application {
	private Button horizontalDrawButton = new Button("Horizontal");
	private Button verticalDrawButton = new Button("Vertical");
	private Button undoButton = new Button("Undo");
	private Button redoButton = new Button("Redo");
	private LinePane linePane = new LinePane();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane mainPane = new BorderPane();
		HBox buttonPane = new HBox();
		buttonPane.setPadding(new Insets(10));
		buttonPane.setSpacing(10);
		buttonPane.setAlignment(Pos.CENTER);		
		horizontalDrawButton.setMinWidth(100);
		verticalDrawButton.setMinWidth(100);
		undoButton.setMinWidth(100);
		redoButton.setMinWidth(100);
		buttonPane.getChildren().addAll(
			horizontalDrawButton, verticalDrawButton, undoButton, redoButton);
		
		linePane.setPrefWidth(500);
		linePane.setPrefHeight(500);
		mainPane.setCenter(linePane);
		mainPane.setBottom(buttonPane);
		
		horizontalDrawButton.setOnAction(e->linePane.drawHorizontalLine());
		verticalDrawButton.setOnAction(e->linePane.drawVerticalLine());
		undoButton.setOnAction(e->linePane.undo());
		redoButton.setOnAction(e->linePane.redo());
		
		primaryStage.setTitle("Command Pattern: DrawLines");
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
