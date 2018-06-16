import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * The skywalker class is the link between the pong game and the neural network.<br>
 * It defines with how many neurons and layers the neural network is started.<br>

 * 
 * @author Benedikt Nork (1188594)
 * @author Geraldine Denise Lengert (1186185)
 * @author Robert Uwe Litschel (1183829)
 * @author Nasim Ahmad (1185555)
 */
public class NeuralNetworkAgent {
	String name;
	public final NeuralNetwork nn;
	Integer scoreCounter = 0;
	int games = 0;
	
	/**
	 * the constructor create neural network.<br>
	 * 
	 * @param name: the name of the agent
	 */
	NeuralNetworkAgent(String name){
		this.name = name;
		nn = new NeuralNetwork(2,1,22,4);
	}
	

	/**
	 * @return the name of the agent
	 */
	public String toString() {
		 return this.name;
	}
	
	private final int genomes_per_generation = 4;
    private final int neurons_amount[] = {2, 2, 2, 2, 1};
	private final double inputs[] = new double[2];
    private double outputs[] = new double[1];
    public Integer score = 0;
	
	static Ball ball;
	static PongGUI pong;
	
	/**
	 * In the learn() method, the agent controls the position of the own bricks with the position of the ball.<br>
	 * Depending on the learning progress, the agent is moved more or less efficient.<br>
	 * 
	 * @param y: the position on the y axis of the brick
	 * @return an int (-10 for up, 10 for down)
	 */
	protected int learn(double y) {        
        // Get the inputs from the game and the output from the neural network

        inputs[0] = y/1000.0;
        inputs[1] = ball.ball.getCenterY()/1000.0;
        outputs = nn.input(inputs); 
        
        double dif = (y+50.0)/1000.0-inputs[1];
        dif *= 0.1;
        double[] error = {dif};

        // Do an action according to the output value
        if(outputs[0] > 0.5) {
            return -10;
        } else {
        	return 10;
        }
    }
	
	/**
	 * In this class, the "score" is also counted (how often the agent's brick hit the ball).<br>
	 * The score is reset every time the brick of the agent does not hit the ball.<br>
	 * 
	 * @throws InterruptedException
	 */

	protected void gameOver(double brickY, double ballY) throws InterruptedException {
		double dif = (brickY+50)/1000-ballY/1000;
		dif *= 0.5;

		double[] error = new double[1];
		
			error[0] = dif;

			nn.training(error);
		
        ball.starrtValues();

        scoreCounter += score;
		
       score = 0;

    }
	
	/**
	 * Each score is added to the scoreCounter and saved at the end of the game in the file with the name of the agent and the ending .diagram.<br>
	 */
	public void endGame() {
		games++;
		System.out.println("Games: "+games);
		scoreCounter += score;
		
		try {
			File file = new File(this.name + ".diagram");
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(scoreCounter.toString());
			bw.newLine();
			bw.close();
			

			scoreCounter = 0;
			score = 0;
		} catch (Exception e) {

		}
	}

}
