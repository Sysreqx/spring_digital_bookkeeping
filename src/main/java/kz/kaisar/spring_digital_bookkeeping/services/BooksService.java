package kz.kaisar.spring_digital_bookkeeping.services;

import kz.kaisar.spring_digital_bookkeeping.models.Book;
import kz.kaisar.spring_digital_bookkeeping.models.Person;
import kz.kaisar.spring_digital_bookkeeping.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findOne(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public Optional<Person> getBookOwner(int id) {
        return Optional.ofNullable(findOne(id).getOwner());
    }


    @Transactional
    public void release(int id) {
        findOne(id).setOwner(null);
    }

    @Transactional
    public void assign(int id, Person selectedPerson) {
        findOne(id).setOwner(selectedPerson);
    }
}
