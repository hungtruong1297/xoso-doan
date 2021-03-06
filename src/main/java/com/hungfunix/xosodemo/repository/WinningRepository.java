package com.hungfunix.xosodemo.repository;

import com.hungfunix.xosodemo.model.Winning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinningRepository extends JpaRepository<Winning, Long> {
}
