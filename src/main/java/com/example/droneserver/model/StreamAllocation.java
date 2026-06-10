package com.example.droneserver.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "stream_allocations")
public class StreamAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String cameraId;
    private String agentId;
    private String proxyEndpoint;
    private Instant expiresAt;

    public StreamAllocation(){}
    public String getId(){return id;} public void setId(String id){this.id=id;}
    public String getCameraId(){return cameraId;} public void setCameraId(String c){this.cameraId=c;}
    public String getAgentId(){return agentId;} public void setAgentId(String a){this.agentId=a;}
    public String getProxyEndpoint(){return proxyEndpoint;} public void setProxyEndpoint(String p){this.proxyEndpoint=p;}
    public Instant getExpiresAt(){return expiresAt;} public void setExpiresAt(Instant e){this.expiresAt=e;}
}
