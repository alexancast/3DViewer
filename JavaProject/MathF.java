public class MathF {
 
    public void matrixMultiplication(double[][] a, double[][] b, int rows, int columns){

        double[][] c = new double[a.length][b[0].length];

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[i].length; j++) {
                c[i][j] = multiplyMatrixCell(a, b, rows, columns);
            }
        }
    }


    private double multiplyMatrixCell(double[][] a, double[][] b, int rows, int columns){
        
        double cell = 0;

        for (int i = 0; i < b.length; i++) {
            cell += a[rows][i] * b[i][columns];
        }

        return cell;
    }

}
