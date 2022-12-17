package com.works.services;

import com.works.entities.Admin;
import com.works.entities.Todo;
import com.works.repositoies.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    final TodoRepository todoRepository;

    public boolean addTodo(Todo todo) {
        boolean status = false;
        try {
            todoRepository.save(todo);
            status = true;
        }catch (Exception ex) {

        }
        return status;
    }

}
