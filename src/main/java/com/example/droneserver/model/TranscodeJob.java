package com.example.droneserver.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "transcode_jobs")
public class TranscodeJob {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String cameraId;
    private String source;
    private String status;
    private int progress;
    private Instant createdAt = Instant.now();

    public TranscodeJob(){}
    public String getId(){return id;} public void setId(String id){this.id=id;}
    public String getCameraId(){return cameraId;} public void setCameraId(String c){this.cameraId=c;}
    public String getSource(){return source;} public void setSource(String s){this.source=s;}
    public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
    public int getProgress(){return progress;} public void setProgress(int p){this.progress=p;}
    public Instant getCreatedAt(){return createdAt;} public void setCreatedAt(Instant t){this.createdAt=t;}
}
