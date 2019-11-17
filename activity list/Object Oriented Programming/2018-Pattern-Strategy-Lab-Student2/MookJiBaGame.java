import java.util.Optional;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application; // JavaFX 프로그램을 만들려면 Application 라이브러리를 상속받아야 함
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기 전략패턴 실습
 * MookJiBaGame
 * 컴퓨터와 사용자 간에 묵찌바 게임을 하는 JavaFX 기반 GUI 프로그램
 * @author 김상진
 *
 */
public class MookJiBaGame extends Application{
	// GUI 노드
	private RadioButton mookButton = new RadioButton("묵");
	private RadioButton jiButton = new RadioButton("찌");
	private RadioButton BaButton = new RadioButton("빠");
	private Button doneButton = new Button("선택");
	private ImageView userView = new ImageView();
	private ImageView compView = new ImageView();
	private TextField gameStatus = new TextField();
	// 게임에 필요한 멤버
	private Player computer = new ComputerPlayer();
	private Player user = new UserPlayer();
	private HandType userHand;
	private HandType compHand;
	private boolean isGameStarted = false;
	private boolean isUserAttack = false;
	
	// 게임 종료 때 사용하는 Dialog (다시할지 끝낼지)
	private boolean confirmDialog(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("묵찌빠 게임");
    	alert.setHeaderText(null);
    	alert.setContentText("새 게임???");
    	ButtonType buttonTypeOK = new ButtonType("확인", ButtonData.OK_DONE);
    	ButtonType buttonTypeCancel = new ButtonType("취소", ButtonData.CANCEL_CLOSE);
    	alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
    	ImageView icon = new ImageView(new Image("가위바위보.jpeg"));
		icon.setFitHeight(80);
		icon.setPreserveRatio(true);
		alert.setGraphic(icon);
    	Optional<ButtonType> result = alert.showAndWait();
    	return (result.get() == buttonTypeOK);
	}
	
	// 사용자 선택에 따라 사용자 묵찌바 이미지를 바꾸어줌
	private void changeUserImage() {
		if(mookButton.isSelected()) userHand = HandType.MOOK;
		else if(jiButton.isSelected()) userHand = HandType.JI;
		else userHand = HandType.BA;
		userView.setImage(userHand.getImage());
	}
	//  컴퓨터 순서 처리
	private void computerTurn() {
		compHand = computer.nextHand();
		// 약간의 애니메이션
		// setOnFished에서 하는 것이 핵심
		SequentialTransition handEffect = new SequentialTransition();
		PauseTransition mookEffect = new PauseTransition(Duration.millis(150));
		mookEffect.setOnFinished(event->compView.setImage(HandType.MOOK.getImage()));
		PauseTransition jiEffect = new PauseTransition(Duration.millis(150));
		jiEffect.setOnFinished(event->compView.setImage(HandType.JI.getImage()));
		PauseTransition baEffect = new PauseTransition(Duration.millis(150));
		baEffect.setOnFinished(event->compView.setImage(HandType.BA.getImage()));
		handEffect.getChildren().addAll(mookEffect,jiEffect,baEffect);
		handEffect.setCycleCount(4); // 기본적으로 4번은 시뮬레이션을 돈다
		handEffect.setOnFinished(e->compView.setImage(compHand.getImage()));
		handEffect.play();
	}
	// 매 라운드 처리
	private void nextTurn() {
		user.setHand(userHand);
		computerTurn();
		// 컴퓨터 애니메이션이 종료될 때까지 기다리기 위한 요소
		PauseTransition delay = new PauseTransition(Duration.millis(2000));
		delay.setOnFinished(e->{
			if(isGameStarted) playMookJiBa();
			else playGawiBawiBo();
		});
		delay.play();
	}
	private void playMookJiBa() {
		if(userHand==compHand) {
			if(isUserAttack) gameStatus.setText("사용자 승");
			else gameStatus.setText("컴퓨터 승");
			Platform.runLater(new Runnable() {
	            @Override
	            public void run() {
	            	boolean doExit = confirmDialog();
	    			if(!doExit) Platform.exit();
	    			isGameStarted = false;
	    			gameStatus.setText("먼저 가위바위보를 하세요!!!");
	            }
	        });	
		}
		else{
			isUserAttack = compHand.winValueOf()==userHand;
			if(isUserAttack) gameStatus.setText("사용자 공격 차례");
			else gameStatus.setText("컴퓨터 공격 차례");
		}
	}
	private void playGawiBawiBo() {
		if(userHand==compHand) {
			gameStatus.setText("비김: 가위바위보를 다시");
			return;
		}
		isUserAttack = compHand.winValueOf()==userHand; // 컴퓨터를 이긴 손이 사용자 손인지 아닌지
		if(isUserAttack) gameStatus.setText("사용자 승: 사용자 공격 차례");
		else gameStatus.setText("컴퓨터 승: 컴퓨터 공격 차례");
		isGameStarted = true;
	}
	@Override // GUI의 기본 순서는 Stage -> Scene -> Pane -> Node 순이다
	public void start(Stage primaryStage) throws Exception { // 내가 보여줄 GUI 화면을 구성해주는 메소드
		BorderPane mainPane = new BorderPane(); // 배치관리자(화면을 구성하는 GUI들을 배치시키는 역할) - 동, 서, 남, 북, 중앙
		gameStatus.setEditable(false);
		gameStatus.setText("먼저 가위바위보를 하세요!!!");
		userView.setImage(HandType.MOOK.getImage());
		userView.setFitWidth(220);
		userView.setPreserveRatio(true);
		compView.setImage(HandType.MOOK.getImage());
		compView.setFitWidth(220);
		compView.setPreserveRatio(true);
		GridPane gamePane = new GridPane(); // 배치관리자의 중앙 부분을 반으로 나누기 위해서 사용(사용자 게임 이미지, 컴퓨터 게임 이미지)
		gamePane.setPadding(new Insets(10));
		gamePane.setHgap(10);
		gamePane.setVgap(10);
		gamePane.add(new Label("사용자"), 0, 0);
		gamePane.add(new Label("컴퓨터"), 1, 0);
		gamePane.add(userView, 0, 1);
		gamePane.add(compView, 1, 1);
		HBox buttonPane = new HBox();
		buttonPane.setPadding(new Insets(10));
		buttonPane.setSpacing(10);
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.getChildren().addAll(mookButton, jiButton, BaButton, doneButton);
		ToggleGroup userChoiceGroup = new ToggleGroup(); // RadioButton을 그룹으로 묵어줌
		userChoiceGroup.getToggles().addAll(mookButton, jiButton, BaButton);
		mookButton.setOnAction(e->changeUserImage()); // 묵 버튼을 누르면 묵 이미지로
		jiButton.setOnAction(e->changeUserImage()); // 찌 버튼을 누르면 찌 이미지로
		BaButton.setOnAction(e->changeUserImage()); // 빠 버튼을 누르면 빠 이미지로
		doneButton.setOnAction(e->nextTurn());
		mookButton.setSelected(true);
		userHand = HandType.MOOK;
		mainPane.setTop(gameStatus);
		mainPane.setCenter(gamePane);
		mainPane.setBottom(buttonPane);
		
		primaryStage.setTitle("묵찌빠 게임");
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.show();
		doneButton.requestFocus();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
