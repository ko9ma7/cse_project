import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * 2018년도 2학기
 * MVC Pattern
 * @author 김상진
 * MVCTest 클래스: 구현한 MVC 패턴을 실행하여 보기 위한 테스트 프로그램
 * 모델: 데이터 유지 및 처리 로직 유지, 뷰와 독립적 
 * 컨트롤러: 사용자 조작 처리하여 모델에 전달, 모델로부터 새 데이터를 받아 뷰에 전달
 * 뷰: 모델과 독립적  
 */
public class Counter extends Application {
	private TextField maxValue = new TextField();
	private TextField stepValue = new TextField();
	private Button startButton = new Button("시작");
	private Stage mainStage = null;
	
	public void startHandle() {
		String sMax = maxValue.getText();
		String sStep = stepValue.getText();
		int max = 0; 
		int step = 0;
		try {
			max = Integer.parseInt(sMax);
			step = Integer.parseInt(sStep);
		}
		catch(NumberFormatException e) {
			return;
		}
		mainStage.hide();
		CounterModelInterface model = new BasicCounterModel(max,step);
		SliderController slideControlView = new SliderController(model,max,step);
		ProgressBarView progressBarView = new ProgressBarView(max);
		TextView textView = new TextView();	
		slideControlView.addObserver(textView);
		slideControlView.addObserver(progressBarView);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		maxValue.setMinWidth(50);
		stepValue.setMinWidth(50);
		startButton.setOnAction(e->startHandle());
		
		GridPane inputPane = new GridPane();
		inputPane.setPadding(new Insets(10));
		inputPane.setHgap(10);
		inputPane.setVgap(10);
		inputPane.add(new Label("최대값"), 0, 0); // column=1 row=0
		inputPane.add(new Label("증가값"), 0, 1);
		inputPane.add(maxValue, 1, 0);
		inputPane.add(stepValue, 1, 1);
		inputPane.add(startButton, 2, 0, 1, 2);
		
		mainStage = primaryStage;
		primaryStage.setTitle("MVC 계산기");
		primaryStage.setScene(new Scene(inputPane));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
