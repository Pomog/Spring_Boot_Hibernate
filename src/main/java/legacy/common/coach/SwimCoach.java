package legacy.common.coach;

import legacy.common.inrerface.Coach;

public class SwimCoach implements Coach {
    @Override
    public String workout() {
        System.out.printf("inside constructor: %s\n",  getClass().getSimpleName());
        return "Practice for 45 min. SwimCoach";
    }
    
    @Override
    public String getCoachName() {
        return null;
    }
}
