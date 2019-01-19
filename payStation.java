//Matthew Anthony; Code for a pay station with 3 different clients

import java.util.Scanner;
import java.util.Date;

public class payStation {
    static Scanner scan = new Scanner(System.in); //all methods can access
    static Date date = new Date();

    public static void payStation(int choice) { //pay station will return a receipt with total payment and total time to park
        int skip = 0; //used for bypassing while loop for Gammatown if Saturday or Sunday
        int totalAmount = 0; //total amount spent from single person
        int amountEntered = 0; //individual coin spent

        if (choice == 3 && (date.getDay() == 6 || date.getDay() == 0)) { //if it's Saturday or Sunday. Free Parking
            System.out.println("FREE PARKING ON SATURDAYS AND SUNDAYS! ENJOY YOUR DAY!");
            skip = 1;
            System.exit(0);
        }

        while (amountEntered != 1 && skip !=1) {
            System.out.println("Enter coin: ");
            amountEntered = scan.nextInt();

            if (amountEntered == 1) { //not sure why the loop doesn't quit on its own when 1 is entered
                break;
            }
            if (amountEntered == 25 || amountEntered == 10 || amountEntered == 5) {
                totalAmount += amountEntered;
            } else {
                System.out.println("We do not accept coins other than nickels, dimes, and quarters.");
                if (totalAmount > 0) {
                    System.out.println("You will be returned " + totalAmount + " cents");
                }
                break;
            }
        }

        switch (choice) { //switch statement for what town is going to be used
            case 1:
                System.out.println("-----------------------------------------------------------------------------");
                System.out.println("Thank you for using Alphatown pay station. \nMoney spent: " + totalAmount +
                        "\nTime allotted: " + alphatownMinutes(totalAmount) + "\nCurrent Time: " + date.toString());
                System.out.println("-----------------------------------------------------------------------------\n");
                break;
            case 2:
                System.out.println("-----------------------------------------------------------------------------");
                System.out.println("Thank you for using Betatown pay station. \nMoney spent: " + totalAmount +
                        "\nTime allotted: " + betatownMinutes(totalAmount) + "\nCurrent Time: " + date.toString());
                System.out.println("-----------------------------------------------------------------------------\n");
                break;
            case 3:
                System.out.println("-----------------------------------------------------------------------------");
                System.out.println("Thank you for using Gammatown pay station. \nMoney spent: " + totalAmount +
                        "\nTime allotted: " + gammatownMinutes(totalAmount) + "\nCurrent Time: " + date.toString());
                System.out.println("-----------------------------------------------------------------------------\n");
                break;
        }
    }

    //5cents is 2 minutes
    public static int alphatownMinutes(int amountEntered) {
        int timeGiven;
        timeGiven = (amountEntered / 5) * 2;
        return timeGiven;
    }

    //5cents is 2 minutes. After one hour 5 cents is 1 minute
    public static int betatownMinutes(int amountEntered) {
        int timeGiven;

        if (amountEntered > 150){
            int overflow = amountEntered - 150;
            timeGiven = 150 + (overflow / 5);
        }
        else {
            timeGiven = (amountEntered / 5) * 2;
        }
        return timeGiven;
    }

    //5cents is 2 minutes. Saturday and Sunday are free.
    public static int gammatownMinutes(int amountEntered) {

        int timeGiven;
        if (date.getDay() == 6 || date.getDay() == 0) {
            timeGiven = 100000000;
        }
        else {
            timeGiven = (amountEntered / 5) * 2;
        }
        return timeGiven;
    }

    public static void welcome() { //welcome message
        System.out.println("Welcome to the pay station. The station accepts quarters, dimes, and nickels only. Enter 1 " +
                "to quit.");
    }

    public static int townChoice() { //returns town choice
        int choice;
        System.out.println("Enter 1 for Alphatown, 2 for Betatown, 3 for Gammatown.");
        choice = scan.nextInt();
        return choice;
    }

    public static void main(String[] args) {

        int choice = townChoice();
        while (true) {
            welcome();
            payStation(choice);
        }
    }
}


