package question02;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Owem
 * @date 2022/9/13 15:59
 * @description TODO
 **/
public class Starter {
    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            persons.add(new Student(getRandomString(5)));
        }
        SortUtils.sortID(persons);
        System.out.println("---------------");
        persons = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            persons.add(new Teacher(getRandomString(5)));
        }
        SortUtils.sortID(persons);
    }


    //length用户要求产生字符串的长度
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        String str="0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
//            int number=random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
