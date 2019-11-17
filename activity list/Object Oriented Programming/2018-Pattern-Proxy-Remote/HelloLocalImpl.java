import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class HelloLocalImpl implements Hello {

	public HelloLocalImpl(){}
	@Override
	public String sayHello() throws RemoteException {
		return "로컬에서 전달되는 황홀한 문자열!!!";
	}

}
