package com.pomogSpringBoot.testApp.rest.errorRespose;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class LabGlasswareErrorResponse {
    @NonNull
    private Integer status;
    @NonNull
    private String message;
    @NonNull
    private Date timeStamp;
}
