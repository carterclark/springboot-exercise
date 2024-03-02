package com.teamviewer.exercise.repository;

import com.teamviewer.exercise.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
