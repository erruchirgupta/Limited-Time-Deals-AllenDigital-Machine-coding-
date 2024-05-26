package com.ruchir.demo.repository.repo.readonly;

import com.ruchir.demo.repository.model.Products;
import com.ruchir.demo.utils.ReadOnlyRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@ReadOnlyRepository
public interface ProductsReadRepository extends JpaRepository<Products, Integer> {

    Optional<Products> findByName(String name);
}
