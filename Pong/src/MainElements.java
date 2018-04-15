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

public class MainElements {
	StackPane stack = new StackPane();
	VBox buttonPane = new VBox();
	
	static Button start = new Button("Start");
	static Button agents = new Button("Agents");
	static Button statistics = new Button("Statistics");
	static Button exit = new Button("Exit");
	
	/**
	 * 
	 * @return
	 * @throws MalformedURLException
	 */
	public Scene insert() throws MalformedURLException{
		
		buttonPane.setSpacing(10);
		buttonPane.setPadding(new Insets(15,10, 15,10));
		
		BorderPane startButtonPane = new BorderPane();
		BorderPane agentsButtonPane = new BorderPane();
		BorderPane statisticsButtonPane = new BorderPane();
		BorderPane exitButtonPane = new BorderPane();
		
	    buttonPane.getChildren().addAll(MainWindow.title());
	    
		start.setPrefSize(MainWindow.buttonWidth, MainWindow.buttonHigh);
		agents.setPrefSize(MainWindow.buttonWidth, MainWindow.buttonHigh);
		statistics.setPrefSize(MainWindow.buttonWidth, MainWindow.buttonHigh);
		exit.setPrefSize(MainWindow.buttonWidth, MainWindow.buttonHigh);
		
		startButtonPane.setCenter(start);
		agentsButtonPane.setCenter(agents);
		statisticsButtonPane.setCenter(statistics);
		exitButtonPane.setCenter(exit);
		buttonPane.getChildren().add(startButtonPane);
		buttonPane.getChildren().add(agentsButtonPane);
		buttonPane.getChildren().add(statisticsButtonPane);
		buttonPane.getChildren().add(exitButtonPane);
		
		stack.getChildren().add(MainWindow.background());
		stack.getChildren().add(buttonPane);
		
		Scene scene = new Scene(stack,MainWindow.windowWidth,MainWindow.windowHigh);
		MainButtons();
		return scene;
	}
	
	/**
	 * 
	 */
	public static void MainButtons(){
		
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				StartElements se = new StartElements();
				try {
					MainWindow.stage.setScene(se.insert());
				} catch (IOException e) {
					e.printStackTrace();
				}
				MainWindow.stage.show();
				
			}
		});
		
		agents.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				AgentElements ae = new AgentElements();
				MainWindow.stage.setScene(ae.insert());
				MainWindow.stage.show();
				
			}
		});
		
		statistics.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				statisticElements se = new statisticElements();
				MainWindow.stage.setScene(se.insert());
				MainWindow.stage.show();
				
			}
		});
		
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MainWindow.stage.close();
				
			}
		});
	}
}