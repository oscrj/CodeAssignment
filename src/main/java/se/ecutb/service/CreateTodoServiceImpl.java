package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ecutb.data.IdSequencers;
import se.ecutb.data.TodoRepository;
import se.ecutb.model.AbstractTodoFactory;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.time.LocalDate;

@Component
public class CreateTodoServiceImpl extends AbstractTodoFactory implements CreateTodoService {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private IdSequencers idSequencers;

    @Override
    public Todo createTodo(String taskDescription, LocalDate deadLine, Person assignee) throws IllegalArgumentException {

        if(taskDescription == null || deadLine == null || assignee == null){
            throw new IllegalArgumentException("Missing an argument!");
        }
        return super.createTodoItem(idSequencers.nextTodoId(),taskDescription,deadLine,assignee);
    }

    @Override
    public Todo createTodo(String taskDescription, LocalDate deadLine) throws IllegalArgumentException {

        if(taskDescription == null || deadLine == null){
            throw new IllegalArgumentException("Missing an argument!");
        }
        return super.createTodoItem(idSequencers.nextTodoId(), taskDescription, deadLine);
    }
}
