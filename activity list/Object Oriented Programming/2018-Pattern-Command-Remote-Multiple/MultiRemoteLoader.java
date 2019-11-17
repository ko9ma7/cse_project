
public class MultiRemoteLoader {

	public static void main(String[] args) {

		RemoteControl remoteControl = new RemoteControl();
		Light livingRoomLight = new Light();
		LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
		LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
		
		remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);

		remoteControl.isOnButtonPressed(0);
		remoteControl.isOffButtonPressed(0);
		remoteControl.isUndoButtonPressed();
	}

}
