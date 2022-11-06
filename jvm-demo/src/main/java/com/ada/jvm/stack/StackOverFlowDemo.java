package com.ada.jvm.stack;

/**
 *
 * <b><code></code></b>
 * <p/>
 *
 * 会报这样的错误：Exception in thread "main" java.lang.StackOverflowError
 * <p/>
 *
 * @Date: 2022/11/6 10:41
 * @Author xwn
 * @Version 1.0.0.1
 *
 */
public class StackOverFlowDemo {

    public static long count=0;

    public static void method(long i){
        System.out.println(count++);
        method(i);
    }

    public static void main(String[] args) {
        method(1);
    }
}
