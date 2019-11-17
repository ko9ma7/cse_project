import java.util.Random;
import java.util.Stack;

import javafx.application.Application;
import javafx.event.ActionEvent;
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

// 명령 버튼을 invoker와 같은 역할처럼 하는 방법

interface Command{
	void execute();
	void undo();
}

/**
 * 명령 객체를 유지할 수 있도 버튼 클래스 확장
 * 
 */
class CommandHolderButton extends Button{
	private Command command;
	public CommandHolderButton(String text) {
		super(text);
	}
	public void setCommand(Command command) {
		this.command = command;
	}
	public Command getCommand() {
		return command;
	}
}

/**
 * 2018년도 2학기 객체지향개발론및실습
 * 명령패턴: GUI의 이벤트 처리
 * 일반적인 내용은 2018-Pattern-Command-GUI02 참조
 * 차이점
 * 버튼이 명령 객체를 유지하도록 함
 * 히스토리 스택에는 매번 새로운 객체가 등록되어야 함. 이를 위해 clone을 활용???
 * @author 김상진
 * 
 */
public class CommandPatternButton extends Application {
	private static final int HEIGHT = 500;
	private static final int WIDTH = 500;
	private static final int IMAGESIZE = 80;
	private Button pikachuDoButton = new Button("피카츄");
	private Button pikachuUndoButton = new Button("피카츄 취소");
	private CommandHolderButton bulbasaurDoButton = new CommandHolderButton("이상해");
	private CommandHolderButton charmanderDoButton = new CommandHolderButton("파이리");
	private Button undoButton = new Button("취소");
	private Pane centerPane = new Pane();
	private static final Random randomGen = new Random();
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
	
	private class ImageDrawCommand implements Command, Cloneable{
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
			if(undoImageView!=null) {
				centerPane.getChildren().remove(undoImageView);
			}
		}
		@Override
		public ImageDrawCommand clone() {
			try {
				return (ImageDrawCommand) super.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	// 하나의 처리자에서 모든 노드의 처리할 이벤트를 하나의 코드로 모두 처리할 수 있음
	void doAction(ActionEvent event) {
		Object source = event.getSource();
		/*
		if(source==bulbasaurDrawCommand){
			drawImage(bulbasaur);
		}
		else if(source==charmanderDrawCommand){
			drawImage(charmander);
		}
		*/
		CommandHolderButton holderButton = (CommandHolderButton)source;
		Command command = holderButton.getCommand();
		command.execute();
		undoCommands.push(((ImageDrawCommand)command).clone()); // 매번 새로운 객체와 같은 효과를 나타내기 위해 clone을 사용
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
		
		Command bulbasaurDrawCommand = new ImageDrawCommand(bulbasaur);
		Command charmanderDrawCommand = new ImageDrawCommand(charmander);
		
		pikachuDoButton.setOnAction(e->drawImage(pikachu));
		pikachuUndoButton.setOnAction(e->removeImage());
		bulbasaurDoButton.setCommand(bulbasaurDrawCommand);
		charmanderDoButton.setCommand(charmanderDrawCommand);		
		
		bulbasaurDoButton.setOnAction(e->doAction(e));
		charmanderDoButton.setOnAction(e->doAction(e));
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
