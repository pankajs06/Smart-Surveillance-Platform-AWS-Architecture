package com.example.droneserver.repository;

import com.example.droneserver.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent,String> {
}
