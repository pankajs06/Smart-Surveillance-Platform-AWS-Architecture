package com.example.droneserver.service;

import com.example.droneserver.dto.CameraDto;
import com.example.droneserver.model.Camera;
import com.example.droneserver.repository.CameraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CameraService {
    private final CameraRepository repo;
    public CameraService(CameraRepository repo){this.repo=repo;}

    public CameraDto toDto(Camera c){
        CameraDto d = new CameraDto();
        d.id = c.getId(); d.cameraId = c.getCameraId(); d.siteId = c.getSiteId(); d.label = c.getLabel(); d.rtspUrl = c.getRtspUrl(); d.status = c.getStatus();
        return d;
    }

    public Camera fromDto(CameraDto d){
        Camera c = new Camera();
        c.setCameraId(d.cameraId); c.setSiteId(d.siteId); c.setLabel(d.label); c.setRtspUrl(d.rtspUrl); c.setStatus(d.status);
        return c;
    }

    public CameraDto create(CameraDto d){
        Camera c = fromDto(d);
        c = repo.save(c);
        return toDto(c);
    }

    public Optional<CameraDto> findById(String id){
        return repo.findById(id).map(this::toDto);
    }

    public List<CameraDto> listAll(){
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<CameraDto> listBySite(String siteId){
        return repo.findBySiteId(siteId).stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<CameraDto> update(String id, CameraDto d){
        return repo.findById(id).map(c->{
            c.setCameraId(d.cameraId); c.setSiteId(d.siteId); c.setLabel(d.label); c.setRtspUrl(d.rtspUrl); c.setStatus(d.status);
            return toDto(repo.save(c));
        });
    }

    public void delete(String id){
        repo.deleteById(id);
    }
}
