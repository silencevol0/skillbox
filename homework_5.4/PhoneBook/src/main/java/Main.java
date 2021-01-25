import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {

        PhoneBook phoneBook = new PhoneBook();

        System.out.println("Начните работу со списком контактов. Введите имя, номер или команду LIST");

        String name = "";
        String phone = "";

        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            String replaceNumberWords = input.replaceAll("[^а-яА-Яa-zA-Z0-9]", "");

            if (input.toUpperCase(Locale.ROOT).equals("LIST")) {
                Set<String> list = phoneBook.getAllContacts();
                for (String contact : list) {
                    System.out.println(contact);
                }
                if (list.isEmpty()) {
                    System.out.println("В вашей телефонной книге еще нет записей.");
                }
                name = "";
                phone = "";
                continue;
            }
            if (!replaceNumberWords.equals(input)) {
                System.out.println("Некорректный ввод.");
                continue;
            }

            String replaceNumber = input.replaceAll("[^0-9]", "");
            String replaceWords = input.replaceAll("[^а-яА-Яa-zA-Z]", "");
            String findSpace = input.replaceAll("\\s", "");

            if (replaceNumber.equals(input)) {
                phone = replaceNumber;
                String output = phoneBook.getNameByPhone(phone);
                if (!output.isEmpty()) {
                    String replaceName = output.replaceAll("[^a-zA-Zа-яА-Я]", "");
                    System.out.println("Этот номер уже есть в списке контактов. Имя владельца - " + replaceName);
                    System.out.println("Если Вы хотите перезаписать Имя владельца номера  - '" + phone + "' , введите 'yes', если нет - нажмите Enter.");
                    Scanner scanner = new Scanner(System.in);
                    String equals = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
                    if (equals.equals("yes")) {
                        System.out.println("Введите новое Имя для номера - " + phone);
                        Scanner scanner1 = new Scanner(System.in);
                        String nameKey = scanner1.nextLine();
                        phoneBook.addContact(phone, nameKey);
                        System.out.println("Контакт перезаписан. Новое имя - " + nameKey + ". Номер телефона - " + phone + ".");
                        phone = "";
                        name = "";
                    }
                } else if (findSpace.isEmpty()) {
                    System.out.println("Начните работу со списком контактов. Введите имя, номер или команду LIST");
                } else {
                    System.out.println("Такого номера нет в телефонной книге.");
                    System.out.println("Если хотите добавить номер - '" + phone + "' в контакты введите 'yes', если нет - нажмите Enter.");
                    Scanner scanner = new Scanner(System.in);
                    String equals = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
                    if (equals.equals("yes")) {
                        System.out.println("Введите Имя для контакта с номером - " + phone);
                        Scanner scanner1 = new Scanner(System.in);
                        String nameContact = scanner1.nextLine();
                        String replaceNameContact = nameContact.replaceAll("[^а-яА-Яa-zA-Z]", "");
                        if (replaceNameContact.equals(nameContact)) {
                            phoneBook.addContact(phone, nameContact);
                            System.out.println("Создан контакт - '" + nameContact + "' под номером - '" + phone + "'.");
                            System.out.println("Продолжите работу со списком контактов. Введите имя, номер или команду LIST");
                            phone = "";
                            name = "";
                        } else {
                            System.out.println("Неправильный формат ввода.");
                        }
                    } else {
                        System.out.println("Начните работу со списком контактов. Введите имя, номер или команду LIST");
                    }
                }
            } else if (replaceWords.equals(input)) {
                name = replaceWords;
                Set<String> output = phoneBook.getPhonesByName(name);
                if (output.isEmpty()) {
                    System.out.println("Контакт " + name + " не найден. Если хотите продолжить создание нового контакта введите - 'yes', если нет - нажмите Enter");
                    Scanner scanner = new Scanner(System.in);
                    String equals = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
                    if (equals.equals("yes")) {
                        System.out.println("Введите номер для контакта с Именем - " + name);
                        Scanner scanner1 = new Scanner(System.in);
                        String phoneRegex = scanner1.nextLine();
                        String replacePhoneRegex = phoneRegex.replaceAll("[^0-9]", "");
                        if (replacePhoneRegex.equals(phoneRegex)) {
                            phoneBook.addContact(replacePhoneRegex, name);
                            System.out.println("Создан контакт - '" + name + "' под номером - '" + replacePhoneRegex + "'.");
                            System.out.println("Продолжите работу со списком контактов. Введите имя, номер или команду LIST");
                            phone = "";
                            name = "";
                        }
                        else {
                            System.out.println("Неправильный формат ввода.");
                            continue;
                        }
                    }
                    else {
                        System.out.println("Начните работу со списком контактов. Введите имя, номер или команду LIST");
                    }
                }
                else if (findSpace.isEmpty()) {
                    System.out.println("Некорректный ввод.");
                    continue;
                }
                else if (!output.isEmpty()) {
                    System.out.println("Контакт(ы) найден(ы) - " + phoneBook.getPhonesByName(name));
                    System.out.println("Если хотите добавить номер для контакта - " + name + ", введите - 'yes', если нет - нажмите Enter");
                    Scanner scanner = new Scanner(System.in);
                    String equals = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
                    if (equals.equals("yes")) {
                        System.out.println("Введите новой номер для контакта - " + name);
                        Scanner scanner1 = new Scanner(System.in);
                        String newNumber = scanner1.nextLine();
                        String replaceNewNumber = newNumber.replaceAll("[^0-9]", "");
                        if (replaceNewNumber.equals(newNumber)) {
                            phoneBook.addContact(newNumber,name);
                            System.out.println("Создан контакт - '" + name + "' под номером - '" + newNumber + "'.");
                            System.out.println("Продолжите работу со списком контактов. Введите имя, номер или команду LIST");
                            phone = "";
                            name = "";
                        }
                        else {
                            System.out.println("Неправильный формат ввода.");
                            continue;
                        }
                    }
                    else {
                        System.out.println("Начните работу со списком контактов. Введите имя, номер или команду LIST");
                    }
                }

//            if (phone != "" && name != ""){
//                phoneBook.addContact(phone,name);
//                phone = "";
//                name = "";
                //           }
            }
            else System.out.println("Некорректный ввод.");
        }
    }
}
