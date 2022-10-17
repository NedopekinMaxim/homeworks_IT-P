
// двумерный класс точки
public class Point2d {

    // координата X
    public double xCoord;

    // координата Y
    public double yCoord;

    // Конструктор инициализации
    public Point2d(double x, double y) {
        xCoord = x;
        yCoord = y;
    }

    // Конструктор по умолчанию
    public Point2d() {
        //Вызовите конструктор с двумя параметрами и определите источник
        this(0, 0);
    }

    // Возвращение координаты X
    public double getX() {
        return xCoord;
    }

    // Возвращение координаты Y
    public double getY() {
        return yCoord;
    }

    // Установка координаты X
    public void setX(double val) {
        xCoord = val;
    }

    // Установка координаты Y
    public void setY(double val) {
        yCoord = val;
    }
}