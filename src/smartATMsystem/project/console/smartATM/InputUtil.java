package smartATMsystem.project.console.smartATM;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtil {

    private final Scanner input = new Scanner(System.in);

    public int readInt(String message) {

        while (true) {

            try {
                System.out.print(message);
                int value = input.nextInt();
                input.nextLine();

                return value;

            } catch (InputMismatchException e) {

                System.err.println("Only numbers allowed.");
                input.nextLine();
            }
        }
    }

    public double readDouble(String message) {

        while (true) {

            try {
                System.out.print(message);
                double value = input.nextDouble();
                input.nextLine();

                return value;

            } catch (InputMismatchException e) {

                System.err.println("Only numbers allowed.");
                input.nextLine();
            }
        }
    }

    public String readString(String message) {

        System.out.print(message);
        return input.nextLine();
    }
}
