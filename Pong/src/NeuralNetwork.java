import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * 
 * @author Benedikt Nork
 *
 */
public class NeuralNetwork {

	private int number_of_input;
	private int number_of_output;
	private int number_of_layers; // >= 2; if it ==2, only input and output layer
	private int neurons_Per_Layer;
	private double[] network_input; // input value
	private double[] network_output;// output value
	private ArrayList<double[][]> layer = new ArrayList<double[][]>();
	private ArrayList<double[]> layer_output = new ArrayList<double[]>();

	public int getNumber_of_input() {
		return number_of_input;
	}

	public void setNumber_of_input(int number_of_input) {
		this.number_of_input = number_of_input;
	}

	public int getNumber_of_output() {
		return number_of_output;
	}

	public void setNumber_of_output(int number_of_output) {
		this.number_of_output = number_of_output;
	}

	public int getNeurons_Per_Layer() {
		return neurons_Per_Layer;
	}

	public void setNeurons_Per_Layer(int neurons_Per_Layer) {
		this.neurons_Per_Layer = neurons_Per_Layer;
	}

	public double[] getNetwork_input() {
		return network_input;
	}

	public void setNetwork_input(double[] network_input) {
		this.network_input = network_input;
	}

	public double[] getNetwork_output() {
		return network_output;
	}

	public void setNetwork_output(double[] network_output) {
		this.network_output = network_output;
	}

	public int getNumber_of_layers() {
		return number_of_layers;
	}

	public void setNumber_of_layers(int number_of_layers) {
		this.number_of_layers = number_of_layers;
	}

	public ArrayList<double[][]> getLayer() {
		return layer;
	}

	public void setLayer(ArrayList<double[][]> layer) {
		this.layer = layer;
	}

	public ArrayList<double[]> getLayer_output() {
		return layer_output;
	}

	public void setLayer_output(ArrayList<double[]> layer_output) {
		this.layer_output = layer_output;
	}

	/**
	 * 
	 * @param number_of_input
	 * @param number_of_output
	 * @param neurons_per_layer
	 * @param number_of_layers
	 */
	public NeuralNetwork(int number_of_input, int number_of_output, int number_of_layers, int neurons_per_layer) {
		setNumber_of_input(number_of_input);
		setNumber_of_output(number_of_output);
		setNumber_of_layers(number_of_layers);
		setNeurons_Per_Layer(neurons_per_layer);
		

		if (getNumber_of_layers() == 2) {
			getLayer().add(networkWeights(number_of_input, number_of_output));
		} else if (getNumber_of_layers() == 3) {
			getLayer().add(networkWeights(number_of_input, neurons_per_layer));
			getLayer().add(networkWeights(neurons_per_layer, number_of_output));
			double[] layer_output = new double[neurons_per_layer];
			getLayer_output().add(layer_output);
		} else {
			getLayer().add(networkWeights(number_of_input, neurons_per_layer));
			for (int i = 0; i < number_of_layers - 3; i++) {
				getLayer().add(networkWeights(neurons_per_layer, neurons_per_layer));
				double[] layer_output = new double[neurons_per_layer];
				getLayer_output().add(layer_output);
			}
			double[] layer_output = new double[neurons_per_layer];
			getLayer_output().add(layer_output);
			getLayer().add(networkWeights(neurons_per_layer, number_of_output));
		}
	}

	/**
	 * 
	 * @param left_neurons
	 * @param right_neurons
	 * @return
	 */
	public double[][] networkWeights(int left_neurons, int right_neurons) {
		NumberFormat nf = NumberFormat.getInstance(Locale.US);
		nf.setMaximumFractionDigits(5);
		double[][] weights = new double[right_neurons][left_neurons];
		for (int i = 0; i < left_neurons; i++) {
			for (int j = 0; j < right_neurons; j++) {
				String random_string = nf.format((Math.random()*2)-1);
				if (random_string.equals("0")) {
					weights[j][i] = 0.1;
				} else {
					weights[j][i] = Double.parseDouble(random_string);
				}
			}
		}
		return weights;
	}

	
	public double[] input(double[] input) {
//		System.out.println("input: "+input[0]+", "+input[1]);
		setNetwork_input(input);
		double[] results = null;
		ArrayList<double[]> layer_output = new ArrayList<double[]>();
		for (int i = 0; i < getLayer().size(); i++) {
			double[][] matrix = layer.get(i);
			results = matrixMultiplication(matrix, input);
			input = results;
			if(i < (getLayer().size()-1)) {
				layer_output.add(results);
			}
		}
		setLayer_output(layer_output);
		setNetwork_output(results);
//		System.out.println("output: "+results[0]);
		return results;
	}

	/**
	 * 
	 * @param matrix
	 * @param results
	 * @return
	 */
	public double[] matrixMultiplication(double[][] matrix, double[] results) {
		double[] new_results = new double[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			double res = 0;
			for (int j = 0; j < matrix[i].length; j++) {
				res += matrix[i][j] * results[j];
			}
			new_results[i] = res;
		}
		return sigmoidFunction(new_results);
	}

	/**
	 * 
	 * @param results
	 * @return
	 */
	public double[] sigmoidFunction(double[] results) {
		NumberFormat nf = NumberFormat.getInstance(Locale.US);
		nf.setMaximumFractionDigits(5);
		double[] new_results = new double[results.length];
		for (int i = 0; i < new_results.length; i++) {
			// new_results[i] = 1 / (1 + Math.exp(-results[i]));
			new_results[i] = Double.parseDouble(nf.format(1 / (1 + Math.exp(-results[i]))));
		}
		return new_results;
	}
	
	public void training(double[] err) {
		ArrayList<double[][]> new_weights = new ArrayList<double[][]>();
		double[] error = err;
//		System.out.println("error: "+error[0]);
//		System.out.println("error.length: "+error.length);
//		System.out.println("layer size: "+getLayer().size());
		for(int i=getLayer().size()-1;i>=0;i--) {
			if(i==0){
				double[] input = getNetwork_input();
				double[][] matrix = getLayer().get(0);
				double[][] matrix_transpose = transpose(matrix);
				double[] sig_sum = matrixMultiplication(matrix, input);
//				System.out.println("sigsum.lenght: "+sig_sum.length);
				for(int j=0;j<error.length;j++) {
					for(int k=0;k<input.length;k++) {
//						System.out.println("j: "+j+" k: "+k);
						double e = -error[j]*sig_sum[j]*(1-sig_sum[j])*input[k];
						e = -(0.3*e);
//						System.out.println("kante "+(k+1)+","+(j+1)+": "+e);
						matrix_transpose[k][j] += e;
					}
				}
				new_weights.add(transpose(matrix_transpose));
				
			} else {
				double[] hidden_output = getLayer_output().get(i-1);
				double[][] matrix = getLayer().get(i);
				double[][] matrix_transpose = transpose(matrix);
				double[] sig_sum = matrixMultiplication(matrix, hidden_output);
				for(int j=0;j<error.length;j++) {
					for(int k=0;k<hidden_output.length;k++) {
						double e = -error[j]*sig_sum[j]*(1-sig_sum[j])*hidden_output[k];
						e = -(0.3*e);
//						System.out.println("kante "+(k+1)+","+(j+1)+": "+e);
						matrix_transpose[k][j] += e;
					}
				}
				new_weights.add(transpose(matrix_transpose));
//				System.out.println(matrix_transpose[0][0]);
				
				double[][] error_calc = new double[matrix_transpose.length][matrix_transpose[0].length];
				double[] error_divisor = new double[matrix_transpose[0].length];
				matrix_transpose = transpose(matrix);
				
				for(int j=0;j<error_divisor.length;j++) {
					double sum = 0;
					for(int k=0;k<matrix_transpose.length;k++) {
//						System.out.println("matrix_transpose "+k+","+j+" = "+matrix_transpose[k][j]);
						sum += matrix_transpose[k][j];
//						System.out.println("sum: "+sum);
					}
					error_divisor[j] = sum;
//					System.out.println("error_divisor "+j+": "+error_divisor[j]); 
				}
				
				for(int j=0;j<error_calc.length;j++) {
					for(int k=0;k<error_calc[0].length;k++) {
//						System.out.println("matrix_transpose[j][k]/error_divisor[k]: "+matrix_transpose[j][k]+" / "+error_divisor[k]);
//						System.out.println("error[k]: "+error[k]);
						error_calc[j][k] = (matrix_transpose[j][k]/error_divisor[k])*error[k];
//						System.out.println("error_calc[j][k]: "+error_calc[j][k]);
					}
				}
				
//				System.out.println("--------------------------------------------------------------------------------------------");
				error = new double[error_calc.length];
				for(int j=0;j<error_calc.length;j++) {
					double sum = 0;
//					System.out.println("sum = null");
					for(int k=0;k<error_calc[0].length;k++) {
//						System.out.println("error_calc[j][k]: "+error_calc[j][k]);
						sum += error_calc[j][k];
//						System.out.println("sum: "+sum);
					}
					error[j] = sum;
//					System.out.println("error "+j+" = "+error[j]);
				}
			}
		}
		ArrayList<double[][]> reverse = new ArrayList<double[][]>();
		for(int i=new_weights.size()-1;i>=0;i--) {
			reverse.add(new_weights.get(i));
		}
		setLayer(reverse);
	}
	
	/**
	 * 
	 * @param matrix
	 * @return
	 */
	public double[][] transpose(double[][] matrix) {
		double[][] transpose_matrix = new double[matrix[0].length][matrix.length];
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[i].length;j++) {
				transpose_matrix[j][i] = matrix[i][j];
			}
		}
		return transpose_matrix;
	}

	/**
	 * 
	 * @param list
	 */
	public void printNetwork() {
		for (int i = 0; i < layer.size(); i++) {
			double[][] weight_matrix = layer.get(i);

			for (int j = 0; j < weight_matrix.length; j++) {
				for (int k = 0; k < weight_matrix[j].length; k++) {
					System.out.print("w" + (k + 1) + "," + (j + 1) + ": " + weight_matrix[j][k]);
					if (k != (weight_matrix[j].length - 1)) {
						System.out.print("  /  ");
					}
				}
				System.out.println();
			}
			System.out.println("----------------------------------------------------");
		}
	}
	
	
	public void save() {
		
	}
	
	public void load() {
		
	}
	
	
}
