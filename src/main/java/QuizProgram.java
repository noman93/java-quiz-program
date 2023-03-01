import java.io.FileWriter;
import java.io.FileWriter;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class QuizProgram {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        List<JSONObject> questions = new ArrayList<JSONObject>();

        while (true) {
            System.out.println("Enter an option:");
            System.out.println("1. Add Question");
            System.out.println("2. Start Quiz");
            int option = input.nextInt();
            input.nextLine(); // Consume the newline character

            if (option == 1) {
                JSONObject question = new JSONObject();

                System.out.println("Enter the question:");
                String questionText = input.nextLine();
                question.put("questionText", questionText);

                Map<String, String> options = new HashMap<String, String>();
                options.put("a", "");
                options.put("b", "");
                options.put("c", "");
                options.put("d", "");
                for (String key : options.keySet()) {
                    System.out.println("Enter option " + key + ":");
                    String optionValue = input.nextLine();
                    options.put(key, optionValue);
                }
                question.put("options", options);

                System.out.println("Enter the answer:");
                String answer = input.nextLine();
                question.put("answer", answer);

                questions.add(question);

                System.out.println("Question added!");
            }
            else if (option == 2) {
                if (questions.isEmpty()) {
                    System.out.println("Please add some questions first!");
                    continue;
                }

                // Shuffle the list of questions
                Collections.shuffle(questions);

                int numQuestions = 5;
                if (questions.size() < 5) {
                    numQuestions = questions.size();
                }

                int score = 0;
                for (int i = 0; i < numQuestions; i++) {
                    JSONObject question = questions.get(i);

                    System.out.println("Question " + (i + 1) + ": " + question.get("questionText"));
                    Map<String, String> options = (Map<String, String>) question.get("options");
                    for (String key : options.keySet()) {
                        System.out.println(key + ") " + options.get(key));
                    }
                    System.out.println("Enter your answer:");
                    String answer = input.nextLine();

                    if (answer.equals(question.get("answer"))) {
                        score++;
                        System.out.println("Correct!");
                    } else {
                        System.out.println("Incorrect!");
                    }
                }

                System.out.println("Quiz finished! You scored " + score + " out of " + numQuestions + ".");

                // Save the questions to a JSON file
                JSONArray jsonQuestions = new JSONArray();
                for (JSONObject question : questions) {
                    jsonQuestions.add(question);
                }

                try (FileWriter file = new FileWriter("./src/main/resources/DATA.json")) {
                    file.write(jsonQuestions.toJSONString());
                    file.flush();
                    file.close();
                    System.out.println("Questions saved to questions.json");
                } catch (Exception e) {
                    System.out.println("An error occurred while saving the questions: " + e.getMessage());
                }

                break;
            }
        }
    }
}
