package com.example.droneserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/streams")
public class StreamController {

    @GetMapping("/available")
    public ResponseEntity<?> available(@RequestParam String cameraId,@RequestParam(required = false) String profile){
        return ResponseEntity.ok(List.of(Map.of("protocol","webrtc","profile","720p","latencyMs",500)));
    }

    @PostMapping("/watch")
    public ResponseEntity<?> watch(@RequestBody WatchRequest req){
        if("webrtc".equalsIgnoreCase(req.protocol)){
            return ResponseEntity.ok(Map.of("sessionId","s-"+System.currentTimeMillis(),"webrtcOffer",Map.of("sdp","v=0..."),"signalingUrl","wss://signaling.example.com"));
        }
        return ResponseEntity.ok(Map.of("playbackUrl","https://cdn.example.com/"+req.cameraId+"/playlist.m3u8?sig=abc"));
    }

    @PostMapping("/stop")
    public ResponseEntity<?> stop(@RequestBody Map<String,String> req){
        return ResponseEntity.ok(Map.of("stopped",true));
    }

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@RequestBody SubscribeRequest req){
        return ResponseEntity.ok(Map.of("subscriptionId","sub-"+System.currentTimeMillis(),"streams",List.of(Map.of("cameraId",req.cameraIds.get(0),"playbackUrl","https://cdn.example.com/playlist.m3u8"))));
    }

    public static class WatchRequest{ public String cameraId; public String profile; public String protocol; public Boolean preferLowLatency; }
    public static class SubscribeRequest{ public String sessionId; public List<String> cameraIds; public List<String> profiles; public String layout; }
}
