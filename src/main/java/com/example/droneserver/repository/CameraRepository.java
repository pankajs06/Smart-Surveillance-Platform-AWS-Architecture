package com.example.droneserver.repository;

import com.example.droneserver.model.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CameraRepository extends JpaRepository<Camera,String> {
    List<Camera> findBySiteId(String siteId);
}
