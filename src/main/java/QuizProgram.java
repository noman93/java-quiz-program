import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QuizProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JSONArray quizBank = loadQuizBank();
        int choice;

        do {
            System.out.println("1. Add Quiz");
            System.out.println("2. Start Quiz");
            System.out.println("3. Exit");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addQuiz(quizBank, scanner);
                    saveQuizBank(quizBank);
                    break;
                case 2:
                    startQuiz(quizBank, scanner);
                    break;
                case 3:
                    System.out.println("Exiting Quiz Program...");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while (choice != 3);

        scanner.close();
    }

    private static JSONArray loadQuizBank() {
        try {
            FileReader reader = new FileReader("./src/main/resources/DATA.json");
            JSONParser parser = new JSONParser();
            return (JSONArray) parser.parse(reader);
        } catch (Exception e) {
            return new JSONArray();
        }
    }

    private static void saveQuizBank(JSONArray quizBank) {
        try {
            FileWriter writer = new FileWriter("./src/main/resources/DATA.json");
            writer.write(quizBank.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addQuiz(JSONArray quizBank, Scanner scanner) {
        System.out.print("Please add a ques here: ");
        String question = scanner.nextLine();

        JSONObject quiz = new JSONObject();
        quiz.put("Question", question);

        for (int i = 0; i < 4; i++) {
            //System.out.println("Input options:");
            System.out.print("Options "+ (char) ('a' + i) + ": ");
            String option = scanner.nextLine();
            quiz.put("Option " + (char) ('a' + i), option);
        }

        System.out.print("Please input the correct ans:");
        String answer = scanner.nextLine();
        quiz.put("answer", answer);

        quizBank.add(quiz);

        System.out.print("Quiz saved at the database. Do you want to add more? (y/n): ");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            addQuiz(quizBank, scanner);
        }
    }

    private static void startQuiz(JSONArray quizBank, Scanner scanner) {
        if (quizBank.isEmpty()) {
            System.out.println("Quiz bank is empty!");
            return;
        }

        System.out.println("You will be asked 5 questions, each question has 1 mark.");
        int score = 0;

        for (int i = 0; i < 5; i++) {
            int index = (int) (Math.random() * quizBank.size());
            JSONObject quiz = (JSONObject) quizBank.get(index);

            System.out.println((i + 1) + ". " + quiz.get("Question"));
            System.out.println("a. " + quiz.get("Option a"));
            System.out.println("b. " + quiz.get("Option b"));
            System.out.println("c. " + quiz.get("Option c"));
            System.out.println("d. " + quiz.get("Option d"));
            System.out.print("Enter your answer: ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase((String) quiz.get("answer"))) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Not correct.");
            }
        }
        System.out.println("Result: You got " + score + " out of 5");
    }
}

