import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InPlaceArrayReversal {
    public static void reverseInPlace(int[] values) {
        int left = 0;
        int right = values.length - 1;
        while (left < right) {
            int temporary = values[left];
            values[left] = values[right];
            values[right] = temporary;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        StringBuilder input = new StringBuilder();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) input.append(scanner.nextLine()).append(' ');
        }

        List<Integer> parsed = new ArrayList<>();
        Matcher matcher = Pattern.compile("-?\\d+").matcher(input.toString());
        while (matcher.find()) parsed.add(Integer.parseInt(matcher.group()));

        int[] values = parsed.stream().mapToInt(Integer::intValue).toArray();
        reverseInPlace(values);
        System.out.println(java.util.Arrays.toString(values));
    }
}
