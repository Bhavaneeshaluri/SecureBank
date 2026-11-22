
public class CurrentAccount extends BankAccount {

    private static final double OVERDRAFT_LIMIT = 5000.0;

    public CurrentAccount(int accountNumber, String accountHolder,
                          double balance, String password) {

        super(accountNumber, accountHolder, balance, password);
    }

    @Override
    public boolean withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount!");
            return false;
        }

        if (amount > getBalance() + OVERDRAFT_LIMIT) {
            System.out.println("Overdraft limit exceeded!");
            return false;
        }

        balance -= amount;

        System.out.println("Withdrawal successful!");
        return true;
    }

    @Override
    public void displayDetails() {

        super.displayDetails();

        System.out.println("Account Type: Current");
        System.out.println("Overdraft Limit: Rs." + OVERDRAFT_LIMIT);
    }
}
