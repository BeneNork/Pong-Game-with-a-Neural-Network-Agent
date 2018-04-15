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

public class MvsMElements {
	static MainWindow mw;
	
	static Button start = new Button("Start");
	static Button back = new Button("back");
	static Label leftAgent = new Label("left Agent");
	static Label rightAgent = new Label("right Agent");
	
	static ComboBox left = new ComboBox(MainWindow.agentList);
	static ComboBox right = new ComboBox(MainWindow.agentList);
	
	StackPane stack = new StackPane();
	VBox vb = new VBox();
	BorderPane leftAgentPane = new BorderPane();
	BorderPane rightAgentPane = new BorderPane();
	BorderPane leftComboPane = new BorderPane();
	BorderPane rightComboPane = new BorderPane();
	BorderPane startPane = new BorderPane();
	BorderPane backPane = new BorderPane();
	
	public Scene insert() throws MalformedURLException {
		stack.getChildren().add(MainWindow.background());
		vb.getChildren().add(MainWindow.title());
		
		left.setPrefSize(MainWindow.buttonWidth, MainWindow.buttonHigh);
		right.setPrefSize(MainWindow.buttonWidth, MainWindow.buttonHigh);
		start.setPrefSize(MainWindow.buttonWidth, MainWindow.buttonHigh);
		back.setPrefSize(MainWindow.buttonWidth, MainWindow.buttonHigh);
		leftAgent.setTextFill(Color.web("#99FF00"));
		rightAgent.setTextFill(Color.web("#99FF00"));
		leftAgent.setFont(new Font("Agency FB",40));
		rightAgent.setFont(new Font("Agency FB",40));
		
		leftAgentPane.setCenter(leftAgent);
		rightAgentPane.setCenter(rightAgent);
		leftComboPane.setCenter(left);
		rightComboPane.setCenter(right);
		startPane.setCenter(start);
		backPane.setCenter(back);
		
		vb.getChildren().add(leftAgentPane);
		vb.getChildren().add(leftComboPane);
		vb.getChildren().add(rightAgentPane);
		vb.getChildren().add(rightComboPane);
		vb.getChildren().add(startPane);
		vb.getChildren().add(backPane);
		
		vb.setSpacing(10);
		vb.setPadding(new Insets(15,10, 15,10));
		
		stack.getChildren().add(vb);
		
		Scene scene = new Scene(stack,MainWindow.windowWidth, MainWindow.windowHigh);
		MvsMButtons();
		return scene;
	}
	
	
	public static void MvsMButtons(){
		
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(left.getSelectionModel().getSelectedItem() != null && right.getSelectionModel().getSelectedItem() != null) {
					
					PongGUI pong = new PongGUI(right.getSelectionModel().getSelectedItem().toString(), left.getSelectionModel().getSelectedItem().toString());
				}
			}
		});
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				StartElements se = new StartElements();
				try {
					MainWindow.stage.setScene(se.insert());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MainWindow.stage.show();
			}
		});
	}

}
