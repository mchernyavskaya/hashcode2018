package org.hedgebits.hashcode;

public class Point {
    int row;
    int col;

    Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    int distance(Point p){
        return Math.abs(row - p.row) + Math.abs(col - p.col);
    }
}

