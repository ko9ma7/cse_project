import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * 2018년도 2학기
 * MVC Pattern
 * @author 김상진
 * CounterSlideController: 사용자로부터 카운터를 조작하는 요청을 받아 Model에게
 *     중계하여 주는 역할을 하는 controller 
 * MVC 패턴에서 Controller에 해당
 */


public class SliderController{
	private CounterModelInterface model;
	private Stage stage;
	private Slider slider;
	private Spinner<Integer> spinner = new Spinner<>();
	private Button decreaseBtn = new Button("<<<");
	private Button increaseBtn = new Button(">>>");
	
	public SliderController(CounterModelInterface model, int max, int step){
		this.model = model;
		slider = new Slider(0,max,0);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(step);
		slider.setMinorTickCount(step/2);
		slider.setShowTickMarks(true);
		slider.valueProperty().addListener((o,ov,nv)->sliderChanged(o,ov,nv));
		
		spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, max, 0, step));
        spinner.setEditable(true);
        spinner.valueProperty().addListener((o,ov,nv)->spinnerChanged(o,ov,nv));
		
		BorderPane mainPane = new BorderPane();	
		VBox mainColumn = new VBox();
		mainColumn.setPadding(new Insets(10));
		mainColumn.setSpacing(10);
		mainColumn.getChildren().addAll(slider, spinner);
		
		HBox buttonPane = new HBox();
		buttonPane.setPadding(new Insets(10));
		buttonPane.setSpacing(10);
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.getChildren().addAll(decreaseBtn, increaseBtn);
		decreaseBtn.setOnAction(e->{
			model.decreaseCounter();
			spinner.getEditor().textProperty().set(model.getCounter()+"");
			slider.setValue(model.getCounter());
		});
		increaseBtn.setOnAction(e->{
			model.increaseCounter();
			spinner.getEditor().textProperty().set(model.getCounter()+"");
			slider.setValue(model.getCounter());
		});
		
		mainPane.setCenter(mainColumn);
		mainPane.setBottom(buttonPane);
		
		stage = new Stage();
		stage.setTitle("Counter Control");
		stage.setScene(new Scene(mainPane));
		stage.show();
		stage.setX(270);
		stage.setY(100);
	}
	public void sliderChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		int counter = newValue.intValue();
		spinner.getEditor().textProperty().set(counter+"");
		model.setCounter(counter);
	}
	public void spinnerChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		int counter = newValue.intValue();
		slider.setValue(counter);
		model.setCounter(counter);
	}
}
