package jsource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by system on 2017/9/3.
 * 泛型
 */
public class JavaGeneric {
    public static void main(String[] args) {
        List<String> name = new ArrayList<String>();
        List<Integer> age = new ArrayList<Integer>();
        List<Number> number = new ArrayList<Number>();
        List<?> number2 = new ArrayList<Number>();
        name.add("icon");
        age.add(18);
        number.add(314);

        getUperNumber(age);//2
        getUperNumber(number);//3
        number.addAll(age);


        //若是不型变的
        List<String> strs = new ArrayList<String>();
        List<Object> objs = (List) strs;
        objs.add(1);
        String s = strs.get(0); // ！！！ ClassCastException：无法将整数转换为字符串

    }

    public static void getData(List<?> data) {
        System.out.println("data :" + data.get(0));
    }

    public static void getUperNumber(List<? extends Number> data) {
        System.out.println("data :" + data.get(0));
    }


}
