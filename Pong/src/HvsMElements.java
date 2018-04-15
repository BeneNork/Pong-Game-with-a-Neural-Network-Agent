import java.io.IOException;
import java.net.MalformedURLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HvsMElements {

	static Button start = new Button("Start");
	static Button back = new Button("back");
	static Label agent = new Label("Agent:");
	
	static ComboBox combo = new ComboBox(MainWindow.agentList);
	
	StackPane stack = new StackPane();
	VBox vb = new VBox();
	BorderPane agentPane = new BorderPane();
	BorderPane comboPane = new BorderPane();
	BorderPane startPane = new BorderPane();
	BorderPane backPane = new BorderPane();
	
	public Scene insert() throws MalformedURLException {
		stack.getChildren().add(MainWindow.background());
		vb.getChildren().add(MainWindow.title());
		
		combo.setPrefSize(MainWindow.buttonWidth, MainWindow.buttonHigh);
		start.setPrefSize(MainWindow.buttonWidth, MainWindow.buttonHigh);
		back.setPrefSize(MainWindow.buttonWidth, MainWindow.buttonHigh);
		agent.setTextFill(Color.web("#99FF00"));
		agent.setFont(new Font("Agency FB",40));
		
		agentPane.setCenter(agent);
		comboPane.setCenter(combo);
		startPane.setCenter(start);
		backPane.setCenter(back);
		
		vb.getChildren().add(agentPane);
		vb.getChildren().add(comboPane);
		vb.getChildren().add(startPane);
		vb.getChildren().add(backPane);
		
		vb.setSpacing(10);
		vb.setPadding(new Insets(15,10, 15,10));
		
		stack.getChildren().add(vb);
		
		
		Scene scene = new Scene(stack,MainWindow.windowWidth, MainWindow.windowHigh);
		HvsMButtons();
		return scene;
	}
	
	
	public static void HvsMButtons(){
		StartElements se = new StartElements();
		
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(combo.getSelectionModel().getSelectedItem() != null) {
					
					PongGUI pong = new PongGUI(combo.getSelectionModel().getSelectedItem().toString());
				}
			}
		});
		

		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					MainWindow.stage.setScene(se.insert());
				} catch (IOException e) {
					e.printStackTrace();
				}
				MainWindow.stage.show();
			}
		});
	}

}
