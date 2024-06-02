package com.pomogSpringBoot.testApp.service.dataService;

import com.pomogSpringBoot.testApp.entity.glassware.GlassJoint;
import com.pomogSpringBoot.testApp.entity.glassware.LabGlassware;
import com.pomogSpringBoot.testApp.model.LabGlasswareModel;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class ModelToEntityTransformer implements ObjectTranformer<LabGlasswareModel, LabGlassware> {
    @Override
    public LabGlassware transform(LabGlasswareModel source) {
        LabGlassware labGlassware = new LabGlassware();
        labGlassware.setId(source.getId());
        labGlassware.setName(source.getName());
        labGlassware.setBroken(source.getBroken());
        labGlassware.setRepaired(source.getRepaired());
        labGlassware.setMaterial(source.getMaterial());
        labGlassware.setManufacturer(source.getManufacturer());
        labGlassware.setLocation(source.getLocation());
        labGlassware.setStatus(source.getStatus());
        labGlassware.setPrice(source.getPrice());
        labGlassware.setProvider(source.getProvider());
        labGlassware.setCapacityML(source.getCapacityML());
        
        if (source.getPurchaseDate() != null && !source.getPurchaseDate().isBlank()) {
            labGlassware.setPurchaseDate(parseStringToSQLDate(source.getPurchaseDate()));
        }
        
        if (source.getCalibrationDate() != null && !source.getCalibrationDate().isBlank()) {
            labGlassware.setCalibrationDate(parseStringToSQLDate(source.getCalibrationDate()));
        }
        
        if (source.getLastMaintenanceDate() != null && !source.getLastMaintenanceDate().isBlank()) {
            labGlassware.setLastMaintenanceDate(parseStringToSQLDate(source.getLastMaintenanceDate()));
        }
        
        if (source.getGlassJoints() != null && !source.getGlassJoints().isEmpty()) {
            source.getGlassJoints().forEach(glassJointModel -> {
                GlassJoint glassJoint = new GlassJoint(glassJointModel.getType(), glassJointModel.getSizeDesignation());
                glassJoint.setLabGlassware(labGlassware);
                labGlassware.addGlassJoint(glassJoint);
            });
        }
        return labGlassware;
    }
    
    private Date parseStringToSQLDate(String dateFromTheHTTP) {
        return dateFromTheHTTP.isBlank() ? null : Date.valueOf(dateFromTheHTTP);
    }
}
