package com.example.TastyKing.repository;

import com.example.TastyKing.entity.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<Tables, Long> {

    List<Tables> findByTablePosition_TablePositionID(Long tablePositionID);
}
