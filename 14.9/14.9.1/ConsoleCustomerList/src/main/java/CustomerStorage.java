import com.google.i18n.phonenumbers.PhoneNumberUtil;

import java.util.HashMap;
import java.util.Map;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class CustomerStorage {
    private final Map<String, Customer> storage;
    protected PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new incorrectNumberOfComponents("Incorrect input format. Correct format - " +
                    "add Василий Петров vasily.petrov@gmail.com +79215637722, try again and use correct format");
        }
        if (!isValidEmailAddressOrPhoneNumber(components[INDEX_EMAIL],true)) {
            throw new incorrectEmailFormat("The entered email does not meet the standards, check the correctness of filling.");
        }
        if (!isValidEmailAddressOrPhoneNumber(components[INDEX_PHONE],false)) {
            throw new incorrectPhoneNumberFormat("The entered phone number does not meet the standards of the number in Russia.");
        }
        String name = firstUpperCase(components[INDEX_NAME]) + " " + firstUpperCase(components[INDEX_SURNAME]);
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        if (storage.containsKey(name)) {
            storage.remove(name);
        }
        else if (storage.containsKey(checkRemoveValueToUpperCase(name))) {
            storage.remove(checkRemoveValueToUpperCase(name));
        }
        else {
            throw new personIsNotFound("The card of the person to delete was not found, check the correctness of the entered data.");
        }
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }

    private String firstUpperCase(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
    }

    private String checkRemoveValueToUpperCase(String names) {
        String correctName = "";
        String[] everyNames = names.split("\\s");
        for (String everyName : everyNames) {
            correctName += (firstUpperCase(everyName)) + " ";
        }
        return correctName.trim();
    }

    private boolean isValidEmailAddressOrPhoneNumber(String emailOrPhoneNumber, boolean mode) {
        boolean result = true;
        if (mode) {
            try {
                InternetAddress emailAddr = new InternetAddress(emailOrPhoneNumber);
                emailAddr.validate();
            } catch (AddressException ex) {
                result = false;
            }
        }
        else if (!phoneNumberUtil.isPossibleNumber(emailOrPhoneNumber, "RU")) {
            result = false;
        }
        return result;
    }

}