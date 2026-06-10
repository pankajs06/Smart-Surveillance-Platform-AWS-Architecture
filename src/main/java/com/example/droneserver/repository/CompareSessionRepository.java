package com.example.droneserver.repository;

import com.example.droneserver.model.CompareSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompareSessionRepository extends JpaRepository<CompareSession,String> {
}
