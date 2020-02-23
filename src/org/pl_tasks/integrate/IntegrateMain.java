/**
 * Задача: Реализуйте метод integrate, который будет вычислять определенный интеграл методом левых прямоугольников.
 * Функия передается в первом агрументе, отрезок ограничиавается 2-мя координатами по оси х заданным вторым и третьим
 * аргументами. При необходимости опишите необходимые классы и интерфейсы.
 * Возьмите шаг 0.1.
 *
 * @Andrey_Zenkov  hi_andy@mail.ru
 */

package org.pl_tasks.integrate;

import java.util.function.DoubleUnaryOperator; // интерфейс функции принимает один параметр double, на выходе double


public class IntegrateMain {

    static double integrate(DoubleUnaryOperator func, double a, double b) {
        /* интегрирование заданной функции на заданном интервале по формуле левых прямоугольников (шаг 0.1) */
        double result = 0, h = 0.1;
        double n = (b - a) / h;

        for(int i = 0; i < n; i++) {
            result += func.applyAsDouble(a + h*(i + 0.5));
        }
        result *= h;
        return result;

    }

    public static void main(String[] args) {
        System.out.println("x -> 1, 0, 10 // = " + integrate(x -> 1, 0, 10));
    }

}
