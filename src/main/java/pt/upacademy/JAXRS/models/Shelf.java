package pt.upacademy.JAXRS.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

//Tem que se chamar desta maneira  Shelf.GET_ALL_SHELVES_QUERY_NAME . porque a variável está definida
//como static e para a chamar tem que se chamar " pela classe "

@Entity
@NamedQueries({
	@NamedQuery(name=Shelf.GET_ALL_SHELVES_QUERY_NAME, query="SELECT s FROM Shelf s"),
	@NamedQuery(name=Shelf.GET_ALL_SHELVES_IDS, query="SELECT s.id FROM Shelf s")
})


public class Shelf extends Entity_ {

	
	public static final String GET_ALL_SHELVES_QUERY_NAME = "Shelf.getAllShelves" ;
	public static final String GET_ALL_SHELVES_IDS = "Shelf.getAllShelfIds" ;
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Product product;
	private int capacity;
	private double cost;
	

	public Shelf() {}
	
	
	public Product getProduct() 
	
	{
		return product;
	}


	public void setProduct(Product product) 
	
	{
		this.product = product;
	}


	public int getCapacity()

	{
		return capacity;
	}

	public void setCapacity(int capacity)

	{
		this.capacity = capacity;
	}


	public double getCost()

	{
		return cost;
	}

	public void setCost(int cost)

	{
		this.cost = cost;
	}

//	@Override
//	public String toString()
//
//	{
//		return "  [capacity=" + capacity + ", product=" + product + ", cost=" + cost + ", prodID=" + "] \n";
//	}


}
