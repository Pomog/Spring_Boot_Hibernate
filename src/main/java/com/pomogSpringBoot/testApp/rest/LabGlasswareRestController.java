package com.pomogSpringBoot.testApp.rest;

import com.pomogSpringBoot.testApp.entity.GlassJoint;
import com.pomogSpringBoot.testApp.entity.JointType;
import com.pomogSpringBoot.testApp.entity.LabGlassware;
import com.pomogSpringBoot.testApp.rest.errorRespose.LabGlasswareErrorResponse;
import com.pomogSpringBoot.testApp.rest.errorRespose.LabGlasswareNotFoundExeption;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LabGlasswareRestController {
    private List<LabGlassware> theLabGlassware;
    
    @PostConstruct
    public void loadData(){
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
        
        this.theLabGlassware = theLabGlassware;
    }
    
    @GetMapping("/labglassware")
    public List<LabGlassware> getTestLabglassware (){
        return theLabGlassware;
    }
    
    @GetMapping("/labglassware/{id}")
    public LabGlassware getLabglasswareByID(@PathVariable int id){
        if (id >= theLabGlassware.size() || id < 0){
            throw new LabGlasswareNotFoundExeption("LabGlassware ID not found - " + id);
        }
        return theLabGlassware.get(id);
    }
    
    @ExceptionHandler
    public ResponseEntity<LabGlasswareErrorResponse> notFoundHandler (LabGlasswareNotFoundExeption exc){
        var error = new LabGlasswareErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(new java.util.Date());
        
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    
}
