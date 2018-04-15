import java.io.IOException;
import java.net.MalformedURLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class StartElements {
	static PongGUI pong;
	
	static Button HvM = new Button("H vs. M");
	static Button MvM = new Button("M vs. M");
	static Button back = new Button("back");
	
	StackPane stack = new StackPane();
	VBox vb = new VBox();
	BorderPane HvMPane = new BorderPane();
	BorderPane MvMPane = new BorderPane();
	BorderPane backPane = new BorderPane();
	
	public Scene insert() throws MalformedURLException{

		stack.getChildren().add(MainWindow.background());
		
	    vb.getChildren().addAll(MainWindow.title());
	    
	    HvM.setPrefSize(MainWindow.buttonWidth, MainWindow.buttonHigh);
		MvM.setPrefSize(MainWindow.buttonWidth, MainWindow.buttonHigh);
		back.setPrefSize(MainWindow.buttonWidth, MainWindow.buttonHigh);
		
		HvMPane.setCenter(HvM);
		MvMPane.setCenter(MvM);
		backPane.setCenter(back);
		vb.getChildren().add(HvMPane);
		vb.getChildren().add(MvMPane);
		vb.getChildren().add(backPane);
		vb.setSpacing(10);
		vb.setPadding(new Insets(15,10, 15,10));
		
		stack.getChildren().add(vb);
		
		Scene scene = new Scene(stack, MainWindow.windowWidth, MainWindow.windowHigh);
		startButtons();
		return scene;
	}
	
	public static void startButtons(){		
		
		HvM.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				HvsMElements hvsm = new HvsMElements();
				try {
					MainWindow.stage.setScene(hvsm.insert());
				} catch (IOException e) {
					e.printStackTrace();
				}
				MainWindow.stage.show();
				
			}
		});
		
		MvM.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MvsMElements mvm = new MvsMElements();
				try {
					MainWindow.stage.setScene(mvm.insert());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				MainWindow.stage.show();
			}
		});
		
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MainElements me = new MainElements();
				try {
					MainWindow.stage.setScene(me.insert());
				} catch (MalformedURLException e) {
				}
				MainWindow.stage.show();
			}
		});
	}

}
