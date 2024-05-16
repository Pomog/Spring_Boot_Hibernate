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
    @NonNull
    private String name;
    private String material;
    private Boolean broken;
    private Boolean repaired;
    
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
        this.broken = labGlassware.getBroken();
        this.repaired = labGlassware.getRepaired();
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