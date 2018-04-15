import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AgentElements {
	
	static Button newAgent = new Button("new Agent");
	static Button deleteAgent = new Button("delete Agent");
	static Button back = new Button("back");
	
	static TextField agentName = new TextField();
	static ToggleGroup group = new ToggleGroup();
	static RadioButton slow = new RadioButton("slow");
	static RadioButton fast = new RadioButton("fast");
	static Button create = new Button("create");
	
	static ListView<String> list = new ListView<String>(MainWindow.agentList);
	
	HBox hb = new HBox();
	static VBox vb = new VBox();
	
	public Scene insert() {
		vb.setSpacing(10);
		vb.setPadding(new Insets(15,10, 15,10));
		hb.setPadding(new Insets(15,10, 15,10));
		hb.setStyle("-fx-background-color: #000000;");
		newAgent.setPrefSize(MainWindow.buttonWidth, MainWindow.buttonHigh);
		deleteAgent.setPrefSize(MainWindow.buttonWidth, MainWindow.buttonHigh);
		back.setPrefSize(MainWindow.buttonWidth, MainWindow.buttonHigh);

		
		if(vb.getChildren().isEmpty()) {
			vb.getChildren().add(newAgent);
			vb.getChildren().add(deleteAgent);
			vb.getChildren().add(back);
		}

		hb.getChildren().add(list);
		hb.getChildren().add(vb);
		
		agentButtons();
		Scene scene = new Scene(hb, MainWindow.windowWidth, MainWindow.windowHigh);
		return scene;
		
	}
	
	public static void agentButtons() {

		newAgent.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				create.setPrefSize(MainWindow.buttonWidth, MainWindow.buttonHigh);
				VBox vb = new VBox();
				vb.setSpacing(10);
				vb.setPadding(new Insets(15,10, 15,0));
				vb.getChildren().add(agentName);
				slow.setToggleGroup(group);
				fast.setToggleGroup(group);
				slow.setSelected(true);
				vb.getChildren().add(slow);
				vb.getChildren().add(fast);
				vb.getChildren().add(create);
				
				AgentElements.vb.getChildren().add(vb);
				
				
			}
		});
		
		deleteAgent.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String agent = list.getSelectionModel().getSelectedItem();
				int index = list.getSelectionModel().getSelectedIndex();
				
				if(index != -1) {
					MainWindow.agentList.remove(index);
					
					File file = new File("Agents.agent");
					try {
						FileWriter fr = new FileWriter(file);
						BufferedWriter bf = new BufferedWriter(fr);
						
						
						for(int i=0;i<MainWindow.agentList.size();i++) {
							bf.write(MainWindow.agentList.get(i));
						}
						
						
					} catch (IOException e) {
						
					}
				}
			}
		});
		
		create.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				File file = new File("agents.agent");
				File agentFiles;
				String nameAgent;
				
				try {
					FileWriter fr = new FileWriter(file, true);
					BufferedWriter bf = new BufferedWriter(fr);
					String dif = group.getSelectedToggle().toString();
					if(dif.contains("slow")) {
						nameAgent = agentName.getText() + " (slow)";
						bf.write(nameAgent);
					} else {
						nameAgent = agentName.getText() + " (fast)";
						bf.write(nameAgent);
					}
					agentFiles = new File(nameAgent + ".agent");
					agentFiles.createNewFile();
					agentFiles = new File(nameAgent + ".diagram");
					agentFiles.createNewFile();
					
					bf.newLine();
					bf.close();
					MainWindow.agentList.add(nameAgent);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				agentName.clear();
				AgentElements.vb.getChildren().remove(AgentElements.vb.getChildren().size()-1);
					
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
