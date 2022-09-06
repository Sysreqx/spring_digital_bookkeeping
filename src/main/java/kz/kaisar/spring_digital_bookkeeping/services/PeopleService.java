package kz.kaisar.spring_digital_bookkeeping.services;

import kz.kaisar.spring_digital_bookkeeping.models.Book;
import kz.kaisar.spring_digital_bookkeeping.models.Person;
import kz.kaisar.spring_digital_bookkeeping.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;


@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

//    public List<Book> getBooksByPersonId(int id) {
//
//        Optional<Person> person = peopleRepository.findById(id);
//
//        Hibernate.initialize(person.get().getBooks());
//
//        return person.get().getBooks();
//    }

    public List<Book> getBooksByPersonIdWithIsExpiredDate(int id) {

        Optional<Person> person = peopleRepository.findById(id);

        Hibernate.initialize(person.orElseThrow().getBooks());

        long milliseconds;
        long days;

        for (Book book : person.get().getBooks()) {
            if (book.getTakenDate() != null) {
                milliseconds = (new Date().getTime() - book.getTakenDate().getTime());
                days = TimeUnit.MILLISECONDS.toDays(milliseconds);

                if (days > 10)
                    book.setExpired(true);
            }
        }

        return person.get().getBooks();
    }

    public Optional<Object> getPersonByName(String name) {
        return Optional.ofNullable(peopleRepository.findByNameIgnoreCase(name));
    }

}
