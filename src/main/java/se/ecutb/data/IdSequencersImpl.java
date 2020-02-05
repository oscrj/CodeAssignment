package se.ecutb.data;

import org.springframework.stereotype.Component;

@Component
public class IdSequencersImpl implements IdSequencers {

    private int personIdCounter;
    private int todoIdCounter;

    @Override
    public int nextPersonId() {
        return ++personIdCounter;
    }

    @Override
    public int nextTodoId() {
        return ++todoIdCounter;
    }

    @Override
    public void clearPersonId() {
        this.personIdCounter = 0;
    }

    @Override
    public void clearTodoId() {
        this.todoIdCounter = 0;
    }
}
