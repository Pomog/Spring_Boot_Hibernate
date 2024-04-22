package legacy;

import legacy.common.coach.SwimCoach;
import legacy.common.inrerface.Coach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    @Bean("aqua")
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
