import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {

    // макс. кол-во итераций
    public static final int MAX_ITERATIONS = 2000;

    // "метод позволяет генератору фракталов определить наиболее "интересную" область
    // комплексной плоскости для конкретного фрактала"
    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        // Значения для фрактала MandelBolt
        range.x = -2;
        range.y = -1.5;
        range.width = 3;
        range.height = 3;
    }

    // реализация итеративной функции для фрактала Мандельброта
    @Override
    public int numIterations(double x, double y) {

        // кол-во итераций
        int iteration = 0;
        // действительная часть
        double z_real = 0;
        // мнимая часть
        double z_imaginary = 0;


        // Вычисляем Zn = Zn-1 ^ 2 + c
        // c - фрактал с координатами (x, y), который мы показываем
        // Вычисляется пока Z < 4 или iteration < MAX_ITERATIONS=2000

        while (iteration < MAX_ITERATIONS && z_real * z_real + z_imaginary * z_imaginary < 4)
        {
            double z_realUpdated = z_real * z_real - z_imaginary * z_imaginary + x;
            double z_imaginaryUpdated = 2 * z_real * z_imaginary + y;

            z_real = z_realUpdated;
            z_imaginary = z_imaginaryUpdated;

            iteration += 1;
        }

        // если алгоритм дошел до MAX_ITERATIONS
        if (iteration == MAX_ITERATIONS)
        {
            return -1;
        }

        return iteration;
    }
    public String toString() {
        return "Mandelbrot";
    }
}


