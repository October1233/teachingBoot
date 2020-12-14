/*
package com.shiyue.springboot;

import com.shiyue.springboot.repository.TestMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;



public class test1 {

    @Autowired
    TestMapper testMapper;
    @Test
    public void text1() throws Exception {

        File zf = new File("D:/CUSTBALCHK_0010_20190914_001.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(zf));

        String c1;
        while((c1 = bufferedReader.readLine())!=null){
            String[] c2 = c1.split("&;");
            String val = testMapper.IntStr(c2[0]);
        }
    }
}
*/
