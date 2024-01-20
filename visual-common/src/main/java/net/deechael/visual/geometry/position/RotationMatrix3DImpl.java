package net.deechael.visual.geometry.position;

import net.deechael.visual.api.geometry.Angle;
import net.deechael.visual.api.geometry.position.RotationMatrix3d;
import net.deechael.visual.api.geometry.position.Vector3d;

public class RotationMatrix3DImpl implements RotationMatrix3d {

    private final double[][] matrix;

    public RotationMatrix3DImpl(Vector3d vector) {
        double x = vector.x();
        double y = vector.y();
        double z = vector.z();


        this.matrix = new double[][]{
                {x * x, x * y, x * z},
                {y * x, y * y, y * z},
                {z * x, z * y, z * z}
        };
    }

    @Override
    public Vector3d rotate(Vector3d vector, Angle angle) {
        double cosTheta = angle.cos();
        double sinTheta = angle.sin();

        double[][] rotationMatrix = {
                {cosTheta, -sinTheta, 0},
                {sinTheta, cosTheta, 0},
                {0, 0, 1}
        };

        double[][] vectorMatrix = {{vector.x()}, {vector.y()}, {vector.z()}};
        double[][] resultMatrix = matrixMultiply(matrixMultiply(matrix, rotationMatrix), vectorMatrix);

        return new Vector3dImpl(resultMatrix[0][0], resultMatrix[1][0], resultMatrix[2][0]);
    }

    private double[][] matrixMultiply(double[][] matrix1, double[][] matrix2) {
        int m1Rows = matrix1.length;
        int m1Cols = matrix1[0].length;
        int m2Rows = matrix2.length;
        int m2Cols = matrix2[0].length;

        if (m1Cols != m2Rows) {
            throw new IllegalArgumentException("The matrix cannot multiply");
        }

        double[][] resultMatrix = new double[m1Rows][m2Cols];

        for (int i = 0; i < m1Rows; i++) {
            for (int j = 0; j < m2Cols; j++) {
                for (int k = 0; k < m1Cols; k++) {
                    resultMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return resultMatrix;
    }

}
