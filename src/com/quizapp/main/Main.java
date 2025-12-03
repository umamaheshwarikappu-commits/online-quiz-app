package com.quizapp.main;

import com.quizapp.model.*;
import com.quizapp.service.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
        
        while(true){
            System.out.println("\n--- ONLINE QUIZ SYSTEM ---");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if(choice == 1){
                break;
            }
            else if(choice==2){
                System.out.println("Enter new Username:");
                String newUser = sc.nextLine();

                System.out.println("Enter password:");
                String newPassword = sc.nextLine();

                boolean success = userService.registerUser(newUser, newPassword);

                if(success){
                    System.out.println("Registration Successful! You can login now");
                }
                else{
                    System.out.println("Username already taken. Try another one");
                }
            }
            else if (choice == 3) {
                System.out.println("Goodbye!");
                return;
            }
            else {
                System.out.println("Invalid choice. Try again.");
            }

        }
        boolean loggedIn = false;

        // Loop until login success
        while (!loggedIn) {
            System.out.print("Enter Username: ");
            String username = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            if (userService.validateLogin(username, password)) {
                System.out.println("\nLogin Successful! Welcome, " + username + "!");
                loggedIn = true;
            } else {
                System.out.println("\nInvalid username or password. Try again.\n");
            }
        }

        // AFTER LOGIN → START QUIZ
        System.out.println("\n===== STARTING QUIZ =====");

        QuestionService service = new QuestionService();
        List<Question> questions = service.loadQuestions();

        if (questions == null) {
            System.out.println("Failed to load questions!");
            return;
        }

        int score = 0;
        Map<Question, String> userAnswers = new LinkedHashMap<>();

        for (Question q : questions) {
            System.out.println("\n" + q.getQuestion());
            String[] opts = q.getOptions();

            for (int i = 0; i < opts.length; i++) {
                System.out.println((i + 1) + ". " + opts[i]);
            }

            System.out.print("Your answer: ");
            int choice = sc.nextInt();

            String userAns = (choice >= 1 && choice <= opts.length) ? opts[choice - 1] : "Invalid";
            userAnswers.put(q, userAns);

            if (userAns.equalsIgnoreCase(q.getAnswer())) {
                score++;
            }
        }

        System.out.println("\n===== QUIZ COMPLETED =====");
        System.out.println("Your Score: " + score + "/" + questions.size());

        System.out.println("\n===== REVIEW =====");
        for (Question q : questions) {
            String userAns = userAnswers.get(q);
            String correct = q.getAnswer();

            System.out.println("\nQ: " + q.getQuestion());
            System.out.println("Your Answer: " + userAns);

            if (userAns.equalsIgnoreCase(correct)) {
                System.out.println("Result: Correct");
            } else {
                System.out.println("Correct Answer: " + correct);
                System.out.println("Result: Wrong");
            }
        }
    }
}
