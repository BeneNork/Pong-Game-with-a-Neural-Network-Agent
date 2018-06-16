/**
 * The JarJarBinks class represents the static agent.<br>
 * 
 * @author Benedikt Nork (1188594)
 * @author Geraldine Denise Lengert (1186185)
 * @author Robert Uwe Litschel (1183829)
 * @author Nasim Ahmad (1185555)
 */
public class StaticAgent {
	
	static PongGUI pong;

	/**
	 * He only moves as soon as the ball enters his field.<br>
	 * 
	 * @param ballPosY: the y positon of the ball
	 * @param brickPosY: the y position of the agent
	 * @return an int value. (7 for up, -7 for down)
	 */
	public static int move(double ballPosY, double brickPosY){
		
		if(ballPosY > (brickPosY +50)){
			return 20;
//			return 7;
		} else if(ballPosY < (brickPosY +50)){
			return -20;
//			return -7;
		} else {
			return 0;
		}
	}

}
