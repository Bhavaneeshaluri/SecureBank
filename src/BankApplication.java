
import java.util.Scanner;

public class BankApplication {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        BankingService service = new BankingService();

        while (true) {

            System.out.println("\n===== SECURE BANK =====");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = readInt("Enter choice: ");

            switch (choice) {

                case 1:
                    createAccount(service);
                    break;

                case 2:
                    login(service);
                    break;

                case 3:
                    System.out.println("Thank you for using SecureBank!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Create account
    static void createAccount(BankingService service) {

        System.out.println("\n--- CREATE ACCOUNT ---");

        int accountNumber = readInt("Enter account number: ");

        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        double balance = readDouble("Enter initial balance: ");

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.print("Enter account type (Savings/Current): ");
        String type = sc.nextLine();

        service.createAccount(
                accountNumber, name, balance, password, type
        );
    }

    // Login
    static void login(BankingService service) {

        System.out.println("\n--- LOGIN ---");

        int accountNumber = readInt("Enter account number: ");

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        BankAccount account = service.login(
                accountNumber, password
        );

        if (account != null) {
            accountMenu(service, account);
        }
    }

    // Account menu
    static void accountMenu(BankingService service,
                            BankAccount account) {

        while (true) {

            System.out.println("\n===== ACCOUNT MENU =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Account Details");
            System.out.println("6. Logout");

            int choice = readInt("Enter choice: ");

            switch (choice) {

                case 1:
                    double depositAmount =
                            readDouble("Enter deposit amount: ");

                    service.deposit(account, depositAmount);
                    break;

                case 2:
                    double withdrawAmount =
                            readDouble("Enter withdrawal amount: ");

                    try {
                        service.withdraw(account, withdrawAmount);
                    } catch (InsufficientFundsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    int receiverNumber =
                            readInt("Enter receiver account number: ");

                    double transferAmount =
                            readDouble("Enter transfer amount: ");

                    try {
                        service.transfer(
                                account, receiverNumber, transferAmount
                        );
                    } catch (InsufficientFundsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    service.checkBalance(account);
                    break;

                case 5:
                    service.displayAccount(account);
                    break;

                case 6:
                    System.out.println("Logged out successfully!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Read integer safely
    static int readInt(String message) {

        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    // Read decimal safely
    static double readDouble(String message) {

        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount!");
            }
        }
    }
}
