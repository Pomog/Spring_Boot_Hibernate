package model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class LabGlasswareModel {
    private Long id;
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
    private Integer capacityML;
    private List<GlassJointModel> glassJoints = new ArrayList<>();
    
    public void addGlassJoint(GlassJointModel glassJointModel){
        this.glassJoints.add(glassJointModel);
    }
}