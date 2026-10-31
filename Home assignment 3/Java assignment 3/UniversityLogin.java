import java.util.Scanner;

class UniversityLogin {

    public void login(String username) {

        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException("Username cannot be null.");
        }
 System.out.println(" Login Successful");
        System.out.println(" Welcome, " + username);

    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UniversityLogin universityLogin = new UniversityLogin();

        System.out.print("Enter Username: ");

        String username = scanner.nextLine();
        if (username.equalsIgnoreCase("null")) {
            username = null;
        }

        try {

            universityLogin.login(username);

        } catch (NullPointerException exception) {

            System.out.println("\nException: " + exception.getMessage());

        } finally {

            System.out.println("Program Executed Successfully.");

        }

        scanner.close();
    }
}