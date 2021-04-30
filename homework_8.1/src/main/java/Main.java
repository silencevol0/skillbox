import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
//        while (true) {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            String input = reader.readLine();
            BankAccount bankAccount = new BankAccount();
            CardAccount cardAccount = new CardAccount();
            DepositAccount depositAccount = new DepositAccount();
            bankAccount.getAmount();
            cardAccount.getAmount();
            bankAccount.put(513);
            cardAccount.put(33);
            bankAccount.getAmount();
            cardAccount.getAmount();
            bankAccount.send(cardAccount, 550);
            bankAccount.getAmount();
            cardAccount.getAmount();


        //}
    }
}
