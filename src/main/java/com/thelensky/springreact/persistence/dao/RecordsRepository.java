package com.thelensky.springreact.persistence.dao;

import com.thelensky.springreact.persistence.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordsRepository extends JpaRepository<Record, Long> {
}
