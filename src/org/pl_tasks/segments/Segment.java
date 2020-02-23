

package org.pl_tasks.segments;

public class Segment {
    // класс - описание отрезка по координатам двух точек
    double[] point1 = new double[2];
    double[] point2 = new double[2];

    Segment(double[] point1, double[] point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public double distance() {
        // расчет длины отрезка
        double dx = this.point1[0] - this.point2[0];
        double dy = this.point1[1] - this.point2[1];
        return Math.round( Math.sqrt(dx * dx + dy * dy) * 100 ) * 0.01;
    }
}


