package org.example.h2.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexUtil {

    public static String regex(String regex, String input){
        return regex(regex, input, -1);
    }

    public static String regex(String regex, String input, int index){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()){
            String ret = (index > -1) ? matcher.group(index) : matcher.group();
            return ret;
        }
        return "";
    }

    public static String[] regex(String regex, String input, Integer[] index){
        String[] matchers = new String[index.length];
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()){
            for(int i = 0; i < index.length; i++){
                String ret = (index != null && index[i] > -1) ? matcher.group(index[i]) : matcher.group();
                matchers[i] = ret;
            }
        }
        return matchers;
    }

    public static boolean matches(String regex, String input){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean match(String regex, String input){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

}
