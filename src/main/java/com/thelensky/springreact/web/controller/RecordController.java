package com.thelensky.springreact.web.controller;

import com.thelensky.springreact.persistence.dao.RecordsRepository;
import com.thelensky.springreact.persistence.model.Record;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecordController {
  private final RecordsRepository repository;

  public RecordController(RecordsRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/record")
  public List<Record> all() {
    return repository.findAll();
  }

  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(
      @PathVariable
          Long id) {
    repository.deleteById(id);
    return ResponseEntity.noContent()
                         .build();
  }

  @PostMapping("/record")
  public Record newRecord(
      @RequestBody
          Record newRecord) {
    return repository.save(newRecord);
  }
}
