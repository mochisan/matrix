class Matrix {

	public static void main(String[] args){
		
		int n = 50;

		double[][] a = new double[50][50];

		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				a[row][col] = row + col + 2 ;
			}
		}

		System.out.printf("%e\n", Calc.fNorm(a));

	}

}




class Calc {

	// printVec( double x[]) ベクトルをコンソール出力する

	public static void printVec(double x[]){
		for (int index = 0; index < x.length; index++){
			System.out.print(x[index]);
			System.out.println("");
		}
	}

	// printMat(double A[][]) 行列をコンドール出力する

	public static void printMat(double A[][]){
		for (int row = 0; row < A.length; row++) {
			for (int col = 0; col < A[row].length; col++) {
				System.out.print(A[row][col]);
				System.out.print(" ");	
			}
			System.out.println("");
		}
	}

	// scalarMultiple(double c, double x[]) ベクトルをスカラー倍する

	public static double[] scalarMultiple(double c, double x[]){

		double[] ans = new double[x.length];

		for (int index = 0; index < x.length; index++){
			ans[index] = c * x[index];
		}

		return ans;

	}

	//addVec (double x[] double y[]) ベクトル同士の加算を計算する

	public static double[] addVec(double x[], double y[]){

		double[] ans = new double[x.length];

		for (int index = 0; index < x.length; index++){
			ans[index] = x[index] + y[index];
		}		

		return ans;

	}

	// subVew (double x[] double y[]) ベクトル同士の減算を計算する

	public static double[] subVec(double x[], double y[]){

		double[] ans = new double[x.length];

		for (int index = 0; index < x.length; index++){
			ans[index] = x[index] - y[index];
		}				

		return ans;

	}

	// innProd (double x[] double y[]) ベクトル同士の内積を計算する

	public static double innProd(double x[], double y[]){

		double ans = 0;

		for (int index = 0; index < x.length; index++){
			ans += x[index] * y[index];
		}				

		return ans;

	}

	// matVec (doubleA[][] double x[]) 行列A と ベクトルx の積を計算する

	public static double[] matVec(double A[][], double x[]){

		double[] ans = new double[A.length];

		for (int row = 0; row < A.length; row++){

			ans[row] = 0;

			for (int col = 0; col < x.length; col++) {

				ans[row] += A[row][col] * x[col];

			}
		}

		return ans;

	}

	// residual (double A[][] double x[] double b[]) Ax-b(残差)を計算する

	public static double[] residual(double A[][], double x[], double b[]){
		return subVec(matVec(A, x), b);
	}

	// 与えられた行列のindex列目のベクトルを取得する独自関数

	public static double[] column(double A[][], int index){
		double[] ans = new double[A.length];
		
		for (int row = 0; row < A.length; row++) {
			ans[row] = A[row][index];
		}

		return ans;
	}

	// addMat (double A[][] double B[][]) 行列同士のかさんを計算する

	public static double[][] addMat(double A[][], double B[][]){
		double[][] ans = new double[A.length][A[0].length];

		for (int row = 0; row < A.length; row++){
			for (int col = 0; col < A[0].length; col++) {
				ans[row][col] = A[row][col] + B[row][col];
			}
		}

		return ans;
	}

	// multipulMat (doubleA[][] doubleB[][[]) 行列同士の積を計算する

	public static double[][] multipulMat(double A[][], double B[][]){

		double[][] ans = new double[A.length][B[0].length];

		for (int row = 0; row < A.length; row++){
			for (int col = 0; col < B[0].length; col++) {
				ans[row][col] = innProd(A[row], column(B, col));
			}
		}

		return ans;
	}

	// vecNorm1 (double x[]) ベクトルの１ノルムを計算する

	public static double vecNorm1(double x[]){
		double ans = 0;

		for (int index = 0; index < x.length; index++) {
			ans += Math.abs(x[index]); 
		}

		return ans;
	}	

	// vecNorm2 (doubole x[]) ベクトルの２ノルムを計算する

	public static double vecNorm2(double x[]){
		double ans = 0;

		for (int index = 0; index < x.length; index++) {
			ans += x[index] * x[index]; 
		}

		return Math.sqrt(ans);
	}

	// vecNormInf (double x[]) ベクトルの無限ノルムを計算する

	public static double vecNormInf(double x[]){
		double ans = 0;

		for (int index = 0; index < x.length; index++) {
			ans = Math.max(ans, Math.abs(x[index])); 
		}

		return ans;
	}

	// matNorm1 (double A[][]) 行列の１ノルムを計算する

	public static double matNorm1(double A[][]){
		double[] tmp = new double[A.length];

		for (int row = 0; row < A.length; row++) {
			tmp[row] = vecNorm1(A[row]);
		}

		return vecNormInf(tmp);

	}

	// matNormInf (double A[][]) 行列の無限ノルムを計算する

	public static double matNormInf(double A[][]){
		double[] tmp = new double[A[0].length];

		for (int col = 0; col < A[0].length; col++) {
			tmp[col] = vecNorm1(column(A, col));
		}

		return vecNormInf(tmp);

	}	

	public static double fNorm(double A[][]){
		double ans = 0;

		for (int row = 0; row < A.length; row++) {
			for (int col = 0; col < A[0].length; col++) {
				ans += A[row][col] * A[row][col];
			}
		}

		return Math.sqrt(ans);

	}

}