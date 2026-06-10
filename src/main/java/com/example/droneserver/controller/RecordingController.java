package com.example.droneserver.controller;

import com.example.droneserver.dto.RecordingDto;
import com.example.droneserver.service.RecordingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/recordings")
public class RecordingController {
    private final RecordingService service;
    public RecordingController(RecordingService service){this.service=service;}

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(required = false) String cameraId, @RequestParam(required = false) String start, @RequestParam(required = false) String end){
        if(cameraId != null && start != null && end != null){
            Instant from = Instant.parse(start);
            Instant to = Instant.parse(end);
            List<RecordingDto> records = service.findByCameraRange(cameraId, from, to);
            return ResponseEntity.ok(records);
        }
        List<RecordingDto> records = service.listAll();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/metadata")
    public ResponseEntity<?> metadata(@PathVariable String id){
        return service.findById(id).map(rec-> ResponseEntity.ok(rec)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/generate-playback-url")
    public ResponseEntity<?> generate(@PathVariable String id, @RequestBody java.util.Map<String,Object> req){
        return ResponseEntity.ok(java.util.Map.of("playbackUrl","https://cdn.example.com/"+id+"/playlist.m3u8?sig=abc","expiresAt","2030-01-01T00:00:00Z"));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RecordingDto dto){
        RecordingDto result = service.create(dto);
        return ResponseEntity.status(201).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody RecordingDto dto){
        return service.update(id, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/timelines/{siteId}")
    public ResponseEntity<?> timelines(@PathVariable String siteId, @RequestParam(required = false) String from, @RequestParam(required = false) String to){
        return ResponseEntity.ok(java.util.Map.of("siteId",siteId,"range",java.util.Map.of("from",from,"to",to),"tracks",List.of()));
    }
}
