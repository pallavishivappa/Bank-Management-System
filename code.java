package com.bank;

import java.util.Random;
import java.util.Scanner;


interface BankOperations {
    void deposit(int amount);
    boolean withdrawal(int amount);
    boolean transfer(Credentials receiver, int amount, int enteredOtp, int generatedOtp);
    void displayBalance();
}


class Credentials implements BankOperations {
    private String bankAccountNo = "";
    private int password;
    private String inputBankAccountNo;
    private int inputPassword;
    private int balance = 10000;
    static Random ran = new Random();

   
    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    void automatedGenerated() {
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";

        bankAccountNo = ""; 

        for (int i = 0; i < 4; i++) {
            int index = ran.nextInt(alphabets.length());
            bankAccountNo += alphabets.charAt(index);
        }

        for (int i = 0; i < 6; i++) {
            int index = ran.nextInt(digits.length());
            bankAccountNo += digits.charAt(index);
        }

        System.out.println("The credentials to login are");
        System.out.println("The account no generated is " + bankAccountNo);
    }

  
    void pin() {
        password = 100000 + ran.nextInt(900000);
        System.out.println("The password generated is " + password);
    }

   
    void setData() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter your credentials to login:");
        System.out.print("Bank Account Number: ");
        inputBankAccountNo = s.next();
        System.out.print("Password: ");
        inputPassword = s.nextInt();
    }

    boolean checkCredentials() {
        return inputBankAccountNo.equals(bankAccountNo) && inputPassword == password;
    }

    

    public void deposit(int amount) {
        balance += amount;
        System.out.println("Current balance: " + balance);
    }

    public boolean withdrawal(int amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
            return false;
        } else {
            balance -= amount;
            System.out.println("Remaining balance: " + balance);
            return true;
        }
    }

   
    public boolean transfer(Credentials receiver, int amount, int inOtp, int otp) {
        if (inOtp == otp) {
            System.out.println("OTP is verified, the transaction can be done");
            if (withdrawal(amount)) {
                receiver.deposit(amount);
                System.out.println("Transfer Successful");
                return true;
            }
        } else {
            System.out.println("OTP is not verified, Transaction unsuccessful");
        }
        return false;
    }

  
    public void displayBalance() {
        System.out.println("Final Balance: " + balance);
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Credentials user = new Credentials();
        Credentials receiver = new Credentials();

        user.automatedGenerated();
        user.pin();

        user.setData();

        if (user.checkCredentials()) {
            System.out.println("Login Successful");

            int choice;
            do {
                System.out.println("\n--- Banking Menu ---");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Display Balance");
                System.out.println("4. Transfer");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Enter the amount to deposit");
                        int dep = sc.nextInt();
                        user.deposit(dep);
                        break;

                    case 2:
                        System.out.println("Enter the amount to withdraw");
                        int w = sc.nextInt();
                        user.withdrawal(w);
                        break;

                    case 3:
                        user.displayBalance();
                        break;

                    case 4:
                        System.out.println("Enter the amount to transfer");
                        int t = sc.nextInt();

                        Random rand = new Random();
                        int otp = 1000 + rand.nextInt(9000);
                        System.out.println("OTP generated for transfer: " + otp);

                        System.out.print("Enter the OTP: ");
                        int inotp = sc.nextInt();

                        user.transfer(receiver, t, inotp, otp);
                        break;

                    case 5:
                        System.out.println("Exit Goodbye!");
                        break;

                    default:	
                        System.out.println("Invalid choice");
                }
            } while (choice != 5);

        } else {
            System.out.println("Invalid Credentials");
        }
    }}



