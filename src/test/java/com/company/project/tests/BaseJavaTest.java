package com.company.project.tests;

import org.junit.jupiter.api.Test;

public class BaseJavaTest {

    @Test
    void first() {
        // типы переменных
        byte one = 127;
        short two = 259;
        int three = 56;
        long four = 43L;
        double five = 0.56;
        float six = 23.56f;

        //операции с переменными
        System.out.println(two + one);
        System.out.println(three - four);
        System.out.println(four * five);
        System.out.println(six / five);
        System.out.println(three % four);

        //переполнение
        System.out.println((byte) (one + 5));
        int a = Integer.MAX_VALUE;
        System.out.println(a);
        System.out.println(a + 2);
    }
}
