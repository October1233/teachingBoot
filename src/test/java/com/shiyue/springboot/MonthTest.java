import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MonthTest{

    public static void main(String[] args) throws ParseException {

        int sum=0;
        for(int i=0;i<=100;i++){
            sum=sum+i;
        }
        System.out.println("for循环：1+2+3+...+100="+sum);


        String d1 = "2022-01-12";
        String d2 = "2012-02-11";
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(d1));
        int year1 = c.get(Calendar.YEAR);
        int month1 = c.get(Calendar.MONTH);

        c.setTime(new Date());
        int year2 = c.get(Calendar.YEAR);
        int month2 = c.get(Calendar.MONTH);

        int result;
        if(year1 == year2) {
            result = month1 - month2;
        } else {
            result = 12*(year1 - year2) + month1 - month2;
        }
        System.out.println(result);
    }

    @Test
    private Integer dateUtil(Date renDate)throws Exception{
        Calendar c = Calendar.getInstance();
        c.setTime(renDate);
        int year1 = c.get(Calendar.YEAR);
        int month1 = c.get(Calendar.MONTH);

        c.setTime(new Date());
        int year2 = c.get(Calendar.YEAR);
        int month2 = c.get(Calendar.MONTH);

        int result;
        if(year1 == year2) {
            result = month1 - month2;
        } else {
            result = 12*(year1 - year2) + month1 - month2;
        }
        System.out.println(result);
        return result;
    }

    public void sss(){

        }
    @Test
    public Integer dateUtil(String renDate,Date beDate)throws Exception{
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse("2020-01-01"));
        int year1 = c.get(Calendar.YEAR);
        int month1 = c.get(Calendar.MONTH);
        c.setTime(new Date());
        int year2 = c.get(Calendar.YEAR);
        int month2 = c.get(Calendar.MONTH);
        int result;
        if(year1 == year2) {
            result = month1 - month2;
        } else {
            result = 12*(year1 - year2) + month1 - month2;
        }
        if (result<0){
            result=0;
        }
        System.out.println(result);
        return result;
    }

    @Test
    public void cascasc(){
        String aaa = "2020-02-03";
        String bbb = "2020-11-02";
        String ccc = "2020-02-03";
        System.out.println(aaa.compareTo(bbb));
    }

}



