import java.util.HashMap;

public class BankingService {

    private HashMap<Integer, BankAccount> accounts = new HashMap<>();

    // Create a new account
    public boolean createAccount(int accountNumber, String name,
                                 double balance, String password,
                                 String accountType) {

        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account number already exists!");
            return false;
        }

        if (balance < 0) {
            System.out.println("Initial balance cannot be negative!");
            return false;
        }

        BankAccount account;

        if (accountType.equalsIgnoreCase("savings")) {
            account = new SavingsAccount(
                    accountNumber, name, balance, password
            );
        } else if (accountType.equalsIgnoreCase("current")) {
            account = new CurrentAccount(
                    accountNumber, name, balance, password
            );
        } else {
            System.out.println("Invalid account type!");
            return false;
        }

        accounts.put(accountNumber, account);

        System.out.println("Account created successfully!");
        return true;
    }

    // Login
    public BankAccount login(int accountNumber, String password) {

        BankAccount account = accounts.get(accountNumber);

        if (account != null && account.authenticate(password)) {
            System.out.println("Login successful!");
            return account;
        }

        System.out.println("Invalid account number or password!");
        return null;
    }

    // Deposit money
    public void deposit(BankAccount account, double amount) {
        account.deposit(amount);
    }

    // Withdraw money
    public void withdraw(BankAccount account, double amount)
            throws InsufficientFundsException {

        if (amount <= 0) {
            throw new InsufficientFundsException(
                    "Invalid withdrawal amount!"
            );
        }

        if (!account.withdraw(amount)) {
            throw new InsufficientFundsException(
                    "Insufficient funds or overdraft limit exceeded!"
            );
        }
    }

    // Transfer money
    public void transfer(BankAccount sender, int receiverNumber,
                         double amount)
            throws InsufficientFundsException {

        BankAccount receiver = accounts.get(receiverNumber);

        if (receiver == null) {
            System.out.println("Receiver account not found!");
            return;
        }

        if (sender.getAccountNumber() == receiverNumber) {
            System.out.println("Cannot transfer to the same account!");
            return;
        }

        if (amount <= 0) {
            throw new InsufficientFundsException(
                    "Invalid transfer amount!"
            );
        }

        if (!sender.withdraw(amount)) {
            throw new InsufficientFundsException(
                    "Insufficient funds for transfer!"
            );
        }

        receiver.deposit(amount);

        System.out.println("Transfer successful!");
    }

    // Check balance
    public void checkBalance(BankAccount account) {
        System.out.println("Available Balance: Rs."
                + account.getBalance());
    }

    // Display account details
    public void displayAccount(BankAccount account) {
        account.displayDetails();
    }
}
