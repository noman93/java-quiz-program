import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class QuizGame {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (choice != 3) {
            System.out.println("Welcome to Quiz Program");
            System.out.println("1. Add Question");
            System.out.println("2. Take Quiz");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    addQuestion(sc);
                    break;
                /*case 2:
                    takeQuiz(sc);
                    break;*/

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public static void addQuestion(Scanner sc) throws IOException, ParseException {
        String fileName="./src/main/resources/DATA.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONObject userObj = new JSONObject();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter question:");
        userObj.put("Question", input.nextLine());
        System.out.println("Enter option 1:");
        String option1 = sc.nextLine();
        System.out.println("Enter option 2:");
        String option2 = sc.nextLine();
        System.out.println("Enter option 3:");
        String option3 = sc.nextLine();
        System.out.println("Enter option 4:");
        String option4 = sc.nextLine();
        System.out.println("Enter answer (1-4):");
        int answer = sc.nextInt();



    }



}
