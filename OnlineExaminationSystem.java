import java.util.*;

public class OnlineExaminationSystem {
    static Scanner sc = new Scanner(System.in);
    static int score = 0;
    static boolean timeUp = false;

    public static void main(String[] args) {
        System.out.println("\n==== ONLINE EXAMINATION SYSTEM ====\n ");
        System.out.println("Login");
        System.out.print("\nEnter username: ");
        String user = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        if (
            (user.equals("Ravinder") && pass.equals("ravinder@1")) ||
            (user.equals("Rayyan") && pass.equals("rayyan@1")) ||
            (user.equals("Mouneesh") && pass.equals("mouneesh@1")) 
        ) 
        {
            System.out.println("\n Login successful! Welcome, " + user);
            System.out.println("\n JAVA QUIZ ");
            System.out.println("\n---- QUIZ INSTRUCTIONS ----");
            System.out.println("\n1. We will be asked 5 multiple-choice questions.");
            System.out.println("2. Each question will have 4 options. Choose the correct answer.");
            System.out.println("3. You have 30 seconds to answer each question.");
            System.out.println("4. Once the timer expires, the question will automatically be submitted.");
            System.out.println("5. After answering all questions, your score will be displayed.");
            System.out.println("6. The exam will be automatically submitted after 3 minutes.");
            System.out.println("\nGood luck! Let's begin the quiz...");
            System.out.print("\nDo you want to start the quiz? (yes/no): ");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine().trim().toLowerCase();
            
            if (choice.equals("yes")) {
                System.out.println("Exam will start in 3 seconds...");
                waitTimer(3); 
                
            } else {
                System.out.println("Quiz aborted. Thank you!");
                System.exit(0);
            }
       
            Thread timerThread = new Thread(() -> {
                try {
                    Thread.sleep(3 * 60 * 1000); 
                    timeUp = true;
                    System.out.println("\n Time's up! Auto-submitting your exam...");
                    System.out.println("Score: " + score + "/5");
                    System.out.println("Result: " + (score >= 3 ? "PASS" : "FAIL"));
                    System.exit(0); 
                } catch (InterruptedException e) {
                    System.out.println("Timer error.");
                }
            });
            timerThread.start();

            for (int i = 1; i <= 5; i++) {
                if (timeUp) break;
                askQuestion(i);
            }

            System.out.println("\nExam completed!");
            System.out.println("Score: " + score + "/5");
            System.out.println("Result: " + (score >= 3 ? "PASS" : "FAIL"));
        } else {
            System.out.println("\n Invalid credentials. Access denied.");
        }
    }

    static void waitTimer(int sec) {
        try {
            while (sec > 0) {
                System.out.println("Starting in: " + sec + " seconds...");
                Thread.sleep(1000);
                sec--;
            }
        } catch (InterruptedException e) {
            System.out.println("Timer error");
        }
    }

    static void askQuestion(int qNo) {
        int answer;
        long start, end;

        switch (qNo) {
            case 1:
                System.out.println("\nQ1. What is the capital of India?");
                System.out.println("1. Delhi\n2. Mumbai\n3. Jaipur\n4. Hyderabad");
                break;

            case 2:
                System.out.println("\nQ2. Who invented Java?");
                System.out.println("1. Dennis Ritchie\n2. James Gosling\n3. Steve Jobs\n4. Bill Gates");
                break;

            case 3:
                System.out.println("\nQ3. Which one is a programming language?");
                System.out.println("1. HTML\n2. CSS\n3. Java\n4. Photoshop");
                break;

            case 4:
                System.out.println("\nQ4. Which company developed Java?");
                System.out.println("1. Microsoft\n2. Apple\n3. Sun Microsystems\n4. IBM");
                break;

            case 5:
                System.out.println("\nQ5. What does OOP stand for?");
                System.out.println("1. Object Oriented Programming\n2. Online Object Programming\n3. Only Object Programming\n4. None");
                break;

            default:
                return;
        }

        start = System.currentTimeMillis();
        if (sc.hasNextInt()) {
            answer = sc.nextInt();
        } else {
            answer = -1;
            sc.next(); 
        }
        end = System.currentTimeMillis();

        if ((end - start) / 1000 > 30) {
            System.out.println("You took too long to answer this question!");
        } else {
            boolean correct = switch (qNo) {
                case 1 -> answer == 1;
                case 2 -> answer == 2;
                case 3 -> answer == 3;
                case 4 -> answer == 3;
                case 5 -> answer == 1;
                default -> false;
            };
            if (correct) {
                score++;
                System.out.println(" Correct!");
            } else {
                System.out.println(" Incorrect!");
            }
        }
    }
}
