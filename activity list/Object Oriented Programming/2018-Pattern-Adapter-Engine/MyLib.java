import java.util.Arrays;

// 기존 엔진 - Adaptee class
public class MyLib {
	public void printPhoto(String filename) { System.out.printf("MyLib Engine 가동 - %s 사진을 출력합니다.\n", filename); }
	public void printPhotoList(String[] listname) {
		Arrays.asList(listname).stream().forEach(x->System.out.println("MyLib Engine 가동 - " + x + "사진이 리스트에 있습니다."));
	}
	public void deletePhoto(String filename) { System.out.printf("MyLib Engine 가동 - %s 사진을 삭제합니다\n", filename); }
	public void addPhoto(String filename) { System.out.printf("MyLib Engine 가동 - %s 사진을 추가합니다.\n", filename); }
}

// A와 기존 엔진의 기능은 같지만 성능이 다르다고 가정 -> A 엔진의 성능이 더 좋아서 교체해야됨