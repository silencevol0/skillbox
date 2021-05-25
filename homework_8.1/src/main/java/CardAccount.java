public class CardAccount extends BankAccount {




    @Override
    public double getAmount() {
        System.out.println("У Вас на карточном счете " + this.getAccount() + " Валюты");
        return this.getAccount();
    }

    @Override
    public boolean put(double amountToPut) {
        if (amountToPut > 0) {
            this.setAccount(this.getAccount() + amountToPut);
            System.out.println("На Ваш счет зачислено " + amountToPut + " валюты.");
            return true;
        }
        else return false;
    }

    @Override
    public boolean take(double amountToTake) {
        if (amountToTake > this.getAccount()) {
            System.out.println("Вы не можете снять денег больше, чем у Вас есть.");
            return false;
        } else {
            this.setAccount(this.getAccount() - amountToTake - (amountToTake * 0.01));
            System.out.println("С Вашего счета списано " + amountToTake + " валюты.");
            return true;
        }
    }
}