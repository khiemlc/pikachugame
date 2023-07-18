/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.PointLight;

/**
 *
 * @author Le Chi Khiem - CE171515
 */
public class Controller {

    private int row;
    private int col;
    private int[][] matrix;
    private ArrayList<ArrayList<Point>> paths = new ArrayList<ArrayList<Point>>();
    MainFrame frame;

    public Controller(MainFrame frame, int row, int col) {
        this.frame = frame;
        this.row = row;
        this.col = col;
        System.out.println(row + "," + col);
        createMatrix();
        showMatrix();
    }

    private void createMatrix() {
        matrix = new int[row][col];
        for (int i = 0; i < col; i++) {
            matrix[0][i] = matrix[row - 1][i] = 0;
        }
        for (int i = 0; i < row; i++) {
            matrix[i][0] = matrix[i][col - 1] = 0;
        }

        Random rand = new Random();
        int imgCount = 21;
        int max = imgCount / 2;
        int[] arr = new int[imgCount + 1];
        ArrayList<Point> listPoint = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                listPoint.add(new Point(i, j));
            }
        }

        int i = 0;
        do {
            int index = rand.nextInt(imgCount) + 1;
            if (arr[index] < max) {
                arr[index] += 2;
                for (int j = 0; j < 2; j++) {
                    try {
                        int size = listPoint.size();
                        int pointIndex = rand.nextInt(size);
                        matrix[listPoint.get(pointIndex).x][listPoint.get(pointIndex).y] = index;
                        listPoint.remove(pointIndex);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                ++i;
            }
        } while (i < row * col / 2);
    }

    public PointLine checkTwoPoint(Point p1, Point p2) {
        if (!p1.equals(p2) && matrix[p1.x][p1.y] == matrix[p2.x][p2.y]) {
            if (p1.x == p2.x) {
                System.out.println("line x");
                if (checkLineX(p1.y, p2.y, p1.x)) {
                    return new PointLine(p1, p2);
                }
            }
            if (p1.y == p2.y) {
                System.out.println("line y");
                if (checkLineY(p1.x, p2.x, p1.y)) {
                    System.out.println("ok line y");
                    return new PointLine(p1, p2);
                }
            }

            if (checkRectX(p1, p2)) {
                System.out.println("rect x");
                return new PointLine(p1, p2);
            }

            if (checkRectY(p1, p2)) {
                System.out.println("rect y");
                return new PointLine(p1, p2);
            }

            if (checkMoreLineX(p1, p2, 1)) {
                System.out.println("more right");
                return new PointLine(p1, p2);
            }

            if (checkMoreLineX(p1, p2, -1)) {
                System.out.println("more left");
                return new PointLine(p1, p2);
            }

            if (checkMoreLineY(p1, p2, 1)) {
                System.out.println("more down");
                return new PointLine(p1, p2);
            }

            if (checkMoreLineY(p1, p2, -1)) {
                System.out.println("more up");
                return new PointLine(p1, p2);
            }
        }
        return null;
    }

    public void showMatrix() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.printf("%3d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    private boolean checkLineX(int y1, int y2, int x) {
        System.out.println("check line x");
        int min = Math.min(y1, y2);
        int max = Math.max(y1, y2);

        for (int y = min + 1; y < max; y++) {
            if (matrix[x][y] != 0) {
                System.out.println("die: " + x + "" + y);
                return false;
            }
            System.out.println("ok: " + x + "" + y);
        }
        return true;
    }

    private boolean checkLineY(int x1, int x2, int y) {
        System.out.println("check line y");
        int min = Math.min(x1, x2);
        int max = Math.max(x1, x2);

        for (int x = min + 1; x < max; x++) {
            if (matrix[x][y] != 0) {
                System.out.println("die: " + x + "" + y);
                return false;
            }
            System.out.println("ok: " + x + "" + y);
        }
        return true;
    }

    private boolean checkRectX(Point p1, Point p2) {
        System.out.println("check rect x");

        Point pMinY = p1, pMaxY = p2;
        if (p1.y > p2.y) {
            pMinY = p2;
            pMaxY = p1;
        }
        for (int y = pMinY.y; y <= pMaxY.y; y++) {
            if (y > pMinY.y && matrix[pMinY.x][y] != 0) {
                return false;
            }
            if ((matrix[pMaxY.y][y] == 0) && checkLineY(pMinY.x, pMaxY.x, y)
                    && checkLineX(y, pMaxY.y, pMaxY.x)) {
                System.out.println("Rect x");
                System.out.println("(" + pMinY.x + "," + pMinY.y + ") -> ("
                        + pMinY.x + "," + y + ") -> (" + pMaxY.x + "," + y
                        + ") -> (" + pMaxY.x + "," + pMaxY.y + ")");
                return true;
            }
        }
        return false;
    }

    private boolean checkRectY(Point p1, Point p2) {
        System.out.println("check rect y");

        Point pMinX = p1, pMaxX = p2;
        if (p1.x > p2.x) {
            pMinX = p2;
            pMaxX = p1;
        }
        for (int x = pMinX.x; x <= pMaxX.x; x++) {
            if (x > pMinX.x && matrix[x][pMinX.y] != 0) {
                return false;
            }
            if ((matrix[x][pMaxX.y] == 0) && checkLineY(pMinX.y, pMaxX.y, x)
                    && checkLineX(x, pMaxX.x, pMaxX.y)) {
                System.out.println("Rect y");
                System.out.println("(" + pMinX.x + "," + pMinX.y + ") -> (" + x
                        + "," + pMinX.y + ") -> (" + x + "," + pMaxX.y
                        + ") -> (" + pMaxX.x + "," + pMaxX.y + ")");
                return true;
            }
        }
        return false;
    }

    private boolean checkMoreLineX(Point p1, Point p2, int type) {
        System.out.println("check more x");
        Point pMinY = p1, pMaxY = p2;
        if (p1.y > p2.y) {
            pMinY = p2;
            pMaxY = p1;
        }
        int y = pMaxY.y + type;
        int row = pMinY.x;
        int colFinish = pMaxY.y;
        if (type == -1) {
            colFinish = pMinY.y;
            y = pMinY.y + type;
            row = pMaxY.x;
            System.out.println("colFinish = " + colFinish);
        }
        if ((matrix[row][colFinish] == 0 || pMinY.y == pMaxY.y)
                && checkLineX(pMinY.y, pMaxY.y, row)) {
            while (matrix[pMinY.x][y] == 0 && matrix[pMaxY.x][y] == 0) {
                if (checkLineY(pMinY.x, pMaxY.x, y)) {
                    System.out.println("TH X " + type);
                    System.out.println("(" + pMinY.x + "," + pMinY.y + ") -> ("
                            + pMinY.x + "," + y + ") -> (" + pMaxY.x + "," + y
                            + ") -> (" + pMaxY.x + "," + pMaxY.y + ")");
                    return true;
                }
                y += type;
            }
        }
        return false;
    }

    private boolean checkMoreLineY(Point p1, Point p2, int type) {
        System.out.println("check more y");
        Point pMinX = p1, pMaxX = p2;
        if (p1.x > p2.x) {
            pMinX = p2;
            pMaxX = p1;
        }
        int x = pMaxX.x + type;
        int col = pMinX.y;
        int rowFinish = pMaxX.x;
        if (type == -1) {
            rowFinish = pMinX.x;
            x = pMinX.x + type;
            col = pMaxX.y;
        }
        if ((matrix[rowFinish][col] == 0 || pMinX.x == pMaxX.x)
                && checkLineY(pMinX.x, pMaxX.x, col)) {
            while (matrix[x][pMinX.y] == 0
                    && matrix[x][pMaxX.y] == 0) {
                if (checkLineX(pMinX.y, pMaxX.y, x)) {
                    System.out.println("TH Y " + type);
                    System.out.println("(" + pMinX.x + "," + pMinX.y + ") -> ("
                            + x + "," + pMinX.y + ") -> (" + x + "," + pMaxX.y
                            + ") -> (" + pMaxX.x + "," + pMaxX.y + ")");
                    return true;
                }
                x += type;
            }
        }
        return false;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

}
