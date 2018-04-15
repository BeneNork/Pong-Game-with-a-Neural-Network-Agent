import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
	static Brick brick;
	static JarJarBinks jarJarBinks = new JarJarBinks();
	static PongGUI pong;
	static Circle ball = new Circle();
	static AnimationTimer animation;
	static int startX = 640;
	static int startY = 360;
	static int radius = 10;
	static boolean move = false;
	static int speed = 10;
	static int speedX = 0;
	static int speedY = 0;
	static int speedBrick = 0;
	static int agent_01_Move;
	static int agent_02_Move;
	static double agent_01_BrickY;
	static double agent_02_BrickY;

	public Ball() {
		ball.setRadius(radius);
		ball.setCenterX(startX);
		ball.setCenterY(startY);
		ball.setFill(Color.rgb(153, 255, 00));
	}

	public static void start(){
		
		int direction = (int)(Math.random()*2);

		if(direction == 0){
			speedX = speed * (-1);
			speedY = (int)(Math.random()*21 +10)-20;
		} else {
			speedX = speed;
			speedY = (int)(Math.random()*21 +10)-20;
		}
		
		animation = new AnimationTimer() {
			@Override
			public void handle(long now) {
				
				if(ball.getCenterX() >= 200 && ball.getCenterX() <= 1100){
					if(ball.getCenterY() > 10 && ball.getCenterY() < 710){

						if(pong.player) {
							double brickY = pong.leftBrick.brick.getY();
							
							if(brickY <= 0 || brickY >= 620) {
								speedBrick = 0;
								if(brickY <=0) {
									pong.leftBrick.brick.setY(1);
								} else {
									pong.leftBrick.brick.setY(619);
								}
							}
							pong.leftBrick.brick.setY(pong.leftBrick.brick.getY() + speedBrick);
						} else if(pong.agent_02.toString().equals("JarJar Binks")) {
							if(speedX < 0 && ball.getCenterX() <= 640){
								pong.leftBrick.brick.setY(pong.leftBrick.brick.getY() + jarJarBinks.move(ball.getCenterY(), pong.leftBrick.brick.getY()));
							}
						} else if(pong.leftBrick.brick.getY() <= 0 || pong.leftBrick.brick.getY() >= 620) {
							if(pong.leftBrick.brick.getY() <= 0) {
								pong.leftBrick.brick.setY(1);
							} else {
								pong.leftBrick.brick.setY(619);
							}
						} else {
							pong.leftBrick.brick.setY(pong.leftBrick.brick.getY() + pong.agent_02.learn(pong.leftBrick.brick.getY()));
						}
						
						
						if(pong.agent_01.toString().equals("JarJar Binks")) {
							if(speedX > 0 && ball.getCenterX() >= 640){
								pong.rightBrick.brick.setY(pong.rightBrick.brick.getY() + jarJarBinks.move(ball.getCenterY(), pong.rightBrick.brick.getY()));
							}
						} else if(pong.rightBrick.brick.getY() <= 0 || pong.rightBrick.brick.getY() >= 620) {
							if(pong.rightBrick.brick.getY() <= 0) {
								pong.rightBrick.brick.setY(1);
							} else {
								pong.rightBrick.brick.setY(619);
							}
						} else {
							pong.rightBrick.brick.setY(pong.rightBrick.brick.getY() + pong.agent_01.learn(pong.rightBrick.brick.getY()));
						}

						//////////////////////////////////////////////////////////////////////////////////////////
						ball.setCenterX(ball.getCenterX() + speedX);
						ball.setCenterY(ball.getCenterY() + speedY);
					} else {
						if(ball.getCenterY() <= 10){
							ball.setCenterY(11);
						} else {
							ball.setCenterY(709);
						}
						speedY = speedY * (-1);
					}
				} else {
					try {
						colision(ball.getCenterX(), ball.getCenterY());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		animation.start();
		
	}

	public static void colision(double posX, double posY) throws InterruptedException {

		double upper;
		double lower;
		
		if (posX <= 300) {
			upper = pong.leftBrick.brick.getY();
			lower = pong.leftBrick.brick.getY() + pong.leftBrick.brick.getHeight();
			if (posY >= upper && posY <= lower) {
				speedX = speed;
				brick.angle(upper, posY);
				ball.setCenterX(201);
				if(!pong.player) {
					pong.agent_02.score++;
				}
			} else {
				pong.counterRight++;
				if(pong.counterRight > 9) {
					pong.agent_01.endGame();
					pong.agent_02.endGame();
					
					pong.counterLeft = 0;
					pong.counterRight = 0;
					
					pong.pointRight.setText(pong.counterRight.toString());
					pong.pointLeft.setText(pong.counterLeft.toString());
					
					animation.stop();
					starrtValues();
					if(pong.player) {
						move = false;
					} else {
						start();
					}
				} else {
					pong.pointRight.setText(pong.counterRight.toString());
					animation.stop();
					starrtValues();
					if(pong.player) {
						move = false;
					} else {
						pong.agent_02.gameOver();
						start();
					}
				}
			}
		} else {
			upper = pong.rightBrick.brick.getY();
			lower = pong.rightBrick.brick.getY() + pong.rightBrick.brick.getHeight();

			if (posY >= upper && posY <= lower) {
				speedX = speed * (-1);
				brick.angle(upper, posY);
				ball.setCenterX(999);
				pong.agent_01.score++;
			} else {
				pong.counterLeft++;
				if(pong.counterLeft > 9) {
					pong.agent_01.endGame();
					pong.agent_02.endGame();
					pong.counterLeft = 0;
					pong.counterRight = 0;
					pong.pointRight.setText(pong.counterRight.toString());
					pong.pointLeft.setText(pong.counterLeft.toString());
					animation.stop();
					starrtValues();
					if(pong.player) {
						move = false;
					}  else {
						start();
					}
				} else {
					pong.pointLeft.setText(pong.counterLeft.toString());
					animation.stop();
					starrtValues();
					if(pong.player) {
						move = false;
					} else {
						pong.agent_01.gameOver();
						start();
					}
				}
			}
		}
	}

	public static void starrtValues() {
		speedX = 0;
		speedY = 0;
		ball.setCenterX(640);
		ball.setCenterY(360);
		pong.leftBrick.brick.setY(310);
		pong.rightBrick.brick.setY(310);

	}
}
