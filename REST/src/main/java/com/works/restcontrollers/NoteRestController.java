package com.works.restcontrollers;

import com.works.entities.Note;
import com.works.entities.Product;
import com.works.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteRestController {

    final NoteService noteService;

    @GetMapping("/list")
    public ResponseEntity list() {
        return noteService.list();
    }

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Note note) {
        return noteService.save(note);
    }


}
