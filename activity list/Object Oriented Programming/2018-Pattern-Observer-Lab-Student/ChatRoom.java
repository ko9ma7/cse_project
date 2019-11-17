import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기 관찰자 패턴 실습
 * ChatRoom
 * 사용자 목록과 채팅 메시지 목록 유지
 * 채팅룸 목록, 사용자 목록 유지
 * @author 김상진
 *
 */
public class ChatRoom{
	private String roomName;
	// 이 채팅방에서 교환된 모든 메시지를 유지함 
	private ChatRoomLog roomLog = new ChatRoomLog();
	// Map<사용자ID,마지막 받은 메시지 색인> 
	// 가입되어 있는 각 사용자 정보를 유지함. 사용자 id가 키가 되고, 최종적으로 받은 메시지 색인이 값이 됨
	// 메시지 색인 값을 지정해주어야 각 사용자마다 마지막에 받은 메시지를 알 수 있다.
	private Map<String, Integer> lastMessageIndex = new HashMap<>(); // 사용자마다 가장 최근에 받은 메시지를 유지하기 위해 Map을 사용
	public ChatRoom(String name) {
		roomName = name;
	}
	public String getRoomName() {
		return roomName;
	}
	public ChatRoomLog getRoomLog() {
		return roomLog;
	}
	// 채팅 서버가 채팅방에 새 메시지가 생길 때마다 사용함
	/*
	 *  채팅 서버가 새 메시지를 수신하면 이 메소드를 이용하여 채팅방에 새 메시지를 전달함. 이 메소드는 roomLog에 메시지를 추가하고, updateUsers 메소드를 이용하여
	 *  모든 사용자에게 전달해야 함. 이 실습에서는 메시지를 작성한 사용자에게도 메시지를 보냄
	 */
	public void newMessage(String userID, String message) {
		// 만약 여기에 예외 처리를 추가한다면... 
		roomLog.addMessage(userID, message);
		updateUsers();
	}
	// 사용자가 가입할 때 사용함
	// 사용자가 가입된 이후 발생한 메시지만 받음
	// LastMessageIndex Map에 사용자를 추가해야 함
	// 관찰자 패턴 중 subject 기능
	/*
	 *  주어진 id에 해당하는 사용자의 가입을 처리함. lastMessageIndex에 id가 키가 되고 이 채팅방의 마지막 메시지의 색인을 값이 되도록 추가함(Observer Strategy - addObserver)
	 */
	public void addUser(String userID) {
		
		if (!lastMessageIndex.containsKey(userID)) // 중복 발생 방지
			lastMessageIndex.put(userID, roomLog.size() - 1);
		
	}
	/*
	 *  주어진 id에 해당하는 사용자의 탈퇴 처리(Observer Strategy - deleteObserver)
	 */
	public void deleteUser(String userID) {
		
		lastMessageIndex.remove(userID);
		
	}
	// 채팅방에 있는 모든 사용자들에게 최신 메시지를 전달한다.
	// 이전에 받은 메시지부터 최신 메시지까지 전달해야 함. 
	// 즉, 사용자마다 전달해야 하는 메시지 수가 다를 수 있음
	// 특정 사용자는 현재 오프라인일 수 있음
	// 관찰자 패턴 중 subject 기능
	/*
	 *  채팅방의 새 메시지를 채팅방의 각 사용자에게 전달함. 현재 온라인 상태인 사용자들에게만 전달해야 하며, 각 사용자마다 사용자가 받지 못한 모든 메시지를 전달해야 함.
	 *  이 때, ChatServer의 forwardMessage 활용해야 함(Observer Strategy - notifyObservers)
	 *  왜냐하면 Chatmessage는 User 객체와 ChatRoom 객체 간의 직접적인 통신이 안되므로 ChatServer가 중간자 역할을 한다.
	 */
	public void updateUsers() {
		
		ArrayList<ChatMessage> messages = roomLog.getMessages(); // 현재까지 있는 채팅룸 메시지를 ArrayList로 받아옴.
		ChatServer chatServer = ChatServer.getServer(); // 통신을 위해 ChatServer 변수 선언
		
		for (var entry: lastMessageIndex.entrySet()) { // 사용자 목록
			
			String userID = entry.getKey();
			User user = ChatServer.getServer().getUser(userID);
			
			if (user.isOnline()) { // 사용자가 온라인인 경우에만
				
				for (int i = entry.getValue() + 1; i < roomLog.size(); i++) {
				
					ChatMessage curMessage = messages.get(i); // i번째 인덱스의 위치에 있는 값을 가져올 때 사용(메시지)
					ChatServer.getServer().forwardMessage(userID, roomName, curMessage.getUserID(), curMessage.getMessage());
					
				}
				
				// 데이터베이스 갱신
				lastMessageIndex.put(userID, roomLog.size() - 1);
				
			}

		}
		
	}
}
