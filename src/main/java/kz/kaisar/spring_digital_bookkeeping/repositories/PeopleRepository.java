package kz.kaisar.spring_digital_bookkeeping.repositories;

import kz.kaisar.spring_digital_bookkeeping.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Person findByNameIgnoreCase(String firstname);
}
