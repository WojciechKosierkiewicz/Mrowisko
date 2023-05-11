package org.app.math;

public class Matrix {
    private int rows;
    private int colums;
    private double[][] data;

    public Matrix(int rows, int colums, double[][] data) {
        this.rows = rows;
        this.colums = colums;
        this.data = new double[rows][colums];
    }

    public int getColums() {
        return colums;
    }

    public int getRows() {
        return rows;
    }

    public double[][] getData() {
        return data;
    }

    public double get(int row, int colum) {
        return data[row][colum];
    }

    public void set(int row, int colum, double value) {
        data[row][colum] = value;
    }

    Matrix Sum(Matrix m) throws Exception {

        if (this.getRows() != m.getRows() || this.getColums() != m.getColums()) {
            throw new Exception("Rozmiar macierzy sie nie zgadza");
        }

        Matrix result = new Matrix(this.getRows(), this.getColums(), this.getData());

        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColums(); j++) {
                result.set(i, j, this.get(i, j) + m.get(i, j));
            }
        }

        return result;
    }

    Matrix subtrack(Matrix m) throws Exception {

        if (this.getRows() != m.getRows() || this.getColums() != m.getColums()) {
            throw new Exception("Rozmiar macierzy sie nie zgadza");
        }

        Matrix result = new Matrix(this.getRows(), this.getColums(), this.getData());

        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColums(); j++) {
                result.set(i, j, this.get(i, j) - m.get(i, j));
            }
        }

        return result;
    }

    Matrix multiply(double num) {
        Matrix result = new Matrix(this.getRows(), this.getColums(), this.getData());

        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColums(); j++) {
                result.set(i, j, this.get(i, j) * num);
            }
        }

        return result;
    }
}