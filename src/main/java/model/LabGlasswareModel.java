package model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class LabGlasswareModel {
    private String name;
    private String material;
    private String manufacturer;
    private String location;
    private String status;
    private String purchaseDate;
    private String calibrationDate;
    private String lastMaintenanceDate;
    private BigDecimal price;
    private String provider;
    private Integer capacityML;
    private List<GlassJointModel> glassJoints = new ArrayList<>();
    
    public void addGlassJoint(GlassJointModel glassJointModel){
        this.glassJoints.add(glassJointModel);
    }
}