package ldv9_lab3;
/**
 *
 * @author dmitriy
 */

import javax.sql.DataSource;
import java.util.List;


/**
 * Интерфейс работы с таблицой Phones
 *
 */
public interface IPhonesDAO {
    void setDataSource(DataSource ds); // Установка связи с данныими
    void insert(Phones phones); // Вставка новой записи
    void append(String Brand, String Model, int capacity, int price); // Добавление новой записи
    void append(String Brand, String Model, int capacity); // Добавление новой записи без цены
    void deleteByModel(String Model);// Удаление записи по модели
    void delete(String Brand, String Model); // Удаление записи с указанным брендом и моделью
    void deleteAll(); // Удаление всех запией
    void update(String oldModel, String newModel); // Изменение записей в таблице
    List<Phones> priceSelect (int scannedPriceMax, int scannedPriceMax2);
    List<Phones> capacitySelect (int scannedCapacityMax, int scannedCapacityMax2);
    Phones findByPrice(int id); // Получение записи с заданной ценой
    List<Phones> findByBrand(String Brand); // Получение записей с заданным брендом 
    List<Phones> select(String Brand, String Model); // Получение записей с заданным брендом и моделью
    List<Phones> selectAll(); // Получение всех записей
}