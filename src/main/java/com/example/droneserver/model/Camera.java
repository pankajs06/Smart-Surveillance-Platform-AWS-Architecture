package com.example.droneserver.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "cameras")
public class Camera {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String cameraId;
    private String siteId;
    private String label;
    private String rtspUrl;
    private String status;
    private Instant lastSeen;

    public Camera(){}
    // getters/setters
    public String getId(){return id;} public void setId(String id){this.id=id;}
    public String getCameraId(){return cameraId;} public void setCameraId(String c){this.cameraId=c;}
    public String getSiteId(){return siteId;} public void setSiteId(String s){this.siteId=s;}
    public String getLabel(){return label;} public void setLabel(String l){this.label=l;}
    public String getRtspUrl(){return rtspUrl;} public void setRtspUrl(String r){this.rtspUrl=r;}
    public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
    public Instant getLastSeen(){return lastSeen;} public void setLastSeen(Instant ls){this.lastSeen=ls;}
}
