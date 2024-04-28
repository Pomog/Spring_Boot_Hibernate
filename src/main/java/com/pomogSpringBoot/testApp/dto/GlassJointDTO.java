package com.pomogSpringBoot.testApp.dto;

import com.pomogSpringBoot.testApp.entity.GlassJoint;
import com.pomogSpringBoot.testApp.entity.JointType;
import lombok.*;

@Data
@RequiredArgsConstructor
public class GlassJointDTO {

    @NonNull
    private JointType type;

    @NonNull
    private String sizeDesignation;
    
    public GlassJointDTO (GlassJoint glassJoint){
        this.type = glassJoint.getType();
        this.sizeDesignation = glassJoint.getSizeDesignation();
    }
    
}