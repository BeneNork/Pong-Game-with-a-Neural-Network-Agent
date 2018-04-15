import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author
 *
 */

public class MainWindow extends Application{
	static ObservableList<String> agentList;
	static MainElements me = new MainElements();
	static Stage stage = new Stage();
	
	static int windowWidth = 1280;
	static int windowHigh = 720;
	static int buttonWidth = 500;
	static int buttonHigh = 50;

	public static void main(String[] args) throws IOException {
		agentList = loadList();
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		try {		
			this.stage.setTitle("Pong");
			this.stage.setScene(me.insert());
			this.stage.show();
			
		} catch (Exception e) {
			
		}
	}
	
	public static ObservableList<String> loadList() throws IOException{
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add("JarJar Binks");
		File file = new File("agents.agent");
		if(file.exists()) {
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String line;
				
				while(true) {
					if((line = br.readLine()) != null) {
						list.add(line);
					} else {
						break;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public static BorderPane title() {
		BorderPane title = new BorderPane();
		title.setMinSize(1280, 300);
		
		Reflection reflection = new Reflection();
		reflection.setBottomOpacity(0.0);
		reflection.setTopOpacity(0.5);
		reflection.setTopOffset(10);
		reflection.setFraction(0.7);
		Text text = new Text();
	    text.setX(20.0);
	    text.setY(90.0);
	    text.setCache(true);
	    text.setText("Pong");
	    text.setFill(Color.web("0x99FF00"));
	    text.setFont(Font.font(null, FontWeight.BOLD, 80));
	    
	    text.setEffect(reflection);
	    title.setCenter(text);
	    
	    return title;
	}
	
	public static ImageView background() throws MalformedURLException {
		File file = new File("pong2.jpg");
		String localUrl = file.toURI().toURL().toString();
		Image image = new Image(localUrl);
		ImageView imageView = new ImageView(image);
		
		return imageView;
	}
}