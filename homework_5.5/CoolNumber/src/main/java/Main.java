import com.github.curiousoddman.rgxgen.RgxGen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> numbers = CoolNumbers.generateCoolNumbers();
        System.out.println("База номеров создана, все номера уникальны.");
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            CoolNumbers.bruteForceSearchInList(numbers,input);
            CoolNumbers.binarySearchInList(numbers, input);
            CoolNumbers.searchInHashSet(CoolNumbers.convertToHashSet(numbers), input);
            CoolNumbers.searchInTreeSet(CoolNumbers.convertToTreeSet(numbers), input);
        }
    }
}
