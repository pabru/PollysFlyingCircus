package polly.TSP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Random;

public class Utility {
	
	static private Random rand;
	//Create a uniformly distributed random integer between aa and bb inclusive
	static public int UI(int aa,int bb)
	{
		int a = Math.min(aa,bb);
		int b = Math.max(aa,bb);
		if (rand == null) 
		{
			rand = new Random();
			rand.setSeed(System.nanoTime());
		}
		int d = b - a + 1;
		int x = rand.nextInt(d) + a;
		return(x);
	}
	
	//Create a uniformly distributed random double between a and b inclusive
	static public double UR(double a,double b)
	{
		if (rand == null) 
		{
			rand = new Random();
			rand.setSeed(System.nanoTime());
		}
		return((b-a)*rand.nextDouble()+a);
	}
	
	//This method reads in a text file and parses all of the numbers in it
		//This code is not very good and can be improved!
		//But it should work!!!
		//It takes in as input a string filename and returns an array list of Doubles
		static public ArrayList<Double> ReadNumberFile(String filename)
		{
			ArrayList<Double> res = new ArrayList<Double>();
			Reader r;
			try
			{
				r = new BufferedReader(new FileReader(filename));
				StreamTokenizer stok = new StreamTokenizer(r);
				stok.parseNumbers();
				stok.nextToken();
				while (stok.ttype != StreamTokenizer.TT_EOF) 
				{
					if (stok.ttype == StreamTokenizer.TT_NUMBER)
					{
						res.add(stok.nval);
					}
					stok.nextToken();
				}
			}
			catch(Exception E)
			{
				System.out.println("+++ReadFile: "+E.getMessage());
			}
		    return(res);
		}
		
		//This method reads in a text file and parses all of the numbers in it
		//This method is for reading in a square 2D numeric array from a text file
		//This code is not very good and can be improved!
		//But it should work!!!
		//'sep' is the separator between columns
		static public double[][] ReadArrayFile(String filename,String sep)
		{
			double res[][] = null;
			try
			{
				BufferedReader input = null;
				input = new BufferedReader(new FileReader(filename));
				String line = null;
				int ncol = 0;
				int nrow = 0;
				
				while ((line = input.readLine()) != null) 
				{
					++nrow;
					String[] columns = line.split(sep);
					ncol = Math.max(ncol,columns.length);
				}
				res = new double[nrow][ncol];
				input = new BufferedReader(new FileReader(filename));
				int i=0,j=0;
				while ((line = input.readLine()) != null) 
				{
					String[] columns = line.split(sep);
					for(j=0;j<columns.length;++j)
					{
						res[i][j] = Double.parseDouble(columns[j]);
					}
					++i;
				}
			}
			catch(Exception E)
			{
				System.out.println("+++ReadArrayFile: "+E.getMessage());
			}
		    return(res);
		}
		
		//used for SA
		static public double PRSA(double fcopy, double f, double T){
			return Math.pow(Math.E, (-(Math.abs(f-fcopy))/T));
		}
}
