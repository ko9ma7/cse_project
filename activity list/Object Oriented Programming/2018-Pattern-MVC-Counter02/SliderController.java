import java.util.ArrayList;

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
 *     중계하여 주고 모델로부터 데이터를 받아와 뷰들에게 전달함 
 * MVC 패턴에서 Controller에 해당
 */
public class SliderController{
	private CounterModelInterface model;
	private Stage stage;
	private Slider slider;
	private Spinner<Integer> spinner = new Spinner<>();
	private Button decreaseBtn = new Button("<<<");
	private Button increaseBtn = new Button(">>>");
	private ArrayList<CounterObserver> observers = new ArrayList<CounterObserver>();
	
	public SliderController(CounterModelInterface model, int max, int step){
		this.model = model;
		slider = new Slider(0,max,0);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(step);
		slider.setMinorTickCount(step/2);
		slider.setShowTickMarks(true);
		slider.setBlockIncrement(step);
		slider.setSnapToTicks(true);
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
			notifyObservers();
		});
		increaseBtn.setOnAction(e->{
			model.increaseCounter();
			spinner.getEditor().textProperty().set(model.getCounter()+"");
			slider.setValue(model.getCounter());
			notifyObservers();
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
		notifyObservers();
	}
	public void spinnerChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		int counter = newValue.intValue();
		slider.setValue(counter);
		model.setCounter(counter);
		notifyObservers();
	}
	public void addObserver(CounterObserver observer) {
		observers.add(observer);
		observer.updateCounter(model.getCounter());
	}

	public void removeObserver(CounterObserver observer) {
		observers.remove(observer);
	}
	
	public void notifyObservers() {
		for(CounterObserver observer: observers)
			observer.updateCounter(model.getCounter());
	}
}
