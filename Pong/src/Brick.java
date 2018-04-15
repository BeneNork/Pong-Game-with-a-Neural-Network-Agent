import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick {
	static Ball ball = new Ball();
	
	Rectangle brick = new Rectangle();
	
	public Brick(int x, int y){
		brick.setX(x);
		brick.setY(y);
		brick.setWidth(20);
		brick.setHeight(100);
		brick.setFill(Color.rgb(153, 255, 00));
	}
	
	public static void angle(double brickUpper, double ballPosY){
		double sector = ballPosY - brickUpper;
		
		if(sector >= 0 && sector <= 10){
			ball.speedY = -16;
		} else if(sector >= 11 && sector <= 20){
			ball.speedY = -13;
		} else if(sector >= 21 && sector <= 30){
			ball.speedY = -10;
		} else if(sector >= 31 && sector <= 40){
			ball.speedY = -6;
		} else if(sector >= 41 && sector <= 50){
			ball.speedY = -3;
		} else if(sector >= 51 && sector <= 60){
			ball.speedY = 3;
		} else if(sector >= 61 && sector <= 70){
			ball.speedY = 6;
		} else if(sector >= 71 && sector <= 80){
			ball.speedY = 10;
		} else if(sector >= 81 && sector <= 90){
			ball.speedY = 13;
		} else if(sector >= 91 && sector <= 100){
			ball.speedY = 16;
		}
	}
}
