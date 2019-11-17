package refactoringProject02;

public class LimitlessNote extends NoteFactory {

	private String tName; // 선택된 곡 이름
	private String difficulty; // 선택된 난이도 명
	private String mTitle; // 선택된 앨범
	private Music music; // 게임에서 선택된 음악

	public void createNote(String titleName) {
		Beat[] beats = null;
		if (titleName.equals("Limitless") && difficulty.equals("Easy")) {
			int startTime = 1000 - Parameter.REACH_TIME.getValue() * 1000;
			int gap = 250;
			
			beats = new Beat[] {
					new Beat(startTime - gap, "S"),
					new Beat(startTime + gap * 2, "D"),
					new Beat(startTime + gap * 4, "F"),
					new Beat(startTime + gap * 6, "D"),
	
					new Beat(startTime + gap * 8, "S"),
					new Beat(startTime + gap * 10, "D"),
					new Beat(startTime + gap * 12, "F"),
					new Beat(startTime + gap * 14, "D"),
					
					new Beat(startTime + gap * 16, "S"),
					new Beat(startTime + gap * 17, "D"),
					new Beat(startTime + gap * 18, "F"),
					new Beat(startTime + gap * 19, "D"),
					
					new Beat(startTime + gap * 20, "S"),
					new Beat(startTime + gap * 22, "D"),
					new Beat(startTime + gap * 24, "F"),
					
					// 26부터
					new Beat(startTime + gap * 26, "L"),
					new Beat(startTime + gap * 28, "K"),
					new Beat(startTime + gap * 30, "J"),
					new Beat(startTime + gap * 32, "K"),
					
					new Beat(startTime + gap * 33, "L"),
					new Beat(startTime + gap * 34, "K"),
					new Beat(startTime + gap * 35, "J"),
					
					new Beat(startTime + gap * 40, "J"),
					new Beat(startTime + gap * 42, "K"),
					new Beat(startTime + gap * 44, "L"),
					new Beat(startTime + gap * 46, "K"),
					new Beat(startTime + gap * 47, "J"),
					
					new Beat(startTime + gap * 49, "S"),
					new Beat(startTime + gap * 50, "D"),
					new Beat(startTime + gap * 51, "F"),
					new Beat(startTime + gap * 52, "J"),
					new Beat(startTime + gap * 53, "K"),
					new Beat(startTime + gap * 54, "L"),
					
					// 55부터
					new Beat(startTime + gap * 55, "Space"),
					new Beat(startTime + gap * 57, "Space"),
					new Beat(startTime + gap * 59, "Space"),
					new Beat(startTime + gap * 61, "Space"),
					new Beat(startTime + gap * 63, "Space"),
					new Beat(startTime + gap * 65, "Space"),
					new Beat(startTime + gap * 67, "Space"),
					
					new Beat(startTime + gap * 69, "S"),
					new Beat(startTime + gap * 71, "L"),
					new Beat(startTime + gap * 73, "F"),
					new Beat(startTime + gap * 75, "J"),

					
					new Beat(startTime + gap * 78, "F"), new Beat(startTime + gap * 78, "J"),
					new Beat(startTime + gap * 79, "F"), new Beat(startTime + gap * 79, "J"),
					new Beat(startTime + gap * 80, "F"), new Beat(startTime + gap * 80, "J"),
					new Beat(startTime + gap * 81, "F"), new Beat(startTime + gap * 81, "J"),
					new Beat(startTime + gap * 82, "F"), new Beat(startTime + gap * 82, "J"),
					new Beat(startTime + gap * 83, "F"), new Beat(startTime + gap * 83, "J"),
				
					// 84부터
					new Beat(startTime + gap * 84, "Space"),
					new Beat(startTime + gap * 86, "Space"),
					new Beat(startTime + gap * 88, "Space"),
					new Beat(startTime + gap * 90, "Space"),
					new Beat(startTime + gap * 92, "Space"),
					
					new Beat(startTime + gap * 94, "S"), new Beat(startTime + gap * 94, "L"),
					new Beat(startTime + gap * 95, "S"), new Beat(startTime + gap * 95, "L"),
					new Beat(startTime + gap * 96, "S"), new Beat(startTime + gap * 96, "L"),
					new Beat(startTime + gap * 97, "S"), new Beat(startTime + gap * 97, "L"),
					
					new Beat(startTime + gap * 99, "S"), new Beat(startTime + gap * 99, "L"), new Beat(startTime + gap * 99, "F"), new Beat(startTime + gap * 99, "J"),
					
					new Beat(startTime + gap * 102, "S"), new Beat(startTime + gap * 102, "L"),
					new Beat(startTime + gap * 104, "S"), new Beat(startTime + gap * 104, "L"),
					new Beat(startTime + gap * 106, "S"), new Beat(startTime + gap * 106, "L"),
					new Beat(startTime + gap * 108, "S"), new Beat(startTime + gap * 108, "L"),
					new Beat(startTime + gap * 110, "S"), new Beat(startTime + gap * 110, "L"),
					new Beat(startTime + gap * 112, "S"), new Beat(startTime + gap * 112, "L"),
					new Beat(startTime + gap * 114, "S"), new Beat(startTime + gap * 114, "L"),
					new Beat(startTime + gap * 116, "Space"),
					
					// 117부터 시작
					new Beat(startTime + gap * 117, "J"),
					new Beat(startTime + gap * 118, "J"),
					new Beat(startTime + gap * 119, "J"),
					new Beat(startTime + gap * 120, "J"),
					new Beat(startTime + gap * 121, "J"),
					new Beat(startTime + gap * 122, "J"),
					
					new Beat(startTime + gap * 124, "F"),
					new Beat(startTime + gap * 125, "F"),
					new Beat(startTime + gap * 126, "F"),
					new Beat(startTime + gap * 127, "F"),
					new Beat(startTime + gap * 128, "F"),
					new Beat(startTime + gap * 129, "F"),
					
					new Beat(startTime + gap * 131, "K"),
					new Beat(startTime + gap * 132, "K"),
					new Beat(startTime + gap * 133, "K"),
					new Beat(startTime + gap * 134, "K"),
					new Beat(startTime + gap * 135, "K"),
					new Beat(startTime + gap * 136, "K"),
					
					new Beat(startTime + gap * 138, "D"),
					new Beat(startTime + gap * 139, "D"),
					new Beat(startTime + gap * 140, "D"),
					new Beat(startTime + gap * 141, "D"),
					new Beat(startTime + gap * 142, "D"),
					new Beat(startTime + gap * 143, "D"),
					
					// 다시 한번 반복
					new Beat(startTime + gap * 144, "Space"),
					
					new Beat(startTime + gap * 145, "L"),
					new Beat(startTime + gap * 146, "L"),
					new Beat(startTime + gap * 147, "L"),
					new Beat(startTime + gap * 148, "L"),
					new Beat(startTime + gap * 149, "L"),
					new Beat(startTime + gap * 150, "L"),
					
					new Beat(startTime + gap * 152, "Space"),
					
					new Beat(startTime + gap * 154, "S"),
					new Beat(startTime + gap * 155, "S"),
					new Beat(startTime + gap * 156, "S"),
					new Beat(startTime + gap * 157, "S"),
					new Beat(startTime + gap * 158, "S"),
					new Beat(startTime + gap * 159, "S"),
					
					new Beat(startTime + gap * 161, "J"),
					new Beat(startTime + gap * 162, "J"),
					new Beat(startTime + gap * 163, "J"),
					new Beat(startTime + gap * 164, "J"),
					new Beat(startTime + gap * 165, "J"),
					new Beat(startTime + gap * 166, "J"),
					
					new Beat(startTime + gap * 168, "Space"),
					
					new Beat(startTime + gap * 169, "F"),
					new Beat(startTime + gap * 170, "F"),
					new Beat(startTime + gap * 171, "F"),
					new Beat(startTime + gap * 172, "F"),
					new Beat(startTime + gap * 173, "F"),
					new Beat(startTime + gap * 174, "F"),
					
					// 175부터 시작
					new Beat(startTime + gap* 175, "S"),
					new Beat(startTime + gap* 177, "J"),
					new Beat(startTime + gap* 179, "D"),
					new Beat(startTime + gap* 181, "K"),
					new Beat(startTime + gap* 183, "F"),
					new Beat(startTime + gap* 185, "L"),
					new Beat(startTime + gap* 187, "J"),
					new Beat(startTime + gap* 189, "F"),
					new Beat(startTime + gap* 191, "K"),
					new Beat(startTime + gap* 193, "D"),
					new Beat(startTime + gap* 195, "L"),
					new Beat(startTime + gap* 197, "S"),
					new Beat(startTime + gap* 199, "K"),
					new Beat(startTime + gap* 201, "D"),
					new Beat(startTime + gap* 203, "J"),
					new Beat(startTime + gap* 205, "F"),
					new Beat(startTime + gap* 207, "L"),
					new Beat(startTime + gap* 209, "S"),
					
					new Beat(startTime + gap* 210, "S"),
					new Beat(startTime + gap* 211, "J"),
					new Beat(startTime + gap* 213, "D"),
					new Beat(startTime + gap* 215, "K"),
					new Beat(startTime + gap* 217, "F"),
					new Beat(startTime + gap* 219, "L"),
					new Beat(startTime + gap* 221, "J"),
					new Beat(startTime + gap* 223, "F"),
					new Beat(startTime + gap* 225, "K"),
					new Beat(startTime + gap* 227, "D"),
					new Beat(startTime + gap* 229, "L"),
					new Beat(startTime + gap* 231, "S"),
					new Beat(startTime + gap* 233, "K"),
					new Beat(startTime + gap* 235, "D"), new Beat(startTime + gap* 236, "Space"),
					new Beat(startTime + gap* 237, "J"), new Beat(startTime + gap* 238, "Space"),
					new Beat(startTime + gap* 239, "F"), new Beat(startTime + gap* 240, "Space"),
					new Beat(startTime + gap* 241, "L"), new Beat(startTime + gap* 242, "Space"),
					new Beat(startTime + gap* 243, "S"), new Beat(startTime + gap* 244, "Space"),
					
					new Beat(startTime + gap* 245, "S"), new Beat(startTime + gap* 246, "Space"),
					new Beat(startTime + gap* 247, "J"), new Beat(startTime + gap* 248, "Space"),
					new Beat(startTime + gap* 249, "D"), new Beat(startTime + gap* 250, "Space"),
					new Beat(startTime + gap* 251, "K"), new Beat(startTime + gap* 252, "Space"),
					new Beat(startTime + gap* 253, "F"), new Beat(startTime + gap* 254, "Space"),
					new Beat(startTime + gap* 255, "L"), new Beat(startTime + gap* 256, "Space"),
					new Beat(startTime + gap* 257, "J"), new Beat(startTime + gap* 258, "Space"),
					new Beat(startTime + gap* 259, "F"), new Beat(startTime + gap* 260, "Space"),
					new Beat(startTime + gap* 261, "K"), new Beat(startTime + gap* 262, "Space"),
					new Beat(startTime + gap* 263, "D"), new Beat(startTime + gap* 264, "Space"),
					new Beat(startTime + gap* 265, "L"), new Beat(startTime + gap* 266, "Space"),
					new Beat(startTime + gap* 267, "S"), new Beat(startTime + gap* 268, "Space"),
					new Beat(startTime + gap* 269, "K"), new Beat(startTime + gap* 270, "Space"),
					new Beat(startTime + gap* 271, "D"), new Beat(startTime + gap* 272, "Space"),
					new Beat(startTime + gap* 273, "J"), new Beat(startTime + gap* 273, "Space"), new Beat(startTime + gap* 274, "Space"),
					new Beat(startTime + gap* 275, "F"), new Beat(startTime + gap* 275, "Space"), new Beat(startTime + gap* 276, "Space"),
					new Beat(startTime + gap* 277, "L"), new Beat(startTime + gap* 277, "Space"), new Beat(startTime + gap* 278, "Space"),
					new Beat(startTime + gap* 279, "S"), new Beat(startTime + gap* 279, "Space"), new Beat(startTime + gap* 280, "Space"),
					
					new Beat(startTime + gap* 281, "Space"),
					new Beat(startTime + gap* 281 + 125, "Space"),
					new Beat(startTime + gap* 282, "Space"),
					new Beat(startTime + gap* 282 + 125, "Space"),
					new Beat(startTime + gap* 283, "Space"),
					new Beat(startTime + gap* 283 + 125, "Space"),
					new Beat(startTime + gap* 284, "Space"),
					new Beat(startTime + gap* 284 + 125, "Space"),
					new Beat(startTime + gap* 285, "Space"),
					new Beat(startTime + gap* 285 + 125, "Space"),
					new Beat(startTime + gap* 286, "Space"),
					new Beat(startTime + gap* 286 + 125, "Space"),
					new Beat(startTime + gap* 287, "Space"),
					new Beat(startTime + gap* 287 + 125, "Space"),
					new Beat(startTime + gap* 288, "Space"),
					new Beat(startTime + gap* 288 + 125, "Space"),
					
					new Beat(startTime + gap* 290, "S"), new Beat(startTime + gap* 290, "F"), new Beat(startTime + gap* 290, "J"), new Beat(startTime + gap* 290, "L"), 
					new Beat(startTime + gap* 291, "S"), new Beat(startTime + gap* 291, "F"), new Beat(startTime + gap* 291, "J"), new Beat(startTime + gap* 291, "L"),
					new Beat(startTime + gap* 292, "Space"),
					
					// 293부터 시작
					
					/*
					// 다시 한번 반복
					new Beat(startTime + gap * 31, "S"),
					new Beat(startTime + gap * 33, "D"),
					new Beat(startTime + gap * 35, "F"),
					new Beat(startTime + gap * 37, "D"),
					new Beat(startTime + gap * 38, "S"),
					new Beat(startTime + gap * 39, "D"),
					new Beat(startTime + gap * 40, "F"),
					new Beat(startTime + gap * 41, "D"),
					new Beat(startTime + gap * 42, "S"),
					
					new Beat(startTime + gap * 44, "S"),
					new Beat(startTime + gap * 44, "D"),
					new Beat(startTime + gap * 44, "F"),
					
					new Beat(startTime + gap * 46, "L"),
					new Beat(startTime + gap * 48, "K"),
					new Beat(startTime + gap * 50, "J"),
					new Beat(startTime + gap * 52, "K"),
					new Beat(startTime + gap * 53, "L"),
					new Beat(startTime + gap * 55, "K"),
					new Beat(startTime + gap * 56, "J"),
					new Beat(startTime + gap * 57, "K"),
					new Beat(startTime + gap * 58, "L"),
					
					new Beat(startTime + gap * 60, "L"),
					new Beat(startTime + gap * 60, "K"),
					new Beat(startTime + gap * 60, "J"),
					
					
					new Beat(startTime + gap * 32, "Space"),
					new Beat(startTime + gap * 36, "Space"),
					new Beat(startTime + gap * 40, "Space"),
					new Beat(startTime + gap * 44, "Space"),
					new Beat(startTime + gap * 48, "Space"),
					new Beat(startTime + gap * 52, "Space"),
					new Beat(startTime + gap * 56, "Space"),
					new Beat(startTime + gap * 60, "Space"),
					new Beat(startTime + gap * 64, "Space"),
					new Beat(startTime + gap * 68, "Space"),
					new Beat(startTime + gap * 72, "Space"),
					new Beat(startTime + gap * 74, "Space"),
					new Beat(startTime + gap * 78, "Space"),
					new Beat(startTime + gap * 82, "Space"),
					new Beat(startTime + gap * 86, "Space"),
					
					new Beat(startTime + gap * 28, "K"),
					new Beat(startTime + gap * 30, "L"),
					new Beat(startTime + gap * 32, "J"),
					new Beat(startTime + gap * 34, "K"),
					
					new Beat(startTime + gap * 36, "L"),
					new Beat(startTime + gap * 38, "K"),
					new Beat(startTime + gap * 40, "J"),
					new Beat(startTime + gap * 42, "K"),
					new Beat(startTime + gap * 44, "L"),
					new Beat(startTime + gap * 46, "K"),
					new Beat(startTime + gap * 48, "J"),
					new Beat(startTime + gap * 50, "F"),
					new Beat(startTime + gap * 50, "J"),
					new Beat(startTime + gap * 54, "Space"),
					new Beat(startTime + gap * 62, "S"),
					new Beat(startTime + gap * 64, "D"),
					new Beat(startTime + gap * 68, "F"),
					new Beat(startTime + gap * 70, "D"),
					new Beat(startTime + gap * 72, "S"),
					new Beat(startTime + gap * 74, "D"),
					new Beat(startTime + gap * 76, "F"),
					
					
					new Beat(startTime + gap * 78, "Space"),
					new Beat(startTime + gap * 80, "Space"),
					new Beat(startTime + gap * 82, "Space"),
					new Beat(startTime + gap * 84, "Space"),
					new Beat(startTime + gap * 86, "Space"),
					new Beat(startTime + gap * 88, "S"),
					new Beat(startTime + gap * 90, "D"),
					new Beat(startTime + gap * 92, "F"),
					new Beat(startTime + gap * 90, "D"),
					new Beat(startTime + gap * 92, "S"),
					new Beat(startTime + gap * 94, "D"),
					new Beat(startTime + gap * 96, "F"),
					new Beat(startTime + gap * 98, "D"),
					new Beat(startTime + gap * 100, "S"),
					new Beat(startTime + gap * 102, "D"),
					new Beat(startTime + gap * 104, "F"),
					new Beat(startTime + gap * 106, "D"),
					new Beat(startTime + gap * 108, "S"),
					new Beat(startTime + gap * 120, "D"),
					new Beat(startTime + gap * 122, "L"),
					new Beat(startTime + gap * 124, "K"),
					new Beat(startTime + gap * 126, "J"),
					new Beat(startTime + gap * 128, "K"),
					new Beat(startTime + gap * 130, "L"),
					new Beat(startTime + gap * 132, "K"),
					new Beat(startTime + gap * 134, "J"),
					new Beat(startTime + gap * 136, "K"),
					new Beat(startTime + gap * 138, "L"),
					new Beat(startTime + gap * 140, "K"),
					new Beat(startTime + gap * 142, "J"),
					new Beat(startTime + gap * 144, "F"),
					new Beat(startTime + gap * 146, "J"),
					new Beat(startTime + gap * 150, "Space"),
					new Beat(startTime + gap * 154, "S"),
					new Beat(startTime + gap * 156, "D"),
					new Beat(startTime + gap * 158, "F"),
					new Beat(startTime + gap * 160, "D"),
					new Beat(startTime + gap * 162, "S"),
					new Beat(startTime + gap * 164, "D"),
					new Beat(startTime + gap * 168, "F"),
					
					new Beat(startTime + gap * 170, "Space"),
					new Beat(startTime + gap * 171, "Space"),
					new Beat(startTime + gap * 172, "Space"),
					new Beat(startTime + gap * 173, "Space"),
					new Beat(startTime + gap * 174, "Space"),
					new Beat(startTime + gap * 175, "Space"),
					new Beat(startTime + gap * 176, "Space"),
					new Beat(startTime + gap * 177, "Space"),
					new Beat(startTime + gap * 178, "Space"),
					new Beat(startTime + gap * 179, "Space"),
					*/
					
			};
			
		}
		
	}
}