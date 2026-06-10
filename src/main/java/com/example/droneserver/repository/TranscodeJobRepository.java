package com.example.droneserver.repository;

import com.example.droneserver.model.TranscodeJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranscodeJobRepository extends JpaRepository<TranscodeJob,String> {
}
