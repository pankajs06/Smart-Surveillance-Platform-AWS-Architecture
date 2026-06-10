package com.example.droneserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest r) {
        AuthResponse resp = new AuthResponse("access-token-sample","refresh-token-sample",3600,
                Map.of("id","user-1","name","Demo User","roles",new String[]{"user"}));
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String,String> body) {
        return ResponseEntity.ok(Map.of("accessToken","access-token-2","refreshToken","refresh-token-2","expiresIn",3600));
    }

    public static class AuthRequest { public String username; public String password; }
    public static class AuthResponse { public String accessToken; public String refreshToken; public int expiresIn; public Object user; public AuthResponse(String a,String r,int e,Object u){this.accessToken=a;this.refreshToken=r;this.expiresIn=e;this.user=u;} }
}
