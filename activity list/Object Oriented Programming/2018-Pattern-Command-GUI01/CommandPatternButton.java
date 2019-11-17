import java.util.Random;
import java.util.Stack;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

// 실행 메소드를 명령 객체화 시킨 방법

interface Command{
	void execute();
	void undo();
}

/**
 * 2018년도 2학기 객체지향개발론및실습
 * 명령패턴: GUI의 이벤트 처리
 * GUI의 이벤트 처리는 관찰자 패턴에서 노드가 Subject, Event 처리자가 Observer 개념으로 설명한 바 있음
 * GUI의 이벤트 처리는 명령 패턴에서 노드가 invoker가 되고 Event 처리자가 명령 객체로 생각할 수도 있음
 * 기존 처리 방식은 execute와 그것에 대응되는 undo가 하나의 객체로 모델링되지 않으며, 실행 로그를 유지하기 힘듦
 * 하지만 자바8 이후 객체 대신에 함수를 처리자로 등록할 수 있기 때문에 다음과 같은 경우를 제외하고는 명령 패턴을 이용할 필요는 없음
 * 1) 여러 노드가 어떤 이벤트가 발생하였을 때 수행해야 하는 기능이 실행시간에 바뀔 수 있는 경우
 * 2) Undo 기능의 제공이 필요한 경우
 * 3) 실행 이력을 유지해야 하는 경우
 * 하지만 여러 노드가 특정 이벤트 때 동일한 기능을 수행해야 하는 경우에는 동일 함수를 등록하면 되기 때문에 명령 패턴으로 꼭 모델링할
 * 필요는 없음
 * @author 김상진
 * 
 */
public class CommandPatternButton extends Application {
	private static final int HEIGHT = 500;
	private static final int WIDTH = 500;
	private static final int IMAGESIZE = 80;
	private Button pikachuDoButton = new Button("피카츄");
	private Button pikachuUndoButton = new Button("피카츄 취소");
	private Button bulbasaurDoButton = new Button("이상해");
	private Button charmanderDoButton = new Button("파이리");
	private Button undoButton = new Button("취소");
	private Pane centerPane = new Pane();
	private static final Random randomGen = new Random();
	// 명령 객체를 유지하는 것과 최근에 그린 이미지 노드를 유지하는 것과의 차이는?
	private Stack<ImageView> undoPikachuImages = new Stack<>();
	private Stack<Command> undoCommands = new Stack<>();
	
	private ImageView createInstance(Image image) {
		ImageView iView = new ImageView(image);
		iView.setFitWidth(100);
		iView.setPreserveRatio(true);
		int x = randomGen.nextInt(500);
		int y = randomGen.nextInt(500);
		if(x+IMAGESIZE>=WIDTH) x -= IMAGESIZE;
		if(y+IMAGESIZE>=HEIGHT) y -= IMAGESIZE;
		iView.setLayoutX(x);
		iView.setLayoutY(y);
		return iView;
	}
	
	private void drawImage(Image image) {
		ImageView iView = createInstance(image);
		undoPikachuImages.push(iView);
		centerPane.getChildren().add(iView);
	}
	private void removeImage() {
		if(!undoPikachuImages.isEmpty()) {
			ImageView iView = undoPikachuImages.pop();
			centerPane.getChildren().remove(iView);
		}
	}
	
	// drawImage, removeImage를 명령 패턴의 execute와 undo로 구현
	private class ImageDrawCommand implements Command{
		private Image image;
		private ImageView undoImageView = null;
		public ImageDrawCommand(Image image) {
			this.image = image;
		}
		@Override
		public void execute() {
			undoImageView = createInstance(image);
			centerPane.getChildren().add(undoImageView);
		}

		@Override
		public void undo() {
			if(undoImageView!=null)
				centerPane.getChildren().remove(undoImageView);
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane mainPane = new BorderPane();
		HBox buttonPane = new HBox();
		buttonPane.setPadding(new Insets(10));
		buttonPane.setSpacing(10);
		buttonPane.getChildren().addAll(pikachuDoButton, pikachuUndoButton,
				bulbasaurDoButton, charmanderDoButton, undoButton);
		
		Image pikachu = new Image("pikachu.png");
		Image bulbasaur = new Image("bulbasaur.png");
		Image charmander = new Image("charmander.png");
		
		pikachuDoButton.setOnAction(e->drawImage(pikachu));
		pikachuUndoButton.setOnAction(e->removeImage());
		
		bulbasaurDoButton.setOnAction(e->{
			Command command = new ImageDrawCommand(bulbasaur); // 매번 새로운 객체를 만들어야 함
			undoCommands.push(command); // undo를 하게 될때 각기 다른 객체이어야지 아니면 모두 같은 객체를 가리키게되어 undo가 먹히지 않는다
			command.execute();
		});
		charmanderDoButton.setOnAction(e->{
			Command command = new ImageDrawCommand(charmander);
			undoCommands.push(command);
			command.execute();
		});
		undoButton.setOnAction(e->{
			if(!undoCommands.isEmpty()) {
				Command command = undoCommands.pop();
				command.undo();
			}
		});
		
		Rectangle rectangle = new Rectangle(0, 0, WIDTH, HEIGHT);
		rectangle.setFill(Color.WHITE);
		centerPane.getChildren().add(rectangle);
		mainPane.setCenter(centerPane);
		mainPane.setBottom(buttonPane);
		primaryStage.setTitle("Command Pattern Example");
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
