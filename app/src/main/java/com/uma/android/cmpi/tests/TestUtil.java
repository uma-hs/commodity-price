package com.uma.android.cmpi.tests;

import com.uma.android.cmpi.api.CMPAPIResponse;
import com.uma.android.cmpi.model.Commodity;
import com.uma.android.cmpi.util.JsonUtil;
import com.uma.android.cmpi.util.RestUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static java.lang.System.out;

/**
 * Created by Umamaheshwar HS on 7/14/2016.
 */
public class TestUtil {
    public static String TAG = TestUtil.class.getCanonicalName();
    public static String DELIMITER = "#$#";
    public static String FILE_NAME = "states.properties";


    public static void main(String[] args) throws MalformedURLException {
        // createPropertiesFile();
    }

    public static void createPropertiesFile() throws MalformedURLException {
        {
            String BASE_URL = RestUtil.getURL();
            int offset = 0;
            int count = 99999999;
            Map<String, Set<String>> states = new TreeMap<>();
            while (count > 0) {
                print(offset + "\n");
                String apiresponse = RestUtil.get(new URL(BASE_URL + "&offset="
                        + offset));

                CMPAPIResponse response = JsonUtil.toObject(apiresponse,
                        CMPAPIResponse.class);

                for (Commodity commodity : response.getRecords()) {
                    String state = commodity.getState();
                    if (states.containsKey(state)) {
                        states.get(state).add(commodity.getDistrict());
                    } else {
                        Set<String> set = new TreeSet<>();
                        set.add(commodity.getDistrict());
                        states.put(state, set);
                    }
                }
                // print(response.getCount());
                offset++;
                count = response.getCount();
            }
            StringBuilder sb = new StringBuilder();
            for (String state : states.keySet()) {
                sb.append(state + "=");
                int cnt = 0;
                for (String district : states.get(state)) {
                    if (cnt > 0)
                        sb.append(DELIMITER);
                    sb.append(district);
                    cnt++;
                }
                sb.append("\n");

            }
            print(sb.toString());
            try {
                createFile(new File(FILE_NAME), sb.toString());
            } catch (IOException e) {

                e.printStackTrace();
            }

        }

    }

    public static void print(Object o) {
        out.print(o);
    }

    public static void createFile(File file, String out) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(out);
        bw.close();
    }

}
