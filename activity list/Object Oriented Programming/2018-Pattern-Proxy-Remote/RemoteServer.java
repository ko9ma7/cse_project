import java.rmi.Naming;


public class RemoteServer {
	public static void main(String[] args) {
		try{
			Hello remoteObject = new HelloRemoteImpl();
			Naming.rebind("RemoteHello", remoteObject);
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}
