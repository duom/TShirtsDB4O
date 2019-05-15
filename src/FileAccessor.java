import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import Entities.Article;
import Entities.CreditCard;
import Entities.Customer;
import Entities.Order;
import Entities.OrderDetail;
/**
 * @author Mario Gordillo
 * @since 19/02/2017
 *
 */
public class FileAccessor {

	public ArrayList<Order> orders = new ArrayList<Order>();
	ArrayList<Customer> customers = new ArrayList<Customer>();
	ArrayList<CreditCard> creditCards = new ArrayList<CreditCard>();
	ArrayList<Article> articles = new ArrayList<Article>();
	
	public FileAccessor(){
	}
	
	public void readCustomersFile(String filename) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		String line = br.readLine();
		
		while(line != null){
			StringTokenizer stk = new StringTokenizer(line, ",");
			
			
			int idCustomer = Integer.parseInt(stk.nextToken());
			String name = stk.nextToken();
			String address = stk.nextToken();
			String email = stk.nextToken();
			String phone = stk.nextToken();
			//CreditCardNumber para relacionarlos
			String ccNumber = stk.nextToken();
			CreditCard customerCreditCard = null;
			
			for(CreditCard cc : creditCards){
				if(cc.getNumber().equalsIgnoreCase(ccNumber)){
					customerCreditCard = cc;
				}
			}
			
			customers.add(new Customer(idCustomer, name, address, email, phone, customerCreditCard));
			
			line = br.readLine();
		}
		br.close();
	}

	public void readCreditCardsFile(String filename) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		String line = br.readLine();
		
		while(line != null){
			StringTokenizer stk = new StringTokenizer(line, ",");
			
			String number = stk.nextToken();
			String securityNumber = stk.nextToken();
			int lastMonth = Integer.parseInt(stk.nextToken());
			int lastYear = Integer.parseInt(stk.nextToken());
			
			creditCards.add(new CreditCard(number, securityNumber, lastMonth, lastYear));
			
			line = br.readLine();
		}
		br.close();
	}
	
	public void readOrdersFile(String filename) throws IOException, ParseException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		String line = br.readLine();
		
		while(line != null){
			StringTokenizer stk = new StringTokenizer(line, ",");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			int idOrder = Integer.parseInt(stk.nextToken());
			Date orderDate = sdf.parse(stk.nextToken());
			Date deliveryDate = sdf.parse(stk.nextToken());
			int idCustomer = Integer.parseInt(stk.nextToken());

			Customer customer = null;
			for(Customer c : customers){
				if(c.getIdCustomer() == idCustomer){
					customer = c;
				}
			}
			
			Set<OrderDetail> details = new HashSet<OrderDetail>();
			
			orders.add(new Order(idOrder, orderDate, deliveryDate, customer, details));
			
			line = br.readLine();
		}
		br.close();
	}
	
	public void readOrderDetailsFile(String filename) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		String line = br.readLine();
		
		while(line != null){
			StringTokenizer stk = new StringTokenizer(line, ",");
			
			int quantity = Integer.parseInt(stk.nextToken());
			float priceEach = Float.parseFloat(stk.nextToken());
			int idArticle = Integer.parseInt(stk.nextToken());
			int idOrder = Integer.parseInt(stk.nextToken());
			
			Article article = null;
			
			for(Article a : articles){
				if(a.getIdArticle() == idArticle){
					article = a;
				}
			}

			for(Order o: orders){
				if(o.getIdOrder() == idOrder){
					o.getDetails().add(new OrderDetail(quantity, priceEach, article));
				}
			}
			
			line = br.readLine();
		}
		br.close();
	}

	public void readArticlesFile(String filename) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		String line = br.readLine();
		
		while(line != null){
			StringTokenizer stk = new StringTokenizer(line, ",");
			
			int idArticle = Integer.parseInt(stk.nextToken());
			String name = stk.nextToken();
			String colour = stk.nextToken();
			String size = stk.nextToken();
			float recommendedPrice = Float.parseFloat(stk.nextToken());

			articles.add(new Article(idArticle, name, colour, size, recommendedPrice));
			
			line = br.readLine();
		}
		br.close();
	}
}