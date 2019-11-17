/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 탬플릿 메소드 패턴
 * Card.java
 * 카드 한 장을 나타내는 클래스
 * 모양과 숫자
 * @author 김상진 
 */
public class Card{
	private int number;
    private CardFace face;
    public Card(int number, CardFace face){
        this.number = number;
        this.face = face;
    }
    public int getNumber(){
        return number;
    }
    public CardFace getFace(){
        return face;
    }
    @Override
    public String toString(){
    	return String.format("%d %s", number, face);
    }
}
