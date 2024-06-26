package com.pomogSpringBoot.testApp.entity.glassware;

import com.pomogSpringBoot.testApp.entity.Status;
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
    private Long id;
    
    @Column(name = "name")
    @NonNull
    private String name;
    
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
  
    @Column(name = "is_broken")
    @NonNull
    private Boolean broken;
    
    @Column(name = "is_repaired")
    @NonNull
    private Boolean repaired;
    
    @Column(name = "material")
    private String material;
    
    @Column(name = "manufacturer")
    private String manufacturer;
    
    @Column(name = "location")
    private String location;
    
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "labglassware-status",
            joinColumns = @JoinColumn(name = "status"),
            inverseJoinColumns = @JoinColumn(name = "labGlassware")
    )
    private List<Status> statuses = new ArrayList<>();
    
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
    private List<GlassJoint> glassJoints = new ArrayList<>();

    public void addGlassJoint(GlassJoint glassJoint) {
        if (glassJoints == null) {
            glassJoints = new ArrayList<>();
        }
        this.glassJoints.add(glassJoint);
        glassJoint.setLabGlassware(this);
    }
    
    public void addStatus(Status status) {
        if (statuses == null) {
            statuses = new ArrayList<>();
        }
        if (!this.statuses.contains(status)) {
            this.statuses.add(status);
            status.addLabGlassware(this);
        }
        
        System.out.println("FROM Labglassware Class");
        System.out.println(this);
    }
}
