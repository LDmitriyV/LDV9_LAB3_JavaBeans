/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ldv9_lab3;

/**
 *
 * @author dmitriy
 */
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.TransactionStatus;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

import java.util.List;

/**
 * Реализация интерфейса работы с таблицей Person
 *
 */
 public class PhonesDAO implements IPhonesDAO {

    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Phones phones) { // Реализация вставки новой записи
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("INSERT INTO phones (brand, model, capacity, price) VALUES(?,?,?,?)",
                new Object[]{phones.getBrand(), phones.getModel(), phones.getCapacity(), phones.getPrice()});
    }

    @Override
    public void append(String Brand, String Model, int capacity, int price) {  // Реализация добавления новой записи
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("INSERT INTO phones (brand, model, capacity, price) VALUES(?,?,?,?)", 
                new Object[]{Brand, Model, capacity, price});
    }

    @Override
    public void append(String Brand, String Model, int capacity) {  // Реализация добавления новой записи
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("INSERT INTO phones (brand, model, capacity) VALUES(?,?,?)", 
                new Object[]{Brand, Model, capacity});
    }
    

    
    @Override
    public void deleteByModel(String model) {  // Реализация удаления записей по модели
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE FROM phones WHERE model LIKE ?", new Object[]{'%' + model + '%'});
    }

    @Override
    public void delete(final String Brand, final String Model) {  // Реализация удаления записей с указанным брендом и моделью
        TransactionTemplate tt = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
        tt.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    JdbcTemplate jt = new JdbcTemplate(dataSource);
                    jt.update("DELETE from phones where brand= ? AND model = ?", new Object[]{Brand, Model});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }

    @Override
    public void deleteAll() {  // Реализация удаления всех запией
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("DELETE from phones");
    }

    @Override
    public void update(String oldModel, String newModel) {  // Изменение записей в таблице
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        jt.update("UPDATE phones SET model = ? WHERE model = ?", new Object[]{newModel, oldModel});
    }

    @Override
    public Phones findByPrice(int price) { // Реализация поиска записи с заданной ценой
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        List<Phones> phones = jt.query("SELECT * FROM phones WHERE price = ?",
                new Object[]{price}, new PhonesRowMapper());
        return phones.size() > 0 ? phones.get(0) : null;
    }
    
    @Override
    public List<Phones> priceSelect(int scannedPriceMax, int scannedPriceMax2) { // Реализация поиска записи с заданными ценами
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt.query("SELECT * FROM phones WHERE price > ? AND price < ?", 
                new Object[]{scannedPriceMax, scannedPriceMax2}, new PhonesRowMapper());
    }
    
    @Override
    public List<Phones> capacitySelect(int scannedCapacityMax, int scannedCapacityMax2) { // Реализация поиска записи с заданными ценами
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt.query("SELECT * FROM phones WHERE capacity > ? AND capacity < ?", 
                new Object[]{scannedCapacityMax, scannedCapacityMax2}, new PhonesRowMapper());
    }
    
    @Override
    public List<Phones> findByBrand(String Brand) {  // Реализация поиска записей по бренду
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM phones WHERE brand LIKE ?";
        List<Phones> phoneslist = jt.query(sql, new Object[]{'%' + Brand + '%'}, new PhonesRowMapper());
        return phoneslist;
    }

    @Override
    public List<Phones> select(String Brand, String Model) {  // Реализация получения записей с заданным брендом и моделью
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt.query("select  * from phones where brand = ? AND model = ?",
                new Object[]{Brand, Model}, new PhonesRowMapper());
    }

    @Override
    public List<Phones> selectAll() {  // Реализация получения всех записей
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        return jt.query("select * from phones", new PhonesRowMapper());
    }

}
