package com.pomogSpringBoot.testApp.service.dbService;

import com.pomogSpringBoot.testApp.dto.LabGlasswareDTO;
import com.pomogSpringBoot.testApp.entity.glassware.LabGlassware;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
public interface LabGlasswareService {
    LabGlasswareDTO saveUsingDAO (LabGlassware labGlassware);

    LabGlasswareDTO save (LabGlassware labGlassware);
    LabGlasswareDTO saveWithImage(LabGlassware labGlasswareReq, MultipartFile file);
    LabGlassware update (LabGlassware labGlassware);
    LabGlassware findLabGlasswareByID (Long id) throws IllegalAccessException;
    List<LabGlasswareDTO> findAllLabGlassware();
    List<LabGlasswareDTO> findLabGlasswareByName(String name);
    void deleteByID (Long id);
    int deleteAllGlassJoint();
    List<LabGlasswareDTO> findByVolume (int maxVol, int minVol);
    List<LabGlasswareDTO> findLabGlassware(LabGlasswareFilter filter);
}
