package legacy;

import com.pomogSpringBoot.testApp.dao.LabGlasswareDAO;

public class OldCommandRunner {

//			createLabGlassware(labGlasswareDAO);
//			createMultipleLabGlassware(labGlasswareDAO);
//			findLabGlasswareByID(labGlasswareDAO, 4);
//			findAllLabGlasswareFromDB(labGlasswareDAO);
//			findLabGlasswareByNameFromDB(labGlasswareDAO, "Dropping funnel");
//			updateLabGlassware(labGlasswareDAO);
//			deleteLabGlasswareByID(labGlasswareDAO, 17);
//			deleteAllLabGlassware(labGlasswareDAO);
    
    private void deleteAllLabGlassware(LabGlasswareDAO labGlasswareDAO) {
        int deletedRows = labGlasswareDAO.deleteAllGlassJoint();
        System.out.printf("\n%d rows were deleted form labGlasswareDAO\n", deletedRows);
    }
    
    private void deleteLabGlasswareByID(LabGlasswareDAO labGlasswareDAO, Long id) {
        labGlasswareDAO.deleteByID(id);
    }
    
//    private void updateLabGlassware(LabGlasswareDAO labGlasswareDAO) {
//        int id = 1;
//        var fondLabGlassware = labGlasswareDAO.findLabGlasswareByID(id);
//
//        fondLabGlassware.setName("Updated Flask");
//        fondLabGlassware.setStatus("broken");
//
//        labGlasswareDAO.update(fondLabGlassware);
//    }
    
    private void findLabGlasswareByNameFromDB(LabGlasswareDAO labGlasswareDAO, String name) {
        var list = labGlasswareDAO.findLabGlasswareByName(name);
        list.forEach(System.out::println);
    }
    
    private void findAllLabGlasswareFromDB(LabGlasswareDAO labGlasswareDAO) {
        var list = labGlasswareDAO.findAllLabGlassware();
        list.forEach(System.out::println);
    }
    
//    private void findLabGlasswareByID(LabGlasswareDAO labGlasswareDAO, Long id) {
//        var fondLabGlassware = labGlasswareDAO.findLabGlasswareByID(id);
//        System.out.println(fondLabGlassware);
//    }
    
//    private void createMultipleLabGlassware(LabGlasswareDAO labGlasswareDAO) {
//        var glassJoint1 = new GlassJoint(JointType.SPHERICAL_BALL, "S35");
//        var glassJoint2 = new GlassJoint(JointType.SPHERICAL_CUP, "S41");
//        var glassJoint3 = new GlassJoint(JointType.CONE_SOCKET, "29/32");
//        var labGlassware1 = new LabGlassware("Flask");
//        var labGlassware2 = new LabGlassware("Flask");
//        var labGlassware3 = new LabGlassware("Flask");
//        labGlassware1.setLastMaintenanceDate(Date.valueOf(LocalDate.now()));
//        labGlassware2.setLastMaintenanceDate(Date.valueOf(LocalDate.now()));
//        labGlassware3.setLastMaintenanceDate(Date.valueOf(LocalDate.now()));
//        labGlassware1.addGlassJoint(glassJoint1);
//        labGlassware2.addGlassJoint(glassJoint2);
//        labGlassware2.addGlassJoint(glassJoint3);
//
//        labGlasswareDAO.save(labGlassware1);
//        labGlasswareDAO.save(labGlassware2);
//        labGlasswareDAO.save(labGlassware3);
//    }
    
//    private void createLabGlassware(LabGlasswareDAO labGlasswareDAO) {
//        System.out.println("Creating test LabGlassware");
//        var glassJoint = new GlassJoint(JointType.SPHERICAL_BALL, "S35");
//        var labGlassware = new LabGlassware("Dropping funnel test");
//        labGlassware.setLastMaintenanceDate(Date.valueOf(LocalDate.now()));
//        labGlassware.addGlassJoint(glassJoint);
//
//        labGlasswareDAO.save(labGlassware);
//    }
}
