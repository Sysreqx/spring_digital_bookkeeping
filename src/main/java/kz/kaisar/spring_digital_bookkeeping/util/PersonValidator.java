package kz.kaisar.spring_digital_bookkeeping.util;

import kz.kaisar.spring_digital_bookkeeping.dao.PersonDAO;
import kz.kaisar.spring_digital_bookkeeping.models.Person;
import kz.kaisar.spring_digital_bookkeeping.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(@NonNull Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(@NonNull Object o,@NonNull Errors errors) {
        Person person = (Person) o;

        if (peopleService.getPersonByName(person.getName()).isPresent())
            errors.rejectValue("name", "", "Person with this name is already exist");
    }
}
