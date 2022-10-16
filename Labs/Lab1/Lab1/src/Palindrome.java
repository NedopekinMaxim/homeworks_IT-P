//2 задание лабораторной работы №1: проверка является ли слово палиндромом
public class Palindrome {

    //test
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            System.out.println(isPalindrome(s));
        }
        //System.out.println(isPalindrome("BAAB"));
        //System.out.println(isPalindrome("Efeewfw"));
        //System.out.println(isPalindrome("ABBA"));
    }

    // метод делает реверс строки
    public static String reverseString(String s){
        String rs = "";
        for (int i = s.length()-1; i >= 0; i--){
            rs += s.charAt(i);
        }
        return rs;
    }

    // метод проверяет является ли строка палиндромом
    public static boolean isPalindrome(String s){
        return s.equals(reverseString(s));
    }
}

