import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기 관찰자 패턴 실습
 * ChatServer 
 * 채팅 프로그램에서 통신 서버와 데이터베이스 서버 역할을 함 
 * 채팅룸 목록, 사용자 목록 유지
 * @author 김상진
 *
 */
public class ChatServer {
	// 데이터베이스 역할을 위한 두 개의 맵
	private Map<String, ChatRoom> chatRooms = new HashMap<>(); // 채팅룸 데이터베이스
	private Map<String, User> users = new HashMap<>(); // 사용자 데이터베이스
	private static ChatServer uniqueInstance = null;
	private ChatServer() {}
	// 싱글톤으로 모델링, 전역변수처럼 사용할 수 있음
	public static ChatServer getServer() {
		if(uniqueInstance==null) uniqueInstance = new ChatServer();
		return uniqueInstance;
	}
	// 채팅방 데이터베이스 접근 메소드
	public void addRoom(String roomName) {
		chatRooms.put(roomName, new ChatRoom(roomName));
	}
	// 채팅방 데이터베이스 접근 메소드
	public ChatRoom getRoom(String roomName) {
		return chatRooms.get(roomName);
	}
	// 사용자 데이터베이스 접근 메소드
	public void addUser(User user) {
		users.put(user.getUserID(),user);
	}
	// 사용자 데이터베이스 접근 메소드
	public User getUser(String userID) {
		return users.get(userID);
	}
	// 사용자 데이터베이스 접근 메소드
	public Collection<User> getUsers(){
		return users.values();
	}
	// 주어진 사용자를 주어진 채팅방에 가입시킨다
	public void addUserToRoom(String userID, String roomName) {
		User user = users.get(userID);
		ChatRoom chatRoom = chatRooms.get(roomName);
		chatRoom.addUser(userID);
		user.joinRoom(roomName);
	}
	// 통신 서버를 위한 메소드들
	// 사용자가 새 메시지를 특정 채팅방에 보낼 때 사용하는 메소드
	public void sendMessage(String userID, String roomName, String message) {
		ChatRoom chatRoom = chatRooms.get(roomName);
		chatRoom.newMessage(userID, message); 
	}
	// 특정 메시지들을 특정 채팅방에 소속되어 있는 사용자에게 전달함
	public void forwardMessage(String destID, String roomName, String senderID, String message) {
		users.get(destID).update(roomName, senderID, message);
	}
}
