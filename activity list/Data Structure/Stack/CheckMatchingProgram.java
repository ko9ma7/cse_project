import java.util.Stack;
import java.util.Scanner;
import java.io.*;

class CheckMatching {
	public void error(String str) {
		System.out.printf("%s", str);
		System.exit(1);
	}
	public void checkMatching(String filename) throws IOException {
		File file = new File(filename);
		if (!file.exists()) error("Error: File is not exist!");

		int nLine = 1; // read line count
		int nChar = 0; // read character count

		Stack<Character> stack = new Stack<>();
		FileReader reader = new FileReader(file);
		char ch;

		while ((ch = (char)reader.read()) != -1) {
			if (ch == '\n') nLine++;
			nChar++;

			if (ch == '[' || ch == '(' || ch == '{') stack.push(ch); /*
			                                                            If compile this code,
										    you can see this line is error.
										    Because this code deos not consider remark.
										 */
			else if (ch == ']' || ch == ')' || ch == '}') {
				// error 1. if stack is empty
				if (stack.isEmpty()) break;
				else {
					char prev = stack.pop();
					// error 1. if character is not matching
					if ((ch == ']' && prev != '[') || (ch == ')' && prev != '(') || (ch == '}' && prev != '{')) break;
				}
			}
		}
		reader.close();
		System.out.printf("[%s] File check result:\n", filename);

		// error 3. if checkMatching is finish but stack is not empty
		if (!stack.isEmpty()) {
			System.out.printf("checkMatching is fail!(Line = %d, Character = %d)\n\n", nLine, nChar);
		} else {
			System.out.printf("checkMatching is success!(Line = %d, Character = %d)\n\n", nLine, nChar);
		}
	}
}
public class Main {
	public static void main(String[] args) throws IOException {
		CheckMatching check = new CheckMatching();
		check.checkMatching("C:\\Users\\JiMyungWha\\eclipse-workspace\\DataStructure_Stack_CheckMatching\\src\\Main.java");
	}
}
