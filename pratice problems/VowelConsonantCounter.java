import java.util.Locale;
import java.util.Scanner;

public class VowelConsonantCounter {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                int vowels = 0;
                int consonants = 0;
                for (char ch : word.toLowerCase(Locale.ROOT).toCharArray()) {
                    if ("aeiou".indexOf(ch) >= 0) vowels++;
                    else consonants++;
                }
                System.out.println("Vowels: " + vowels);
                System.out.println("Consonants: " + consonants);
            }
        }
    }
}
