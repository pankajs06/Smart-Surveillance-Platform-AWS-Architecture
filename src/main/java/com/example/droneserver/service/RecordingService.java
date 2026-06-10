package com.example.droneserver.service;

import com.example.droneserver.dto.RecordingDto;
import com.example.droneserver.model.Recording;
import com.example.droneserver.repository.RecordingRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecordingService {
    private final RecordingRepository repo;
    public RecordingService(RecordingRepository repo){this.repo=repo;}

    public RecordingDto toDto(Recording r){
        RecordingDto d = new RecordingDto();
        d.id = r.getId(); d.cameraId = r.getCameraId(); d.startAt = r.getStartAt(); d.endAt = r.getEndAt(); d.sizeBytes = r.getSizeBytes(); d.storageUrl = r.getStorageUrl();
        return d;
    }

    public Recording fromDto(RecordingDto d){
        Recording r = new Recording();
        r.setCameraId(d.cameraId); r.setStartAt(d.startAt); r.setEndAt(d.endAt); r.setSizeBytes(d.sizeBytes); r.setStorageUrl(d.storageUrl);
        return r;
    }

    public RecordingDto create(RecordingDto d){
        Recording r = fromDto(d);
        r = repo.save(r);
        return toDto(r);
    }

    public Optional<RecordingDto> findById(String id){
        return repo.findById(id).map(this::toDto);
    }

    public List<RecordingDto> listAll(){
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<RecordingDto> findByCameraRange(String cameraId, Instant from, Instant to){
        return repo.findByCameraIdAndStartAtBetween(cameraId,from,to).stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<RecordingDto> update(String id, RecordingDto d){
        return repo.findById(id).map(r->{
            r.setCameraId(d.cameraId); r.setStartAt(d.startAt); r.setEndAt(d.endAt); r.setSizeBytes(d.sizeBytes); r.setStorageUrl(d.storageUrl);
            return toDto(repo.save(r));
        });
    }

    public void delete(String id){
        repo.deleteById(id);
    }
}
