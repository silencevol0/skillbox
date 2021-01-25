import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailList {

    TreeSet<String> emails = new TreeSet<>();

    public void help() {
        System.out.println("ADD [Ваша почта] - добавить почту в базу данных. (Обязательно правильно написать синтаксис почты)\n" +
                "LIST - Выводит список всех актуальных почтовых ящиков.");
    }

    public void add(String email) {
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        if (emails.contains(email.toLowerCase(Locale.ROOT))){
            System.out.println("Этот Email уже добавлен.");

        }
        else if (matcher.find()) {
            emails.add(email.toLowerCase(Locale.ROOT));
            System.out.println("Ваш Email - " + email + " добавлен в список.");
        }

        else {
            System.out.println(Main.WRONG_EMAIL_ANSWER);
        }
    }

    public List<String> getSortedEmails() {
        List <String> sortedEmails = new ArrayList<>();
        Iterator<String> itr = emails.iterator();
        while (itr.hasNext()) {
            String next = itr.next();
            sortedEmails.add(next);
            System.out.println(next);
        }
        return sortedEmails;
    }

}
