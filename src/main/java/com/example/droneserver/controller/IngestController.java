package com.example.droneserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ingest")
public class IngestController {

    @PostMapping("/announce")
    public ResponseEntity<?> announce(@RequestBody AnnounceRequest req){
        return ResponseEntity.ok(Map.of("accepted",true,"ingestId","ingest-"+System.currentTimeMillis()));
    }

    @PostMapping("/signed-url")
    public ResponseEntity<?> signedUrl(@RequestBody Map<String,Object> req){
        return ResponseEntity.ok(Map.of("url","https://ingest.example.com/push/abc","method","POST","expiresAt","2030-01-01T00:00:00Z"));
    }

    public static class AnnounceRequest { public String agentId; public String cameraId; public List<Map<String,Object>> streams; }
}
