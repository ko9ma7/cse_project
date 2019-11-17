import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MultipleActionTest extends Application {
	
	class A implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			System.out.println("Apple");			
		}
	}
	class B implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			System.out.println("Banana");			
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane mainPane = new StackPane();
		Button multiActionButton = new Button("제발");
		mainPane.getChildren().add(multiActionButton);
		
		multiActionButton.setOnAction(e->System.out.println("라라!"));
		multiActionButton.addEventHandler(ActionEvent.ACTION, new A()); // A를 옵저버로 만든다
		multiActionButton.addEventHandler(ActionEvent.ACTION, new B()); // B를 옵저버로 만든다
		
		primaryStage.setTitle("관찰자 패턴 테스트");
		primaryStage.setScene(new Scene(mainPane, 300, 300));
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
