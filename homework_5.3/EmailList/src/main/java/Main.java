import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Main {
    public static final String WRONG_EMAIL_ANSWER = "Неверный формат email";

    public static void main(String[] args) throws IOException {

        EmailList emailList = new EmailList();

        System.out.println("Введите одну из команд работы со списком. Если Вы не знаете команд, введите HELP");
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();

            List<String> array = Arrays.asList(input.split("\\s"));
            String email = "";
            if (array.size() > 1){
                email = array.get(1).toLowerCase(Locale.ROOT);
            }


            switch (array.get(0)){
                case ("HELP"):
                    emailList.help();
                    break;
                case ("ADD"): {
                    emailList.add(email);
                    break;
                }
                case ("LIST"): {
                    emailList.getSortedEmails();
                    break;
                }
                default: System.out.println(("Такой команды несуществует, используйте команду HELP."));
            }

        }
    }
}

