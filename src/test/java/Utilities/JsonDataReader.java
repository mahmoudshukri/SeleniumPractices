package Utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonDataReader {
//    public String firstName, lastName, Email, PassWord;
//
//    public void JsonReader() throws IOException, ParseException {
//        String filePath = System.getProperty("user.dir") + "/src/test/java/Data/UserRegData.json";
//        File srcFile = new File(filePath);
//        JSONParser parser = new JSONParser();
//        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(srcFile));
//
//        for (Object jsonObj : jsonArray) {
//            JSONObject person = (JSONObject) jsonObj;
//            firstName = (String) person.get("firstName");
//            // System.out.println(firstName);
//            lastName = (String) person.get("lastName");
//
//            // System.out.println(lastName);
//            Email = (String) person.get("Email");
//            //  System.out.println(Email);
//            PassWord = (String) person.get("PassWord");
//            // System.out.println(PassWord);
//            // Perform actions or assertions with the user data here
//            System.out.println("First Name: " + firstName);
//            System.out.println("Last Name: " + lastName);
//            System.out.println("Email: " + Email);
//            System.out.println("Password: " + PassWord);
//        }
//    }
    public List<String> firstNames = new ArrayList<>();
    public List<String> lastNames = new ArrayList<>();
    public List<String> emails = new ArrayList<>();
    public List<String> passwords = new ArrayList<>();

    public void JsonReader() throws IOException, ParseException {
        String filePath = System.getProperty("user.dir") + "/src/test/java/Data/UserRegData.json";
        File srcFile = new File(filePath);
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(srcFile));

        for (Object jsonObj : jsonArray) {
            JSONObject person = (JSONObject) jsonObj;
            firstNames.add((String) person.get("firstName"));
            lastNames.add((String) person.get("lastName"));
            emails.add((String) person.get("Email"));
            passwords.add((String) person.get("PassWord"));
        }
    }

}


