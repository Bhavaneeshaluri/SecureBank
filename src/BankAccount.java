public class BankAccount {

    private int accountNumber;
    private String accountHolder;
    protected double balance;

    private String passwordHash;
    private byte[] salt;

    public BankAccount(int accountNumber, String accountHolder,
                       double balance, String password) {

        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;

        this.salt = PasswordManager.generateSalt();

        this.passwordHash =
                PasswordManager.hashPassword(password, salt);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public boolean authenticate(String password) {
        return PasswordManager.verifyPassword(
                password, salt, passwordHash
        );
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid deposit amount!");
            return;
        }

        balance += amount;

        System.out.println("Deposit successful!");
    }

    public void displayDetails() {

        System.out.println("\n----- ACCOUNT DETAILS -----");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: Rs." + balance);
    }

    public boolean withdraw(double amount) {

        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }
}
