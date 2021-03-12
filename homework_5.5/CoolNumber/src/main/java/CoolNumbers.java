import com.github.curiousoddman.rgxgen.RgxGen;

import java.util.*;

public class CoolNumbers {

    public static List<String> generateCoolNumbers() {

        ArrayList<String> numbers = new ArrayList<>();
        String[] letters = {"А","В","Е","К","М","Н","О","Р","С","Т","У","Х"};
        int[] number = {111,222,333,444,555,666,777,888,999};
        for (String letter : letters) {
            for (int digit : number) {
                for (String letter2 : letters) {
                    for (String letter3 : letters){
                        for (int reg = 1; reg <= 199; reg++ ){
                            numbers.add(String.format("%s%d%s%s%d", letter , digit , letter2 , letter3 , reg));
                        }
                    }
                }
            }
        }

//        RgxGen rgxGen = new RgxGen("^[АВЕКМНОРСТУХ]([0]{3}|[1]{3}|[2]{3}|[3]{3}|[4]{3}|[5]{3}|[6]{3}|[7]{3}|[8]{3}|[9]{3})[АВЕКМНОРСТУХ]{2}(1?[0-9]{2})");
//        ArrayList<String> numbers = new ArrayList<>();
//
//        Random rnd = new Random(genericSeed());
//        for (int i = 0; i < 2000000; i++ ) {
//            String number = rgxGen.generate(rnd);
//            while (numbers.contains(number)) {
//                number = rgxGen.generate(rnd);
//            }
//            numbers.add(number);
//            System.out.println("строка номер " + i);
//        }
        return numbers;

    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        long scriptStartTime = System.nanoTime();
        boolean result = false;
        String outPut = "Номер - " + number;
        if (list.contains(number)) {
            outPut += " найден, ";
            result = true;
        }
        else {
            outPut += " не найден, ";
        }
        long scriptWorkTime = System.nanoTime() - scriptStartTime;
        outPut += "поиск занял - " + scriptWorkTime + " нс, был использован метод поиска перебором";
        System.out.println(outPut);
        return result;
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {
        Collections.sort(sortedList);
        long scriptStartTime = System.nanoTime();
        boolean result = false;
        String outPut = "Номер - " + number;
        if (Collections.binarySearch(sortedList,number) >= 0 ) {
            outPut += " найден, ";
            result = true;
        }
        else {
            outPut += " не найден, ";
        }
        long scriptWorkTime = System.nanoTime() - scriptStartTime;
        outPut += "поиск занял - " + scriptWorkTime + " нс, был использован метод бинарного поиска";
        System.out.println(outPut);
        return result;
    }


    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        long scriptStartTime = System.nanoTime();
        boolean result = false;
        String outPut = "Номер - " + number;
        if (hashSet.contains(number)) {
            outPut += " найден, ";
            result = true;
        }
        else {
            outPut += " не найден, ";
        }
        long scriptWorkTime = System.nanoTime() - scriptStartTime;
        outPut += "поиск занял - " + scriptWorkTime + " нс, был использован метод поиска по HashSet";
        System.out.println(outPut);
        return result;
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        TreeSet<String> treeSetNumbers = new TreeSet<>(treeSet);
        long scriptStartTime = System.nanoTime();
        boolean result = false;
        String outPut = "Номер - " + number;
        if (treeSetNumbers.contains(number)) {
            outPut += " найден, ";
            result = true;
        }
        else {
            outPut += " не найден, ";
        }
        long scriptWorkTime = System.nanoTime() - scriptStartTime;
        outPut += "поиск занял - " + scriptWorkTime + " нс, был использован метод поиска по TreeSet";
        System.out.println(outPut);
        return result;
    }

    public static long genericSeed() {
        int a = 156823;
        int b = 341651;
        int randomTwo = b + (int) (Math.random() * a);
        int random = a + (int) (Math.random() * b);
        return randomTwo + random;

    }

    public static HashSet convertToHashSet(List<String> list) {
        HashSet<String> setNumbers = new HashSet<>(list);
        return  setNumbers;
    }

    public static TreeSet convertToTreeSet(List<String> list) {
        TreeSet<String> setNumbers = new TreeSet<>(list);
        return  setNumbers;
    }
}
