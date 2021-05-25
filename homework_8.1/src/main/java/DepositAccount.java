import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DepositAccount extends BankAccount {
    private LocalDate lastIncome;


    @Override
    public double getAmount() {
        System.out.println("У Вас на депозитном счете " + this.getAccount() + " Валюты");
        return this.getAccount();
    }

    @Override
    public boolean put(double amountToPut) {
        if (amountToPut > 0) {
            this.setAccount(this.getAccount() + amountToPut);
            System.out.println("На Ваш счет зачислено " + amountToPut + " валюты.");
            lastIncome = LocalDate.now();
            return true;
        }
        return false;
    }

    @Override
    public boolean take(double amountToTake) {
        if (lastIncome.isBefore(lastIncome.plusMonths(1)) && amountToTake > this.getAccount()) {
            System.out.println("Вы не можете снять денег больше, чем у Вас есть. Либо пытаетесь снять менее чем через месяц после пополнения.");
            return false;
        }
        else {
            this.setAccount(this.getAccount() - amountToTake);
            System.out.println("С Вашего счета списано " + amountToTake + " валюты.");
            return false;
        }
    }
}

