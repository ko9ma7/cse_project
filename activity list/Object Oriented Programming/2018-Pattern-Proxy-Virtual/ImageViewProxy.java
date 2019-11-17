import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ImageViewProxy extends ImageView {
	private Image progressImage = new Image("progress.gif");
	private Image actualImage = null;
	public void setImage(String urlString, Stage stage){
		setImage(progressImage);
		Thread retrievalThread = new Thread(new Runnable(){ // 스레드로 돌림
			public void run(){ // 처음 이미지는 progress 이미지로 대체됨 -> 실제 이미지로 바꿔줌
				try{
					URL url = new URL(urlString);
					URLConnection con = url.openConnection();
			        con.setConnectTimeout(1000); // 1초동안에 이미지를 불러오지 못하면 안됨
			        con.setReadTimeout(1000);
			        InputStream in = con.getInputStream();
					actualImage = new Image(in);
				}
				catch(Exception e){
					actualImage = new Image("sorry.jpg");
				}
				setImage(actualImage);
				stage.setHeight(actualImage.getHeight());
				stage.setWidth(actualImage.getWidth());
			}
		});
		retrievalThread.start();
	}
}
