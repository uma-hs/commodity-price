package com.uma.android.cmpi.util;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Umamaheshwar HS on 7/16/2016.
 */
public class StringUtil {
    public static final String join(String delim, List<String> parts) {
        StringBuilder builder = new StringBuilder();
        int length=parts.size();
        for (int i = 0; i <length - 1; i++) {
            builder.append(parts.get(i)).append(delim);
        }
        if(length > 0){
            builder.append(parts.get(length - 1));
        }
        return builder.toString();
    }

    public static List<String> split(String delim,String string){
        if(string==null||string.isEmpty()||delim==null){
            return null;
        }
        String vals[]=string.split(delim);
        return Arrays.asList(vals);

    }
}
