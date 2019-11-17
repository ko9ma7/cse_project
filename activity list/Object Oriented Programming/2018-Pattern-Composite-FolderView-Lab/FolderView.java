import java.io.File;
import java.util.Iterator;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

// 함수형 프로그래밍 요소임
@FunctionalInterface
interface FileFilter{
	boolean filter(String fileName);
}

public class FolderView extends Application {
	private Button selectBtn = new Button("폴더 선택");
	private TextArea infoboard = new TextArea();
	private BorderPane mainPane = null;
	private Stage mainStage  = null;
	private TreeView<String> tree; 
	
	// 이 메소드는 트리를 구성한 다음 선택한 폴더를 나타내는 NonLeaf 노드를 반환하여 줌. 즉, 반환된 NonLeaf 노드의 getName() 메소드의
	// 호출 결과와 selectedDirectory의 getName()의 호출 결과 값이 같아야 하며, NonLeaf 노드는 해당 폴더에 있는 모든 폴더와 파일을 자식 노드로 가져와야 함.
	private NonLeaf constructFolderTree(File selectedDirectory){
		NonLeaf folder = new NonLeaf(selectedDirectory.getName());
		File[] list = selectedDirectory.listFiles();
		for(File file: list){
			TreeNode node = file.isDirectory() ? constructFolderTree(file) : new Leaf(file.getName());
			folder.add(node);
		}
		return folder;
	}
	
	// 이 메소드는 주어진 NonLeaf 노드가 루트인 부분 트리를 나타내는 TreeItem을 만들어 줌. 즉, 반환되는 TreeItem은 NonLeaf의 getName()을 값을
	// 가지고 있어야 하며, NonLeaf의 자식 노드들을 자식(마찬가지로 TreeItem 타입)으로 가지고 있어야 함.
	private TreeItem<String> constructTreeItem(NonLeaf currentFolder){
		TreeItem<String> root = new TreeItem<>(currentFolder.getName());
		for(int i = 0; i < currentFolder.numberOfChilds(); i++) {
			TreeItem<String> child;
			// 현재 폴더의 자식노드가 있다면
			if(currentFolder.getChild(i).numberOfChilds() != 0) {
				NonLeaf subFolder = new NonLeaf(currentFolder.getChild(i).getName());
				for(int j = 0; j < currentFolder.getChild(i).numberOfChilds(); j++)
					subFolder.add(currentFolder.getChild(i).getChild(j));
//				System.out.println("자식이 O 폴더 -> " + currentFolder.getChild(i).getName());
				
				// recursive
				child = constructTreeItem(subFolder);
			}
			// 현재 폴더의 자식노드가 없다면
			else {
				child = new TreeItem<>(currentFolder.getChild(i).getName());
			}
			
			root.getChildren().add(child);
//			System.out.println("자식이 X 폴더 -> " + currentFolder.getChild(i).getName());
		}
		/*
		for (int i = 0; i < currentFolder.numberOfChilds(); i++) {
			if (currentFolder.getChild(i) instanceof NonLeaf) {
				constructTreeItem(new NonLeaf(currentFolder.getChild(i).getName()));
			} 
			
			root.getChildren().add(new TreeItem(currentFolder.getChild(i).getName()));
		}
		*/
		return root;
	}

	// 특정 확장자를 가진 파일들만 표시되도록 필터링 기능
	// 이 메소드는 constructTreeItem가 필터링 기능이 추가된다는 것을 제외하고는 동일하다. 주어진 filter에 참인 파일들만 추가되며, 하위 디렉토리에 해당 파일이
	// 전혀 없는 경우에는 폴더자체도 포함되지않아야 한다.
	private TreeItem<String> constructFilteredTreeItem(NonLeaf currentFolder, FileFilter filter) {		
		TreeItem<String> root = new TreeItem<>(currentFolder.getName());
		for (int i = 0; i < currentFolder.numberOfChilds(); i++) {
			// 만약, 필터링할 파일과 이름이 같으면 true이므로 파일 추가
			if (filter.filter(currentFolder.getChild(i).getName())) {
				TreeItem<String> child;
				if (currentFolder.getChild(i).numberOfChilds() != 0) {
					NonLeaf subFolder = new NonLeaf(currentFolder.getChild(i).getName());
					for (int j = 0; j < currentFolder.getChild(i).numberOfChilds(); j++)
						subFolder.add(currentFolder.getChild(i).getChild(j));
					
					// recursive
					child = constructFilteredTreeItem(subFolder, filter);
				} else {
					child = new TreeItem<>(currentFolder.getChild(i).getName());
				}
				
				root.getChildren().add(child);
			}
		}
		return root;
	}
	
	private void directoryList(){
		DirectoryChooser chooser = new DirectoryChooser();
		File selectedDirectory = chooser.showDialog(mainStage);
		if(selectedDirectory==null) return;
		
		NonLeaf currentFolder = constructFolderTree(selectedDirectory);
		currentFolder = currentFolder.getRearranged(); //폴더 내용 정렬 
		
		// 1. 폴더 필터링 안하고 검사
		// TreeItem<String> root = constructTreeItem(currentFolder);
		// 2. 폴더 필터링하고 검사(확장자가 JPG인 경우)
		TreeItem<String> root  = constructFilteredTreeItem(currentFolder, name ->{	
			if (name.contains(".JPG")) return true;
			else return false;
		});
		root.setExpanded(true);
		tree = new TreeView<>(root);
		mainPane.setCenter(tree);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		mainPane = new BorderPane();
		
		HBox buttonPane = new HBox();
		buttonPane.setPadding(new Insets(10));
		buttonPane.setSpacing(10);
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.getChildren().add(selectBtn);
		selectBtn.setOnAction(e->directoryList());
		
		StackPane centerPane = new StackPane();
		centerPane.setPadding(new Insets(10));
		centerPane.getChildren().add(infoboard);
		infoboard.setEditable(false);
		
		mainPane.setCenter(centerPane);
		mainPane.setBottom(buttonPane);
		primaryStage.setTitle("폴더 내용 보여주기");
		primaryStage.setScene(new Scene(mainPane,400,200));
		primaryStage.show();
	}
	public static void main(String[] args){
		Application.launch(args);
	}
}
