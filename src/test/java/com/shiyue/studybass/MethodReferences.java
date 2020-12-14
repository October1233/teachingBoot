package com.shiyue.studybass;

import com.shiyue.springboot.domain.User;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MethodReferences {
    @Test
    public void ccc5(){
        int[] numbers= {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        List<Integer> list=new ArrayList<>();
        for(int i:numbers) {
            list.add(i);
        }
        Predicate<Integer> p1= i->i>3 ;
        Predicate<Integer> p2=i->i<20;
        Predicate<Integer> p3=i->i%2==0;
        Predicate<Integer> p4= i->i!=4;
        List test=list.stream().filter(p1.and(p2).and(p3).and(p4)).collect(Collectors.toList());
        System.out.println(test.toString());

        BiFunction<Integer,Integer,Integer> sum = Integer::sum;
        System.out.println(sum.apply(1,2));


    }
    @Test
    public void test5() {
        Supplier<User> sup = () -> new User();
        User emp = sup.get();
    }


    /**
     * 函数式接口predicate，接受若干值并返回一个boolean
     */
    @Test
    public void vvddv(){ MethodReferences predicate = new MethodReferences();

    /** - 1.判断传入的字符串的长度是否大于5 */
        System.out.println(predicate.judgeConditionByFunction(12345,value -> String.valueOf(value).length() > 5));
    /** - 2.判断传入的参数是否是奇数 */
        System.out.println(predicate.judgeConditionByFunction(4,value -> value % 2 == 0));
    /** - 3.判断数字是否大于10 */
        System.out.println(predicate.judgeConditionByFunction(-1, value-> value > 10));
}
    public boolean judgeConditionByFunction(int value,Predicate<Integer> predicate) {
        return predicate.test(value);
    }



    public void bufferES(){
        Supplier<List<User>> allUser = ArrayList::new;
        allUser.get();
    }

    @Test
    public void dateUtil()throws Exception{
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse("2020-12-15"));
        int year1 = c.get(Calendar.YEAR);
        int month1 = c.get(Calendar.MONTH);
        int day1 = c.get(Calendar.DAY_OF_MONTH);
        c.setTime(sdf.parse("2020-12-15"));
        int day2 = c.get(Calendar.DAY_OF_MONTH);
        int year2 = c.get(Calendar.YEAR);
        int month2 = c.get(Calendar.MONTH);
        int result=0;
        if(year1 == year2) {
            if (day1>=day2) {
                result = month1 - month2 + 1;
            }else {
                result = month1 - month2;
            }
        } else {
            if (day1>=day2) {
                result = 12 * (year1 - year2) + month1 - month2 + 1;
            }else {
                result = 12 * (year1 - year2) + month1 - month2;
            }
        }
        System.out.println("前"+result);
        if (result<0){
            result=0;
        }
        System.out.println("后"+result);
    }
    @Test
    public void fffff(){
        List<Integer> residue = Arrays.asList(4,4,3,2,5,2);
//        List<Integer> residue = new ArrayList<>();
//        for (Map<String,Object> infomap : allLoan){
//            Integer num = Integer.parseInt(String.valueOf(infomap.get("num")));
//            residue.add(num);
//        }
        long allResidueNum = 0;
        System.out.println(residue);
        Map<Integer, Long> result2 = residue.stream().collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(result2);
        Iterator<Integer> iter = result2.keySet().iterator();
        while(iter.hasNext()) {
            Integer key = iter.next();
            long value = result2.get(key);
            allResidueNum=key*value+allResidueNum;
            System.out.println(key+"---"+value);
        }


    }

    public void Sasfasf(){
        List<String> list = Arrays.asList("123", "45634", "7892", "abch", "sdfhrthj", "mvkd");
        list.stream().forEach(e ->{
            if(e.length() >= 5){
                return;
            }
            System.out.println(e);
        });
    }




}
