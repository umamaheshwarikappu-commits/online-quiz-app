import java.util.List;
import java.util.Scanner;

public class Main {
     public static void main(String[] args) {

        QuestionService service = new QuestionService();

        List<Question> questions = service.loadQuestions();

        if (questions == null) {
            System.out.println("Failed to load questions!");
            return;
        }

        int score;
         try (Scanner sc = new Scanner(System.in)) {
             score = 0;
             for (Question q : questions) {
                 System.out.println("\n" + q.getQuestion());
                 String[] opts = q.getOptions();
                 
                 for (int i = 0; i < opts.length; i++) {
                     System.out.println((i + 1) + ". " + opts[i]);
                 }
                 
                 System.out.print("Your answer: ");
                 int choice = sc.nextInt();
                 
                 // validate input
                 if (choice >= 1 && choice <= opts.length) {
                     if (opts[choice - 1].equalsIgnoreCase(q.getAnswer())) {
                         score++;
                     }
                 }
             }}

        System.out.println("\nQuiz Completed!");
        System.out.println("Your Score: " + score + "/" + questions.size());
    }
}
