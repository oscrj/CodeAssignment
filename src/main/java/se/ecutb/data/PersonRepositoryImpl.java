package se.ecutb.data;


import org.springframework.stereotype.Component;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PersonRepositoryImpl implements PersonRepository{

    List<Person> personList = new ArrayList<>();

    @Override
    public Optional<Person> findById(int personId) {
        return personList.stream()
                .filter(person -> person.getPersonId() == personId)
                .findFirst();
    }

    @Override
    public Person persist(Person person) throws IllegalArgumentException {

        if(findByEmail(person.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email already exist");
        }

        if(!personList.contains(person)){
            personList.add(person);
        }
        return person;
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return personList.stream()
                .filter(person -> person.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public List<Person> findByAddress(Address address) {
        if(address == null){
            return personList.stream()
                    .filter(person -> person.getAddress() == null)
                    .collect(Collectors.toList());
        }
        return personList.stream()
                .filter(person -> person.getAddress() != null)
                .filter(person -> person.getAddress().equals(address))
               .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByCity(String city) {
       List<Person> listOfPerson = personList.stream()
               .filter(person -> person.getAddress() != null)
               .filter(person -> person.getAddress().getCity().equalsIgnoreCase(city))
               .collect(Collectors.toList());
       return listOfPerson;
    }

    @Override
    public List<Person> findByLastName(String lastName) {
        return personList.stream()
                .filter(person -> person.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findByFullName(String fullName) {
        return personList.stream()
                .filter(person -> (person.getFirstName() + " " + person.getLastName()).equalsIgnoreCase(fullName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findAll() {
        return personList;
    }

    @Override
    public boolean delete(int personId) throws IllegalArgumentException {
       return personList.remove(findById(personId).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public void clear() {
        personList.clear();
    }
}
