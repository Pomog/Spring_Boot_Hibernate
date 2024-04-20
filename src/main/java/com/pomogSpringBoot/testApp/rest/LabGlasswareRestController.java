package com.pomogSpringBoot.testApp.rest;

import com.pomogSpringBoot.testApp.entity.GlassJoint;
import com.pomogSpringBoot.testApp.entity.JointType;
import com.pomogSpringBoot.testApp.entity.LabGlassware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LabGlasswareRestController {
    
    @GetMapping("/labglassware")
    public List<LabGlassware> getTestLabglassware (){
        var glassJoint1 = new GlassJoint(JointType.SPHERICAL_BALL, "S35");
        var glassJoint2 = new GlassJoint(JointType.SPHERICAL_CUP, "S41");
        var glassJoint3 = new GlassJoint(JointType.CONE_SOCKET, "29/32");
        var labGlassware1 = new LabGlassware("FlaskTest");
        var labGlassware2 = new LabGlassware("FlaskTest");
        var labGlassware3 = new LabGlassware("FlaskTest");
        labGlassware1.setLastMaintenanceDate(Date.valueOf(LocalDate.now()));
        labGlassware2.setLastMaintenanceDate(Date.valueOf(LocalDate.now()));
        labGlassware3.setLastMaintenanceDate(Date.valueOf(LocalDate.now()));
        labGlassware1.addGlassJoint(glassJoint1);
        labGlassware2.addGlassJoint(glassJoint2);
        labGlassware2.addGlassJoint(glassJoint3);
        
        List<LabGlassware> theLabGlassware = new ArrayList<>();
        theLabGlassware.add(labGlassware1);
        theLabGlassware.add(labGlassware2);
        theLabGlassware.add(labGlassware3);
        
        return theLabGlassware;
    }
}
