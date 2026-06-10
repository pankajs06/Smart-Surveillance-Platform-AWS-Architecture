package com.example.droneserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/internal")
public class InternalController {

    @PostMapping("/streams/allocate")
    public ResponseEntity<?> allocate(@RequestBody AllocateRequest req){
        return ResponseEntity.status(201).body(Map.of("allocationId","alloc-"+System.currentTimeMillis(),"proxyEndpoint","rtmp://proxy/stream","expiresAt","2030-01-01T00:00:00Z"));
    }

    @PostMapping("/streams/release")
    public ResponseEntity<?> release(@RequestBody Map<String,String> req){
        return ResponseEntity.ok(Map.of("released",true));
    }

    @PostMapping("/transcode/jobs")
    public ResponseEntity<?> transcode(@RequestBody Map<String,Object> req){
        return ResponseEntity.status(201).body(Map.of("jobId","job-"+System.currentTimeMillis(),"status","queued"));
    }

    @GetMapping("/transcode/jobs/{id}/status")
    public ResponseEntity<?> transcodeStatus(@PathVariable String id){
        return ResponseEntity.ok(Map.of("jobId",id,"status","running","progress",10));
    }

    @PostMapping("/cdn/sign")
    public ResponseEntity<?> sign(@RequestBody Map<String,Object> req){
        return ResponseEntity.ok(Map.of("signedUrl","https://cdn.example.com/signed/playlist.m3u8","expiresAt","2030-01-01T00:00:00Z"));
    }

    public static class AllocateRequest{ public String cameraId; public String agentId; public List<String> targetProfiles; public String protocol; public String sessionId; }
}
