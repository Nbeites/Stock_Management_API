package pt.upacademy.JAXRS.repositories;


import java.util.Collection;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Path;

import pt.upacademy.JAXRS.models.Product;
import pt.upacademy.JAXRS.models.Shelf;


// Isto é a implementaçao de um singleton, é um bean

@ApplicationScoped
public class ProductRepository extends EntityRepository<Product>


{
	
	@Override
	public Class<Product> getEntityClass() {
		return Product.class;
	}

	@Override
	public String getAllEntityQueryName() {
		return Product.GET_ALL_PRODUCTS_QUERY_NAME;
	}

	@Override
	protected String getAllIdsQueryName() {
		return Product.GET_ALL_PRODUCTS_IDS;
	}

	
	
	/// As que faltam implementar - Servico , Controller e Repo ///////
	
	public String addShelfToProduct() {
		return null;
	};
	
	public String removeShelfInProduct() {
		return null;
	};
	
	public String consultProdByShelfId() {
		return null;
	};
	
	// Também se pode definir logo aqui a queryName, em vez de ir à variável de
	// Prodcut como está no getAllEntityQueryName e getAllIdsQueryName por exemplo
	

	

	
	
//	public boolean getAll()
//
//	{ 
//		// é assim que o entityManager vai criar a named query que é definida do Product ou Shelf
//		return entityManager.createNamedQuery(getAllEntityQueryName(), getEntityClass()).getResultList();
//		// Serve para Product e Shelf, ver os overrides que estao nos repositorios de Shelf e Product
//		// .getResultList para retornar uma Collection
//	}
	
}

