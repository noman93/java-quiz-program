import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public void saveJsonList(String question,String option1, String option2, String option3,String option4, String correctans) throws IOException, ParseException {
        String fileName="./src/test/resources/Users.json";
        JSONParser parser=new JSONParser();
        Object obj= parser.parse(new FileReader(fileName));

        JSONArray jsonArray= (JSONArray) obj;

        JSONObject userObject=new JSONObject();
        userObject.put("firstname",firstname);
        userObject.put("lastname",lastname);
        userObject.put("userName",username);
        userObject.put("password",password);
        //userObject.put("employeeId",empId);

        jsonArray.add(userObject);

        FileWriter file=new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
        //System.out.println("Saved data");

    }
}
