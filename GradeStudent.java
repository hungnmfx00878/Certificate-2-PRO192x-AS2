import java.util.Scanner;

public class GradeStudent {
    static int totalWeight = 0; //Tracking & validate sum of midterm's weight, finalterm's weight & homework's weight equal to 100 or not.

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        begin();
        System.out.println();

        double weightMidtermScore = midterm(scanner); //Store midterm's weightScore to calculate overall grade.
        System.out.println();

        double weightFinalExamScore = finalterm(scanner); //Store finalterm's weightScore to calculate overall grade.
        System.out.println();

        double weightHomeworkScore = homework(scanner); //Store homework's weightScore to calculate overall grade.
        System.out.println();

        report(weightMidtermScore + weightFinalExamScore + weightHomeworkScore);
    }

    private static void begin() {
        System.out.println("This program reads exam/homework scores and reports your overall course grade.");
    }

    //Midterm's weight, score & calculate midterm's weightScore:
    private static double midterm(Scanner scanner) {
        System.out.println("Midterm:");
        return getInfor(scanner);
    }

    //Finalterm's weight, score & calculate finalterm's weightScore:
    private static double finalterm(Scanner scanner) {
        System.out.println("Final:");
        return getInfor(scanner);
    }

    //Homework's weight, score & calculate homework's weightScore:
    private static double homework(Scanner scanner) {
        System.out.println("Homework:");

        //Weight:
        int weight;
        do {
            weight = getWeight(scanner);
        } while (validWeight(weight)); //Validate totalWeight, it has to be equal to 100 to continue process.

        //Quantity of Assignment:
        System.out.print("Number of assignments? ");
        int numAssignment = scanner.nextInt();

        //Total Point:
        int totalPoint = 0;
        int maxPoint = 0;
        for (int i = 1; i <= numAssignment; i++) {
            System.out.print("Assignment " + i + " score and max? ");
            totalPoint += scanner.nextInt();
            maxPoint += scanner.nextInt();
        } //Fill information for all Assignments.

        //Section Point:
        System.out.print("How many sections did you attend? ");
        int section = scanner.nextInt();
        int sectionPoint = section * 5;
        if (sectionPoint > 30) {
            sectionPoint = 30;
        } //Maximum limit of section point is 30.
        System.out.println("Section points = " + sectionPoint + " / 30");

        //Total Point vs Max Point:
        totalPoint = totalPoint > 150? 150 + sectionPoint : totalPoint + sectionPoint; //Maximum limit of totalPoint is 150;
        maxPoint = maxPoint > 150? 150 + 30 : maxPoint + 30; //Maximum limit of maxPoint is 180;
        System.out.println("Total points = " + totalPoint + " / " + maxPoint);

        //Weight Score:
        double weightScore = (double)totalPoint / maxPoint * weight;
        System.out.println("Weighted score = " + (double)Math.round(weightScore * 10) / 10 + " / " + weight); //Weight scores have to be rounded to 1st decimal.
        return weightScore; //return weightScore to calculate grade in report().
    }

    //Report Grade & GPA (with comment):
    private static void report(double grade) {
        System.out.println("Overall percentage = " + (double)Math.round(grade * 10) / 10); //Grades have to be rounded to 1st decimal.
        if (grade >= 85) {
            System.out.println("Your grade will be at least: 3.0");
            System.out.println("Good Job!");
        } else if (grade >= 75 && grade < 85) {
            System.out.println("Your grade will be at least: 2.0");
            System.out.println("Not Bad!");
        } else if (grade >= 60 && grade < 75) {
            System.out.println("Your grade will be at least: 0.7");
            System.out.println("Bad!");
        } else {
            System.out.println("Your grade will be at least: 0.0");
            System.out.println("Too Bad!");
        }
    }

        //Update & calculate method:
        private static double getInfor(Scanner scanner) {

            //Weight:
            int weight = getWeight(scanner);
            totalWeight += weight; //Tracking weight to validate total equal to 100 later

            //Score:
            System.out.print("Score earned? ");
            int score = scanner.nextInt(); //Update score earned.

            //Shifted Score:
            System.out.print("Were scores shifted (1=yes, 2=no)? ");
            int shiftAmount = 0;
            if (scanner.nextInt() == 1) {
                System.out.print("Shift amount? ");
                shiftAmount = scanner.nextInt();
            } //Only update shift amount when scores were shifted.

            //Total Point:
            int totalPoint = score + shiftAmount;
            if (totalPoint > 100) {
                totalPoint = 100;
            } //Maximum Limit of total point is 100.
            System.out.println("Total points = " + totalPoint + " / 100");

            //Weight Score:
            double weightScore = (double)totalPoint / 100 * weight;
            System.out.println("Weighted score = " + (double)Math.round(weightScore * 10) / 10 + " / " + weight); //Weight scores have to be rounded to 1st decimal.
            return weightScore; //return weightScore to calculate grade in report().
        }

        //Update weight method:
        private static int getWeight(Scanner scanner) {
            System.out.print("Weight (0-100)? ");
            int weight = scanner.nextInt();
            return weight < 0 || weight > 100 ? getWeight(scanner) : weight; //Weight have to be in range (0-100).
        }

        //Validate totalWeight:
        private static boolean validWeight(int weight) {
            int testTotalWeight = totalWeight + weight;
            if (testTotalWeight != 100) {
                System.out.println("Total weight must be equal to 100");
            }
            return testTotalWeight != 100;
        }
}