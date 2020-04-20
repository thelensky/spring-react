package com.thelensky.springreact.record;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordsRepository extends JpaRepository<Record, Long> {
}
