package com.pomogSpringBoot.testApp.entity.glassware;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "glass_joint")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class GlassJoint {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;
        
        @Column(name = "type")
        @Enumerated(EnumType.STRING)
        @NonNull
        private JointType type;
        
        @Column(name = "size_designation")
        @NonNull
        private String sizeDesignation;
        
        // Define the relationship to LabGlassware
        @ManyToOne
        @JoinColumn(name = "lab_glassware_id")
        @ToString.Exclude
        @EqualsAndHashCode.Exclude
        @Getter(AccessLevel.NONE)
        private LabGlassware labGlassware;
}
