import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ShowImage extends Application {
	private String imageURL = "";
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		while(true) {
			TextInputDialog getURLDialog = new TextInputDialog();
			getURLDialog.setTitle("이미지 주소 입력창");
			getURLDialog.setHeaderText("웹 이미지 주소를 입력하여 주십시오.");
			Optional<String> userInput = getURLDialog.showAndWait();
			if(userInput.isPresent()){
				imageURL = userInput.get();
				try {
				    URL url = new URL(imageURL);
				    URLConnection conn = url.openConnection();
				    conn.connect();
				    break;
				} 
				catch (Exception e) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Exception Message");
					alert.setHeaderText(null);
					alert.setContentText(e.getMessage());
					alert.showAndWait();
				}
			}
		}
		
		Pane pane = new Pane();
		//Image img = new Image(imageURL);
		ImageViewProxy iView = new ImageViewProxy();
		pane.getChildren().add(iView);
		
		primaryStage.setTitle("Virtual Proxy Pattern App");
		primaryStage.setScene(new Scene(pane));
		primaryStage.show();
		iView.setImage(imageURL,primaryStage);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
