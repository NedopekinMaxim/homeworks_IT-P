import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.geom.Rectangle2D;
import java.awt.event.*;
import java.io.*;


public class FractalExplorer {
    // размер экрана, является шириной и высотой для состояния программ
    private int displaySize;
    // ссылка для обновления отображения в разных методах в процессе вычисления фрактала
    private JImageDisplay display;
    // ссылка на базовый класс для отображения других видов фракталов в будущем
    private FractalGenerator fractal;
    // диапазон комплексной плоскости, которая выводится на экран
    private Rectangle2D.Double rectangle;
    private static final String TITLE = "Навигатор фракталов";
    private static final String RESET = "Сброс";
    private static final String SAVE = "Сохранить";
    private static final String CHOOSE = "Выбрать фрактал :";
    private static final String COMBOBOX_CHANGE = "comboBoxChanged";
    private static final String SAVE_ERROR = "Ошибка при сохранении изображения";
    private JComboBox comboBox; // выбор фрактала
    private JButton resetButton; // сброс
    private JButton saveButton; // кнопка save


    // конструктор, принимает размер отображения в качестве аргумента
    public FractalExplorer(int size) {
        displaySize = size;
        fractal = new Mandelbrot();
        rectangle = new Rectangle2D.Double();
        fractal.getInitialRange(rectangle);
        display = new JImageDisplay(displaySize, displaySize); // квадрат
    }

    // инициализация интерфейса Swing
    public void createAndShowGUI() {
        // создаем отработчик действий
        ActionsHandler actionsHandler = new ActionsHandler();

        //Frame
        JFrame frame = new JFrame(TITLE); //название
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // закрыть

        //Display
        display = new JImageDisplay(displaySize, displaySize);
        frame.add(display, BorderLayout.CENTER); //границы отображения содержимого окна

        //Panels
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        //label
        JLabel label = new JLabel(CHOOSE);
        topPanel.add(label);

        //ComboBox
        comboBox = new JComboBox();
        comboBox.addItem(new Mandelbrot());
        comboBox.addItem(new Tricorn());
        comboBox.addItem(new BurningShip());
        comboBox.addActionListener(actionsHandler); // COMBOBOX_CHANGE
        topPanel.add(comboBox, BorderLayout.NORTH);


        //Save Button
        saveButton = new JButton(SAVE);
        saveButton.addActionListener(actionsHandler);
        bottomPanel.add(saveButton, BorderLayout.WEST);

        //Reset Button
        resetButton = new JButton(RESET);
        resetButton.addActionListener(actionsHandler);
        bottomPanel.add(resetButton, BorderLayout.EAST);


        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(topPanel, BorderLayout.NORTH);

        //Mouse Handler
        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);

        //Misc
        frame.pack(); // подготовка всего к отображению
        frame.setVisible(true); // делает видимым содержимое
        frame.setResizable(false); // запрет на изменение окна
        drawFractal(); // рисовать фрактал
    }

    /*
     * Метод для отрисовки фрактала
     * Окрашивает пиксели в зависимотси от итерации
     */
    private void drawFractal() {

        for (int x = 0; x < displaySize; x++) {
            for (int y = 0; y < displaySize; y++) {

                double xCoord = FractalGenerator.getCoord(rectangle.x, rectangle.x +
                        rectangle.width, displaySize, x);
                double yCoord = FractalGenerator.getCoord(rectangle.y, rectangle.y +
                        rectangle.height, displaySize, y);

                // кол-во итерация для соответствующих координат в области отражения фрактала
                int iteration = fractal.numIterations(xCoord, yCoord);

                if (iteration == -1) { // точка не выходит за границы, ставим черный
                    display.drawPixel(x, y, 0);
                } else {
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    display.drawPixel(x, y, rgbColor);
                }
            }
        }
        display.repaint(); //обновляем JimageDisplay
    }

    // кнопка сброса - сброс
    private class ResetHandler implements ActionListener {

        // метод созданный по умолчанию
        @Override
        public void actionPerformed(ActionEvent e) {
            // возвращение фрактала к начальному положению
            fractal.getInitialRange(rectangle);
            drawFractal();
        }
    }

    // приближение области кликом мышки по фракталу
    private class MouseHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            // Координаты клика
            int x = e.getX();
            int y = e.getY();
            // Новые координаты центра
            double xCoord = FractalGenerator.getCoord(rectangle.x,
                    rectangle.x + rectangle.width, displaySize, x);
            double yCoord = FractalGenerator.getCoord(rectangle.y,
                    rectangle.y + rectangle.height, displaySize, y);

            // Устанавливаем центр в точку по которой был клик и приближаем
            fractal.recenterAndZoomRange(rectangle, xCoord, yCoord, 0.5);
            // перерисовываем
            drawFractal();
        }
    }


    //Имплементируем интерфейс ActionListener для обработки событий
    class ActionsHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case RESET -> { //сброс
                    fractal.getInitialRange(rectangle);
                    drawFractal();
                }
                case COMBOBOX_CHANGE -> { //выбор
                    JComboBox source = (JComboBox) e.getSource();
                    fractal = (FractalGenerator) source.getSelectedItem();
                    assert fractal != null;
                    fractal.getInitialRange(rectangle);
                    display.clearImage();
                    drawFractal();
                }
                case SAVE -> { //сохранение
                    JFileChooser fileChooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                    fileChooser.setFileFilter(filter);
                    fileChooser.setAcceptAllFileFilterUsed(false);
                    if (fileChooser.showSaveDialog(display) == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        String path = file.toString();
                        if (path.length() == 0) return;
                        if (!path.contains(".png")) {
                            file = new File(path + ".png");
                        }
                        try {
                            javax.imageio.ImageIO.write(display.getImage(), "png", file);
                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(display, exception.getMessage(), SAVE_ERROR, JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        // размер окна
        FractalExplorer fractal = new FractalExplorer(800);
        // рисование gui
        fractal.createAndShowGUI();
        // оторисовка
        fractal.drawFractal();
    }

}