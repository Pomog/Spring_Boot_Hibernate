package model;

import com.pomogSpringBoot.testApp.entity.JointType;
import lombok.Data;

@Data
public class GlassJointModel {
    private Long id;
    private JointType type;
    private String sizeDesignation;
    private Long labGlasswareId;
}