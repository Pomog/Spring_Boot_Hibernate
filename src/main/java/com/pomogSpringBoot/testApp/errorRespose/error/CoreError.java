package com.pomogSpringBoot.testApp.errorRespose.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoreError {
    private String field;
    private String message;
}
