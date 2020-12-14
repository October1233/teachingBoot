package com.shiyue.superUtil;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Slf4j
public class DateUtil {

    /**
     * Calender类，可分部设置时间日期
     * @param args
     */
    public static void main(String[] args) {
        //日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        //将日期转化为字符串根据-《格式》-转换
        String date = df1.format(new Date());
        //创建一个日历
        Calendar calendar = Calendar.getInstance();
        // 时  分   秒
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        //根据字符串“-”分割成数组
        String[] dateStr = date.split("-");
        System.out.println(Integer.parseInt(dateStr[0])+Integer.parseInt(dateStr[1])+Integer.parseInt(dateStr[2]));
        //分别设置年月日
        //--------------------------------注意：月份从0开始计算所以需要-1
        calendar.set(Calendar.YEAR,Integer.parseInt(dateStr[0]));
        calendar.set(Calendar.MONTH,Integer.parseInt(dateStr[1])-1);
        calendar.set(Calendar.DATE,Integer.parseInt(dateStr[2]));
        calendar.set(Integer.parseInt(dateStr[0]),Integer.parseInt(dateStr[1])-1,Integer.parseInt(dateStr[2]));
        //获取Calendar日历中的Date对象
        Date time = calendar.getTime();
        String format = df.format(time);
        System.out.println(format);
    }

    /**
     * 通过字符串的compareTo方法进行时间比较
     */
    @Test
    public void cascasc(){
        String aaa = "2020-02-03 13:12:11";
        String bbb = "2020-11-02";
        String ccc = "2020-02-03 13:12:12";
        System.out.println("前者大于后者返回="+bbb.compareTo(aaa));
        System.out.println("前后相等返回="+ccc.compareTo(aaa));
        System.out.println("前者小于后者返回="+aaa.compareTo(bbb));
    }

    /**
     * 计算时间差，两年月日相差几个月
     * @param renDate
     * @param beDate
     * @return
     * @throws Exception
     */
    private Integer dateUtil(String renDate,Date beDate)throws Exception{
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(renDate));
        int year1 = c.get(Calendar.YEAR);
        int month1 = c.get(Calendar.MONTH);
        int day1 = c.get(Calendar.DAY_OF_MONTH);
        c.setTime(beDate);
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
        if (result<0){
            result=0;
        }
        return result;
    }

    @Test
    public void dateUtil() throws Exception{
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate3 = simpleFormat.parse("2020-04-11 16:00:00");
        Date toDate3 = new Date();
        log.info("入参="+fromDate3+"------现在"+toDate3);
        long from3 = fromDate3.getTime();
        long to3 = toDate3.getTime();
        log.info("入参="+from3+"------现在"+to3);
        int minutes = (int) ((to3 - from3) / (1000 * 60));
        log.info("最后时间="+minutes);
        if (minutes>=5){
           log.info("大");
        }else {
            log.info("不大");
        }
    }

    @Test
    public void bijiao() throws Exception{
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat simpleMinFormat = new SimpleDateFormat("mm");
        Date fkDate = simpleFormat.parse("2020-04-10 15:55:55");
        Date newDate = new Date();
        if (newDate.compareTo(fkDate)==1){
            Integer c = Integer.parseInt(simpleMinFormat.format(newDate))-Integer.parseInt(simpleMinFormat.format(fkDate));
            System.out.println(c);
        }
        System.out.println((((newDate.getTime() - fkDate.getTime()) / 1000) % 60) + "分钟");
    }









}
