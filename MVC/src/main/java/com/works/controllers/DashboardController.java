package com.works.controllers;

import com.works.entities.Todo;
import com.works.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class DashboardController {

    final TodoService todoService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @PostMapping("/addTodo")
    public String addTodo(Todo todo) {
        todoService.addTodo(todo);
        return "redirect:/dashboard";
    }


}
