package com.thelensky.springreact.persistence.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
@Data
public class Record {
  @Id
  @GeneratedValue
  private Long id;
  @CreationTimestamp
  private LocalDate datePublish;
  private String author;
  private String record;


  public Record() {
  }
}
