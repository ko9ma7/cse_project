import java.util.ArrayList;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기
 * @author 김상진
 * CalculatorController.java
 * 컨트롤러: 
 * 1) 뷰와 모델 유지
 * 2) 뷰의 구성요소에 대한 처리자 구현
 * 3) 정규표현식 등을 이용하여 사용자가 입력한 표현식의 유효성 검사
 */
public class CalculatorController {
	private CalculatorView theView;
	private CalculatorModel theModel;
	private ArrayList<Predicate<String>> predicates = new ArrayList<>();
	private ArrayList<String> errorMessages = new ArrayList<>();
	public CalculatorController(CalculatorView theView, CalculatorModel theModel) {
		this.theView = theView;
		this.theModel = theModel;
		this.theView.setCalculatorListener(e->handleCompute());
		predicates.add(s->hasIllegalCharacter(s));
		predicates.add(s->hasInvalidSequence(s));
		predicates.add(s->hasMissingOperatorOrOperands(s));
		predicates.add(s->hasInvalidParenthesis(s));
		errorMessages.add("사용가능하지 않은 문자가 포함되어 있습니다.");
		errorMessages.add("연산자가 올바르지 않은 식입니다.");
		errorMessages.add("연산자 수 또는 피연산자 수가 올바르지 않은 식입니다.");
		errorMessages.add("괄호가 일치하지 않습니다.");
	}

	private boolean hasIllegalCharacter(String infix) {
		String illegal = infix.replaceAll("[\\(\\)\\d\\+\\-\\*/\\s]", "");
		return illegal.length()>0;
	}
	private boolean hasInvalidSequence(String infix) {
		Pattern p = Pattern.compile("[\\+\\-\\*/]{2,}");
		Matcher m = p.matcher(infix.trim().replaceAll(" +", " "));
		return m.find();
	}
	private boolean hasMissingOperatorOrOperands(String infix){
		String[] operands = infix.replaceAll("[^\\d]", " ").trim().
				replaceAll("\\s{2,}", " ").split("\\s");
		String[] operators = infix.replaceAll("[^\\+\\-\\*/]", " ").trim().
				replaceAll("\\s{2,}", " ").split("\\s");
		return (operands.length-1!=operators.length);
	}
	private boolean hasInvalidParenthesis(String infix) {
		String parenthesis = infix.replaceAll("[^\\(\\)]", "");
		Stack<Character> check = new Stack<>();
		if(parenthesis.length()%2==1) return false;
		char[] P = parenthesis.toCharArray();
		for(var c: P) {
			switch(c) {
			case '(': check.push(c); break;
			default: 
				if(check.empty()) return false;
				check.pop();
			}
		}
		return check.empty();  
	}
	
	public void handleCompute() {
		String infix = theView.getExpression();
		String message = null;
		// 연속된 일련의 조건문이 별로
		/*
		if(hasIllegalCharacter(infix)) 
			message = "사용가능하지 않은 문자가 포함되어 있습니다.";
		else if(hasInvalidSequence(infix)) 
			message = "연산자가 올바르지 않은 식입니다.";
		else if(hasMissingOperatorOrOperands(infix))
			message = "연산자 수 또는 피연산자 수가 올바르지 않은 식입니다.";
		else if(hasInvalidParenthesis(infix)) 
			message = "괄호 불일치 식입니다.";
		*/
		for(int i=0; i<predicates.size(); i++) {
			if(predicates.get(i).test(infix)) {
				message = errorMessages.get(i);
				break;
			}
		}
		if(message==null){
			theView.setCalcSolution(theModel.compute(infix));
			theView.setException(
				"주의. 이항 사칙 연산자(+,-,*,/)와 괄호만 사용할 수 있음"
			);
		}
		else theView.setException(message);
	}
	
}
