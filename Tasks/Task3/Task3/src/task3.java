import java.util.Arrays;

public class task3 {

    // TESTS
    public static void main(String[] args) {

        System.out.println(".............TEST 1.............");
        System.out.println(solutions(1, 0, -1)); // -> 2
        System.out.println(solutions(1, 0, 0));  // -> 1
        System.out.println(solutions(1, 0, 1));  // -> 0

        System.out.println(".............TEST 2.............");
        System.out.println(findZip("all zip files are zipped")); // -> 18
        System.out.println(findZip("all zip files are zipped and again zipped")); // -> 18
        System.out.println(findZip("all zip files are bum")); // -> -1

        System.out.println(".............TEST 3.............");
        System.out.println(checkPerfect(6)); // -> true
        System.out.println(checkPerfect(97)); // -> false

        System.out.println(".............TEST 4.............");
        System.out.println(flipEndChars("Cat, dog, and mouse.")); // ->  ".at, dog, and mouseC"
        System.out.println(flipEndChars("ada")); // -> Two's a pair.
        System.out.println(flipEndChars("Ada")); // -> adA
        System.out.println(flipEndChars("z")); // -> Incompatible.

        System.out.println(".............TEST 5.............");
        System.out.println(isValidHexCode("#CD5C5C")); // -> true
        System.out.println(isValidHexCode("CD5C5C")); // -> false

        System.out.println(".............TEST 6.............");
        System.out.println(same(new int[]{1, 2, 3, 2, 4, 6, 4}, new int[]{1, 2, 3, 90, 20})); // -> true
        System.out.println(same(new int[]{9, 8, 7, 6}, new int[]{4, 4, 3, 1})); // -> false

        System.out.println(".............TEST 7.............");
        System.out.println(isKaprekar(100)); // ->  false
        System.out.println(isKaprekar(45)); // ->  true

        System.out.println(".............TEST 8.............");
        System.out.println(longestZero("01100001011000")); // -> 0000
        System.out.println(longestZero("100100100")); // -> 00
        System.out.println(longestZero("11111")); // -> ""

        System.out.println(".............TEST 9.............");
        System.out.println(nextPrime(12)); // -> 13
        System.out.println(nextPrime(24)); // -> 29
        System.out.println(nextPrime(11)); // -> 11

        System.out.println(".............TEST 10.............");
        System.out.println(rightTriangle(3, 4, 5)); // ➞ true
        System.out.println(rightTriangle(70, 130, 110)); // ➞ false
    }


    // SOURCE
    // 1. Сколько решений у кв. урав.
    public static String solutions(int a, int b, int c) {
        int D = b * b - 4 * a * c;
        if (D > 0) {
            return "2";
        } else if (D == 0) {
            return "1";
        }
        return "0";
    }


    // 2. Индекс 1 символа 2-го zip, либо -1
    public static int findZip(String s) {
        s = s.replaceFirst("zip", "***");
        return s.indexOf("zip");
        // indexOf выводит -1 если zip в строке нет
    }


    // 3. Если сумма делителей числа = числу, то true
    public static boolean checkPerfect(int numb) {
        int sum = 0;
        for (int i = 1; i < numb - 1; i++) {
            if (numb % i == 0)
                sum += i;
        }
        return numb == sum;
    }


    //  4. Меняет последнюю и первую символы местами, если они не одинаковые
    public static String flipEndChars(String s) {
        char firstSymb = s.charAt(0);
        char lastSymb = s.charAt(s.length()-1);

        if (s.length() < 2)
            return "Incompatible.";
        if (lastSymb == firstSymb)
            return "Two's a pair";

        // Форматируем строку в StringBuilder, чтобы можно было пользоваться setCharAt
        StringBuilder sb = new StringBuilder(s);

        sb.setCharAt(0, lastSymb);
        sb.setCharAt(s.length()-1, firstSymb);

        return sb.toString();
    }


    // 5. Правильность шестнад-го кода
    public static boolean isValidHexCode(String s) {
        boolean result = s.matches("^#[0-9a-fA-F]{6}");
        return result;

    }


    // 6. Если оба массива включают равное кол-во уникальных эл-в -> true
    public static boolean same(int[] arr1, int[] arr2) {
        // по умолчанию берем, что весь массив идеальный, если находим повторения, вычитаем дубликаты
        int uniq1 = arr1.length; // кол-во уникальных эл-в

        /*
        Сортируем массив, чтобы, дубликаты следующими за оригиналами в массиве
        Например: массив 1 4 5 6 2 4 1 4 1 -> 1 1 1 2 4 4 4 5 6
        Теперь легко удалить лишниее: 1 1 1 2 4 4 4 5 6 -> 1 2 4 5 6 => уникальные эл-ты первонач. массива
        Но в методе, мы удалем не элементы из массива, а вычитаем из общего кол-ва элементов уникальные
         */
        Arrays.sort(arr1);

        // если за следующим эл-м идет дубликат, вычитаем из общего кол-ва эл-в uniq1 уникальные
        for (int i = 0; i < arr1.length - 1; i++) {
            if (arr1[i] == arr1[i + 1])
                uniq1 -= 1;
        }

        int uniq2 = arr2.length;
        Arrays.sort(arr2);

        for (int i = 0; i < arr2.length - 1; i++) {
            if (arr2[i] == arr2[i + 1])
                uniq2 -= 1;
        }

        return (uniq1 == uniq2);
    }


    // 7. Число Капрекара. Является ли. Если число нечетное, правая половинка больше левой.
    public static boolean isKaprekar(int n) {
        String nSqr = Integer.toString(n * n); // число в квадрате, переводим в строку, чтобы разделить на половинки
        String pS1 = nSqr.substring(0, nSqr.length() / 2); // 1 половинка
        String pS2 = nSqr.substring(nSqr.length() / 2); // 2 половинка
        // если число 89297, 1 половинка - 89, 2 - 297 => условие задачи выполняется

        int pI1 = Integer.parseInt(pS1);
        int pI2 = Integer.parseInt(pS2);

        return (pI1 + pI2 == n);

    }


    // 8. Макс. последовательность 0
    public static String longestZero(String s) {

        /*
        Добавил "." на конец строки, чтобы, когда программа заходит в цикл
        while (цикл, который вычитывает длинну найденной последовательности "0" в строке)
        он не выходил за пределы, если строка заканчивается на "0".
        Например: на входе строка "1110100", она заканчивается на "00", цикл работает, пока видит "0",
        на втором "0" он пойдет дальше и сломает программу, т.к. выйдет за пределы строки.
        Если стоит "." (или любой другой символ) он ее увидит и прекратит ход по строке.
        */
        s = s + ".";

        int sub = 0; // длина найденной последовательности
        int max = 0; // max последовательность

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                int j = i;
                do {
                    sub += 1;
                    j++;
                } while (s.charAt(j) == '0');
                if (sub > max) {
                    max = sub;
                }
                sub = 0;
            }
        }

        return "0".repeat(max);
    }


    // 9. Сл. простое число или текущее
    public static int nextPrime(int n) {
        if (isSimple(n))
            return n;
        else
            do {
                n++;
            } while (!isSimple(n));
        return n;
    }
    
    private static boolean isSimple(int n) {
        if (n < 2)
            return false;
        for (int i = 2; i <= Math.sqrt(n); i++) { // можно проверять до корня из числа
            if (n % i == 0)
                return false;
        }
        return true;
    }


    // 10. Является ли прямоугольным треугольником
    public static boolean rightTriangle(int x, int y, int z) {
        int[] edges = {x, y, z};
        Arrays.sort(edges);
        // сортируем up, самая большая сторона - гипотенуза, проверяем т. Пифагора
        if (edges[2] * edges[2] == edges[1] * edges[1] + edges[0] * edges[0])
            return true;
        else
            return false;
    }

}
