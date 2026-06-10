package com.example.droneserver.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "recordings")
public class Recording {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String cameraId;
    private Instant startAt;
    private Instant endAt;
    private long sizeBytes;
    private String storageUrl;

    public Recording(){}
    public String getId(){return id;} public void setId(String id){this.id=id;}
    public String getCameraId(){return cameraId;} public void setCameraId(String c){this.cameraId=c;}
    public Instant getStartAt(){return startAt;} public void setStartAt(Instant s){this.startAt=s;}
    public Instant getEndAt(){return endAt;} public void setEndAt(Instant e){this.endAt=e;}
    public long getSizeBytes(){return sizeBytes;} public void setSizeBytes(long s){this.sizeBytes=s;}
    public String getStorageUrl(){return storageUrl;} public void setStorageUrl(String u){this.storageUrl=u;}
}
