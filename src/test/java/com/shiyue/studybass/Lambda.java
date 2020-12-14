package com.shiyue.studybs;

import com.shiyue.springboot.domain.User;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@Slf4j
public class Lambda {
    public static void main(String args[]){
        Lambda lambda = new Lambda();

        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> { return a * b; };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

//        BigDecimal a = new BigDecimal(100);





        System.out.println("10 + 5 = " + lambda.operate(10, 5, addition));
        System.out.println("10 - 5 = " + lambda.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + lambda.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + lambda.operate(10, 5, division));


        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
    }
    @Test
    public void start(Integer as){
        Integer aA = as;
        Integer bB = 10;
        BigDecimal bigDecimal = (d,b) -> (d*b);

        BigDecimal bigDecimal1 = (a, b) -> {
            if (a>b){
                return a+b;
            }else {
                return b-a;
            }
        };
        bigDecimal.jingtai(aA,bB,bigDecimal.jiajian(aA,bB).toString(),bigDecimal1.jiajian(aA,bB).toString());
        log.info(String.valueOf(bigDecimal1));
    }


    @FunctionalInterface
    interface BigDecimal{
        Integer jiajian(int a,int b);
        default void jingtai(int a,int b,String s,String b1){
            log.info("a={},b={},a*b={},a+b={}",a,b,s,b1);
        }
    }

    @Test
    public void sss(){
        Function<String,Boolean> function = "hello world"::startsWith;
        System.out.println(function .apply("hello"));}


    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }


    @Test
    public void losi(){
        Random rand = new Random();
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            str.append(rand.nextInt(10));
        }
        System.out.println(str);
        System.out.println(UUID.randomUUID().toString().replace("-", "").toLowerCase());
    }
    @Test
    public void ccc(){
        String lihai = "ssss";
        String bulihai = "cccc";
        allStatus(lihai);
    }


    public boolean allStatus(String id){
        User user = new User();

        user.setUsername("ssss");
//        Predicate<String> checkId -> feeRuleDao.findRuleById(checkId).getStatus().equals(1)?true:false);
        BooleanMark booMark =checkId -> user.getUsername().equals(checkId)?true:false;
        booMark.logerBuffer(id);
        return booMark.checkId(id);
    }
    @FunctionalInterface
    interface BooleanMark{
        boolean checkId(String id);
        default void logerBuffer(String data){log.info("输入lambda的id为={}",data);}
    }

    /**
     * 对构造器引用和普通创建进行性能比较，构造引用由于
     * Param：普通创建 for 1000000000  耗时6s 111ms
     * Param：循环外创建内实例化 for 1000000000  耗时5s 778ms
     * Param：函数式接口Supplier构造器引用 for 1000000000  耗时6s 166ms
     */
    @Test
    public void 普通创建() throws InterruptedException {
        for (int i=0;i<20;i++){
            User user = new User();
            TimeUnit.MILLISECONDS.sleep(100); } }
    @Test
    public void 循环外创建内实例化() throws InterruptedException {
        User user = null;
        for (int i=0;i<20;i++){
            user = new User();
            TimeUnit.MILLISECONDS.sleep(100); } }
    @Test
    public void 函数式接口Supplier构造器引用() throws InterruptedException {
        Supplier<User> userSupplier = User::new;
        for (int i=0;i<20;i++){
            User user = userSupplier.get();
            TimeUnit.MILLISECONDS.sleep(100); } }



}
