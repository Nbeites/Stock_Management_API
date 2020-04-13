package pt.upacademy.JAXRS.services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

import pt.upacademy.JAXRS.models.Product;
import pt.upacademy.JAXRS.models.Shelf;
import pt.upacademy.JAXRS.repositories.ProductRepository;

@RequestScoped // Para evitar dependencia circular (O ProductService chamar o ShelfService e
				// vice versa "recursivamente")
public class ProductService extends EntityService<ProductRepository, Product>

{

// <R, E>

	// Quando h� altera��es nos produtos que implicam altera��es nas shelves,
	// esta � uma maneira de isntanciar a classe para chamar o servi�o de shelves
	// dentro do servi�o de produtos, isto para o servi�o de productos n�o
	// mexer no reposit�rio de shelves, e sim comunicar com o servi�o de
	// shelves
	// para este chamar o repositorio de shelves

	@Inject
	protected ShelfService SHELF_SERVICE;

	// As excepções são throwed no Service e catched no Controller , MTO IMP

	@Override
	public Product save(Product object) throws Exception // Nao estou a conseguir bem lidar com o erro
	// de quando n�o ha prateleiras

	{
		// super.save(object); - nao pode ser com super porque senao nao actualiza o id
		// no controller
		return repository.save(object);

	}

	@Override
	public void deleteObj(long id) throws Exception

	{

		
		// Isto podia -se resolver com um query só : 
		
		// UPDATE mysqldb.shelf SET product_id = 0 WHERE product_id = 1;
		
		// onde o product_id é 1, passar a 0 basicamente
		
		
		List <Long> shelfList = SHELF_SERVICE.getShelvesByProdId(id);
		
		
		if (shelfList != null && !shelfList.isEmpty()) 

		{
			SHELF_SERVICE.clearProdInShelves(shelfList);
		}
		
		 repository.remove(id);
	}
	
	
	
	
	

//
//	@Override
//	public Product updateObj(long id, Product object) throws Exception
//
//	{
//		// Clear produtos antigos nas prateleiras
//
//		Product oldObject = repository.getObj(id);
//
//		if (oldObject != null) { // Na criacao do primeiro objecto, o old nao esta la, ou seja fica null, cuidado
//
//			clearProdInShelves(oldObject);
//			// Update productos novos nas prateleiras
//			super.updateObj(id, object);
//		}
//
//		if (object != null) {
//			updateProdInShelves(object);
//		}
//
//		return object;
//
//	}
//
//	@Override
//	public void deleteObj(long id) throws Exception
//
//	{
//
//		// o oldObject tem de ser buscado para limpar as prateleiras
//		// onde estava colocado
//
//		if (repository.getAllKeys() != null) {
//			if (repository.getAllKeys().contains(id) == true) {
//
//				Product oldObject = repository.getObj(id);
//				clearProdInShelves(oldObject);
//				super.deleteObj(id);
//
//			} else {
//				throw new Exception("O produto com o id " + id + " nao foi encontrado (deleteObj)");
//			}
//		} else {
//			throw new Exception("Nao ha produtos ainda (deleteObj)");
//		}
//
//	}
//
//	// Estas duas funcoes abaixo podem ser refactored, a unica coisa que muda
//	// � o shelf.setProdID(object.getId()) ou shelf.setProdID(0);
//
//	public void updateProdInShelves(Product object) throws Exception
//
//	{
//
//		ArrayList<Integer> prodShelves = object.getShelves();
//
//		if (prodShelves.size() != 0) {
//
//			for (int i = 0; i < prodShelves.size(); i++) {
//
//				long shelfID = prodShelves.get(i);
//				Shelf shelf = SHELF_SERVICE.getObject(shelfID); // Aqui o productService comunica com o Shelf Service
//				shelf.setProdID(object.getId());
//				shelf.setProduct(object.getName());
//
//				SHELF_SERVICE.updateObj(shelfID, shelf);
//			}
//		}
//	}
//

//
//	public void addShelfToProduct(long prodId, long shelfId) throws Exception
//
//	{
//		Product prod = repository.getObj(prodId);
//		ArrayList<Integer> shelves = prod.getShelves();
//		shelves.add((int) shelfId);
//
//		Shelf shelf = SHELF_SERVICE.getObject(shelfId);
//
//		if (shelf.getProdID() != 0) {
//			throw new Exception("Prateleira Ja Cheia (addShelfToProduct)");
//
//		} else {
//			shelf.setProdID((int) prodId);
//		}
//
//	}
//
//	public void removeShelfInProduct(long prodId, long shelfId) throws Exception
//
//	{
//
//		Product prod = repository.getObj(prodId);
//		ArrayList<Integer> shelves = prod.getShelves();
//
//		// Remove o elemento com o indice resultante do indexOf do shelfId
//		
//		shelves.remove(shelves.indexOf((int)shelfId)); // O arrayList só aceita remove de ints, por isso fazer o cast para int
//
//		prod.setShelves(shelves);
//		
//		updateObj(prodId, prod);
//
//		// Esvazia a prateleira correspondente
//
//		SHELF_SERVICE.getObject(shelfId).setProdID(0);
//
//	}
//
//	// Vai procurar os productos com o shelfId introduzido no queryparameters
//
//	public Collection<Product> ProdByShelfId(long shelfId)
//
//	{
//		Collection<Product> allProds = repository.getObjsFromMap();
//
//		return allProds.stream().filter(s -> s.getShelves().contains((int) shelfId) == true)
//				.collect(Collectors.toList());
//	}

}
