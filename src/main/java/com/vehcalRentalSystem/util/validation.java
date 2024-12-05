package com.vehcalRentalSystem.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validation {
    
    public boolean isValidUserName(String userName){

        String regex = "^[A-Za-z]\\w{5,29}$" ;
        Pattern p = Pattern.compile(regex);
        
        if (userName == null) {
            return false;
        }

        Matcher m = p.matcher(userName);
        return m.matches();
    }

    public void passRequirment(){
        System.out.println("\nPassword Requirement:\nIt contains at least 8 characters and at most 20 characters.\r\n" + 
                        "It contains at least one digit.\r\n" + 
                        "It contains at least one upper case alphabet.\r\n" + 
                        "It contains at least one lower case alphabet.\r\n" + 
                        "It contains at least one special character which includes !@#$%&*()-+=^.\r\n" + 
                        "It doesn't contain any white space.");
    }

    public int passwordStrength(String pass){
        int strength = 0;
        int length = pass.length();

        // String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";        
        // Pattern p = Pattern.compile(regex);
    
        if (length >= 8 && length <= 10 ) {
            strength += 1;
            //System.out.println("Strength: Weak");
        } else if (length >= 11 && length <= 15) {
            strength += 2;
            //System.out.println("Strength: Average");
        } else if (length >= 16){
            strength += 3;
            //System.out.println("Strength: Strong");
        }

        if (pass.matches("^(?=.*[0-9]).*")) {
            strength++;
        } 
        if (pass.matches("(?=.*[a-z]).*")) {
            strength++;
        }
        if (pass.matches("(?=.*[A-Z]).*")) {
            strength++;
        }
        if (pass.matches("(?=.*[@#$%^&+=]).*")) {
            strength++;
        }
        if (pass.matches("(?=.*\\\\S+$).{8,20}$.*")) {
            strength++;
        }

        return strength;
    }

    public boolean isValidContact(String contact){
        
        int length = contact.length();
        
        if (length == 11) {
            return true;
        }
        System.out.println("Enter valid Contact number");
        return false;
    }

    public boolean isValidNIC(String contact){
        int length = contact.length();
        
        if (length == 13) {
            return true;
        }
        System.out.println("Enter valid NIC");
        return false;
    }

    public boolean isValidEmail(String email){
        
        if (email.matches("(?=.*[@#$%^&+=]).*")) {
            return true;
        }
        System.out.println("Enter valid Email address");
        return false;
    }


}
