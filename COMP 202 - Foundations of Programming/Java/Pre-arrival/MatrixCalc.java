public class MatrixCalc{
  public static double[][] matrixAdd(double[][] mat1, double[][] mat2){
    double[][] sum = new double[mat1.length][mat1[0].length];
    for(int i = 0; i < sum.length; i++){
      for(int j = 0; j < sum[i].length; j++)
        sum[i][j] = mat1[i][j] + mat2[i][j];
    }
    return sum;
  }
  
  public static double[][] matrixMult(double[][] mat1, double[][] mat2){
    double[][] product = new double[mat1.length][mat2[0].length];
    for(int i = 0; i < product.length; i++){
      for(int j = 0; j < product[i].length; j++){
        product[i][j] = 0;
        for(int k = 0; k < mat2.length; k++){
          product[i][j] += mat1[i][k]*mat2[k][j];
        }
      }
    }
    return product;
  }
  
  public static void main(String[] args){
    double[][] mat1 = { {2, 4, 6},
      {8, 9, 10} };
    double[][] mat2 = { {1, 2, 3, 4},
      {5, 6, 7, 8},
      {3, 4, 5, 6} };
    double[][] product = matrixMult(mat1, mat2);
    for(int i = 0; i < product.length; i++){
      for(int j = 0; j < product[i].length; j++)
        System.out.print(product[i][j] + " ");
      System.out.println();
    }
  }
}