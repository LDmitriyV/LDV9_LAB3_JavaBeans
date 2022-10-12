/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ldv9_lab3;

/**
 *
 * @author dmitriy
 */
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Класс загрузки данных в объект Phones из считанной записи таблицы БД
 *
 */
public class PhonesRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException {
        PhonesResultSetExtractor extractor = new PhonesResultSetExtractor();
        return extractor.extractData(rs);
    }

    /**
     * Класс загрузки данных в объект данных из считанной записи таблицы
     *
     */
    class PhonesResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet rs) throws SQLException {
            Phones phones = new Phones();
            phones.setId(rs.getInt("id"));
            phones.setBrand(rs.getString("Brand"));
            phones.setModel(rs.getString("Model"));
            phones.setCapacity(rs.getInt("capacity"));
            phones.setPrice(rs.getInt("price"));
            return phones;
        }
    }
}