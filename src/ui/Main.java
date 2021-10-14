/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dto.Bank;
import dto.BankList;
import dto.User;
import dto.UserList;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author Phien
 */
public class Main {
    public static void main(String[] args)throws IOException {
        String bank = "bank.dat";
        String user = "user.dat";
        UserList u = new UserList();
        BankList b = new BankList();
        List<User> userList = new Vector<>();
        List<Bank> bankList = new Vector<>();
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {
            u.loadUserFromFileU(userList,user);
            b.loadBankFromFileB(bankList,bank);
            System.out.println("------------------------------------------");
            System.out.println("\nWelcome to The Manager Bank System.");
            System.out.println("1.Create new account.");
            System.out.println("2.Login account");
            System.out.println("3.Exit");
            System.out.println("------------------------------------------");
            do {
                System.out.print("Please enter your choice : ");
                try {
                    choice = Integer.parseInt(sc.nextLine());
                } catch (Exception e) {
                     System.out.println("Wrong format.\nPlease try again!!");
                }
            } while (choice < 1 || choice > 4);
            switch (choice) {
                case 1: {
                    u.createAccount(userList, bankList);
                    u.saveToFileU(userList,user);
                    b.saveToFileB(bankList,bank);
                    break;
                }
                case 2: {
                    u.login(bankList, userList);
                    u.saveToFileU(userList,user);
                    b.saveToFileB(bankList,bank);
                    break;
                }
            }
        } while (choice > 0 && choice < 3);
    }
}
