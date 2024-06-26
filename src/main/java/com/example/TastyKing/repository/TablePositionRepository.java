package com.example.TastyKing.repository;

import com.example.TastyKing.entity.TablePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TablePositionRepository extends JpaRepository<TablePosition, Long> {


    boolean existsByTablePosition(String tablePosition);
}
