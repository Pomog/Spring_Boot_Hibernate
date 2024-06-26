package com.pomogSpringBoot.testApp.dao;

import com.pomogSpringBoot.testApp.entity.glassware.LabGlassware;
import org.springframework.data.jpa.domain.Specification;

public class LabGlasswareSpecifications {
    public static Specification<LabGlassware> hasName(String name) {
        return (root, query, builder) ->
                name == null ? builder.conjunction() : builder.like(builder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }
    
    public static Specification<LabGlassware> brokenRepaired(Boolean broken, Boolean repaired) {
        return (root, query, builder) -> {
            if (broken == null && repaired == null) {
                return builder.conjunction();
            }
            if (broken != null && repaired != null) {
                return builder.and(
                        builder.equal(root.get("broken"), broken),
                        builder.equal(root.get("repaired"), repaired)
                );
            }
            if (broken != null) {
                return builder.equal(root.get("broken"), broken);
            }
            return builder.equal(root.get("repaired"), repaired);
        };
    }
    
    public static Specification<LabGlassware> hasVolumeBetween(Integer min, Integer max) {
        return (root, query, builder) -> {
            if (min != null && max != null && min > max) {
                return builder.conjunction();
            }
            if (min == null && max == null) {
                return builder.conjunction();
            }
            if (min == null) {
                return builder.lessThanOrEqualTo(root.get("capacityML"), max);
            }
            if (max == null) {
                return builder.greaterThanOrEqualTo(root.get("capacityML"), min);
            }
            return builder.between(root.get("capacityML"), min, max);
        };
    }
}
