package polly.TSP;

import java.util.ArrayList;

/*
 * Class used to represent TSP solutions
 * Solutions generated randomly and small changes made to solutions avoid changing the starting point
 * This is to maintain a fixed starting point for every TSP tour 
 */

public class TSPSol {
		//solution representation or tour
		private ArrayList<Integer> rep = new ArrayList<Integer>();
		//solution fitness
		private double fitness = -1;
		//number of cities
		private int N = -1;
		//distance matrix
		private double[][] d;
		
		//index of starting point is always the 1st or 0th coordinate
		private static final int STARTING_POINT = 0;
		
		//construct a new solution with given representation
		//used for copying solution object
		public TSPSol(TSPSol copy){
			rep = new ArrayList<Integer>(copy.getRep());
			N = rep.size();
			fitness = copy.getFitness();
			d = copy.getDistanceMatrix();
		}
			
		//construct a new random solution
		public TSPSol(double [][] d){
			this.d = d; //distance matrix
			N = this.d.length; //number of cities 
			generateRandomSolution();
		}
		
		/*
		 * Create a random solution
		 * Starting point of this random solution is the same 
		 * as the starting point of the file or coordinates fed into
		 * TSP 
		 */
		private void generateRandomSolution() {
			ArrayList<Integer> P  = new ArrayList<Integer>(N);
			
			//P - is index array of all cities that can be swapped
			//limit P here to everything after 0 (to keep the same staring point)
			for (int t = 1; t<N; t++){
				P.add(t);
			}
			
			//add starting point of 0, always the same location
			rep.add(STARTING_POINT);
			
			while (P.size() > 0){
				int m = Utility.UI(0, P.size()-1);
				rep.add(P.get(m));
				P.remove(m);
			}
		}

		//return representation/solution
		public ArrayList<Integer> getRep(){
			return rep;
		}
		
		//return fitness of the solution
		public double getFitness(){
			return fitness;
		}
		
		public double[][] getDistanceMatrix(){
			return d;
		}
		
		//evaluate fitness of solution
		public void calculateFitness() {
			double sum = 0;
			//add city distances in the body
			for (int i =0; i<(N-1); i++){
				int a = rep.get(i);
				int b = rep.get(i+1);
				sum+= d[a][b];
			}
			//join the last and first city
			int end_city = rep.get(rep.size()-1);
			int start_city = rep.get(0);
			sum+= d[end_city][start_city];
			//the sum of distances is our representation
			fitness = sum;
		}
		
		/*
		 *Make a small change to tour/solution
		 *by swapping a pair of randomly chosen cities
		 *Method deliberately limits seclection to between 1 and end of 
		 *representation. This is done to avoid changing the start point.
		*/
		public void smallChange() {
			int i=0, j = 0;
			while (i==j){
				i=Utility.UI(1, rep.size()-1);
				j=Utility.UI(1, rep.size()-1);
			}
			//swap the two cities
			int temp = rep.get(i);
			rep.set(i, rep.get(j));
			rep.set(j, temp);
		}
		
}
