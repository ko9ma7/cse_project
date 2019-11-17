import java.util.ArrayList; // 새로운 메시지는 뒤에 들어옴

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기 관찰자 패턴 실습
 * ChatRoomLog
 * 채팅 메시지 목록 
 * ChatRoom과 User가 모두 개별적으로 유지
 * @author 김상진
 *
 */
public class ChatRoomLog{
	private ArrayList<ChatMessage> messages = new ArrayList<>(); // 이 채팅방에서 교환된 모든 메시지를 유지하는 리스트
																 // 사용자의 경우에는 자신이 최초 가입 이후 지금까지 받은 메시지만 유지함
	
	public void addMessage(String userID, String message) { // 주어진 사용자가 전송한 메시지를 저장함
		messages.add(new ChatMessage(userID, message));
	}
	public ArrayList<ChatMessage> getMessages(){ // getter 함수
		return messages;
	}
	public int size() { // 이 Log에 저장되어 있는 메시지 수를 반환함.
		return messages.size();
	}
}