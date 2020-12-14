package com.shiyue.springboot.service;

import com.shiyue.springboot.domain.User;
import com.shiyue.springboot.repository.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@Service
public class TestServiceImpl implements TestService{

    @Autowired
    TestService testService;

    @Autowired
    TestMapper testMapper;

    @Autowired
    TransactionalService transactionalService;

    private TestServiceImpl() {}

    private static TestServiceImpl single=null;

    public static TestServiceImpl getInstance() {
        if (single == null) {
            synchronized (TestServiceImpl.class) {
                if (single == null) {
                    single = new TestServiceImpl();
                }
            }
        }
        return single;
    }



    public void abcd(){

    }




    public void testA(){
        final Integer c = 12;
        ccc jiaziduan = a -> System.out.println(a + c);
        jiaziduan.sayHello(2);
    }
    interface ccc{
        void sayHello(Integer in);
    }
//    public TestService sayHello(){
//        TestService sayHello1 = (String i, String j) -> (i.equals(j));
//        return sayHello1;
//    }

    public void sk(){
        transactionalService.propagation_REQUIRES_NEW_A();
    }



    public void sdasd(){
        List<Map<String,Object>> listr = testMapper.selectaddCode();
        System.out.println(listr);
        long start = System.currentTimeMillis();
        Supplier<Map<String,Object>> mapFct = HashMap::new;
        List<Map<String,Object>> cpList = new ArrayList<>();
        listr.stream()
                .collect(Collectors.groupingBy(map1 -> map1.get("parent_id")))
                .forEach((k,list)->{
                    Map<String,Object> nmap= mapFct.get();
                    nmap.put("parent_id",k);
                    BigDecimal bigDecimal = list.stream()
                            .map(m->m.get("id"))
                            .map(mapcccd -> new BigDecimal(String.valueOf(mapcccd)))
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    nmap.put("amount",bigDecimal);
                    cpList.add(nmap);
                });
        long end = System.currentTimeMillis();
        System.out.println("老-耗时（毫秒）：" + (end - start));
        System.out.println(cpList);
    }

    public void shsdaaa(){
        List<Map<String,Object>> listr2 = testMapper.selectaddCode();
        System.out.println(listr2);
        long start = System.currentTimeMillis();
        List list = listr2.stream()
                .collect(Collectors.groupingBy(map1 -> map1.get("parent_id")))
                .entrySet()
                .stream()
                .map(bag-> bag.getValue()
                        .stream()
                        .map(m->m.get("id"))
                        .map(mapcccd -> new BigDecimal(String.valueOf(mapcccd)))
                        .reduce(BigDecimal::add)
                        .map(bigDecimal -> new HashMap(){{put(bag.getKey(),bigDecimal);}}))
                .map(Optional::get)
                .collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.println("新-耗时（毫秒）：" + (end - start));
        System.out.println(list);
    }
}
