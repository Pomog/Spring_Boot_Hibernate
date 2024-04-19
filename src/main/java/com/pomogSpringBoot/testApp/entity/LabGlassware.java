package com.pomogSpringBoot.testApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lab_glassware")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class LabGlassware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @Column(name = "name")
    @NonNull
    private String name;
    
    @Column(name = "material")
    private String material;
    
    @Column(name = "manufacturer")
    private String manufacturer;
    
    @Column(name = "location")
    private String location;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "purchase_date")
    private Date purchaseDate;
    
    @Column(name = "calibration_date")
    private Date calibrationDate;
    
    @Column(name = "last_maintenance_date")
    private Date lastMaintenanceDate;
    
    @Column(name = "price")
    private BigDecimal price;
    
    @Column(name = "provider")
    private String provider;
    
    @Column(name = "capacity_ml")
    private Integer capacityML;
    
    // List of glass joints associated with the lab glassware.
    @OneToMany(
            mappedBy = "labGlassware",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<GlassJoint> glassJoints;

    public void addGlassJoint(GlassJoint glassJoint) {
        if (glassJoints == null) {
            glassJoints = new ArrayList<>();
        }
        this.glassJoints.add(glassJoint);
        glassJoint.setLabGlassware(this);
    }
}
