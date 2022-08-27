package rikkei.academy.view;

import rikkei.academy.controller.UserController;
import rikkei.academy.model.User;

public class Main {
    UserController userController = new UserController();
    public Main(){
        User currentuser = userController.getCurrentuser();
        if (currentuser == null){
            new ViewMainMenu().menu();
        }else {
            new ViewHome();
        }
    }

    public static void main(String[] args) {
       new Main();

    }
}