package com.tang.javafunction.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author tangzhipeng
 * @project javaFunction
 * @description:
 * @date 2024/2/1 8:53
 */
@Data
@AllArgsConstructor
public class Department {
    private String departmentId;
    private String departmentName;
    private String parentDepartmentId;
}
