
public class JarJarBinks {
	
	static PongGUI pong;

	public static int move(double ballPosY, double brickPosY){
		
		if(ballPosY > (brickPosY +50)){
//			return 10;
			return 7;
		} else if(ballPosY < (brickPosY +50)){
//			return -10;
			return -7;
		} else {
			return 0;
		}
	}

}
