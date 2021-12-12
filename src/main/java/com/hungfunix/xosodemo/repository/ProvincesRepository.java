package com.hungfunix.xosodemo.repository;

import com.hungfunix.xosodemo.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvincesRepository extends JpaRepository<Province, Long> {

}
