package data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Data
@JsonAutoDetect
@AllArgsConstructor
@NoArgsConstructor
public class TgBot {
    private String name;
    private String token;

    public static List<TgBot> getListTgBots(){
        List<TgBot> botList = new ArrayList<TgBot>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("\\\\TECH-1C-04\\Test\\TgBot.json"));
            StringBuilder strReader = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null){
                strReader.append(line);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            List<LinkedHashMap<String,String>> objectList = objectMapper.readValue(strReader.toString(),botList.getClass());

            for (LinkedHashMap ob:objectList) {
                String name = (String) ob.get(new String("name"));
                String token = (String) ob.get(new String("token"));
                botList.add(new TgBot(name,token));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botList;
    }
}
