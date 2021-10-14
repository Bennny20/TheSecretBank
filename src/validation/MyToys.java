/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import java.util.Scanner;

/**
 *
 * @author Phien
 */
public class MyToys {
    private static Scanner sc = new Scanner(System.in);
    
    public static int getAnInteger(String inputMsg, String errorMsg){
        int n;
        while(true){
            try {
                System.out.println(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    public static int getAnInteger(int lowerBound, int upperBound, String inputMsg, String errorMsg) {
        int n, tmp;
        
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine()); 
                if (n < lowerBound || n > upperBound)
                    throw new Exception();                      
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }
    public static String getTranferID() {
        String result = "";
        boolean valid = true;
        System.err.println("The format of ID : SEXXX (X is digit)");
        do {
            System.out.print("Enter ID you want to tranfer: ");
            result = sc.nextLine().toUpperCase();
            valid = result.matches("^[SE]{2}+\\d{3}$");
            if (!valid) {
                System.err.println("The ID:SE + 3 digits");
            }
        } while (!valid);
        return result;
    }
    public static String getID() {
        String result = "";
        boolean valid = true;
        System.err.println("The format of ID : SEXXX (X is digit)");
        do {
            System.out.print("Enter new ID: ");
            result = sc.nextLine().toUpperCase();
            valid = result.matches("^[SE]{2}+\\d{3}$");
            if (!valid) {
                System.err.println("The ID:SE + 3 digits");
            }
        } while (!valid);
        return result;
    }
        
    public static String getStringByPattern(String inputMsg, String errorMsg, String format) {
        String stdString;
        boolean match;
        
        while (true) {
            System.out.print(inputMsg);
            stdString = sc.nextLine().trim().toUpperCase();
            match = stdString.matches(format);
            if (stdString.length() == 0 || stdString.isEmpty() || match == false)
                System.out.println(errorMsg);
            else
                return stdString;            
        }
    }
    

    public static String getString(String inputMsg, String errorMsg){
        String id;
        while(true){
            System.out.println(inputMsg);
            id = sc.nextLine().trim();
            if(id.length() == 0 || id.isEmpty())
                System.out.println(errorMsg);
            else
                return id;
        }
    }
    public static String getPassword() {
        String result = "";
        boolean valid = true;
        System.err.println("Password follow format :Have upper letters + lower letters + 1 special letter + least 6 character.");
        do {
            System.out.print("New customer's password: ");
            result = sc.nextLine();
            valid = result.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,22}$");
            if (!valid) {
                System.err.println("Password follow format :Have upper letters + lower letters + 1 special letter + least 6 character.");
            }
        } while (!valid);
        return result;
    }
    
    public static double getADouble(String inputMsg, String errMsg) {
        double n;
        while (true) {
            try {
                System.out.println(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.err.println(errMsg);
            }
        }
    }
    
    public static String checkInputString(){
           String words = "";
        boolean valid = true;
        System.err.println("The name like (phientruong)!");
        do {
            System.out.print("Enter new Name: ");
            words = sc.nextLine();
            valid = words.matches("[a-z0-9_-]{6,12}$");
            if (!valid) {
                System.err.println("Wrong format.");
            }
        } while (!valid);
        return words;
    }
    
}
