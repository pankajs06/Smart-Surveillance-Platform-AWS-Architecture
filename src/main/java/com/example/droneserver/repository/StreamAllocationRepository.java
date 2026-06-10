package com.example.droneserver.repository;

import com.example.droneserver.model.StreamAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreamAllocationRepository extends JpaRepository<StreamAllocation,String> {
}
