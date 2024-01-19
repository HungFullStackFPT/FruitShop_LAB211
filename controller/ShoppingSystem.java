package controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import model.Customer;
import model.Fruit;

public class ShoppingSystem {
	private Hashtable<String, ArrayList<Fruit>> orders;
	private Scanner scanner;
	private FruitController fruitController;
	private ArrayList<Fruit> orderList;
	Fruit fruits = new Fruit();

	public ShoppingSystem(FruitController fruitController) {
		orders = new Hashtable<>();
		scanner = new Scanner(System.in);
		this.fruitController = fruitController;
		orderList = new ArrayList<>();
	}

	public void displayFruits() {
		System.out.println("List of Fruits:");
		System.out.printf("| ++ Item ++ | ++ Fruit Name ++ | ++ Origin ++ | ++ Price ++ |\n");
		int i = 1;
		for (Fruit fr : fruitController.fruit) {
			System.out.printf("| %-10d | %-15s | %-10s | $%-5.2f |\n", i++, fr.getFruitName(), fr.getOrigin(),
					fr.getPrice());
		}
	}

	public Fruit getFruitById(String id) {
		int index = Integer.parseInt(id) - 1;
		if (index < 0 || index >= fruitController.fruit.size()) {
			return null;
		}
		return fruitController.fruit.get(index);
	}

	public void shop() {
		while (true) {
			displayFruits();
			System.out.print("Please select an item to buy (0 to finish)");
			String itemId = scanner.nextLine();
			if (itemId.equals("0")) {
				break;
			}
			Fruit selectedFruit = getFruitById(itemId);
			if (selectedFruit == null) {
				System.out.println("Invalid item id, please try again.");
				continue;
			}
			System.out.printf("You selected: %s\n", selectedFruit.getFruitName());
			System.out.print("Please input quantity: ");
			int quantity = scanner.nextInt();
			scanner.nextLine();
			selectedFruit.setQuantity(quantity);
			double amount = selectedFruit.getPrice() * quantity;
			System.out.printf("Amount: $%.2f\n", amount);
			orderList.add(selectedFruit);
			while (true) {
				System.out.print("Do you want to order now (Y/N)? ");
				String answer = scanner.nextLine();
				if (answer.equalsIgnoreCase("Y")) {
					System.out.println("Product | Quantity | Price | Amount");
					double total = 0;
					for (Fruit fruit : orderList) {
						double fruitAmount = fruit.getPrice() * fruit.getQuantity();
						System.out.printf("%-8s | %-8d | $%-4.2f | $%-4.2f\n", fruit.getFruitName(),
								fruit.getQuantity(), fruit.getPrice(), fruitAmount);
						total += fruitAmount;
					}
					System.out.printf("Total: $%.2f\n", total);
					System.out.print("Input your name: ");
					String name = scanner.nextLine();
					Customer customer = new Customer(name);
					addOrder(customer, orderList);
					System.out.println("Order added successfully.");
					orderList.clear();
					break;
				} else if (answer.equalsIgnoreCase("N")) {
					break;
				} else {
					System.out.println("Invalid input, please try again.");
				}
			}
	}
	}

	public ArrayList<Fruit> getOrderList() {
		return new ArrayList<>(orderList);
	}

	public void addOrder(Customer customer, ArrayList<Fruit> orderList) {
		String name = customer.getName();
		if (orders.containsKey(name)) {
			ArrayList<Fruit> existingOrderList = orders.get(name);
			existingOrderList.addAll(orderList);
		} else {
			orders.put(name, orderList);
		}
	}

	public void displayOrders() {
		System.out.println("List of Orders:");
		for (String name : orders.keySet()) {
			ArrayList<Fruit> orderList = orders.get(name);
			double total = 0;
			System.out.println("Customer: " + name);
			System.out.printf("| %-15s | %-8s | %-6s | %-8s |\n", "Product", "Quantity", "Price", "Amount");
			for (Fruit fruit : orderList) {
				double amount = fruit.getPrice() * fruit.getQuantity();
				System.out.printf("| %-15s | %-8d | $%-5.2f | $%-5.2f |\n", fruit.getFruitName(), fruit.getQuantity(),
						fruit.getPrice(), amount);
				total += amount;
			}
			System.out.printf("| %-31s | $%-5.2f |\n", "Total:", total);
		}
	}

}