import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/*
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기
 * @author 김상진
 * CalculatorView.java
 */
public class CalculatorView extends BorderPane {
	private TextField expression = new TextField();
	private Button calculateButton = new Button("계산");
	private TextField result = new TextField();
	private TextField exception = new TextField();
	
	public CalculatorView(){
		expression.setMinWidth(240);
		result.setMaxWidth(160);
		result.setEditable(false);
		exception.setMinWidth(400);
		exception.setEditable(false);
		
		HBox calculatorPane = new HBox();
		calculatorPane.setPadding(new Insets(10));
		calculatorPane.setSpacing(10);
		calculatorPane.setAlignment(Pos.CENTER);
		calculatorPane.getChildren().addAll(expression,calculateButton,result);
		
		HBox exceptionPane = new HBox();
		exceptionPane.setPadding(new Insets(10));
		exceptionPane.setSpacing(10);
		exceptionPane.setAlignment(Pos.CENTER);
		exceptionPane.getChildren().add(exception);
		
		setTop(calculatorPane);
		setBottom(exceptionPane);
		exception.setText("주의. 이항 사칙 연산자(+,-,*,/)와 괄호만 사용할 수 있음");
	}	
	public String getExpression() {
		return expression.getText();
	}
	public void setCalcSolution(int value){
		result.setText(value+"");
	}
	public void setException(String msg) {
		exception.setText(msg);
	}
	public void setCalculatorListener(EventHandler<ActionEvent> handler){
		calculateButton.setOnAction(handler);
	}
}
