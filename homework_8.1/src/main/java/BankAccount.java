public class BankAccount {

  private double account = 0;

  public double getAmount() {
    System.out.println("У Вас на банковском счете " + account + " Валюты");
    return account;
  }

  public boolean put(double amountToPut) {
    if (amountToPut > 0) {
      account += amountToPut;
      System.out.println("На Ваш счет зачислено " + amountToPut + " валюты.");
      return true;
    }
    else  return false;
  }

  public boolean take(double amountToTake) {
    if (amountToTake > account){
      System.out.println("Вы не можете снять денег больше, чем у Вас есть.");
      return false;
    }
    else {
      account -= amountToTake;
      System.out.println("С Вашего счета списано " + amountToTake + " валюты.");
      return true;
    }
  }

  public boolean send(BankAccount receiver, double amount) {
    if (take(amount)) {
      receiver.put(amount);
      return true;
    }
    else return false;
  }

}
