public class Strassen{
	
	public static int THREESHOLD = 1;


	public static void main(String[] args){
		
		double[][] a = {{1,2,3,4},{4,3,2,1},{3,2,1,4},{2,1,3,4}};
		double[][] b = {{4,5,6,7},{6,5,4,4},{4,6,5,3},{3,2,1,0}};
		double[][] c = strassen(a,b);

		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a.length; j++){
				System.out.print(c[i][j]+ " ");
			}
			System.out.println();
		}

	}


	//O(N^3)
	public static double [][] ikjAlgorithm(double [][] x, double [][] y){

		int n = x.length;
		double [][] z = new double[n][n];

		for(int i = 0; i < n; i++){
			for(int k = 0; k < n; k++)
				for(int j = 0; j < n; j++)
					z[i][j] += x[i][k] * y [k][j];
		}

		return z;
	}


	//O(N^log 7)
	public static double [][] strassen(double [][] x, double [][] y){
		
		int n = x.length;
		if(n <= THREESHOLD){
			return ikjAlgorithm(x,y);
		}
		
		System.out.println("Hello from rec");

		int m = n/2; //new size	

		//X = ({A, B} , 
		//	   {C, D})
		//Y = ({E, F} , 
		//	   {G, H})
		
		double [][] a = new double[m][m];
		double [][] b = new double[m][m];
		double [][] c = new double[m][m];
		double [][] d = new double[m][m];
		double [][] e = new double[m][m];
		double [][] f = new double[m][m];
		double [][] g = new double[m][m];
		double [][] h = new double[m][m];


		//split matrix
		for(int i = 0; i < m; i++){
			for(int j = 0; j < m; j++){
				a[i][j] = x[i][j];
				c[i][j] = x[i+m][j];
				b[i][j] = x[i][j+m];
				d[i][j] = x[i+m][j+m];

				e[i][j] = y[i][j];	
				g[i][j] = y[i+m][j];
				f[i][j] = y[i][j+m];
				h[i][j] = y[i+m][j+m];	

			}
		}



		double [][] p1 = strassen(a, subtract(f,h));
		double [][] p2 = strassen(add(a,b), h);
		double [][] p3 = strassen(add(c,d), e);
		double [][] p4 = strassen(d, subtract(g,e));
		double [][] p5 = strassen(add(a,d), add(e,h));
		double [][] p6 = strassen(subtract(b,d),add(g,h));
		double [][] p7 = strassen(subtract(a,c), add(e,f));

		// calculating c21, c21, c11 e c22:
        double[][] c12 = add(p1, p2); // c12 = p1 + p2
        double[][] c21 = add(p3, p4); // c21 = p3 + p4

         // c11 = p5  + p4 - p2  + p6
        double[][] c11 = add(subtract(p5, p2), add(p4, p6) );
       
        // c22 = p1 + p5 - p3 - p7
        double[][] c22 = add(subtract(p1, p3), subtract(p5, p7));
        



        // Grouping the results obtained in a single matrix:
        double[][] z = new double[n][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                z[i][j] = c11[i][j];
                z[i][j + m] = c12[i][j];
                z[i + m][j] = c21[i][j];
                z[i + m][j + m] = c22[i][j];
            }
        }
        return z;

		

}

	private static double[][] add(double[][] x, double[][] y) {
	    int n = x.length;
	    double[][] z = new double[n][n];
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            z[i][j] = x[i][j] + y[i][j];
	        }
	    }
	    return z;
	}
	private static double[][] subtract(double[][] x, double[][] y) {
	    int n = x.length;
	    double[][] z = new double[n][n];
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            z[i][j] = x[i][j] - y[i][j];
	        }
	    }
	    return z;
	}




}