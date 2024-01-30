package com.tang.javafunction;

import java.util.Arrays;
import java.util.List;

/**
 * @author tangzhipeng
 * @project javaFunction
 * @description:
 * @date 2024/1/30 16:03
 */
public class JavaAPITest {
    public static void main(String[] args) {
        testArrayAndListOf();
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
