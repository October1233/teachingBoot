package com.shiyue.springboot;

import com.shiyue.springboot.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Slf4j
public class test {

    final static int b = 5;





    @Test
    public void testA(){
        final Integer c = 12;
        Jiaziduan jiaziduan = a -> System.out.println(a + c);
        jiaziduan.sayName(2);
    }
    interface Jiaziduan{
        void sayName(Integer str);
    }

    @Test
    public void sss(){
        String s = "ssass";
        String cc = s.substring(2,3);
        log.info("s="+cc);

//        Map map = new HashMap();
//        map.put("ccc",s);
//        log.info("map="+map);



    }
    @Test
    public void ccascas(){
        String a = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJMz5RBKARuQOSNr4H43tAq9dxd92FbWAwGZyWRoB1/93zGuuYO6CUKmt5PkExIrEsoB2L4heDY7FM2Whuxpqed0ALKz2jpm60whznaY\n" +
                "auebDsFsKOUUcWgdEEu7ekBC8OUn7s7tC6avkWvVUwm2UqD0zZ0Y5cHL/xo/HBXHR30RAgMBAAECgYAwcaOw+KnkqFIllhsLbsmRv0iyMnhAG65hmHru0E2dCa+qahYjg9YIWVWRFj4wfHxyfqkV6W3mawstnZxJsXx4b0m8\n" +
                "W3pmh8a6hXFETPauJmAxSLm3m2xbO+1pGeqD8L9jaj8d/Mu8LsMAsGVUzN5zOQZ8nR3z4WRyavRw7JmQAQJBANIC6eHR9nahQ8051rRqza1GgJchi2FFnxq0QVCpX+VmXLhoXN+MxwIjbWDq3d4JHmKXwt7yZhunJ8fzbDcl\n" +
                "GskCQQCzb/j+9gKVWrep5ozrqApkHc/rTsDCslph+Nj0FCFfBVsfWknXEM8H1kL45LDyRDECtlrxeBgWm1H/bvpz5ywJAkAw8EzgOrVcX6gVIu1NjbXwMwW0MjTwrfCtoGO4EEAwjPH+ZxN9ZKjbbrYGZ7TcEPPrtcHLgmEZ\n" +
                "AJLcnXUSRKtBAkAqMzpXFBp1b7lkHtGjloijFRPSHlpdL77r7a9wb8+NXmAY1o4pI03dZPmpOXeX8ouoPKTUx066+lplfX8vhP2pAkBilp8Cz8KwjbTLeIUfSw//tSz2f2FI980qZhypo/xSQMPgP0FuzJRa5f9YKm1ctmMN\n" +
                "CDyVmO7OmoPtROqYZE4+";
        String b = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJMz5RBKARuQOSNr4H43tAq9dxd92FbWAwGZyWRoB1/93zGuuYO6CUKmt5PkExIrEsoB2L4heDY7FM2Whuxpqed0ALKz2jpm60whznaY\n" +
                "auebDsFsKOUUcWgdEEu7ekBC8OUn7s7tC6avkWvVUwm2UqD0zZ0Y5cHL/xo/HBXHR30RAgMBAAECgYAwcaOw+KnkqFIllhsLbsmRv0iyMnhAG65hmHru0E2dCa+qahYjg9YIWVWRFj4wfHxyfqkV6W3mawstnZxJsXx4b0m8\n" +
                "W3pmh8a6hXFETPauJmAxSLm3m2xbO+1pGeqD8L9jaj8d/Mu8LsMAsGVUzN5zOQZ8nR3z4WRyavRw7JmQAQJBANIC6eHR9nahQ8051rRqza1GgJchi2FFnxq0QVCpX+VmXLhoXN+MxwIjbWDq3d4JHmKXwt7yZhunJ8fzbDcl\n" +
                "GskCQQCzb/j+9gKVWrep5ozrqApkHc/rTsDCslph+Nj0FCFfBVsfWknXEM8H1kL45LDyRDECtlrxeBgWm1H/bvpz5ywJAkAw8EzgOrVcX6gVIu1NjbXwMwW0MjTwrfCtoGO4EEAwjPH+ZxN9ZKjbbrYGZ7TcEPPrtcHLgmEZ\n" +
                "AJLcnXUSRKtBAkAqMzpXFBp1b7lkHtGjloijFRPSHlpdL77r7a9wb8+NXmAY1o4pI03dZPmpOXeX8ouoPKTUx066+lplfX8vhP2pAkBilp8Cz8KwjbTLeIUfSw//tSz2f2FI980qZhypo/xSQMPgP0FuzJRa5f9YKm1ctmMN\n" +
                "CDyVmO7OmoPtROqYZE4+";
        System.out.println(a.equals(b));
    }


}
