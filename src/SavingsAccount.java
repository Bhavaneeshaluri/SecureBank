public class SavingsAccount extends BankAccount {

    private static final double INTEREST_RATE = 0.04;

    public SavingsAccount(int accountNumber, String accountHolder,
                          double balance, String password) {

        super(accountNumber, accountHolder, balance, password);
    }

    public double calculateInterest() {
        return getBalance() * INTEREST_RATE;
    }

    @Override
    public boolean withdraw(double amount) {

        if (amount <= 0 || amount > getBalance()) {
            System.out.println("Insufficient balance!");
            return false;
        }

        balance -= amount;

        System.out.println("Withdrawal successful!");
        return true;
    }

    @Override
    public void displayDetails() {

        super.displayDetails();

        System.out.println("Account Type: Savings");
        System.out.println("Interest Rate: 4%");
        System.out.println("Interest Amount: Rs." + calculateInterest());
    }
}
