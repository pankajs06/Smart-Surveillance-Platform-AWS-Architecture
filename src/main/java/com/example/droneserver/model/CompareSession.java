package com.example.droneserver.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "compare_sessions")
public class CompareSession {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String leftRecordingId;
    private String rightRecordingId;
    private String status;
    private Instant createdAt = Instant.now();

    public CompareSession(){}
    public String getId(){return id;} public void setId(String id){this.id=id;}
    public String getLeftRecordingId(){return leftRecordingId;} public void setLeftRecordingId(String l){this.leftRecordingId=l;}
    public String getRightRecordingId(){return rightRecordingId;} public void setRightRecordingId(String r){this.rightRecordingId=r;}
    public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
    public Instant getCreatedAt(){return createdAt;} public void setCreatedAt(Instant t){this.createdAt=t;}
}
