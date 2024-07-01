package com.pomogSpringBoot.testApp.entity;

import com.pomogSpringBoot.testApp.entity.glassware.LabGlassware;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "status", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @NonNull
    @Column(name = "name")
    private String name;
    
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Getter(AccessLevel.NONE)
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(
            name = "labglassware-status",
            joinColumns = @JoinColumn(name = "labGlassware"),
            inverseJoinColumns = @JoinColumn(name = "status")
    )
    private List<LabGlassware> labGlasswareList = new ArrayList<>();;
    
    public void addLabGlassware (LabGlassware labGlassware){
        if (labGlasswareList == null){
            labGlasswareList = new ArrayList<>();
        }
        labGlasswareList.add(labGlassware);
    }
}
