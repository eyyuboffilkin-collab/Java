package Bank;

public class Main{
    public static void main(String[] args) {
    BankAccount account = new BankAccount();

    account.setOwner("Ilkin");

    account.deposit(1000);
    account.withdraw(200);
    account.withdraw(200);

        System.out.println("Sahib: " + account.getOwner());
        System.out.println("Balans: " + account.getBalance());

    }
}