import java.util.Arrays;

// 객체 어댑터 클래스
public class MyLibAdapter implements PhotoEngine {
	MyLib lib; // Target class륾 멤버변수로(HAS-A)
	public MyLibAdapter(MyLib lib) {
		this.lib = lib;
	}
	public void printPhoto(String filename) {
		lib.printPhoto(filename);
	}
	public void printPhotoList(String[] listname) {
		Arrays.asList(listname).stream().forEach(x->System.out.println("MyLib Engine 가동 - " + x + "사진이 리스트에 있습니다."));
	}
	public void deletePhoto(String filename) {
		lib.deletePhoto(filename);
	}
	public void addPhoto(String filename) {
		lib.addPhoto(filename);
	}
}