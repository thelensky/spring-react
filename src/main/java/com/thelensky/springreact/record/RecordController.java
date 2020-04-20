package com.thelensky.springreact.record;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecordController {
  private final RecordsRepository repository;

  public RecordController(RecordsRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/record")
  List<Record> all() {
    return repository.findAll();
  }

  @PostMapping("/record")
  Record newRecord(
      @RequestBody
          Record newRecord) {
    return repository.save(newRecord);
  }
}
