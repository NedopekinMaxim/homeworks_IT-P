//1 задание лабораторной работы №1: является ли число простым
public class Primes {

    // test
    public static void main(String[] args) {
        for (int i = 2; i <= 100; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }

    }

    // метод проверяет является ли число простым
    public static boolean isPrime(int n) {
        for (int i = 2; i < n; i++)
            if (n % i == 0) {
                return false;
            }
        return true;

    }

}