import java.sql.SQLOutput;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {

    TreeMap <String , TreeSet<String>> phonesBook = new TreeMap<>();



    public void addContact(String phone, String name) {
        Set <String> keys = phonesBook.keySet();

        Pattern pattern = Pattern.compile("[0-9]");
        Matcher mather = pattern.matcher(phone);

        if (!mather.find()) {
           return;
        }

        pattern = Pattern.compile("[а-яА-Яa-zA-Z]");
        mather = pattern.matcher(name);

        if (!mather.find()) {
            return;
        }

        if (keys.contains(name)) {
            TreeSet<String> value = phonesBook.get(name);
            value.add(phone);
            phonesBook.put(name, value );
            return;
        }
        else {
            Collection<TreeSet <String>> values = phonesBook.values();
            for(TreeSet<String> item : values) {
                if (item.contains(phone)) {
                    for(Map.Entry<String, TreeSet<String>> keyList : phonesBook.entrySet()) {
                        if (keyList.getValue() == item) {
                            String key = keyList.getKey();
                            TreeSet<String> value = phonesBook.get(key);
                            if (value.size() > 1) {
                                value.remove(phone);
                                phonesBook.put(key,value);
                            }
                            else {
                                phonesBook.remove(key);
                            }
                            TreeSet<String> phoneSet = new TreeSet<>();
                            phoneSet.add(phone);
                            phonesBook.put(name, phoneSet);
                            return;
                        }

                    }
                }
            }
            TreeSet<String> phoneSet = new TreeSet<>();
            phoneSet.add(phone);
            phonesBook.put(name, phoneSet);
            return;
        }
        // проверьте корректность формата имени и телефона
        // если такой номер уже есть в списке, то перезаписать имя абонента
    }

    public String getNameByPhone(String phone) {
        Collection<TreeSet <String>> values = phonesBook.values();

            for(TreeSet<String> item : values) {
                if (item.contains(phone)) {
                    for (Map.Entry<String, TreeSet<String>> key : phonesBook.entrySet()) {
                        if (key.getValue() == item) {
                            return key.getKey() + " - " + phone;
                        }
                    }
                }
            }
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        return "";
    }

    public Set<String> getPhonesByName(String name) {
        TreeSet <String> output = new TreeSet<>();
        if (phonesBook.containsKey(name)) {
           TreeSet<String> value = phonesBook.get(name);
           for (String phone : value){
               output.add(name + " - " + phone);
           }
        }
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        return output;
    }

    public Set<String> getAllContacts() {
        Set <String> keys = phonesBook.keySet();
        TreeSet <String> output = new TreeSet<>();
        for (String name : keys) {
            TreeSet<String> value = phonesBook.get(name);
            for (String phone : value) {
                output.add(name + " - " + phone);
            }
        }
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        return output;
    }

}