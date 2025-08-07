import java.util.Scanner;

public class Calculator {

    // Addition method
    public static double add(double a, double b) {
        return a + b;
    }

    // Subtraction method
    public static double subtract(double a, double b) {
        return a - b;
    }

    // Multiplication method
    public static double multiply(double a, double b) {
        return a * b;
    }

    // Division method with handling exceptions
    public static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Cannot divide by zero.");
            return Double.NaN;
        }
        return a / b;
    }

    public static void main(String[] args) {
         try(Scanner scanner = new Scanner(System.in)){
        boolean continueCalc = true;

        System.out.println("Welcome to Java Console Calculator!");

        while (continueCalc) {
            System.out.print("Enter The first number: ");
            double num1 = scanner.nextDouble();

            System.out.print("Enter The second number: ");
            double num2 = scanner.nextDouble();

            System.out.print("Choose Rigth operation (+, -, *, /): ");
            char oper = scanner.next().charAt(0);

            double result = 0;
            switch (oper) {
                case '+':
                    result = add(num1, num2);
                    break;
                case '-':
                    result = subtract(num1, num2);
                    break;
                case '*':
                    result = multiply(num1, num2);
                    break;
                case '/':
                    result = divide(num1, num2);
                    break;
                default:
                    System.out.println("Invalid operation.");
                    continue;
            }
        

            System.out.println("Result: " + result);

            System.out.print("you want to perform another calculation? say (yes/no): ");
            String choice = scanner.next();
            if (!choice.equals("yes")) {
                continueCalc = false;
            }
        }
    }
    catch(Exception e){
        System.out.println("Error: " + e.getMessage());
    }

        System.out.println("use calculator! Thankyou");
       

    }
}

