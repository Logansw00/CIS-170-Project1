import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int operation = 0;

        while (operation != 6) {

            System.out.println("Pick what operation you would like to do (1-6)");
            System.out.println("Addition");
            System.out.println("Subtraction");
            System.out.println("Multiplication");
            System.out.println("Division");
            System.out.println("Modulo");
            System.out.println("Quit");
            System.out.print("Enter your choice: ");

            operation = scan.nextInt();

            if (operation == 6) {
                System.out.println("Closing calculator");
                break;
            }

            if (operation >= 1 && operation <= 5) {
                System.out.print("Enter the first number: ");
                double number1 = scan.nextDouble();

                System.out.print("Enter the second number: ");
                double number2 = scan.nextDouble();

                switch (operation) {
                    case 1:
                        System.out.println("Answer: " + (number1 + number2));
                        break;
                    case 2:
                        System.out.println("Answer: " + (number1 - number2));
                        break;
                    case 3:
                        System.out.println("Answer: " + (number1 * number2));
                        break;
                    case 4:
                        if (number2 == 0) {
                            System.out.println("Error: Cannot divide by zero.");
                        } else {
                            System.out.println("Answer: " + (number1 / number2));
                        }
                        break;
                    case 5:
                        if (number2 == 0) {
                            System.out.println("Error: Cannot modulo by zero.");
                        } else {
                            System.out.println("Answer: " + (number1 % number2));
                        }
                        break;
                }
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }


    }
}
