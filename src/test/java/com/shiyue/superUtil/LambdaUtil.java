package com.shiyue.superUtil;

import com.shiyue.springboot.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@Slf4j
public class LambdaUtil {

    /**
     * 将获取数字符串通过stream转换为List
     */
    @Test
    public void listString转换(){
        String 以逗号分割的字符串 = "2,3,4,5,6";
        List<String> list = Arrays.stream(以逗号分割的字符串.split(",")).
                collect(Collectors.toCollection(ArrayList::new));
        log.info("入参字符串->{},字符串List->{}",以逗号分割的字符串,list);
    }

    /**
     * 将数组用转换为
     */
    @Test
    public void 数组转换为List(){
        String[] 以逗号分割的数组 = {"2","3","4","5","6"};
        List<String> list = Arrays.asList(以逗号分割的数组);
        list.add("2");
        log.info("入参字符串->{},字符串List->{}",以逗号分割的数组,list);
    }
    /**
     * int Integer专用统计流
     */
    @Test
    public void 统计流(){
        List<Integer> residue = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        IntSummaryStatistics allAverLoanAges = residue.stream().mapToInt((x) -> x).summaryStatistics();
        log.info("求和={}",allAverLoanAges.getSum());
        log.info("平均={}",allAverLoanAges.getAverage());
        log.info("最大={}",allAverLoanAges.getMax());
        log.info("最小={}",allAverLoanAges.getMin());
        log.info("个数={}",allAverLoanAges.getCount());

    }

    /**
     * 布尔流
     */
    @Test
    public void 布尔流(){
        List<String> strs = Arrays.asList("a", "a", "a", "a", "b");
        //变量aa的表达式，strs里的元素，任意有“a”，表示true
        boolean aa = strs.stream().anyMatch(str -> str.equals("a"));
        //strs里的元素，全部为“a”，表示true，否则false
        boolean bb = strs.stream().allMatch(str -> str.equals("a"));
        //strs里的元素，全部不为“a”，表示true，否则false
        boolean cc = strs.stream().noneMatch(str -> str.equals("a"));
        //统计strs中某元素的个数
        long count = strs.stream().filter(str -> str.equals("a")).count();
        log.info("变量aa的表达式，strs里的元素，任意有“a”，表示true---->>>>{}",aa);
        log.info("strs里的元素，全部为“a”，表示true，否则false---->>>>{}",bb);
        log.info("strs里的元素，全部不为“a”，表示true，否则false---->>>>{}",cc);
        log.info("统计strs中某元素的个数---->>>>{}",count);
    }
    /**
     * 花式统计
     */
    @Test
    public void 金额统计(){
        List<User> userList = new ArrayList<>();
        Supplier<User> userFactory = User::new;
        for (int i=0;i<10;i++){
            User user = userFactory.get();
            user.setAmount(new BigDecimal(1000));
            userList.add(user);
        }
        BigDecimal amounts1 = userList
             .stream()
             .filter(c->c.getSex()!="女")
             .map(item -> Optional.ofNullable(item.getAmount()).orElse(BigDecimal.ZERO))
             .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("资产总和 :" + amounts1);

    }








}
