import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task4 {
    public static void main(String[] args) {
        System.out.println("task 1");
        System.out.println(bessie(10, 7, "hello my name is Bessie and this is my essay"));

        System.out.println("task 2");
        System.out.println(split("((()))"));
        System.out.println(split("((()))(())()()(()())"));
        System.out.println(split("((())())(()(()()))"));
        System.out.println();

        System.out.println("task 3");
        System.out.println(toCamelCase("hello_edabit"));
        System.out.println(toCamelCase("is_modal_open"));
        System.out.println(toSnakeCase("helloEdabit"));
        System.out.println(toSnakeCase("toSnakeCase"));
        System.out.println();

        System.out.println("task 4");
        System.out.println("4 задание: " + overTime(new double[] {9, 17, 30, 1.5}));
        System.out.println();

        System.out.println("task 5");
        System.out.println(BMI("205 pounds", "73 inches"));
        System.out.println(BMI("55 kilos", "1.65 meters"));
        System.out.println(BMI("154 pounds", "2 meters"));
        System.out.println();

        System.out.println("task 6");
        System.out.println(bugger(39));
        System.out.println(bugger(999));
        System.out.println(bugger(4));
        System.out.println();

        System.out.println("task 7");
        System.out.println(toStarShorthand("abbvvccc"));
        System.out.println(toStarShorthand("77777geff"));
        System.out.println(toStarShorthand("abc"));
        System.out.println(toStarShorthand(""));
        System.out.println();

        System.out.println("task 8");
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM."));
        System.out.println(doesRhyme("You are off to the races", "a splendid day."));
        System.out.println(doesRhyme("and frequently do?", "you gotta move."));
        System.out.println(doesRhyme("and frequently deo?", "you gotta move."));
        System.out.println();

        System.out.println("task 9");
        System.out.println(trouble(1222345, 12345));
        System.out.println(trouble(666789, 12345667));
        System.out.println(trouble(33789, 12345337));
        System.out.println();

        System.out.println("task 10");
        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));
        System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", '$'));
        System.out.println(countUniqueBooks("ZZABCDEF", 'Z'));
        System.out.println();
    }

    //1. В строке n слова до k символов без пробелов
    public static String bessie(int n, int k, String input) {
        var result = new StringBuilder(); //выводимая строка
        int x = 0; // длина 1 строки из k символов

        for (var word : input.split(" ")) {     // проходимся по словам в input
            if (x != 0 && x + word.length() <= k) {
                result.append(" ");             // добавляет пробел после слова, если строка <k
                x += word.length();
            } else {                            // если превышаем k
                if (x != 0)
                    result.append("\n");        // переход на новую строку
                x = word.length();              // длина новой строки начинается с длины 1 слова
            }
            result.append(word); // если x = 0 сразу result.append(word)
        }
        return result.toString();
    }

    // 2 кластеры скобок
    public static List<String> split(String s) {
        char[] str = s.toCharArray();
        int count = 0; // кол-во скобок в 1 кластере
        StringBuilder result = new StringBuilder(); // кластер
        List<String> skobki = new ArrayList<>(); // лист из кластеров

        for (char symbol : str) {
            if (symbol == '(')
                count += 1; // открываем скобки
            else
                count -= 1; // закрываем скобки

            result.append(symbol); // добавляем в кластер

            if (count == 0) { // все все скобки закрыты
                skobki.add(result.toString()); // добавляем полученный кластер в лист
                result.setLength(0); // переменную для кластера обнуляем
            }
        }
        return skobki;
    }


    // 3 camel_case -> camelCase
    public static String toCamelCase(String str)
    {
        StringBuilder s = new StringBuilder(str);

        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == '_') // когда найден пробел
            {
                String a = String.valueOf(s.charAt(i+1)); // буква за пробелом
                s.replace(i, i+2, a.toUpperCase()); // заменяем пробел и букву за ним на большую букву за пробелом
            }
        }

        return s.toString();
    }

    // snakeCase -> snake_case
    public static String toSnakeCase(String str)
    {
        StringBuilder s = new StringBuilder(str);
        for (int i = 0; i < s.length(); i++)
        {
            if (String.valueOf(s.charAt(i)).equals(String.valueOf(s.charAt(i)).toUpperCase())) // когда найдена большая буква
            {
                String a = String.valueOf(s.charAt(i)); //больша буква
                s.replace(i, i+1, "_" + a.toLowerCase()); // заменяем большую букву на "_" + маленькую букву
            }
        }

        return s.toString();
    }


    // 4 зп (начало дня, конец дня, почасовая ставка, множитель)
    public static String overTime(double[] massive)
    {
        // результат, просто считаем по формуле
        double payDay = (massive[1] - massive[0] + ((massive[1] - 17) + Math.abs(massive[1] - 17)) / 2 * (massive[3] - 1)) * massive[2];
        // выводим резльтат округленный до сотых с $
        return String.format("$" + "%.2f", payDay);
    }

    //5 индекс имт
    public static String BMI(String weight, String height) {

        var weight1 = weight.split(" "); // делим переменную по пробелам (до пробела нужное нам число)
        double w_d = Double.parseDouble(weight1[0]); // берем число
        w_d *= "kilos".equals(weight1[1]) ? 1 : 0.453; // переводим кг в фунты если написано что кг

        var height1 = height.split(" ");
        double h_d = Double.parseDouble(height1[0]);
        h_d *= "meters".equals(height1[1]) ? 1 : 0.0254; // метры в дюймы если дюймы

        double res = w_d / (h_d * h_d); // считаем имт и выводим округленный до десятый результат

        if (res < 18.5) {
            return String.format("%.1f Underweight", res);
        }
        if (res < 25) {
            return String.format("%.1f Normal weigth", res);
        }
        return String.format("%.1f Overweight", res);
    }

    //6 сколько нужно сделать раз {переумножает, пока не останется 1 цифра в числе}
    public static int bugger (int number) {
        int count = 0; // кол-во операций
        while (number > 9) { // пока number не цифра, а число
            int tempNumber = 1; // производение цифр
            while (number > 0) {
                tempNumber *= number % 10; // умножаем цифры
                number /= 10; // уменьшаем
            }
            number = tempNumber; // number становится текущим произведением
            count++;
        }
        return count;
    }

    //7 звездная стенография
    public static StringBuilder toStarShorthand(String line) {
        StringBuilder NewLine = new StringBuilder(); // выводиммая строка
        line += " "; // чтобы не выйти за границы в цикле
        char last = line.charAt(0); // символ i-1
        int count = 1;
        for (int i = 1; i < line.length(); i++) {
            char a = line.charAt(i); // символ i
            if (a == last) count++; // если i-1 = i
            else if (count != 1) { //если не б, а бббббб
                NewLine.append(last).append('*').append(count);
                count = 1;
            } else {
                NewLine.append(last); // если не бббббб, а б
            }
            last = a; // приравниваем предыдущий к текущему
        }
        return NewLine;
    }

    //8 рифма
    public static boolean doesRhyme(String firstLine, String secondLine) {
        // если глассные в последних словах строк равны, то рифма есть, проверяем с помощью рег. выраж.
        String w1 = firstLine.substring(firstLine.lastIndexOf(' ')).toLowerCase().replaceAll("[^aeiouy]", "");
        String w2 = secondLine.substring(secondLine.lastIndexOf(' ')).toLowerCase().replaceAll("[^aeiouy]", "");
        return w2.equals(w1);
    }

    //9 если 3 символа подряд в 1 и 2 символа в 2
    public static boolean trouble (int num1, int num2) {
        String num1_str = String.valueOf(num1);
        String num2_str = String.valueOf(num2);
        for (int i = 0; i < num1_str.length(); i++) {
            String current_1 = Character.toString(num1_str.charAt(i)).repeat(3); // количество повторений в 1
            String current_2 = Character.toString(num1_str.charAt(i)).repeat(2); // количество повторений в 2
            if (num1_str.contains(current_1) && num2_str.contains(current_2)) {
                return true;
            }
        }
        return false;
    }

    //10 уникальные буквы между разделителями
    public static int countUniqueBooks(String line, char marker) {
        Set<Character> characters = new HashSet<>(); // в HashSet хранятся уникальные элементы
        boolean flag = false;
        // если встречен marker, все символы буду добавляться в set до 2-го marker
        for (int i = 0; i < line.length(); i++) {
            char currentCharacter = line.charAt(i); //
            if (currentCharacter == marker) {
                flag = !flag;
            } else if (flag)
                characters.add(currentCharacter); // добавляем что в Set в HashSet
        }
        return characters.size();
    }

    $AA$BBCATT$C$$B$
}


