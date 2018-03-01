package org.hedgebits.hashcode;

class Point {
    final int row;
    final int col;

    Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    int distance(Point p){
        return Math.abs(row - p.row) + Math.abs(col - p.col);
    }
}

