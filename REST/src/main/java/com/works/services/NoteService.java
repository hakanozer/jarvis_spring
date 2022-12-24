package com.works.services;

import com.works.entities.Note;
import com.works.entities.Product;
import com.works.repositories.NoteRepository;
import com.works.utils.REnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NoteService {

    final NoteRepository repository;

    public ResponseEntity list() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        hm.put(REnum.result, repository.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }


    public ResponseEntity save(Note note) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            repository.save(note);
            hm.put(REnum.status, true);
            hm.put(REnum.result, note);
            return new ResponseEntity(hm, HttpStatus.OK );
        }catch (Exception ex) {
            hm.put(REnum.status, false);
            if ( ex.getMessage().contains("ConstraintViolationException") ) {
                hm.put(REnum.message, "Title Unique");
            }
            hm.put(REnum.result, note);
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST );
        }
    }

}
