package com.example.droneserver.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "agents")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String agentId;
    private String siteId;
    private String ip;
    private String capabilities; // comma separated
    private Instant registeredAt = Instant.now();

    public Agent(){}
    public String getId(){return id;} public void setId(String id){this.id=id;}
    public String getAgentId(){return agentId;} public void setAgentId(String a){this.agentId=a;}
    public String getSiteId(){return siteId;} public void setSiteId(String s){this.siteId=s;}
    public String getIp(){return ip;} public void setIp(String i){this.ip=i;}
    public String getCapabilities(){return capabilities;} public void setCapabilities(String c){this.capabilities=c;}
    public Instant getRegisteredAt(){return registeredAt;} public void setRegisteredAt(Instant t){this.registeredAt=t;}
}
