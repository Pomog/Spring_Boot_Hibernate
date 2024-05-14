package com.pomogSpringBoot.testApp.service.dataService;

public interface ObjectTranformer<T, G> {
   G transform(T source);
}
