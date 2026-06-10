package com.example.droneserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CompareController {

    @PostMapping("/api/compare")
    public ResponseEntity<?> createCompare(@RequestBody CompareRequest req){
        return ResponseEntity.accepted().body(Map.of("compareSessionId","cmp-"+System.currentTimeMillis(),"status","queued"));
    }

    @GetMapping("/api/compare/{id}/status")
    public ResponseEntity<?> compareStatus(@PathVariable String id){
        return ResponseEntity.ok(Map.of("compareSessionId",id,"status","ready","left",Map.of("playbackUrl","https://cdn.example.com/left.m3u8"),"right",Map.of("playbackUrl","https://cdn.example.com/right.m3u8")));
    }

    public static class CompareRequest{ public String leftRecordingId; public String rightRecordingId; public String leftStart; public String rightStart; }
}
