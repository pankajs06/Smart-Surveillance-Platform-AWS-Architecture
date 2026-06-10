package com.example.droneserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/onprem/agents")
public class OnPremController {

    @PostMapping
    public ResponseEntity<?> registerAgent(@RequestBody AgentRequest req){
        return ResponseEntity.status(201).body(Map.of("id","agent-1","agentId",req.agentId));
    }

    @PostMapping("/{id}/heartbeat")
    public ResponseEntity<?> heartbeat(@PathVariable String id, @RequestBody Map<String,Object> body){
        return ResponseEntity.ok(Map.of("ok",true));
    }

    @GetMapping("/{id}/capabilities")
    public ResponseEntity<?> caps(@PathVariable String id){
        return ResponseEntity.ok(Map.of("agentId",id,"capabilities",new String[]{"transcode","edge-record","webrtc-proxy"}));
    }

    public static class AgentRequest { public String agentId; public String siteId; public String ip; public Object metadata; }
}
