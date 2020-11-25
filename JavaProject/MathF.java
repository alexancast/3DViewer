public class MathF {
 
    public static double[][] matrixMultiplication(double[][] a, double[][] b){

        if(a[0].length != b.length){
            System.err.println("Length of columns in matrix A must match length of rows in matrix b");
            return null;
        }

        double[][] c = new double[a.length][b[0].length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {

                double sum = 0;
                for (int k = 0; k < a[0].length; k++) {
                    sum += a[i][k] * b[k][j];
                }
                c[i][j] = sum;
            }
        }

        return c;
    }

    public static double[][] matrixMultiplication(Vector3 a, Vector3 b){

        double[][] matrixA = vectorToMatrix(a);
        double[][] matrixB = vectorToMatrix(b);

        return matrixMultiplication(matrixA, matrixB);
    }

    public static double[][] matrixMultiplication(double[][] matrixA, Vector3 b){

        double[][] matrixB = vectorToMatrix(b);
        return matrixMultiplication(matrixA, matrixB);
    }

    public static double[][] vectorToMatrix(Vector3 vector){

        double[][] matrix = new double[3][1];

        matrix[0][0] = vector.x;
        matrix[1][0] = vector.y;
        matrix[2][0] = vector.z;

        return matrix;
    }

    public static Vector3 matrixToVector3(double[][] matrix){

        Vector3 vector = new Vector3();
        vector.x = matrix[0][0];
        vector.y = matrix[1][0];
        
        if (matrix.length >= 3) {
            vector.z = matrix[2][0];
        }
        return vector;
    }

}
