package com.tang.javafunction;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.tang.javafunction.model.Department;
import com.tang.javafunction.model.DepartmentVO;

import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author tangzhipeng
 * @project javaFunction
 * @description:
 * @date 2024/1/30 16:03
 */
public class JavaAPITest {
    public static void main(String[] args) {
        testTree();
    }

    /**
     * 测试树的使用和查询
     */
    public static void testTree() {
        List<Department> departments = Arrays.asList(new Department("1", "研发部门", null),
                new Department("2", "文档部门", null),
                new Department("3", "产品部门", null),
                new Department("4", "研发子部门1", "1"),
                new Department("5", "研发子部门2", "1"),
                new Department("6", "研发子部门3", "1"),
                new Department("7", "文档子部门", "2"),
                new Department("8", "研发子部门1的子部门", "4"));
        List<DepartmentVO> departmentVOS = BeanUtil.copyToList(departments, DepartmentVO.class);
        List<DepartmentVO> collect = departmentVOS.stream().filter(i -> null == i.getParentDepartmentId()).collect(Collectors.toList());
        for (DepartmentVO i : collect) {
            i.setDepartments(buildChildTree(departmentVOS, i.getDepartmentId()));
        }
        collect.forEach(System.out::println);
    }

    private static List<DepartmentVO> buildChildTree(List<DepartmentVO> departmentVOS, String departmentId) {
        List<DepartmentVO> departmentVOS1 = new ArrayList<>(32);
        for (DepartmentVO i : departmentVOS) {
            if (Objects.equals(i.getParentDepartmentId(), departmentId)) {
                i.setDepartments(buildChildTree(departmentVOS, i.getDepartmentId()));
                departmentVOS1.add(i);
            }
        }
        return departmentVOS1;
    }

    /**
     * 测试hutool工具类
     */
    public static void testHutool() {
        DateTime date = DateUtil.date(System.currentTimeMillis());
        ZoneId zoneId = date.getZoneId();
        //时区
        System.out.println(zoneId);
        //日期转成年月日字符串
        System.out.println(date.toDateStr());
    }

    /**
     * 测试流的使用
     */
    public static void testStream() {
        //Stream的类加载器是启动类加载器
        Stream<Object> empty = Stream.empty();
        System.out.println(empty.getClass().getClassLoader());
        //Supplier接口的应用，generate生成一个无限流，和limit搭配使用就可以生成指定个数的数据
        /*Stream<Integer> generate = Stream.generate(() -> 1);
        generate.forEach(System.out::println);*/

        // 生成斐波那契数列的前10个元素
        /*Stream.iterate(new int[]{0, 1}, fib -> new int[]{fib[1], fib[0] + fib[1]})
                .limit(10)
                .forEach(fibonacci -> System.out.print(fibonacci[0] + " "));
        List<String> strings = Arrays.asList("apple", "banana", "orange");*/

        /*List<String> result = strings.stream()
                .filter(s -> s.length() > 5)
                .peek(s -> System.out.println("Filtered value: " + s))
                .map(String::toUpperCase)
                .peek(s -> System.out.println("Mapped value: " + s))
                .collect(Collectors.toList());
        result.stream().forEach(System.out::println);*/
        //测试流的总和
        List<String> apple = List.of("apple", "banana", "peach");
        IntSummaryStatistics collect = apple.stream().collect(Collectors.summarizingInt(String::length));
        System.out.println(collect.getAverage());

    }

    /**
     * 测试List.of和Arrays.asList()的区别
     */
    public static void testArrayAndListOf() {
        //测试List.of和Arrays.asList的区别
        List<Integer> integers = List.of(4, 6, 8, 2, 32);
        List<Integer> integers2 = Arrays.asList(4, 6, 8, 2, 32);
        List<Integer> integers3 = Arrays.asList(4, 6, 8, 2, 32, null);
        /*
        Java 9 引入了新的列表工厂方法 List.of
        Arrays.asList 返回可变列表，而 List.of 返回的列表是不可变的
        Arrays.asList 允许 null 元素，而 List.of 不允许
        contains 对于 null 的行为有所不同：对于List.of()后的结果使用contains会报空指针错误
        任何结构性更改 List.of 的尝试都会导致 UnsupportedOperationException 。
        其中包括添加、设置和删除等操作。但是，您可以更改列表中对象的内容（如果对象不是不可变的），因此列表不是“完全不可变的”。
         */
    }
}
