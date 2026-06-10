package com.example.droneserver.dto;

import java.time.Instant;

public class RecordingDto {
    public String id;
    public String cameraId;
    public Instant startAt;
    public Instant endAt;
    public long sizeBytes;
    public String storageUrl;
}
