import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

//        created Arraylist users to store all users informations
        ArrayList<User> users = new ArrayList<>();

//using while loop to enter the details of multiple users
        boolean isTrue = true;

        while (isTrue) {

            //     Input and store User Firstname
            System.out.println("Enter The Firstname");
            String firstname = scanner.nextLine();

            //     Input and store User Lastname
            System.out.println("Enter the Lastname");
            String lastname = scanner.nextLine();

            //     Input and store User Email address with exceptional handling
            String email;
            while (true) {
                System.out.println("Enter the Email");
                email = scanner.nextLine();
                try {
                    if (!email.contains("@") | !email.contains(".com")) {
                        throw new IllegalArgumentException("Please enter valid email id");
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            //    Input and exceptional handling for User age
            int age;
            while (true) {
                System.out.println("Enter the Age");
                try {
                    age = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a numberic value");
                    scanner.next();
                }
            }
            scanner.nextLine();

            //   Input User Password

            String password;

       while(true){
           System.out.println("Enter the Password.\n " +
                   "-It should Start with a letter (uppercase or lowercase).\n" +
                   "- Contain at least one digit.\n" +
                   "- Contain at least one special character (@, #, !, %).)\n" +
                   " - Be at least 8 characters long.");
           password = scanner.nextLine();
            try {
                String passwordPattern = "^[A-Za-z](?=.*\\d)(?=.*[@#!%]).{7,}$";

                Pattern pattern = Pattern.compile(passwordPattern);

                Matcher matcher = pattern.matcher(password);

                if (!matcher.matches()) {
                    throw new InputMismatchException("Password is invalid.Please Try again ");
                } break;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }

        }

//  Creating Instance for class User
            User user = new User(firstname, lastname, email, age, password);
            users.add(user);

// Providing condition to stop the loop
            System.out.println("Do you want to enter another user? (yes/no) ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                isTrue = false;
                System.out.println("Thank you for your Informations ");
                break;
            }
        }
//For blank line
        System.out.println();

// Sign in confirmation
        System.out.println("Welcome to the Signin Portal ");
        System.out.println();

//        Username input


//        IIterating through users arraylist
        while(true){

            System.out.println("Enter the Username: ");
            String userName = scanner.nextLine();

//        Password input
            System.out.println("Enter your Password: ");
            String userPassword = scanner.nextLine();

           boolean  isAuthenticated = false;

           try {
               for (User user : users) {
                   if (user.getEmail().equalsIgnoreCase(userName) && (user.getPassword().equals(userPassword))) {
                       System.out.println("Welcome " + user.getFirstName() + " " + user.getLastName() + "!!");
                       System.out.println("You are Signed in now");
                       isAuthenticated = true;
                       break;
                   }
               }
               if (!isAuthenticated) {
                   throw new AuthenticationException("Invalid Email or Password");
               }
               break;

           } catch (AuthenticationException e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again.");

            }
        }
            scanner.close();
        }
    }

