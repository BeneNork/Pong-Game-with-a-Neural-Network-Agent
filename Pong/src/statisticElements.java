import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class statisticElements {
	static ComboBox combo;
	static Button back = new Button("back");
	static Button show = new Button("show");
	static VBox root = new VBox();
	
//	static ArrayList scoreList = new ArrayList();
	
	public Scene insert(){
		back.setPrefSize(250, 30);
		
		combo = new ComboBox(MainWindow.agentList);
		combo.setPrefSize(250, 30);
		
		HBox top = new HBox();
		top.getChildren().add(combo);
		top.getChildren().add(show);
		top.getChildren().add(back);
		
		
		root.setStyle("-fx-background-color: #000000;");
		root.getChildren().add(top);
		
		Scene scene = new Scene(root,MainWindow.windowWidth, MainWindow.windowHigh);
		backButton();
		return scene;
	}
	
	public static void backButton(){
		
		show.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(combo.getSelectionModel().getSelectedItem() != null) {
					String name = combo.getSelectionModel().getSelectedItem().toString();

					try {
						XYChart.Series<Number, Number> agent = new XYChart.Series<Number, Number>();
						agent.setName(name);
						
						File file = new File(name + ".diagram");
						FileReader fr = new FileReader(file);
						BufferedReader br = new BufferedReader(fr);
						String line;
						int count = 0;
						
						while((line = br.readLine()) != null) {
							//////////OOOOPTIIIII////////////
							count+= 10;
							////////MOOOOPTIIII////////////
					        agent.getData().add(new XYChart.Data<Number, Number>(count, Integer.parseInt(line)));
						}
						
						//////////OOOOPTIIIII////////////
						final NumberAxis xAxis = new NumberAxis(10, count, 10);
				        final NumberAxis yAxis = new NumberAxis(0, 35, 5);
				        ////////MOOOOPTIIII////////////
				        final AreaChart<Number, Number> areaChart = new AreaChart<Number, Number>(xAxis, yAxis);
				        
				        
				        areaChart.setLegendSide(Side.BOTTOM);
				        areaChart.setCreateSymbols(false);
				        
				        root.getChildren().add(areaChart);
				        areaChart.getData().addAll(agent);
				        MainWindow.stage.show();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MainElements me = new MainElements();
				// TODO Auto-generated method stub
				try {
					MainWindow.stage.setScene(me.insert());
					MainWindow.stage.show();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}