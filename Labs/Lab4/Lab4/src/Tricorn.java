import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator {

    public static final int MAX_ITERATIONS = 2000; // Максимальное количество итераций

    public void getInitialRange(Rectangle2D.Double rectangle) {
        // Значения для фрактала Tricorn
        rectangle.x = -2;
        rectangle.y = -2;
        rectangle.height = 4;
        rectangle.width = 4;
    }

    public int numIterations(double x, double y) {

        // Вычисляем Zn = Zn-1 ^ 2 + c
        // c - фрактал с координатами (x, y), который мы показываем
        // Вычисляется пока Z < 4 или iteration < MAX_ITERATIONS=2000

        int iteration = 0;

        double zreal = 0.0;
        double zimaginary = 0.0;
        while (iteration < MAX_ITERATIONS && zreal * zreal + zimaginary * zimaginary < 4) {
            double zRealNew = zreal * zreal - zimaginary * zimaginary + x;
            double zComplexNew = -2 * zreal * zimaginary + y;
            zreal = zRealNew;
            zimaginary = zComplexNew;
            iteration += 1;
        }
        if (iteration == MAX_ITERATIONS) {
            return -1;
        }
        return iteration;
    }
    public String toString() {
        return "Tricorn";
    }
}
