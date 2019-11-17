import java.util.ArrayList;
import java.util.Stack;
import java.util.function.Predicate;

/*
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기
 * @author 김상진
 * CalculatorModel.java
 * 모델: 
 * 1) Infix 표현을 Postfix로 바꿈
 * 2) Postfix를 평가함
 */
public class CalculatorModel {
	public int compute(String infix){
		ArrayList<String> postfix = convertToPostfix(infix);
		return computePostfix(postfix);
	} 
	private boolean isAddTypeOperator(char c){
		return c=='+'||c=='-';
	}
	private boolean isMultTypeOperator(char c){
		return c=='*'||c=='/';
	}
	private boolean isOperator(char c){
		return isAddTypeOperator(c)||isMultTypeOperator(c);
	}
	private void moveOperatorsToPostfix(
		ArrayList<String> postfix,
		Stack<Character> operatorStack,
		Predicate<Character> pushPredicate, 
		Predicate<Character> stopPredicate){
		while(!operatorStack.empty()){
			char top = operatorStack.peek();
			if(pushPredicate.test(top)){
				postfix.add(top+"");
				operatorStack.pop();
			}
			else if(stopPredicate.test(top)){
				break;
			}
			//else throw new RuntimeException("processing ) or Operators");
		}
	}
	
	private ArrayList<String> convertToArrayList(String infix) {
		String[] operands = infix.replaceAll("[^\\d]", " ").trim().replaceAll("\\s{2,}", " ").split("\\s");
		infix = infix.replaceAll("\\s", "");
		infix = infix.replaceAll("[\\d]+", "0");
		char[] E = infix.toCharArray();
		ArrayList<String> SE = new ArrayList<>();
		int operandCount = 0;
		for(int i=0; i<E.length; i++){
			switch(E[i]) {
			case '(': case ')': case '+': case '-': case '*': case '/':
				SE.add(E[i]+""); 
				break;
			default:
				SE.add(operands[operandCount]);
				++operandCount;
				break;		
				//else if(Character.isWhitespace(E[i])) continue;
				//else throw new RuntimeException("invalid character");
			}
		}
		return SE;
	}
	// infix를 Postfix로
	// 1) 문자열을 각 연산자, 피연산자로 구분하여 리스트에 저장함
	// 2) 저장된 infix 리스트를 postfix로 바꿈
	private ArrayList<String> convertToPostfix(String infix) {
		ArrayList<String> postfix = new ArrayList<>();
		Stack<Character> operatorStack = new Stack<>();
		ArrayList<String> expressionList = convertToArrayList(infix);
		for(String s: expressionList) {
			char c = s.charAt(0);
			switch(c){
			case '(': operatorStack.push(c); break;
			case ')': 
				moveOperatorsToPostfix(postfix, operatorStack, C->isOperator(C), C->C=='(');
				operatorStack.pop();
				break;
				//pop operators off the stack and append them onto the postfix string until you pop a matching “(“.
			case '+': case '-': 
				if(operatorStack.empty()) operatorStack.push(c);
				else {
					moveOperatorsToPostfix(postfix, operatorStack, C->isOperator(C), C->C=='(');
					operatorStack.push(c);
				}
				break;
			case '*': case '/':
				if(operatorStack.empty()) operatorStack.push(c);
				else {
					moveOperatorsToPostfix(postfix, operatorStack, C->isMultTypeOperator(C), C->C=='('||isAddTypeOperator(C));
					operatorStack.push(c);
				}
				//Pop all operators with greater or equal precedence off the stack 
				//and append them on the postfix string.
				//b. Stop when you reach an operator with lower precedence or a (.
				//c. Push the new operator on the stack.
				break;
			default:
				postfix.add(s);
			}
		}
		moveOperatorsToPostfix(postfix, operatorStack, C->isOperator(C), C->false);
		// When all infix tokens are gone, pop each operator and append it to the postfix string.
		return postfix;
	}	
	private int doOperation(int n1, int n2, String s) {
		switch(s){
		case "+": return n1+n2; 
		case "-": return n1-n2; 
		case "*": return n1*n2; 
		default: return n1/n2; 
		}
	}
	private int computePostfix(ArrayList<String> postfix) {		
		Stack<Integer> operandStack = new Stack<>();
		operandStack.clear();
		for(String s: postfix) {
			switch(s) {
			case "+": case "-": case "*": case "/":
				//if(operandStack.size()<2) throw new CalculatorException("피연산자 부족");
				int n2 = operandStack.pop();
				int n1 = operandStack.pop();
				operandStack.push(doOperation(n1,n2,s));
				break;
			default:
				operandStack.push(Integer.valueOf(s));
			}
		}
		return operandStack.pop();
	}
}
