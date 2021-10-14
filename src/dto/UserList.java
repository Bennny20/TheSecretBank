/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.File;
import java.io.FileInputStream;
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
public class UserList {
    Scanner sc = new Scanner(System.in);
    ArrayList<User> userList;
    
    public void loadUserFromFileU(List<User> userList,String fName){
        if(userList.size() > 0)userList.clear();
        try {
            File f = new File(fName);
            if(!f.exists()) return;
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            User u;
            while(true){
                try {
                    u = (User) fo.readObject();
                    userList.add(u);
                } catch (Exception e) {
                    break;
                }
            } 
            fo.close(); fi.close();
        } catch (Exception e) {
            System.out.println("File User is Empty!");
        }
    }
    
     public void saveToFileU(List<User> userList,String fName){
        try {
            FileOutputStream f = new FileOutputStream(fName);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            for (User u : userList)  fo.writeObject(u);
            fo.close(); f.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
     
      public int find( List<User> userList,String words) {
        for (int i = 0; i < userList.size(); i++) {
            if (words.equalsIgnoreCase(userList.get(i).getId())) {
                return 1;
            }
        }
        return -1;
    }
      public int searchByID(String aniID) {
        int pos;
        if (userList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equalsIgnoreCase(aniID)) {
                return i;
            }
        }

        return -1;
    }
    
      public int checkName(List<User> userList,String account) {
        for (int i = 0; i < userList.size(); i++) {
            if (account.equalsIgnoreCase(userList.get(i).getName())) {
                return 1;
            }
        }
        return -1;
    }
      
      public int checkPw(List<User> userList,String password) {
        for (int i = 0; i < userList.size(); i++) {
            if (password.equalsIgnoreCase(userList.get(i).getPassword())) {
                return 1;
            }
        }
        return -1;
    }
    public void createAccount(List<User> userList, List<Bank> bankList) {
        String id;
        String password;
        String cpassword;
        String name;
        
        do {
            id = MyToys.getID();
            if(find(userList, id) > 0){
                System.out.println("This ID already exist!");
            }
        } while (find(userList,id)>0);
        do{
            name =MyToys.checkInputString();
            if(checkName(userList, name) > 0){
                System.out.println("This name already exist!");
            }
        }while(checkName(userList, name) > 0);
        password = MyToys.getPassword();
        do {
            System.out.print("Confirm password:");
            cpassword=sc.nextLine();
            if (cpassword.equalsIgnoreCase(password)) {
                break;
            } else {
                System.out.println("Confirm password and password not the same.");
            }
        } while (cpassword != password);
        bankList.add(new Bank(id, 0));
        userList.add(new User(id, name, password, cpassword));
        System.out.println("Create new account successfully."); 
    }
    
    public void login(List<Bank> bankList, List<User> userList) throws IOException {
        BankList b = new BankList();
        String id = "", password, name;
        int choice = 0;
        
        System.out.print("Enter your Name:");
        name =sc.nextLine();
        System.out.print("Enter your Password: ");
        password=sc.nextLine();
        if (checkPw(userList,password)>0 && checkName(userList,name)>0) {
            do {
                for (int i = 0; i < userList.size(); i++) {
                    if (name.equalsIgnoreCase(userList.get(i).getName())) {
                        id = userList.get(i).getId();
                        System.out.println("Your ID:" + id);
                    }
                }
                for (int j = 0; j < bankList.size(); j++) {
                    if (id.equalsIgnoreCase(bankList.get(j).getId())) {
                        System.out.println("Your surplus:" + bankList.get(j).getBalance());
                        break;
                    }
                }              
                System.out.println("1.Withdrawal.");
                System.out.println("2.Deposit.");
                System.out.println("3.Transfer.");
                System.out.println("4.Remove account.");
                System.out.println("5.Quit");
                System.out.println("------------------------------");            
                System.out.print("Enter your choice : ");
                try {
                    choice = Integer.parseInt(sc.nextLine());
                } catch (Exception e) {                
                }
                switch (choice) {
                    case 1: {
                        b.withdraw( bankList,id);
                        b.saveToFileB(bankList,"bank.dat");
                        break;
                    }
                    case 2: {
                        b.deposit(bankList,id);
                        b.saveToFileB(bankList,"bank.dat");
                        break;
                    }
                    case 3:
                        b.tranfer(bankList,id);
                        b.saveToFileB(bankList,"bank.dat");
                        break;
                    case 4: {
                        String dec = "";
                        System.out.print("Do you want to remove your account.\nYes or No:");
                        dec = sc.nextLine().toUpperCase();
                        if (dec.equalsIgnoreCase("y".toUpperCase()) || dec.equalsIgnoreCase("yes".toUpperCase())) {
                            for (int j = 0; j < bankList.size(); j++) {
                                if (id.equalsIgnoreCase(bankList.get(j).getId())) {
                                    bankList.remove(bankList.get(j));
                                }
                            }
                            for (int i = 0; i < userList.size(); i++) {
                                if (id.equalsIgnoreCase(userList.get(i).getId()));
                                userList.remove(userList.get(i));
                            }
                            System.out.println("This ID has been removed");
                            saveToFileU(userList, "user.dat");
                            b.saveToFileB(bankList, "bank.dat");
                            break;
                        } else {
                            System.out.println("Can not remove.");
                        }
                    }
                }
            } while (choice > 0 && choice < 4);
        } else {
            System.out.println("This ID is not exist.");
        }

    }
}
