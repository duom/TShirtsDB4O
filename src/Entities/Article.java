package Entities;

/**
 * @author Joan Anton Pérez Braña
 * @since 19/02/2017
 *
 */
public class Article {
	private int idArticle;
	private String name;
	private String colour;
	private String size;
	private float recommendedPrice;
	
	public Article(int idArticle, String name, String colour, String size, float recommendedPrice) {
		super();
		this.idArticle = idArticle;
		this.name = name;
		this.colour = colour;
		this.size = size;
		this.recommendedPrice = recommendedPrice;
	}
	
	public Article() {
		super();
	}

	public int getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public float getRecommendedPrice() {
		return recommendedPrice;
	}

	public void setRecommendedPrice(float recommendedPrice) {
		this.recommendedPrice = recommendedPrice;
	}

	@Override
	public String toString() {
		return "Article [idArticle=" + idArticle + ", name=" + name + ", colour=" + colour + ", size=" + size
				+ ", recommendedPrice=" + recommendedPrice + "]";
	}
}