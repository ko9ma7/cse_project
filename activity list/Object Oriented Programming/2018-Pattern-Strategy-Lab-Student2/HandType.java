import javafx.scene.image.Image;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기 전략패턴 실습
 * HandType
 * 묵찌바에서 각 손을 표현하는 열거형
 * @author 김상진
 *
 */
public enum HandType { // 이길 수 있는 타입
	MOOK("주먹.jpeg"){ 
		@Override
		public HandType winValueOf(){
			return BA;
		}
	}, 
	JI("가위.jpeg"){
		@Override
		public HandType winValueOf(){
			return MOOK;
		}
	}, 
	BA("보.jpeg"){
		@Override
		public HandType winValueOf(){
			return JI;
		}
	};
	private Image image;
	private HandType(String filename) {
		image = new Image(filename);
	}
	public Image getImage() {
		return image;
	}
	public static HandType valueOf(int n){ // n은 랜덤수(0~2)
		HandType[] enumList = HandType.values();
		assert (n<0&&n>=enumList.length);
		return HandType.values()[n];
	}
	public abstract HandType winValueOf();
}
