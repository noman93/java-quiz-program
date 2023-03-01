import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QuizProgram{


    static ArrayList<Question> questionBank = new ArrayList<Question>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (choice != 3) {
            System.out.println("Welcome to Quiz Program");
            System.out.println("1. Add Question");
            System.out.println("2. Take Quiz");
            System.out.println("3. Exit");
            System.out.println("Enter your choice:");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    addQuestion(sc);
                    break;
                case 2:
                    takeQuiz(sc);
                    break;
                case 3:
                    saveQuizBank();
                    System.out.println("Thank you for using Quiz Program!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public static void addQuestion(Scanner sc) {
        System.out.println("Enter question:");
        String question = sc.nextLine();
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
        sc.nextLine();
        questionBank.add(new Question(question, option1, option2, option3, option4, answer));
        System.out.println("Question added successfully!");
    }

    public static void takeQuiz(Scanner sc) {
        if (questionBank.isEmpty()) {
            System.out.println("Quiz bank is empty, please add questions first.");
            return;
        }
        ArrayList<Question> quizQuestions = new ArrayList<Question>();
        for (int i = 0; i < 5; i++) {
            int index = (int)(Math.random() * questionBank.size());
            quizQuestions.add(questionBank.get(index));
        }
        int score = 0;
        for (int i = 0; i < quizQuestions.size(); i++) {
            Question q = quizQuestions.get(i);
            System.out.println("Question " + (i+1) + ": " + q.getQuestion());
            System.out.println("1. " + q.getOption1());
            System.out.println("2. " + q.getOption2());
            System.out.println("3. " + q.getOption3());
            System.out.println("4. " + q.getOption4());
            System.out.println("Enter your answer (1-4):");
            int userAnswer = sc.nextInt();
            sc.nextLine();
            if (userAnswer == q.getAnswer()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is " + q.getAnswer());
            }
        }
        System.out.println("Quiz complete. Your score is " + score + " out of 5.");
    }
    public static void saveQuizBank() {
        JSONArray questionList = new JSONArray();
        for (Question q : questionBank) {
            JSONObject questionObj = new JSONObject();
            questionObj.put("question", q.getQuestion());
            questionObj.put("option1", q.getOption1());
            questionObj.put("option2", q.getOption2());

            questionObj.put("option3", q.getOption3());
            questionObj.put("option4", q.getOption4());
            questionObj.put("answer", q.getAnswer());
            questionList.add(questionObj);
        }
        try (FileWriter file = new FileWriter("quiz_bank.json")) {
            file.write(questionList.toJSONString());
            System.out.println("Quiz bank saved successfully!");
        } catch (IOException e) {
            System.out.println("Error occurred while saving quiz bank: " + e.getMessage());
        }





}

    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int answer;

    public void Question(String question, String option1, String option2, String option3, String option4, int answer) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public int getAnswer() {
        return answer;
    }

}
