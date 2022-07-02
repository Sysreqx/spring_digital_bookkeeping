package kz.kaisar.spring_digital_bookkeeping.dao;

import kz.kaisar.spring_digital_bookkeeping.models.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM Book WHERE id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)
        ).stream().findAny().orElse(null);
    }

}
