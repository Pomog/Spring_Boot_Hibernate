package com.pomogSpringBoot.testApp.model;

import com.pomogSpringBoot.testApp.entity.glassware.GlassJoint;
import com.pomogSpringBoot.testApp.entity.glassware.JointType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GlassJointModel {
    @NotNull(message = "type is required")
    private JointType type;
    @NotNull(message = "designation is required")
    @Pattern(regexp = "^(?:\\d{1,2}\\/\\d{1,2} (cone|socket)|\\w\\d{1,2} (ball|cup)|\\d{1,3})$", message = "Invalid format. Use 'dd/dd cone', 'dd/dd socket', 'wd ball', 'wd cup', or 'ddd'.")
    private String sizeDesignation;
    
    public GlassJointModel(GlassJoint glassJoint) {
        this.type = glassJoint.getType();
        this.sizeDesignation = glassJoint.getSizeDesignation();
    }
}