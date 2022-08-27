package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.dto.reponse.ResponseMessenger;
import rikkei.academy.dto.request.SignInDTO;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViewMainMenu {
    UserController userController = new UserController();
    List<User> userList = userController.getUserList();

    public void menu() {

        System.out.println("******MENU ĐĂNG KÝ ******");
        System.out.println("1.Show user list");
        System.out.println("2.Register");
        System.out.println("3.Login");
        int choice = -1;
        try {
            choice = Integer.parseInt(Config.scanner().nextLine());
        }catch (Exception e){
            menu();
        }
        switch (choice) {
            case 1:
                FromShowListUser();
                break;
            case 2:
                fromRegister();
                break;
            case 3:
                fromLogin();
                break;
            default:
                System.out.println("Invalid choice");
        }
        menu();

    }

    private void fromLogin() {
        ///check user name
        String userName;
        while (true) {
            System.out.println("Enter username");
            userName = Config.scanner().nextLine();
            if (userName.matches("[a-zA-Z]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid username try again");
            }
        }
        ////check passs
        String password;
        while (true) {
            System.out.println("Enter password");
            password = Config.scanner().nextLine();
            if (password.matches("[a-zA-Z\\d]{1,40}")) {
                break;
            } else {
                System.out.println("Invalid password try again");
            }
        }
        SignInDTO signInDTO = new SignInDTO(userName,password);
        ResponseMessenger responseMessenger = userController.login(signInDTO);
        switch (responseMessenger.getMessage()){
            case "login_success":
                System.out.println("login success");
                new ViewHome();
                break;
            case "login_failure":
                System.out.println("login failer");
                break;
        }

    }

    private void fromRegister() {
        System.out.println("*******REGISTER********");
        //cần id,name.user name,pass,role
        int id;
        //check id nếu đang rỗng thì id = 1 còn k thì + thêm 1 vào cuối
        if (userList.isEmpty()) {
            id = 1;
        } else {
            id = userList.get(userList.size() - 1).getId() + 1;
        }
        //name
        String name;
        while (true) {
            System.out.println("Enter name");
            name = Config.scanner().nextLine();
            if (name.matches("[a-zA-Z\\d]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid name try again");
            }
        }
        ////user name
        String userName;
        while (true) {
            System.out.println("Enter username");
            userName = Config.scanner().nextLine();
            if (userName.matches("[a-zA-Z\\d]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid username try again");
            }
        }
        //mail
        String email;
        while (true) {
            System.out.println("Enter email");
            email = Config.scanner().nextLine();
            if (email.matches("^(.+)@(.+)$")) {
                break;
            } else {
                System.out.println("Invalid email try again");
            }
        }
        ///pass
        String password;
        while (true) {
            System.out.println("Enter password");
            password = Config.scanner().nextLine();
            if (password.matches("[a-zA-Z\\d]{1,40}")) {
                break;
            } else {
                System.out.println("Invalid password try again");
            }
        }
        ///role
        System.out.println("Enter role:");
        String role = Config.scanner().nextLine();
        Set<String> strRole = new HashSet<>();
        strRole.add(role);
        SignUpDTO signUpDTO = new SignUpDTO(id, name, userName, email, password, strRole);
        ResponseMessenger responseMessenger = userController.register(signUpDTO);
        switch (responseMessenger.getMessage()) {
            case "user_existed":
                System.out.println("UserName already exists");
                break;
            case "email_existed":
                System.out.println("Email already");
                break;
            case "success":
                System.out.println("Register success!");
                break;
        }
    }

    private void FromShowListUser() {
        System.out.printf("%-15s %s %n", "Username", "Role");
        for (User user : userList) {
            System.out.printf("%-15s %s %n", user.getUserName(), new ArrayList<>(user.getRoles()).get(0).getRoleName());
        }

    }
}
