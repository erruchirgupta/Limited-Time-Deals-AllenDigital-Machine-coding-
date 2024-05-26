package com.ruchir.demo.repository.repo;

import com.ruchir.demo.repository.model.DealsConfig;
import com.ruchir.demo.repository.model.Products;
import com.ruchir.demo.utils.ReadOnlyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface DealsConfigRepository extends CrudRepository<DealsConfig, Integer> {

}
