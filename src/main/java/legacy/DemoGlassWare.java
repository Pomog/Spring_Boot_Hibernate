package legacy;

import com.pomogSpringBoot.testApp.entity.GlassJoint;
import com.pomogSpringBoot.testApp.entity.JointType;
import com.pomogSpringBoot.testApp.entity.LabGlassware;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

public class DemoGlassWare {
    public static void main(String[] args) {
        
        printConstructors(GlassJoint.class.getConstructors());
        printConstructors(LabGlassware.class.getConstructors());
        
        var glassJoint = new GlassJoint(JointType.SPHERICAL_BALL, "S35");
        var labGlassware = new LabGlassware();
//        labGlassware.addGlassJoint(glassJoint);
        
        System.out.println(labGlassware.toString());
    }
    
    private static void printConstructors(Constructor<?>[] constructors) {
        // Print information about each constructor
        for (Constructor<?> constructor : constructors) {
            System.out.println("Constructor: " + constructor.getName());
            System.out.println("Parameter count: " + constructor.getParameterCount());
            Parameter[] parameters = constructor.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println(parameter.getType().getName() + " " + parameter.getName());
            }
            System.out.println();
        }
    }
}
