package com.works.controllers;

import com.works.entities.Todo;
import com.works.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class DashboardController {

    final TodoService todoService;
    final CacheManager cacheManager;

    @GetMapping("/dashboard")
    public String dashboard(@RequestParam(defaultValue = "0") String p,  Model model) {
        Page<Todo> page = todoService.allTodos(p);
        if ( page.getContent().size() == 0 ) {
            page = todoService.allTodos("0");
        }
        int totalPage = page.getTotalPages();
        int[] pages = new int[totalPage];
        model.addAttribute("list", page.getContent() );
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page.getPageable().getPageNumber() );
        cacheManager.getCache("allProduct").clear();
        return "dashboard";
    }

    @PostMapping("/addTodo")
    public String addTodo(Todo todo) {
        todoService.addTodo(todo);
        return "redirect:/dashboard";
    }

    @GetMapping("/todoDelete/{tid}")
    public String todoDelete(@PathVariable String tid) {
        todoService.todoDelete(tid);
        return "redirect:/dashboard";
    }



}
