package com.thelensky.springreact.record;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class Record {
  @Id
  @GeneratedValue
  private Long id;
  private String author;
  private LocalDate datePublish;
  private String record;

  public Record() {
  }

  public Record(String author, LocalDate datePublish, String record) {
    this.datePublish = datePublish;
    this.author = author;
    this.record = record;
  }
}
