package com.example.droneserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/internal")
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<?> health(){
        return ResponseEntity.ok(Map.of("status","ok","components",Map.of("ingest","ok","transcoder","ok","storage","ok")));
    }
}
