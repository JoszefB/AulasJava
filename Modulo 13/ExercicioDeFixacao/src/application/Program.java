package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;


public class Program {
	
	private Scanner kb;
	private SimpleDateFormat sdf;
	private ArrayList<Client> clients;
	private ArrayList<Order> orders;
	private Order order;
	private OrderStatus status;
	private OrderItem orderItem;
	private boolean loop;
	
	public Boolean getLoop() {
		return loop;
	}
	
	public void setLoop(boolean loop) {
		this.loop=loop;
		kb.close();
	}
	
	public Program() {
		loop = true;
		kb = new Scanner(System.in);
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		clients = new ArrayList<Client>();
		orders = new ArrayList<Order>();
	}
	
	public void menu() throws ParseException {
		System.out.print("MENU:"
				+ "\n1 - Register Customer"
				+ "\n2 - Order"
				+ "\n3 - Updade Order Status"
				+ "\n4 - List Client"
				+ "\n5 - List Order"
				+ "\n6 - Remove Order"
				+ "\n7 - Exit"
				+ "\n->");
		int menu = kb.nextInt();
		switch (menu) {
			case 1:
				registerCustomer();
				break;
			case 2:
				newOrder();
				break;
			case 3:
				updateStatusOrder();
				break;	
			case 4:
				listClient();
				break;
			case 5:
				listOrder();
				break;
			case 6:
				removeOrder();
				break;
			case 7:
				System.out.println("Finishing");
				setLoop(false);
				break;
			default:
				System.out.println("Invalid option");
				break;
		}
	}
	
	public void registerCustomer() throws ParseException {
		System.out.println("Enter client data:");
		System.out.print("Name: ");
		kb.nextLine();
		String name = kb.nextLine();
		System.out.print("Email: ");
		String email = kb.next();
		System.out.print("Birth date (DD/MM/YYYY): ");
		Date birthDate = sdf.parse(kb.next());
		
		Client client = new Client(name, email, birthDate);
		
		clients.add(client);
	}
	
	public void newOrder() {
		System.out.print("Select customer:");
		kb.nextLine();
		String name = kb.nextLine();
		for(Client client: clients) {
			if(name.equals(client.getName())) {				
				System.out.println("Enter order data:");
				System.out.print("Status: ");
				status = OrderStatus.valueOf(kb.next());
				order = new Order(new Date(), status, client);
				orders.add(order);
				addItems();
			}
		}
	}
	
	public void addItems() {
		System.out.print("How many items to this order? ");
		int n = kb.nextInt();
		for (int i=1; i<=n; i++) {
			System.out.println("Enter #" + i + " item data:");
			System.out.print("Product name: ");
			kb.nextLine();
			String productName = kb.nextLine();
			System.out.print("Product price: ");
			double productPrice = kb.nextDouble();

			Product product = new Product(productName, productPrice);

			System.out.print("Quantity: ");
			int quantity = kb.nextInt();

			orderItem = new OrderItem(quantity, productPrice, product); 

			order.addItem(orderItem);
		}
	}
	
	public void updateStatusOrder() {
		System.out.println("Select customer:");
		kb.nextLine();
		String name = kb.nextLine();
		for(Client client: clients) {
			if(name.equals(client.getName())) {
				System.out.println("Enter order data:");
				System.out.print("Status: ");
				status = OrderStatus.valueOf(kb.next());
				order.setStatus(status);
			}
		}
	}
	
	public void listClient() {
		for(Client client: clients) {
			System.out.println(client);
		}
	}
	
	public void listOrder() {
		System.out.print("Select customer:");
		kb.nextLine();
		String name = kb.nextLine();
		for(Order o : orders) {
			if(name.equals(o.getClient().getName())) {
				System.out.println("ORDER SUMMARY:");
				System.out.println(o.toString());
			}
		}
	}
	
	public void removeOrder() {
		System.out.println("Select customer:");
		kb.nextLine();
		String name = kb.nextLine();
		for(Order o : orders) {
			if(name.equals(o.getClient().getName())) {
				System.out.print("Enter the product: ");
				String nameItem = kb.next();
				for(OrderItem oi : o.getItems()) {
					if(nameItem.equals(oi.getProduct().getName())) {
						o.removeItem(oi);
					}
				}
			}
		}
	}

}