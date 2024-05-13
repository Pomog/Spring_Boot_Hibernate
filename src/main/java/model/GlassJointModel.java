package model;

import com.pomogSpringBoot.testApp.entity.JointType;
import lombok.Data;

@Data
public class GlassJointModel {
    private JointType type;
    private String sizeDesignation;
}