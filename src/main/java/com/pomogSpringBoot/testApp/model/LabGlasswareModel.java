package com.pomogSpringBoot.testApp.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class LabGlasswareModel implements Annotation {
    @NotNull(message = "Lab Glassware name is required")
    @Size(min = 1, max = 50, message = "inappropriate Lab Glassware name")
    private String name;
    private String material;
    @NotNull(message = "Lab Glassware conditions is required")
    private Boolean broken;
    @NotNull(message = "Lab Glassware conditions is required")
    private Boolean repaired;
    private String manufacturer;
    private String location;
    private String status;
    private String purchaseDate;
    private String calibrationDate;
    private String lastMaintenanceDate;
    @Min(value = 0, message = "must be greater or equal to 0")
    private BigDecimal price;
    private String provider;
    @NotNull(message = "Lab Glassware capacity is required")
    @Min(value = 0, message = "must be greater or equal to 0")
    private Integer capacityML;
    private List<GlassJointModel> glassJoints = new ArrayList<>();
    
    public void addGlassJoint(GlassJointModel glassJointModel) {
        this.glassJoints.add(glassJointModel);
    }
}