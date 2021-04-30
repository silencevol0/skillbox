public class CardAccount extends BankAccount {


    double account = 0;


    @Override
    public double getAmount() {
        System.out.println("У Вас на карточном счете " + account + " Валюты");
        return account;
    }

    @Override
    public boolean put(double amountToPut) {
        if (amountToPut > 0) {
            account += amountToPut;
            System.out.println("На Ваш счет зачислено " + amountToPut + " валюты.");
            return true;
        }
        else return false;
    }

    @Override
    public boolean take(double amountToTake) {
        if (amountToTake > account) {
            System.out.println("Вы не можете снять денег больше, чем у Вас есть.");
            return false;
        } else {
            account = account - amountToTake - (amountToTake * 0.01);
            System.out.println("С Вашего счета списано " + amountToTake + " валюты.");
            return true;
        }
    }
}