import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기 
 * MVP 패턴
 * @author 김상진
 * TicTacToe 프로그램
 */
public class TicTacToe extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		TTTModel theModel = new TTTModel();
		TTTView theView = new TTTView();
		TTTController theController = new TTTController(theModel, theView);
		primaryStage.setTitle("TicTacToe");
		primaryStage.setScene(new Scene(theView));
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}
