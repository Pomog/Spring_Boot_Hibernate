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
@Table(name="lab-glassware")
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
    @NonNull
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
    @NonNull
    private Integer capacityML;
    
    // List of glass joints associated with the lab glassware.
    @OneToMany(mappedBy = "labGlassware", cascade = CascadeType.ALL)
    private List<GlassJoint> glassJoints = new ArrayList<>();
    
    public void addGlassJoint(GlassJoint glassJoint) {
        this.glassJoints.add(glassJoint);
    }
}