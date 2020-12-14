package com.shiyue.superUtil;


import java.util.function.Consumer;
import java.util.function.Function;

class ExceptionUtil{


    @FunctionalInterface
    public interface ThrowingReturnFunction<T, R, E extends Exception>{
        R apply(T t) throws E;
    }
    /**
     * function手写函数式接口自定义异常
     * 异常上抛
     * @param throwingFunction
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> Function<T, R> throwReturn(
            ThrowingReturnFunction<T, R, Exception> throwingFunction) {

        return i -> {
            try {
                return throwingFunction.apply(i);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }


    /**
     * lambda专用自定义高拓展异常方法
     * 异常主动处理
     * @param consumer
     * @param clazz
     * @param <T>
     * @param <E>
     * @return
     */
    private static <T, E extends Exception> Consumer<T> exceptionClass(Consumer<T> consumer, Class<E> clazz) {
        return i -> {
            try {
                consumer.accept(i);
            } catch (Exception ex) {
                try {
                    E exCast = clazz.cast(ex);
                    System.err.println("Exception occured : " + exCast.getMessage());
                } catch (ClassCastException ccEx) {
                    throw ex;
                }
            }
        };
    }
}



