package com.teamviewer.exercise.repository;

import com.teamviewer.exercise.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
