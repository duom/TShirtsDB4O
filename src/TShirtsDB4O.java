import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import Entities.Article;
import Entities.CreditCard;
import Entities.Customer;
import Entities.Order;
import Entities.OrderDetail;


/**
 * @author Albert Fernández Garcia
 * @since 15/05/2019
 *
 */

public class TShirtsDB4O {
	public static ArrayList<Order> orders;
	static ObjectContainer db;

	/**
	 * Implement todo methods and run to test
	 * 
	 * @param args
	 *            no args
	 * @throws IOException
	 *             in order to read files
	 * @throws ParseException
	 *             in order to parse data formats
	 */
	public static void main(String[] args) throws IOException, ParseException {

		TShirtsDB4O TSM = new TShirtsDB4O();
		FileAccessor fa = new FileAccessor();

		fa.readArticlesFile("articles.csv");
		fa.readCreditCardsFile("creditCards.csv");
		fa.readCustomersFile("customers.csv");
		fa.readOrdersFile("orders.csv");
		fa.readOrderDetailsFile("orderDetails.csv");
		orders = fa.orders;
		try {

			File file = new File("orders.db");
			String fullPath = file.getAbsolutePath();
			db = Db4o.openFile(fullPath);

			TSM.clearDatabase();
			TSM.addOrders();
			TSM.listOrders();
			TSM.listArticles();
			TSM.addArticle(7, "CALCETINES EJECUTIVOS 'JACKSON 3PK'", "gris", "45", 18.00f);
			TSM.showCreditCardByCustomerName("Jordi");
			TSM.updatePriceArticle(7, 12.00f);
			TSM.llistaArticlesByName("CALCETINES EJECUTIVOS 'JACKSON 3PK'");
			TSM.deletingArticlesByName("POLO BÁSICO 'MANIA'");
			TSM.deleteArticleById(7);
			TSM.listArticles();
			TSM.listCustomers();
			TSM.changeCreditCardToCustomer(1);
			TSM.listCustomers();
			TSM.llistaCustomerByName("Laura");
			TSM.showOrdersByCustomerName("Hernán");
			TSM.showCreditCardByCustomerName("Laura");

			// Per alguna raó no vol esborrar el Customer 2
			TSM.deleteCustomerbyId(22);
			TSM.deleteCustomerbyId(4);
			TSM.retrieveOrderContentById_Order(2);
			TSM.deleteOrderContentById_Order(2);
			TSM.retrieveOrderContentById_Order(2);
			TSM.listCustomers();
			TSM.clearDatabase();
			TSM.listOrders();

		} finally {
			db.close();
		}
	}

	/**
	 * Select Customer using customer id and next generate a new credit card and
	 * update customer using the new credit card
	 * 
	 * @param i
	 *            idCustomer
	 */
	public void changeCreditCardToCustomer(int i) {

		ObjectSet result = db.queryByExample(new Customer(i, null, null, null, null, null));
		
		Customer customerTrobat = (Customer) result.next();
		customerTrobat.setCreditCard(new CreditCard("12345", "123", 12, 2019));

		db.store(customerTrobat);
		System.out.println("S'ha assignat una nova targeta aleatòria al Customer " + i);
	}

	/**
	 * Select Article using id and next update price
	 * 
	 * @param id
	 *            article
	 * @param newPrice
	 */
	public void updatePriceArticle(int id, Float newPrice) {

		ObjectSet result = db.queryByExample(new Article(id, null, null, null, 0));

		Article articleTrobat = (Article) result.next();
		articleTrobat.setRecommendedPrice(newPrice);
		db.store(articleTrobat);

		System.out.println("Actualitzat preu del article " + id);
	}

	/**
	 * Add a new article into database
	 * 
	 * @param i
	 *            article id
	 * @param string
	 *            article name
	 * @param string2
	 *            article colour
	 * @param string3
	 *            article size
	 * @param d
	 *            article price
	 */
	public void addArticle(int i, String string, String string2, String string3, Float d) {
		db.store(new Article(i, string, string2, string3, d));
		System.out.println("\nAfegit nou article");
	}

	/**
	 * Delete an article using idArticle
	 * 
	 * @param i
	 *            idArticle
	 */
	public void deleteArticleById(int i) {
		ObjectSet result = db.queryByExample(new Article(i, null, null, null, 0));

		Article aEsborrar = (Article) result.next();
		db.delete(aEsborrar);

		System.out.println("\nEsborrat l'article " + aEsborrar);
	}

	/**
	 * Delete Order and its orderdetails using idOrder
	 * 
	 * @param i
	 *            idOrder
	 */
	public void deleteOrderContentById_Order(int i) {
		ObjectSet result = db.queryByExample(new Order(i, null, null, null, null));
		
		Order orderTrobat = (Order) result.next();
		orderTrobat.setDetails(new HashSet<OrderDetail>());
		db.store(orderTrobat);
	
		System.out.println("\nEsborrat detalls del order " + i);
	}

	/**
	 * Select Order using his id and order details
	 * 
	 * @param i
	 *            idOrder
	 */
	public void retrieveOrderContentById_Order(int i) {
		System.out.println("\nMostrat detalls del Order " + i);
		ObjectSet result = db.queryByExample(new Order(i, null, null, null, null));
		
		Order aConsultar = (Order) result.next();
		aConsultar.getDetails().stream()
				.forEach(System.out::println);
	}

	/**
	 * Delete Customer using idCustomer
	 * 
	 * @param i
	 *            idCustomer
	 */
	public void deleteCustomerbyId(int i) {
		ObjectSet result = db.queryByExample(new Customer(i, null, null, null, null, null));

		while (result.hasNext()) {
			db.delete(result.next());
		}
		System.out.println("\nEsborrat el customer " + i);
	}

	/**
	 * Select Customer using customer name and next select the credit card
	 * values
	 * 
	 * @param string
	 *            customer name
	 */
	public void showCreditCardByCustomerName(String string) {
		System.out.println("Mostrant credit cards del customer " + string);

		ObjectSet<Customer> customers = db.query(new Predicate<Customer>() {
			public boolean match(Customer customer) {
				return customer.getName().equalsIgnoreCase(string);
			}
		});
		customers.stream()
				.peek(System.out::println)
				.forEach(c -> System.out.println(c.getCreditCard()));
	}

	/**
	 * Method to list Oders and orderdetails from the database using the
	 * customer name
	 */
	public void showOrdersByCustomerName(String string) {

		System.out.println("\nMostrant Orders del customer anomenat " + string);
		Customer example = new Customer(0, string, null, null, null, null);

		ObjectSet<Order> orderResult = db.query(new Predicate<Order>() {
			public boolean match(Order order) {
				return order.getCustomer().getName().equals(example.getName());
			}
		});
		orderResult.stream()
				.peek(System.out::println)
				.flatMap(o -> o.getDetails().stream())
				.forEach(System.out::println);
	}

	/** delete all objects from the whole database */
	public void clearDatabase() {

		ObjectSet<Article> articles = db.query(Article.class);
		articles.stream()
			.forEach(db::delete);

		ObjectSet<CreditCard> creditCards = db.query(CreditCard.class);
		creditCards.stream()
			.forEach(db::delete);

		ObjectSet<Customer> customers = db.query(Customer.class);
		customers.stream()
			.forEach(db::delete);

		ObjectSet<Order> orders = db.query(Order.class);
		orders.stream()
			.forEach(db::delete);

		ObjectSet<OrderDetail> orderDetails = db.query(OrderDetail.class);
		orderDetails.stream()
			.forEach(db::delete);
		
		System.out.println("\nBase de dades esborrada per complet");
	}

	/**
	 * Delete Article using article name
	 * 
	 * @param string
	 *            Article name
	 */
	public void deletingArticlesByName(String string) {
		System.out.println("\nArticles anomenats " + string + " :");
		
		ObjectSet<Article> articles = db.query(new Predicate<Article>() {
			public boolean match(Article article) {
				return article.getName().equalsIgnoreCase(string);
			}
		});
		articles.stream()
				.peek(System.out::println)
				.forEach(db::delete);
	}

	/** Method to list Articles from the database using their name */
	public void llistaArticlesByName(String string) {
		System.out.println("\nArticles anomenats " + string + " :");
		
		ObjectSet<Article> articles = db.query(new Predicate<Article>() {
			public boolean match(Article article) {
				return article.getName().equalsIgnoreCase(string);
			}
		});
		articles.stream()
				.forEach(System.out::println);
	}

	/** Method to list Customers from the database using their name */
	public void llistaCustomerByName(String string) {
		System.out.println("\nCustomers anomenats " + string + " :");
		
		ObjectSet<Customer> customers = db.query(new Predicate<Customer>() {
			public boolean match(Customer customer) {
				return customer.getName().equalsIgnoreCase(string);
			}
		});
		
		customers.stream()
			.forEach(System.out::println);
	}

	/** Method to list all Customers from the database */
	public void listCustomers() {
		System.out.println("\nCustomers llegits desde la base de dades");

		ObjectSet<Customer> customers = db.query(Customer.class);
		customers.stream()
			.forEach(System.out::println);

	}

	/** Method to list all Articles from the database */
	public void listArticles() {
		System.out.println("\nArticles llegits desde la base de dades");

		ObjectSet<Article> articles = db.query(Article.class);
		articles.stream()
			.forEach(System.out::println);
	}

	/** Method to add all orders from ArrayList and store them into database */
	public void addOrders() {
		orders.stream()
			.forEach(db::store);
	}

	/** Method to list all Orders from the database */
	public void listOrders() {
		System.out.println("\nMostrant tots els orders");
		ObjectSet<Order> orders = db.query(Order.class);
		orders.stream()
			.peek(System.out::println)
			.flatMap(o -> o.getDetails().stream())
			.forEach(System.out::println);
	}
}