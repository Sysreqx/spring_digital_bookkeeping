package kz.kaisar.spring_digital_bookkeeping.repositories;

import kz.kaisar.spring_digital_bookkeeping.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findByNameContaining(String name);
}
