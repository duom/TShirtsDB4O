package Entities;

import java.io.Serializable;

/**
 * @author Joan Anton Pérez Braña
 * @since 19/02/2017
 *
 */
public class OrderDetail implements Serializable{
	private int quantity;
	private float priceEach;
	private Article article;
	
	public OrderDetail(int quantity, float priceEach, Article article) {
		super();
		this.quantity = quantity;
		this.priceEach = priceEach;
		this.article = article;
	}
	
	public OrderDetail() {
		super();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPriceEach() {
		return priceEach;
	}

	public void setPriceEach(float priceEach) {
		this.priceEach = priceEach;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "OrderDetail [quantity=" + quantity + ", priceEach=" + priceEach + ", article=" + article + "]";
	}
	
	
}