package refactoringProject05;

public class EasyStrategy extends Note implements JudgeEasyStrategy {
	String difficulty;
	
	public EasyStrategy(String noteType, String difficulty) {
		super(noteType);
		this.difficulty = difficulty;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String judge() {
		
		System.out.println("asdfdsafdsfdssdf");
		System.out.println(y);
		
		
		if (y >= 800) {
			System.out.println("Miss");
			close();

			score = 0;

			return "Miss";
		} else if (y >= 780) {
			score += 1;
			System.out.println("Too late");
			close();

			return "Too late";
		} else if (y >= 750) {

			score += 20;
			System.out.println("So So");
			close();

			return "So So";
		} else if (y >= 730) {
			score += 30;
			System.out.println("Good");
			close();

			return "Good";
		} else if (y >= 650) {
			score += 50;
			System.out.println("Great");
			close();

			return "Great";
		} else if (y >= 400) {
			score += 1;
			System.out.println("Too fast");
			close();

			return "Too fast";
		}

		return "None";
	}
}
