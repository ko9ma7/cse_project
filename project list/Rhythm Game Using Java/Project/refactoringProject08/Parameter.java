package refactoringProject08;

public enum Parameter {	
	SCREEN_WIDTH {
		@Override
		public int getValue() { return 1280; }
	},
	SCREEN_HEIGHT {
		@Override
		public int getValue() { return 720; }
	},
	NOTE_SPEED_EASY {
		@Override
		public int getValue() { return 3; }
	}, 
	NOTE_SPEED {
		@Override
		public int getValue() { return 7; }
	},
	SLEEP_NOTE {
		@Override
		public int getValue() { return 3; }
	},
	REACH_TIME {
		@Override
		public int getValue() { return 1; }
	};
	
	public static final String PATH = "C:/Users/지명화/eclipse-workspace/2018-Pattern-SoftWareEngineering_Project-RhythmGame/src/images/";
	public abstract int getValue();
}
