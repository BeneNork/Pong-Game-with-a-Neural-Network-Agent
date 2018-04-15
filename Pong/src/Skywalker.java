import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Skywalker {
	String name;
	public final NeuralNetwork nn;
	Integer scoreCounter = 0;
	
	Skywalker(String name){
		this.name = name;
		nn = new NeuralNetwork(neurons_amount, genomes_per_generation, 0.5, -1, 1, this.name);
	}
	
	@Override
	public String toString() {
		 return this.name;
	}
	
	private final int genomes_per_generation = 3;
    private final int neurons_amount[] = {2, 2, 1};
	private final double inputs[] = new double[2];
    private double outputs[] = new double[1];
    public Integer score = 0;
	
	static Ball ball;
	static PongGUI pong;
	
	protected int learn(double y) {        
        // Get the inputs from the game and the output from the neural network
        inputs[0] = ball.ball.getCenterY();
//        inputs[1] = pong.rightBrick.brick.getY();
        inputs[1] = y;
        outputs = nn.getOutputs(inputs);

        // Do an action according to the output value
        if(outputs[0] > 0.5) {
            return -10;
        }
        else {
        	return 10;
        }
    }
	
	protected void gameOver() throws InterruptedException {
        // Get the fitness of the current genome, then create a new genome
        nn.newGenome(score);
        // RESET ALL
        
        ball.starrtValues();

        scoreCounter += score;
		
       score = 0;

    }
	
	public void endGame() {
		scoreCounter += score;
		
		try {
			File file = new File(this.name + ".diagram");
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(scoreCounter.toString());
			bw.newLine();
			bw.close();
			
			nn.newGenome(score);
			scoreCounter = 0;
			score = 0;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
