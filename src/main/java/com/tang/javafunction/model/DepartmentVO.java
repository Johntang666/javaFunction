package com.tang.javafunction.model;

import lombok.Data;

import java.util.List;

/**
 * @author tangzhipeng
 * @project javaFunction
 * @description:
 * @date 2024/2/1 8:55
 */
@Data
public class DepartmentVO {
    private String departmentId;
    private String departmentName;
    private String parentDepartmentId;
    private List<DepartmentVO> departments;
}
