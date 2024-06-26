package com.pomogSpringBoot.testApp.service.dbService;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class LabGlasswareFilter {
    private String name;
    private Integer minVol;
    private Integer maxVol;
    private Boolean broken;
    private Boolean repaired;
}
