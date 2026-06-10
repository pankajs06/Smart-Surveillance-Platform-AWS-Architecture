package com.example.droneserver.repository;

import com.example.droneserver.model.Recording;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface RecordingRepository extends JpaRepository<Recording,String> {
    List<Recording> findByCameraIdAndStartAtBetween(String cameraId, Instant from, Instant to);
}
