import java.util.HashMap;
import java.util.Map;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기 관찰자 패턴 실습
 * User 
 * 채팅 프로그램에서 사용자 역할을 함 
 * 가입된 채팅방마다 채팅메시지 목록 유지
 * 채팅방에 대해서는 관찰자, 채팅창에 대해서는 subject
 * @author 김상진
 *
 */
public class User{
	private String userID;
	private boolean isOnline = true; // 현재 온라인 상태를 유지
	// Map<채팅방, 채팅메시지 목록>
	// 자신이 가입한 후 발생된 메시지만 유지
	private Map<String, ChatRoomLog> roomLogs = new HashMap<>(); // 사용자가 가입한 각 채팅방의 대화목록 유지
	// 사용자 채팅 뷰
	private UserChatWindow chatWindow; // 사용자의 GUI
	
	public User(String userID) {
		this.userID = userID;
	}
	public String getUserID() {
		return userID; 
	}
	public void setOnline(boolean flag) {
		isOnline = flag;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void joinRoom(String roomName) { // 주어진 이름의 채팅방에 가입하고 채팅방의 대화 목록을 두 번째 인자로 초기화함
		roomLogs.put(roomName, new ChatRoomLog());
	}
	public void leaveRoom(String roomName) { // 주어진 이름의 채팅방에서 나감
		roomLogs.remove(roomName);
	}
	public String[] getRooms() {  // 채팅방의 이름을 반환함
		String[] roomNames = new String[roomLogs.size()];
		roomLogs.keySet().toArray(roomNames);
		return roomNames;
	}
	public ChatRoomLog getRoomLog(String roomName) { // 주어진 이름의 채팅방의 대화 목록을 반환함
		return roomLogs.get(roomName);
	}
	public void setView(UserChatWindow chatWindow) {
		this.chatWindow = chatWindow;
	}
	public void notifyView(String roomName) {
		chatWindow.update(roomName);
	}
	/*
	 *  ChatRoom에 새 메시지가 생기면 각 사용자들에게 그 사실을 알리기 위해 사용함. 사용자가 유지하고 있는 메시지 이후 새로 발생한 메시지들만 두 번째 인자로 보내준다.
	 */
	public void update(String roomName, String senderID, String message) {
		roomLogs.get(roomName).addMessage(senderID, message);
		notifyView(roomName);
	}

}
