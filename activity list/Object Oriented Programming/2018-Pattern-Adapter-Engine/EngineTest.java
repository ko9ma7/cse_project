
public class EngineTest {
	public static void main(String[] args) {
		ALib alib = new ALib(); // 새로운 엔진
		MyLib mlib = new MyLib(); // 기존 엔진
		PhotoEngine lib = new MyLibAdapter(mlib);
		String[] photoList = { "abc.jpg", "def.jpg" };
		lib.printPhotoList(photoList);
		lib.printPhoto("abc.jpg");
		lib.deletePhoto("abc.jpg");
		lib.addPhoto("aaa.jpg");
	}
}
