package polly.TSP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class DroneTSP {
	
	private static String FILEPATH = "/Users/adam/Desktop/DroneTestData.txt";
	private final static String SEP = ",";
	
	//Simulated Annealing 
	private static double COOLING_RATE;
	private final static double T_ITER = 0.001;
	
	private static ArrayList<String> SARESULTSET = new ArrayList<String>();

	int ITERATIONS = 100000;
	
	//coordinates of qrcodes
	double[][] c;
	
	double[][] d;
	
	double[][] SolutionSet;
	
	public DroneTSP() {
		System.out.println("Is file path set to its default y/n");
		Scanner in = new Scanner(System.in);
		 
	    String s = in.nextLine();
	    
	    if (s.equals("y")){
	    	runSA();
	    } else if (s.equals("n")){
	    	System.out.println("Enter path to coordinate file");
	    	FILEPATH = in.nextLine();
	    	runSA();
	    }
	    
	}
	
	public void createResultCoordinateArray(tspsol sol){
		ArrayList<Integer> rep = new ArrayList<Integer>(sol.getRep());
		SolutionSet = new double[c.length][4];

		int iter = 0;
		for(int node : rep){
			   SolutionSet[iter][0] = c[node][0];
			   SolutionSet[iter][1] = c[node][1];
			   SolutionSet[iter][2] = c[node][2];
			   SolutionSet[iter][3] = c[node][3];
			   iter++;
		}
		
		System.out.println("Finished running with " + ITERATIONS + " and created result set" );
	}
	
	public void runSA(){
		c = utility.ReadArrayFile(FILEPATH, SEP);
		
		d = new double[c.length][c.length];
		DistanceCalculation distanceCalculation = new DistanceCalculation(c);
		d = distanceCalculation.getDistanceMatrix();

//		for(int i=0; i<d.length;i++){
//		for(int j=0; j<d[0].length;j++){
//			System.out.print(d[i][j] + " ");
//		}
//		System.out.println("");
//		
//	}
		tspsol firstsol = new tspsol(d);
		prepareSA(ITERATIONS, 100); //generating lamba - cooling rate
		tspsol  sol = SA(firstsol, 100, COOLING_RATE);
		rateEfficiency(sol);
		createResultCoordinateArray(sol);
	}
	
	public tspsol SA(tspsol sol, double temperature, double coolingrate){
		for (int i=0; i<ITERATIONS; i++){
			sol.calculateFitness();
			
			tspsol copysol = new tspsol(sol); 
			
			sol.smallChange();
			
			sol.calculateFitness();
			
			//if new solution is worse than old
			if (sol.getFitness() > copysol.getFitness()){
				double p = utility.PRSA(sol.getFitness(), copysol.getFitness(), temperature);
				if (p<utility.UR(1,0)){
					//reject change
					sol = new tspsol(copysol);
				} else {
					//do nothing and accept change
				} 
			} else {
				//do nothing and accept change
			}
			//log 
			System.out.println(i + "\t " + sol.getFitness() + "\t " + temperature);
			appendResultSet(i, sol.getFitness());
			
			temperature*=coolingrate;
		}
		
		return sol;
	}
	
	/**
	 * used to append a result to a result row
	 * @param row
	 * @param fitness
	 */
	public static void appendResultSet(int row, double fitness){
        	
        if (!(row >= 0 && row < SARESULTSET.size())) {
				SARESULTSET.add(row, String.valueOf(fitness));
			} else {
				SARESULTSET.set(row, fitness + "\t" + SARESULTSET.get(row));
			}
                    
                     
        }
	
	/**
	 * method used to calculate coolingrate  
	 * @param iter
	 * @param temperature
	 */
	public static void prepareSA(double iter, double temperature){
		COOLING_RATE = Math.pow(Math.sqrt(T_ITER/temperature), (1/iter));
		System.out.println(COOLING_RATE);
	}
	
	public static void rateEfficiency(tspsol sol){
		double MST[][] = PrimsMST(sol.getDistanceMatrix());
		double MSTedgelength = 0;
		for (int x=0;x<MST.length;x++){
			for (int y=0;y<MST.length;y++){
				MSTedgelength+=MST[x][y];
			}
		}
		System.out.println(((MSTedgelength/sol.getFitness()) * 100) + "%");
	}
	/**
	 * Prims MST
	 * @param distanceMatrix
	 * @return
	 */
	private static double[][] PrimsMST(double[][] d)
	{
		int i,j,n = d.length;
		double res[][] = new double[n][n];
		//Store edges as an ArrayList
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for(i=0;i<n-1;++i)
		{
			for(j=i+1;j<n;++j)
			{
				//Only non zero edges
				if (d[i][j] != 0.0) edges.add(new Edge(i,j,d[i][j]));
			}
		}
		//Sort the edges by weight
		Collections.sort(edges,new CompareEdge());
		//Don't do anything more if all the edges are zero
		if (edges.size() == 0) return(res);
		//List of variables that have been allocated
		ArrayList<Integer> v = new ArrayList<Integer>();
		//Pick cheapest edge
		v.add(edges.get(0).i);
		//Loop while there are still nodes to connect
		while(v.size() != n)
		{
			Edge e = LocateEdge(v,edges);
			if (v.indexOf(e.i) == -1) v.add(e.i);
			if (v.indexOf(e.j) == -1) v.add(e.j);
			res[e.i][e.j] = e.w;
			res[e.j][e.i] = e.w;
		}
		return(res);
	}
	
	//Search for the next applicable edge
		static private Edge LocateEdge(ArrayList<Integer> v,ArrayList<Edge> edges)
		{
			for (Iterator<Edge> it = edges.iterator(); it.hasNext();)
			{
		        Edge e = it.next();
				int x = e.i;
				int y = e.j;
				int xv = v.indexOf(x);
				int yv = v.indexOf(y);
				if (xv > -1 && yv == -1)
				{
					return(e);
				}
				if (xv == -1 && yv > -1)
				{
					return(e);
				}
			}
			//Error condition
			return(new Edge(-1,-1,0.0));
		}
	
	public double[][] getResult(){
		return SolutionSet;
	}
}
