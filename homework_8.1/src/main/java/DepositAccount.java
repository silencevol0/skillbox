import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DepositAccount extends BankAccount {
    private LocalDate lastIncome;
    private double account = 0;

    @Override
    public double getAmount() {
        System.out.println("У Вас на депозитном счете " + account + " Валюты");
        return account;
    }

    @Override
    public boolean put(double amountToPut) {
        if (amountToPut > 0) {
            account += amountToPut;
            System.out.println("На Ваш счет зачислено " + amountToPut + " валюты.");
            lastIncome = LocalDate.now();
            return true;
        }
        return false;
    }

    @Override
    public boolean take(double amountToTake) {
        if (lastIncome.isBefore(lastIncome.plusMonths(1)) && amountToTake > account) {
            System.out.println("Вы не можете снять денег больше, чем у Вас есть. Либо пытаетесь снять менее чем через месяц после пополнения.");
            return false;
        }
        else {
            account -= amountToTake;
            System.out.println("С Вашего счета списано " + amountToTake + " валюты.");
            return false;
        }
    }
}

