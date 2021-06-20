package co.com.narfco.meli.mutants.meli.mutants.service.mutantdetector.impl;

import co.com.narfco.meli.mutants.meli.mutants.service.mutantdetector.MutantDetector;

public class MutantDetectorImpl implements MutantDetector {

    private static final int DIRECTIONS = 8;
    private static final int[] X = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] Y = {-1, 0, 1, -1, 1, -1, 0, 1};


    @Override
    public boolean isMutant(String[] dnaChain) {
        char[][] matrix = getMatrixFromDnaChain(dnaChain);
        return findMutantChain(matrix);
    }

    private boolean findMutantChain(char[][] grid) {
        int xTotal = grid.length;
        int yTotal = grid[0].length;
        int count = 0;
        for (int row = 0; row < xTotal; row++) {
            for (int col = 0; col < yTotal; col++) {
                if (searchChain(grid, row, col, xTotal, yTotal)) {
                    count++;
                    if (count >= 2)
                        return true;
                }
            }
        }
        return false;
    }

    private boolean searchChain(char[][] matrix, int row, int col, int xTotal, int yTotal) {

        char searchingChar = matrix[row][col];

        for (int direction = 0; direction < DIRECTIONS; direction++) {
            int count;
            int nextRow = row + X[direction];
            int nextCol = col + Y[direction];

            for (count = 1; count < 5; count++) {
                if (!isValidPosition(nextRow, nextCol, xTotal, yTotal))
                    break;
                if (matrix[nextRow][nextCol] != searchingChar)
                    break;
                nextRow = nextRow + X[direction];
                nextCol = nextCol + Y[direction];
            }
            if (count == 4)
                return true;
        }
        return false;
    }

    private boolean isValidPosition(int nextRow, int nextCol, int xTotal, int yTotal) {
        return (nextRow < xTotal && nextRow >= 0) && (nextCol < yTotal && nextCol >= 0);
    }

    private char[][] getMatrixFromDnaChain(String[] dnaChain) {
        int xTotal = dnaChain.length;
        int yTotal = dnaChain[0].length();
        char[][] matrix = new char[xTotal][yTotal];
        for (int i = 0; i < xTotal; i++) {
            for (int j = 0; j < yTotal; j++) {
                matrix[i][j] = dnaChain[i].charAt(j);
            }
        }
        return matrix;
    }
}
