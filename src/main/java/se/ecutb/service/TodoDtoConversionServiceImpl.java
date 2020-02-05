package se.ecutb.service;

import org.springframework.stereotype.Component;
import se.ecutb.dto.TodoDto;
import se.ecutb.model.Todo;

@Component
public class TodoDtoConversionServiceImpl implements TodoDtoConversionService {

    @Override
    public TodoDto convertToDto(Todo todo) {
        TodoDto todoDto = null;

        if(todo.getAssignee() == null){
            todoDto = new TodoDto(todo.getTodoId(), todo.getTaskDescription(), todo.getDeadLine(),null, todo.isDone());
        }else {
            todoDto =
                    new TodoDto(todo.getTodoId(), todo.getTaskDescription(),
                            todo.getDeadLine(), todo.getAssignee().getPersonId(), todo.isDone());
        }
        return todoDto;
    }
}
