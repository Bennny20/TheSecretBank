/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import validation.MyToys;

/**
 *
 * @author Phien
 */
public class BankList{
    Scanner sc = new Scanner(System.in);
    ArrayList<Bank> bankList;
    
    public void loadBankFromFileB(List<Bank> bankList,String fName){
        if (bankList.size() > 0) {bankList.clear();
        }
        try {
            File f = new File(fName);
            if(!f.exists()) return;
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Bank b;
            while(true){
                try {
                    b = (Bank) fo.readObject();
                    bankList.add(b);
                } catch (Exception e) {
                    break;
                }
            } 
            fo.close(); fi.close();
        } catch (Exception e) {
            System.err.println("File bank is Empty!");
        }
    }
    
    public void saveToFileB(List<Bank> bankList, String fName) throws FileNotFoundException, IOException{
        
        try {
            FileOutputStream f = new FileOutputStream(fName);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            for (Bank b : bankList)  fo.writeObject(b);
            fo.close(); f.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public int find( List<Bank> bankList,String words) {
        for (int i = 0; i < bankList.size(); i++) {
            if (words.equalsIgnoreCase(bankList.get(i).getId())) {
                return 1;
            }
        }
        return -1;
    }
    
    public void withdraw(List<Bank> bankList, String id) {
        double money = 0;
        double newMoney = 0;
        money = MyToys.getADouble("The money you want to withdraw is : ", "Please try again !");
        boolean check = false;
        if (find(bankList,id) > 0) {
            for (int i = 0; i < bankList.size(); i++) {
                if (bankList.get(i).getId().equalsIgnoreCase(id)) {
                    if (bankList.get(i).getBalance() != 0 && bankList.get(i).getBalance() >= money) {
                        check = true;
                        newMoney = bankList.get(i).getBalance() - money;
                        bankList.get(i).setBalance(newMoney);
                        System.out.println("You have successfully withdrawn!");
                    }
                }
            }
            if (check == false) {
                System.err.println("Not have enough money!!");
            }
        } else {
            System.err.println("ID is not exist.");
        }
    }
    
    public void deposit( List<Bank> bankList,String id) {
        String cf;
        double money = 0;
        double newMoney = 0;
        money = MyToys.getADouble("The money you want to deposit is : ", "Please try again !");
        System.out.println("Are you sure you want to deposit this money ?");
        System.out.println("Select yes or no : ");
        cf = sc.nextLine();
        boolean check = false;
        if(cf.equals("yes")){
            for (int i = 0; i < bankList.size(); i++) {
                if (id.equalsIgnoreCase(bankList.get(i).getId())) {
                check = true;
                newMoney = bankList.get(i).getBalance() + money;
                bankList.get(i).setBalance(newMoney);
                System.out.println("Your surplus:" + bankList.get(i).getBalance());
                System.out.println("You have successfully deposit !");
                }
            }
        }else{
            if (check == false) {
            System.out.println("Deposit is not successfull");
            }
        }  
    }
    
    public void tranfer(List<Bank> bankList,String sendId) {
        String receivedId;
        double money=0;
        double rmoney;
        receivedId = MyToys.getTranferID();
        System.out.println(receivedId);
        do {
            try {
                System.out.print("How much money do you want to stranfer :");
                money = Double.parseDouble(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Money must be a number.");
            }
        } while (money < 0);
        boolean check = false;
        boolean check2 = false;
        for (int i = 0; i < bankList.size(); i++) {
            if (sendId.equalsIgnoreCase(bankList.get(i).getId())) {
                double m = bankList.get(i).getBalance();
                if (m >= money) {
                    check2 = true;
                    bankList.get(i).setBalance(m - money);
                }
            }
        }
        if (check2 == false) {
            System.out.println("The balance is not enough!");
            return;
        }
        for (int i = 0; i < bankList.size(); i++) {
            if (receivedId.equalsIgnoreCase(bankList.get(i).getId())) {
                check = true;
                rmoney = bankList.get(i).getBalance() + money;
                bankList.get(i).setBalance(rmoney);
                System.out.println("Congratulations on successful money transfer!");
            }
        }

        if (check == false) {
            System.out.println("This ID is incorrect or not exist.");
        }
    }
}
