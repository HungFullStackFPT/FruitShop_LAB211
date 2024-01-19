package test;

import java.util.ArrayList;
import java.util.Scanner;

import controller.FruitController;
import controller.ShoppingSystem;
import model.Customer;
import model.Fruit;
import view.Menu;

public class Main {
    private static final String[] MAIN_MENU_ITEMS = {
            "Create Fruit",
            "View Orders",
            "Shopping(for buyer)",
            "Exit"
    };
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        FruitController fruitController=new FruitController();
        ShoppingSystem shoppingSystem=new ShoppingSystem(fruitController);
    	Menu mainMenu = new Menu("FRUIT SHOP SYSTEM", MAIN_MENU_ITEMS) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1:
                    	fruitController.createFruit();
                        break;
                    case 2:
                        shoppingSystem.displayOrders();
                        break;
                    case 3:
                        shoppingSystem.shop();
                        System.out.println("Enter customer name:");
                        String customerName = scanner.nextLine();
                        Customer customer = new Customer(customerName);
                        ArrayList<Fruit> orderList = shoppingSystem.getOrderList();
                        shoppingSystem.addOrder(customer, orderList);     
                        break;

                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            }
        };

        mainMenu.run();
    }
}

