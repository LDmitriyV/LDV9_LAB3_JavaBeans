package ldv9_lab3;

/**
 *
 * @author dmitriy
 */

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import java.util.List;
import java.util.Scanner;

class LDV9_LAB3 {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // Загрузка файла с бинами

            PhonesDAO phonesDAO = (PhonesDAO) context.getBean("phonesDAO"); // Загрузка бина доступа к таблице клиентов 

            phonesDAO.deleteAll(); // Удаление всех записей
            
            Phones phones = new Phones("Samsung", "Galaxy S20", 64, 660); // Создание нового объекта таблицы телефонов 
            phonesDAO.insert(phones); // Вставить новый объект (запись) в таблицу телефонов

            phonesDAO.insert(new Phones("Xiaomi", "12", 256, 800)); // Вставить новый объект (запись) в таблицу телефонов
            phonesDAO.insert(new Phones("Apple", "iPhone 14", 512, 1000)); // Вставить новый объект (запись) в таблицу телефонов

            Phones phones1 = phonesDAO.findByPrice(660); // Поиск записи по цене телефона
            System.out.println(phones1 != null ? phones1 : "Нет данных"); // Вывод на экран найденной записи

            phonesDAO.deleteByModel("12"); // Удаление записей по фрагменту модели
            phonesDAO.delete("Apple", "iPhone 14"); // Удаление записи по бренду и модели

            List<Phones> phoneslist = phonesDAO.findByBrand("App"); // Поиск записей по фрагменту модели
            System.out.println(phoneslist != null ? phoneslist : "Нет данных");

            phonesDAO.append("Vivo", "V25", 128, 500); // Добавлние записей
            phonesDAO.append("Huawei", "Mate 10", 128, 770);
            phonesDAO.append("Xiaomi", "Poco X4",64, 600);
            phonesDAO.append("Oppo", "Find X5", 256, 800);
            Scanner sc = new Scanner(System.in);
            System.out.print("Brand - ");
            String scannedBrand = sc.nextLine();
            System.out.print("Model - ");
            String scannedModel = sc.nextLine();
            System.out.print("Capacity - ");
            int scannedCapacity = sc.nextInt();
            phonesDAO.append(scannedBrand, scannedModel, scannedCapacity);

            phonesDAO.update("Mate 10", "P50"); // Изменение записей в таблице

            System.out.println("Данные в таблице БД:");

            List<Phones> list = phonesDAO.selectAll();
            for (Phones myPhones : list) {
                System.out.println(myPhones.getBrand() + " " + myPhones.getModel() + " " + myPhones.getCapacity()+ " " + myPhones.getPrice());
            }


            System.out.println("Вывод записей с брендом Samsung и моделью Galaxy S20:");

            list = phonesDAO.select("Samsung", "Galaxy S20");
            for (Phones myPhones : list) {
                System.out.println(myPhones.getBrand() + " " + myPhones.getModel() + " " + myPhones.getCapacity() + " " + myPhones.getPrice());
            }
            
            System.out.println("Вывод записей с брендом Samsung и моделью Note 10: (без цены)");
            
            list = phonesDAO.select("Samsung", "Note 10");
            for (Phones myPhones : list) {
                System.out.println(myPhones.getBrand() + " " + myPhones.getModel() + " " + myPhones.getCapacity());
            }
            
            System.out.println("Введите диапазон цен");
            System.out.println("Цена 1 - ");
            int scannedPriceMax = sc.nextInt();
            System.out.println("Цена 2 - ");
            int scannedPriceMax2 = sc.nextInt();
            list = phonesDAO.priceSelect(scannedPriceMax, scannedPriceMax2);
                        for (Phones myPhones : list) {
                System.out.println(myPhones.getBrand() + " " + myPhones.getModel() + " " + myPhones.getCapacity());
            }

            System.out.println("Введите диапазон памяти");
            System.out.println("Память 1 - ");
            int scannedCapacityMax = sc.nextInt();
            System.out.println("Память 2 - ");
            int scannedCapacityMax2 = sc.nextInt();
            list = phonesDAO.capacitySelect(scannedCapacityMax, scannedCapacityMax2);
                        for (Phones myPhones : list) {
                System.out.println(myPhones.getBrand() + " " + myPhones.getModel() + " " + myPhones.getCapacity());
            } 
                        
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
    }
}