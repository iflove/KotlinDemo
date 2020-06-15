package jsource.chapter7;

import demo.chapter7.KolinCallJavaKt;

import java.util.Arrays;
import java.util.List;

/**
 * Created by system on 2017/9/16.
 */
public class JavaDataType {

    //原始类型
    public static Object nullObj;
    public static final String NAME = "R";
    public byte aByte = 1;
    public Integer anInt = null;

    public static native void load();

    public static List<String> IDES = Arrays.asList("IntelliJ", "Eclipse", "Android Studio");

    public static void main(String[] args) {
        KolinCallJavaKt.getClassName();
    }
}
