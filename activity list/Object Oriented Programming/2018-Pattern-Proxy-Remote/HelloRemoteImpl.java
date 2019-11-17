import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class HelloRemoteImpl extends UnicastRemoteObject implements Hello {

	public HelloRemoteImpl() throws RemoteException {}
	@Override
	public String sayHello() throws RemoteException {
		return "원격에서 전달되는 황홀한 문자열!!!";
	}

}
