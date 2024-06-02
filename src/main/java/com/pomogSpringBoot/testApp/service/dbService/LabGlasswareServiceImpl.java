package com.pomogSpringBoot.testApp.service.dbService;

import com.pomogSpringBoot.testApp.dao.LabGlasswareDAO;
import com.pomogSpringBoot.testApp.dao.LabGlasswareRepository;
import com.pomogSpringBoot.testApp.dto.LabGlasswareDTO;
import com.pomogSpringBoot.testApp.entity.glassware.LabGlassware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LabGlasswareServiceImpl implements LabGlasswareService{
    private final LabGlasswareDAO labGlasswareDAO;
    private final LabGlasswareRepository labGlasswareRepository;

    
    @Autowired
    public LabGlasswareServiceImpl(LabGlasswareDAO labGlasswareDAO, LabGlasswareRepository labGlasswareRepository) {
        this.labGlasswareDAO = labGlasswareDAO;
        this.labGlasswareRepository = labGlasswareRepository;
    }
    
    @Override
    @Transactional
    public LabGlasswareDTO save(LabGlassware labGlasswareReq){
        var labGlassware = createLabGlasswareFormReq(labGlasswareReq);
        System.out.println(labGlassware.getId());
        LabGlassware savedLabGlassware = labGlasswareRepository.save(labGlassware);

        return new LabGlasswareDTO(savedLabGlassware);
    }
    
    @Override
    @Transactional
    public LabGlasswareDTO saveUsingDAO(LabGlassware labGlasswareReq) {
       var labGlassware = createLabGlasswareFormReq(labGlasswareReq);
       return new LabGlasswareDTO(labGlasswareDAO.save(labGlassware));
    }
    
    @Override
    public LabGlassware update(LabGlassware labGlassware) {
        return null;
    }
    
    private LabGlassware createLabGlasswareFormReq(LabGlassware labGlasswareReq) {
        labGlasswareReq.setGlassJoints(
                labGlasswareReq.getGlassJoints().stream()
                        .peek(glassJoint -> glassJoint.setLabGlassware(labGlasswareReq))
                        .collect(Collectors.toList())
        );
        return labGlasswareReq;
    }
    
    @Override
    public LabGlassware findLabGlasswareByID(Long id) {
        return this.labGlasswareDAO.findLabGlasswareByID(id);
    }
    
    @Override
    public List<LabGlasswareDTO> findAllLabGlassware() {
        return this.labGlasswareDAO
                .findAllLabGlassware().stream()
                .map(LabGlasswareDTO::new)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<LabGlasswareDTO> findLabGlasswareByName(String name) {
        return this.labGlasswareDAO.findLabGlasswareByName(name).stream()
                .map(LabGlasswareDTO::new)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public void deleteByID(Long id) {
        this.labGlasswareDAO.deleteByID(id);
    }
    
    @Override
    @Transactional
    public int deleteAllGlassJoint() {
        return this.labGlasswareDAO.deleteAllGlassJoint();
    }
}
