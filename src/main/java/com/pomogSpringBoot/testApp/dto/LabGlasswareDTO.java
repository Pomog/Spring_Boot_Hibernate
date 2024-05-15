package com.pomogSpringBoot.testApp.dto;

import com.pomogSpringBoot.testApp.entity.LabGlassware;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class LabGlasswareDTO {
    
   
    private String name;
    
    private String material;
    
    private String manufacturer;
    
    private String location;
    
    private String status;
    
    private Date purchaseDate;
    
    private Date calibrationDate;
    
    private Date lastMaintenanceDate;
    
    private BigDecimal price;
    
    private String provider;

    @NonNull
    private Integer capacityML;
    
    private List<GlassJointDTO> glassJointsDTO=new ArrayList<>();
    
    public LabGlasswareDTO(LabGlassware labGlassware) {
        this.name = labGlassware.getName();
        this.material = labGlassware.getMaterial();
        this.manufacturer = labGlassware.getManufacturer();
        this.location = labGlassware.getLocation();
        this.status = labGlassware.getStatus();
        this.purchaseDate = labGlassware.getPurchaseDate();
        this.calibrationDate = labGlassware.getCalibrationDate();
        this.lastMaintenanceDate = labGlassware.getLastMaintenanceDate();
        this.price = labGlassware.getPrice();
        this.provider = labGlassware.getProvider();
        this.capacityML = labGlassware.getCapacityML();
        
        if (labGlassware.getGlassJoints() != null) {
            labGlassware.getGlassJoints().stream()
                    .map(GlassJointDTO::new)
                    .forEach(this::addGlassJoint);
        }
    }
    
    public void addGlassJoint(GlassJointDTO glassJointDTO) {
        this.glassJointsDTO.add(glassJointDTO);
    }
    
    public void setGlassJoints (List<GlassJointDTO> glassJointsDTO){
        glassJointsDTO.forEach(this::addGlassJoint);
    }
}