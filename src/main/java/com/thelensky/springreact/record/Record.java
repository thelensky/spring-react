package com.thelensky.springreact.record;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Record {
  @Id
  @GeneratedValue
  private Long id;
  private String author;
  private LocalDate datePublish;
  private String record;

  public Record() {
  }

  public Record(String author, String record, String datePublish) {
    this.author = author;
    this.record = record;
    this.datePublish = LocalDate.parse(datePublish);

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Record record1 = (Record) o;
    return id.equals(record1.id) && Objects.equals(author,
                                                   record1.author) &&
           datePublish.equals(record1.datePublish) && Objects.equals(record,
                                                                     record1.record);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id,
                        author,
                        datePublish,
                        record);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public LocalDate getDatePublish() {
    return datePublish;
  }

  public void setDatePublish(LocalDate datePublish) {
    this.datePublish = datePublish;
  }

  public String getRecord() {
    return record;
  }

  public void setRecord(String record) {
    this.record = record;
  }

  @Override
  public String toString() {
    return "Record{" + "id=" + id + ", author='" + author + '\'' +
           ", datePublish=" + datePublish + ", record='" + record + '\'' + '}';
  }
}
