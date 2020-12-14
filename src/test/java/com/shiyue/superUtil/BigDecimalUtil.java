package com.shiyue.superUtil;

import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalUtil {

    /**
     * Division is undefined错误
     *
     * 此错误仅在使用BigDecimal做除法时，且0/0的情况下才会提示。
     * x/0时，仅提示Division by zero。
     * BigDecimal判断一个值是否为0时，不能使用equals，因为equals会比较值的大小和精度的大小，即0.00 和 0.000是不同的。
     * 需要使用 x.compareTo(BigDecimal.ZERO) == 0 来判断。
     * 且使用divide做除法时，标准的形式为 x.divide(y， scale，rm)
     * 如果不指明scale的值，会默认使用 x.scale - y.scale的值替代，如果此时值为负数，则会报错 Division undefined。
     * 且不指定scale 和 rm四舍五入方式，如果遇到 1/3 这种除不尽的情况，会报如下错误：
     * Non-terminating decimal expansion; no exact representable decimal result
     */


   public void 公开类(){

       BigDecimal bigDecimal = new BigDecimal(10);
       //.divide(除数    ,    保留几位小数   ,  (BigDecimal.ROUND_HALF_UP四舍五入))
       new BigDecimal(10).divide(bigDecimal,4,BigDecimal.ROUND_HALF_UP);
   }

    @Test
    public void 判断等于0(){
       //判断Bigdecimal是否为0.00必须要用第二种否则不准确
        BigDecimal bigDecimal = new BigDecimal(0.00);
        System.out.println(bigDecimal.equals(0));
        System.out.println(bigDecimal.compareTo(BigDecimal.ZERO)!=0);
    }
}
