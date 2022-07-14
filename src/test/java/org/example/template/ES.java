package org.example.template;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liaowei
 * @version V1.0
 * @date 2022/7/14 13:02:56
 */
public class ES {
    @Test
    void test() {
        String readLine = null;
        Map map;
        Map<String, Object> m1 = null;
        int i = 0;
        int bytes = 0;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) ;
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\yss\\Desktop\\shga_sample_750k\\case_data_index_source.json")));
             BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\yss\\Desktop\\shga_sample_750k\\case_data_index.json")))) {


            while ((readLine = reader.readLine()) != null) {
                map = objectMapper.readValue(readLine, Map.class);
                m1 = (Map) map.get("_source");
                writer.write(objectMapper.writeValueAsString(m1));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(readLine);
        }
    }

    @Test
    void test2() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\yss\\Desktop\\shga_sample_750k\\case_data_index.json");

        int r;
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        while ((r = fileInputStream.read()) != -1) {
            if (r == '\n') {
                String s = arrayOutputStream.toString(StandardCharsets.UTF_8);
                System.out.println(s);
                arrayOutputStream.reset();
            } else {
                arrayOutputStream.write(r);
            }

        }
    }
}
