package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ecutb.data.IdSequencers;
import se.ecutb.data.PersonRepository;
import se.ecutb.model.AbstractPersonFactory;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

@Component
public class CreatePersonServiceImpl extends AbstractPersonFactory implements CreatePersonService{

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private IdSequencers idSequencers;

    @Override
    public Person create(String firstName, String lastName, String email) throws IllegalArgumentException {

        if(firstName == null || lastName == null || email == null){
            throw new IllegalArgumentException("Missing an argument!");
        }
            Person personToBeCreated = super.createNewPerson(idSequencers.nextPersonId(), firstName, lastName, email, null);

        return personToBeCreated;
    }

    @Override
    public Person create(String firstName, String lastName, String email, Address address) throws IllegalArgumentException {

        if(firstName == null || lastName == null || email == null || address == null){
            throw new IllegalArgumentException("Missing an argument!");
        }
        return super.createNewPerson(idSequencers.nextPersonId(),firstName, lastName, email, address);
    }
}
