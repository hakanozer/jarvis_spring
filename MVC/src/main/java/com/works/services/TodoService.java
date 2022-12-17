package com.works.services;

import com.works.entities.Admin;
import com.works.entities.Todo;
import com.works.repositoies.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public Page<Todo> allTodos(String page) {
        int p = 0;
        try {
            p = Integer.parseInt(page);
            p = p < 0 ? 0 : p;
        }catch (Exception ex) {}
        Pageable pageable = PageRequest.of(p, 3);
        return todoRepository.findAll(pageable);
    }

    public boolean todoDelete( String stTid ) {
        boolean status = false;
        try {
            long longTid = Long.parseLong(stTid);
            todoRepository.deleteById(longTid);
            status = true;
        }catch (Exception ex) {}
        return status;
    }

}
