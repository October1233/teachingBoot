package com.shiyue.studybass;

import com.shiyue.springboot.domain.Students;
import com.shiyue.springboot.domain.User;
import com.shiyue.springboot.service.EazyUiservice;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
public class OptionalLi {

    @Autowired
    EazyUiservice eazyUiservice;

    public static void main(String[] args) {
        OptionalLi optionalLi = new OptionalLi();
        Integer value1 = null;
        Integer value2 = new Integer(10);
        StringBuffer string = new StringBuffer(10);

        // Optional.ofNullable - 允许传递为 null 参数
        java.util.Optional<Integer> a = java.util.Optional.ofNullable(value1);

        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        java.util.Optional<Integer> b = java.util.Optional.of(value2);
        System.out.println(optionalLi.sum(a,b));
    }
    public Integer sum(java.util.Optional<Integer> a, java.util.Optional<Integer> b){

        // Optional.isPresent - 判断值是否存在

        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));

        //Optional.get - 获取值，值需要存在
        Integer value2 = b.get();


        String value3 = "1234";

        java.util.Optional<String> str = java.util.Optional.ofNullable(value3);
        System.out.println("新"+str);

        System.out.println("新2"+str.orElse("abcdefg"));
        return value1 + value2;
    }
    @Test
    public void testOptainal(){
        User user = new User();
        String userAddress = user.getAddress();
        java.util.Optional<Object> c = java.util.Optional.ofNullable(userAddress);
        System.out.println(c);
        System.out.println(c.get());
        userAddress = String.valueOf(c.orElse("中国"));
        System.out.println(userAddress);
        c.ifPresent(System.out::println);
    }

    @Test
    public void testBDOptional(){
        Arrays.asList(3, 1, 2, 1).stream().distinct().sorted().forEach(System.out::println);
        User user = new User();
        List<User> userss = new ArrayList<>();
        user.setUsername("sssssyyyyy");
        userss.add(user);
        Map map = new HashMap();
        String ccc = "0.000270000";
        map.put("ccc1",ccc);
        BigDecimal muls = new BigDecimal("100");// 取100的倍数
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(map.get("ccc1")));
        BigDecimal format = bigDecimal.multiply(muls);
        log.info("ccc{}nnn{}",format,bigDecimal);
        List<User> users =
                eazyUiservice.findAll();
        Optional<List<User>> OPUsers = Optional.ofNullable(users);
        OPUsers.orElse(userss);
        log.info("Optional打印{}",OPUsers.toString());

        List<Students> students = users.stream().map(user66 -> {
            Students students1 = new Students();
            students1.setName(user66.getUsername());

            return students1;
        }).collect(Collectors.toList());
        students.stream().forEach(System.out::println);

    }



    /**
     * 这个示例中，两个 Optional  对象都包含非空值，两个方法都会返回对应的非空值。不过，orElse()
     * 方法仍然创建了 User 对象。与之相反，orElseGet() 方法不创建 User 对象
     */
    @Test
    public void givenPresentValue_whenCompare_thenOk() {
        User user = new User();
        log.info("Using orElse");
        User result = Optional.ofNullable(user).orElse(createNewUser());
        log.info("Using orElseGet");
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
    }
    private User createNewUser() {
        log.debug("Creating New User");
        return new User();
    }
    @Test
    public void hashmapc(){
        List<Map> mapList = new ArrayList<>();

        for (int i=0;i<6;i++){
            Map map = new HashMap();
            map.put("a",i);
            map.put("b",i+5);
            mapList.add(map);
        }
        Map map1 = new HashMap();
        map1.put("a",null);
        map1.put("b",5);
        mapList.add(map1);
        Map map2 = new HashMap();
        map2.put("a",null);
        map2.put("b",6);
        mapList.add(map2);
        log.info("list=="+mapList);
        List now = mapList.stream().filter(map -> map.get("a")!=null).collect(Collectors.toCollection(ArrayList::new));
        log.info("now===="+now);


    }

    public void sss(){
        String a = null;
    }

}
