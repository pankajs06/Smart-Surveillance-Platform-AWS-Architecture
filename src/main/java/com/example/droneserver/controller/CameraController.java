package com.example.droneserver.controller;

import com.example.droneserver.dto.CameraDto;
import com.example.droneserver.service.CameraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cameras")
public class CameraController {
    private final CameraService service;
    public CameraController(CameraService service){this.service=service;}

    @PostMapping
    public ResponseEntity<?> register(@RequestBody CameraDto req) {
        CameraDto result = service.create(req);
        return ResponseEntity.status(201).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(required = false) String siteId){
        List<CameraDto> result = siteId != null ? service.listBySite(siteId) : service.listAll();
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody CameraDto dto){
        return service.update(id, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
