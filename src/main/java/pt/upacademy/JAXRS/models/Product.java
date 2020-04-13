package pt.upacademy.JAXRS.models;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


//Por causa desta anotacao e que a classe abstrata se chama Entity_

// O que define a query é o que ta dentro da query = ""

//Tem que se chamar desta maneira Product.GET_ALL_PRODUCTS_QUERY_NAME . porque a variável está definida
//como static e para a chamar tem que se chamar " pela classe "

@Entity
@NamedQueries({
@NamedQuery(name = Product.GET_ALL_PRODUCTS_QUERY_NAME, query="SELECT p FROM Product p"),
@NamedQuery(name = Product.GET_ALL_PRODUCTS_IDS, query="SELECT p FROM Product p")
})

public class Product extends Entity_

{

	public static final String GET_ALL_PRODUCTS_QUERY_NAME = "Product.getAllProducts" ;
	public static final String GET_ALL_PRODUCTS_IDS = "Product.getAllProductIds" ; // Este é para depois
	
	// a variavel serialVersionUID tem de estar implementada porque a superclasse tambem a tem
	private static final long serialVersionUID = 1L;
	private String name;
	private int discount;
	private int iva;
	private double pvp;

	
	public Product() {}

	public String getName() 

	{
		return name;
	}

	public void setName(String name) 

	{
		this.name = name;
	}


	// Aqui utiliza-se uma vari�vel est�tica da classe para poder incrementar
	// id's
	// para que nunca se repitam


	public int getDiscount() 

	{
		return discount;
	}

	public void setDiscount(int discount) 

	{
		this.discount = discount;
	}

	public int getIva() 

	{
		return iva;
	}

	public void setIva(int iva) 

	{
		this.iva = iva;
	}

	public double getPvp() 

	{
		return pvp;
	}

	public void setPvp(double pvp) 

	{
		this.pvp = pvp;
	}

//	@Override
//	public String toString() {
//		return this.getId() + "  [name=" + name + ", discount=" + discount + ", iva=" + iva + ", pvp="
//				+ pvp + "] \n";
//	}
	
//	@Override
//	public String toString() {
//		return " [name=" + name + ", discount=" + discount + ", iva=" + iva + ", pvp="
//				+ pvp + "] \n";
//	}


}
