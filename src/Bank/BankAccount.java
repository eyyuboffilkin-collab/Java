package Bank;

public class BankAccount{
    private String owner;
    private double balance;

    public String getOwner(){
        return owner;
    }
    public void setOwner(String owner){
        this.owner = owner;
    }
    public double getBalance(){
        return balance;
    }
    public void deposit(double amount){
        if(amount > 0){
            balance += amount;
            System.out.println(amount + " AZN elave olundu");
        }else{
            System.out.println("Mebleg duzgun deyil");
        }
    }
    public void withdraw(double amount){
        if(amount > 0 && amount <= balance){
            balance -= amount;
            System.out.println(amount + " AZN cixarildi");
        }else{
            System.out.println("Balans kifayet deyil");
        }
    }

}
