import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PongGUI {
	Scene scene;
	Group root = new Group();
	static Ball ball = new Ball();
	static Brick leftBrick = new Brick(180, 310);
	static Brick rightBrick = new Brick(1100, 310);
	static Label pressSpace = new Label("Press Space to Start");
	static int speedBrick = 10;
	static Integer counterLeft = 0;
	static Integer counterRight = 0;
	static Label pointLeft = new Label(counterLeft.toString());
	static Label pointRight = new Label(counterRight.toString());
 
	static boolean player;
	static Skywalker agent_01;
	static Skywalker agent_02;
	
	public PongGUI(String chosenAgent_01) {
		agent_01 = new Skywalker(chosenAgent_01);
		player = true;
		MainWindow.stage.setScene(insert());
		MainWindow.stage.show();
	}
	
	public PongGUI(String chosenAgent_01, String chosenAgent_02) {
		agent_01 = new Skywalker(chosenAgent_01);
		agent_02 = new Skywalker(chosenAgent_02);
		player = false;
		MainWindow.stage.setScene(insert());
		MainWindow.stage.show();
	}
	
	public Scene insert(){
		pointLeft.setTextFill(Color.web("#99FF00"));
		pointLeft.setFont(new Font("Agency FB",200));
		pointLeft.setLayoutX(10);
		pointLeft.setLayoutY(240);
		
		pointRight.setTextFill(Color.web("#99FF00"));
		pointRight.setFont(new Font("Agency FB",200));
		pointRight.setLayoutX(1185);
		pointRight.setLayoutY(240);
		
		pressSpace.setLayoutX(575);
		pressSpace.setLayoutY(100);
		pressSpace.setTextFill(Color.web("#99FF00"));
		root.getChildren().add(pressSpace);
		root.getChildren().add(pointLeft);
		root.getChildren().add(pointRight);
		root.getChildren().add(leftBrick.brick);
		root.getChildren().add(rightBrick.brick);
		root.getChildren().add(ball.ball);
		scene = new Scene(root, 1280, 720, Color.BLACK);
		pongButtons();
		return scene;
	}

	static boolean boolW = false;
	static boolean boolS = false;
	
//	public void upDownButtons() {
//		
//		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//			@Override
//			public void handle(KeyEvent event) {
//				// TODO Auto-generated method stub
//				KeyCode let = event.getCode();
//				if(let == KeyCode.W) {
//					boolW = true;
//					double y = leftBrick.brick.getY();
//						ball.speedBrick = speedBrick * (-1);					
//				} else if(let == KeyCode.S){
//					boolS = true;
//					double y = leftBrick.brick.getY();
//						ball.speedBrick = speedBrick;
//				} 
//			}
//		});
//		
//		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
//			@Override
//			public void handle(KeyEvent event) {
//				KeyCode let = event.getCode();
//				
//				if(let == KeyCode.W) {
//					boolW = false;
//				}
//				if(let == KeyCode.S) {
//					boolS = false;
//				}
//				if(!(boolW || boolS)) {
//					ball.speedBrick = 0;
//				}
//			}	
//		});
//	}
	
	
	public void pongButtons(){
		MainWindow mw = new MainWindow();
		StartElements se = new StartElements();
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				
				if(player) {
					KeyCode let = event.getCode();
					if(let == KeyCode.W) {
						boolW = true;
						double y = leftBrick.brick.getY();
							ball.speedBrick = speedBrick * (-1);					
					} else if(let == KeyCode.S){
						boolS = true;
						double y = leftBrick.brick.getY();
							ball.speedBrick = speedBrick;
					} 
				}
				
				if(event.getCode() == KeyCode.SPACE){
					if(ball.move == false){
						ball.move = true;
						root.getChildren().remove(pressSpace);
						ball.start();
					}
				}
				
				if(event.getCode() == KeyCode.ESCAPE){
					try {
						ball.move = false;
						ball.animation.stop();
						mw.stage.setScene(se.insert());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					mw.stage.show();
				}
			}
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				KeyCode let = event.getCode();
				
				if(let == KeyCode.W) {
					boolW = false;
				}
				if(let == KeyCode.S) {
					boolS = false;
				}
				if(!(boolW || boolS)) {
					ball.speedBrick = 0;
				}
			}	
		});
	}
}
