package little.things.algorithm.geometry;

import java.text.DecimalFormat;

public class DotInPolygon {
    public static void main(String[] args) {
        int Ox = 2;
        int Oy = 3;
        int[] polygonX = new int[]{0, 1, 3, 4, 2};
        int[] polygonY = new int[]{0, 0, 2, 4, 3};

        double polygonArea = 0;
        for (int i = 1; i < polygonX.length - 1; i++) {
            polygonArea = getPolygonFromDotArea(polygonX[0], polygonY[0], polygonX, polygonY, polygonArea, i, i + 1);
        }
        System.out.println(polygonArea);

        System.out.println();
        double polygonFromDotArea = 0;
        for (int i = 0; i < polygonX.length; i++) {
            int next;
            if (i != polygonX.length - 1) {
                next = i + 1;
            } else {
                next = 0;
            }
            polygonFromDotArea = getPolygonFromDotArea(Ox, Oy, polygonX, polygonY, polygonFromDotArea, i, next);
        }
        System.out.println(polygonFromDotArea);

        System.out.println();
        DecimalFormat df = new DecimalFormat("#.00");
        if (df.format(polygonArea).equals(df.format(polygonFromDotArea))) {
            System.out.println("Dot in polygon");
        } else {
            System.out.println("Dot not in polygon");
        }
    }

    private static double getPolygonFromDotArea(int ox, int oy, int[] polygonX, int[] polygonY, double polygonFromDotArea, int i, int next) {
        double distA = getDistAB(ox, oy, polygonX[i], polygonY[i]);
        double distB = getDistAB(ox, oy, polygonX[next], polygonY[next]);
        double distC = getDistAB(polygonX[i], polygonY[i], polygonX[next], polygonY[next]);
        double halfPerimeter = getHalfPerimeter(distA, distB, distC);
        double triangleArea = getTriangleArea(halfPerimeter, distA, distB, distC);
        System.out.println("i: " + i + " triangle area: " + triangleArea);
        polygonFromDotArea += triangleArea;
        return polygonFromDotArea;
    }

    private static double getDistAB(int Ax, int Ay, int Bx, int By) {
        return Math.sqrt((Ax - Bx) * (Ax - Bx) + (Ay - By) * (Ay - By));
    }

    private static double getHalfPerimeter(double distA, double distB, double distC) {
        return (distA + distB + distC) / 2;
    }

    private static double getTriangleArea(double halfPerimeter, double distA, double distB, double distC) {
        return Math.sqrt(halfPerimeter * (halfPerimeter - distA) * (halfPerimeter - distB) * (halfPerimeter - distC));
    }
}
