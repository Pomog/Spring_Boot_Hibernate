package com.pomogSpringBoot.testApp.service.dataService;

import com.pomogSpringBoot.testApp.entity.GlassJoint;
import com.pomogSpringBoot.testApp.entity.LabGlassware;
import com.pomogSpringBoot.testApp.model.LabGlasswareModel;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class ModelToEntityTransformer implements ObjectTranformer<LabGlasswareModel, LabGlassware>{
    @Override
    public LabGlassware transform(LabGlasswareModel source) {
        LabGlassware labGlassware = new LabGlassware();
        labGlassware.setName(source.getName());
        labGlassware.setMaterial(source.getMaterial());
        labGlassware.setManufacturer(source.getManufacturer());
        labGlassware.setLocation(source.getLocation());
        labGlassware.setStatus(source.getStatus());
        labGlassware.setPrice(source.getPrice());
        labGlassware.setProvider(source.getProvider());
        labGlassware.setCapacityML(source.getCapacityML());
        
        labGlassware.setPurchaseDate(Date.valueOf(source.getPurchaseDate()));
        labGlassware.setCalibrationDate(Date.valueOf(source.getCalibrationDate()));
        labGlassware.setLastMaintenanceDate(Date.valueOf(source.getLastMaintenanceDate()));
        
        if (!source.getGlassJoints().isEmpty()) {
            source.getGlassJoints().forEach(glassJointModel ->{
                GlassJoint glassJoint = new GlassJoint(glassJointModel.getType(), glassJointModel.getSizeDesignation());
                glassJoint.setLabGlassware(labGlassware);
                labGlassware.addGlassJoint(glassJoint);
            });
        }
        return labGlassware;
    }
}
