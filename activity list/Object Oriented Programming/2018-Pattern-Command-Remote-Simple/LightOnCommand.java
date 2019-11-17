// ConcreteCommand 클래스
public class LightOnCommand implements Command {

	Light light; // Light 객체는 receiver 객체
	
	public LightOnCommand(Light light) {
		this.light = light;
	}
	
	@Override
	public void execute() {
		light.on();
	}

}
