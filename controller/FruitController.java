package controller;

import java.util.ArrayList;
import java.util.Scanner;

import Common.Validation;
import model.Fruit;

public class FruitController {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Fruit> fruit = new ArrayList<>();
    Validation validation = new Validation();

    public void createFruit() {
        do {
            System.out.println("Enter Fruit Id: ");
            String fruitId = scanner.nextLine();
            System.out.println("Enter Fruit Name: ");
            String fruitName = scanner.nextLine();
            System.out.println("Enter Price: ");
            Double price = scanner.nextDouble();
            System.out.println("Enter Quantity: ");
            Integer quantity = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Origin: ");
            String origin = scanner.nextLine();
            Fruit fr = new Fruit(fruitId, fruitName, price, quantity, origin);
            fruit.add(fr);
            boolean continueCreating = validation.checkInputYN("Do you want to continue (Y/N)? ");
            if (!continueCreating) {
                break;
            }
        } while (true);
    }
}
