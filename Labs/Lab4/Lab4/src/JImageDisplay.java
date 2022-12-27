import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent {

    private BufferedImage image;

    // конструктор инициализирует объект BufferedImage новым изображением c w,h
    // TYPE_INT_RGB - rgb формат цветов для изображения
    public JImageDisplay(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Dimension dimension = new Dimension(width, height); // размер
        super.setPreferredSize(dimension); // отобразить в интерфейсе все изображение
    }

    // метод для отрисовки изображения
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // чтобы объекты отображались парвильно
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null); // отрисовка
    }

    // данный метод устанавливает все пиксели в черный цвет
    public void clearImage() {
        for (int i=0; i < image.getHeight(); i++ ) {
            for (int j = 0; j < image.getWidth(); j ++) {
                drawPixel(i,j,0); // (черный - 0)
            }
        }
    }

    // данный метод устанавливает все пиксели в определенный цвет
    public void drawPixel (int x, int y, int rgbColor) {
        image.setRGB(x,y, rgbColor);
    }

    public BufferedImage getImage() {
        return image;
    }
}