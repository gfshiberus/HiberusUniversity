package com.hiberus.repositorios;

import com.hiberus.modelos.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPizza extends JpaRepository<Pizza,Long> {
}
