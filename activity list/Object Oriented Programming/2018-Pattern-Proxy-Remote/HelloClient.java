import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Random;


public class HelloClient {
	public static Hello getRemoteOrLocalObject() {
		Random randomGen = new Random();
		Hello service = null;
		if(randomGen.nextInt(2)==0) {
			try{
				service = 
					//(Hello)Naming.lookup("rmi://127.0.0.1/RemoteHello");
					(Hello)Naming.lookup("rmi://220.68.82.25:11099/RemoteHello");
				
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		else service = new HelloLocalImpl();
		return service;
	}
	public static void main(String[] args) throws RemoteException {
		for(int i=0; i<5; i++) {
			Hello object = getRemoteOrLocalObject();
			String s = object.sayHello();
			System.out.println(s);
		}
	}

}
