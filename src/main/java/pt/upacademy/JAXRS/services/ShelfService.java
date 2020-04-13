package pt.upacademy.JAXRS.services;

import java.security.Provider.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

import pt.upacademy.JAXRS.models.Product;
import pt.upacademy.JAXRS.models.Shelf;
import pt.upacademy.JAXRS.repositories.ProductRepository;
import pt.upacademy.JAXRS.repositories.ShelfRepository;

@RequestScoped // Para evitar dependencia circular (O ProductService chamar o ShelfService e
				// vice versa "recursivamente")
public class ShelfService extends EntityService<ShelfRepository, Shelf>

{

	@Inject
	protected ProductService PRODUCT_SERVICE;

	// As excepções são throwed no Service e catched no Controller , MTO IMP

//	public ShelfService()
//
//	{
//		repository = ShelfRepository.getInstance();
//	}

	@Override // o override nao e preciso aqui se o metodo da classe for igual
	public Shelf save(Shelf object)

	{
		return repository.save(object);

	}

	@Override
	public Shelf updateObj(long id, Shelf object) throws Exception

	{
		// Actualiza a prateleira com novos valores

		Shelf updatedShelf = super.updateObj(id, object);
		// Shelf shelfToUpdate = object; // este object podia estar directamente dentro
		// do updateShelfInProds, mas ficou assim por questao de leitura

		// updateShelfInProds(shelfToUpdate); // isto est� a criar um ciclo
		// infinito,

		return updatedShelf;
	}

	public List<Long> getShelvesByProdId(long productId) {

		return repository.consultShelfByProdId(productId);

	}

	public void clearProdInShelves(List<Long> shelfList) throws Exception

	{
		// Isto podia ter sido resolvido com uam query de SQL (Acho eu),
		// Se for possível fazer com query apenas optar por essa solução.

		for (Long shelf : shelfList)
		{
			long shelfId = shelf.intValue();
			repository.updateProdinShelves(shelfId); 
			// Aqui o productService comunica com o Shelf Service
		}
	}

	public void changeProdInShelf(long shelfId, long prodId)

	{
		repository.editProdInShelf(shelfId, prodId);
	}

//	@Override
//	public void deleteObj(long id) throws Exception
//
//	{
//		// o oldObject tem de ser buscado para limpar as prateleiras
//		// onde estava colocado
//		if (repository.getAllKeys() != null) {
//			if (repository.getObj(id) != null) {
//
//				Shelf shelfToDelete = repository.getObj(id);
//				// Abaixo o produto correspondente à prateleira que se vai remover
//
//				Product prodToUpdate = PRODUCT_SERVICE.getObject(shelfToDelete.getProdID());
//				removeShelfInProds(shelfToDelete.getId(), prodToUpdate);
//				super.deleteObj(id);
//
//			} else {
//				throw new Exception("A prateleira com o id " + id + " nao existe (deleteObj)");
//			}
//		} else {
//			throw new Exception("Nao ha Shelves ainda (deleteObj)");
//		}
//
//	}
//
//	public void changeProdInShelf(long shelfId, long newProdId) throws Exception {
//
//		// String prodIdString = prodId.toString() ;
//		// Faz set na shelf do valor do novo prodId
//
//		try {
//
//			
//			// Set ao novo prodID na shelf
//			if (repository.getObj(shelfId).getProdID() != 0) {
//
//				long oldProdId = repository.getObj(shelfId).getProdID();
//
//				Shelf shelf = repository.getObj(shelfId);
//
//				shelf.setProdID(newProdId);
//
//				Product newProd = PRODUCT_SERVICE.getObject(newProdId);
//				Product oldProd = PRODUCT_SERVICE.getObject(oldProdId);
//
//				// Actualiza o array de shelves no produto que está a ser alterado na shelf
//				// Se o shelfID estiver contido no array de shelves entao é porque é para tirar,
//				// se nao é porque é para acrescentar
//
//				// Porque o produto antigo saiu da prateleira e o novo "ganhou" uma prateleira
//
//				removeShelfInProds(shelf.getId(), oldProd);
//
//				addShelfInProds(shelf.getId(), newProd);
//
//			} else {
//
//				Product newProd = PRODUCT_SERVICE.getObject(newProdId);
//				Shelf shelf = repository.getObj(shelfId);
//
//				addShelfInProds(shelf.getId(), newProd);
//			}
//
//		} catch (Exception e) {
//			throw new Exception("Shelf Ou Produto Nao Existe");
//		}
//
//	}
//
//	// Refazer esta logica de remove e add no update
//
//	// Quando for possível dar refactor a estas duas funcoes abaixo (Poderia-se
//	// fazer com o mesmo nome secalhar e fazer um overload)
//	
//	// Método Auxiliar, por isso é que está no Shelf Service , tal como o removeShelfInProds
//	public void addShelfInProds(long shelfToUpdate, Product prod) throws Exception
//
//	{
//		// Selecciona-se o produto referente à prateleira que se vai alterar
//
//		ArrayList<Integer> oldShelves = prod.getShelves();
//
//		// A unica coisa que muda daqui para o remove ShelfInProds é a definição do
//		// newShelves e aqui nao tem ciclo for porque é só para adicionar uma
//		// Aqui começa já com os valroes anteriores, e no remove não
//
//		ArrayList<Integer> newShelves = oldShelves; // Esta escrito assim por motivos de leitura
//
//		// Adiciona-se ao array de shelves a que nao esta la ainda, no caso de ser
//		// update
//
//		Integer shelfIdToUpdate = (int) shelfToUpdate;
//		newShelves.add(shelfIdToUpdate);
//
//		prod.setShelves(newShelves);
//
//		PRODUCT_SERVICE.updateObj(prod.getId(), prod);
//
//	}
//	// Método Auxiliar, por isso é que está no Shelf Service
//	public void removeShelfInProds(long shelfToUpdate, Product prod) throws Exception
//
//	{
//		
////		long shelfID = shelfToUpdate.getId(); // podia estar dentro do if mas fica aqui por uma quest�o de leitura
//
//		// Selecciona-se o produto referente à prateleira que se vai alterar
//
////		Product prod = PRODUCT_SERVICE.getObject(shelfToUpdate);
//
//		ArrayList<Integer> oldShelves = prod.getShelves();
//
//		ArrayList<Integer> newShelves = new ArrayList<Integer>();
//
//		// Elimina-se de shelves a que é para apagar, no caso de ser para eliminar
//
//		for (int shelf : oldShelves) {
//			if (shelf != shelfToUpdate) {
//				newShelves.add(shelf);
//			}
//		}
//
//		prod.setShelves(newShelves);
//
//		PRODUCT_SERVICE.updateObj(prod.getId(), prod);
//
//	}
//
//	// Filtra todas as prateleiras com um certo prodId
//	public Collection<Shelf> shelfsByProdId(long prodId)
//
//	{
//		Collection<Shelf> allShelfs = repository.getObjsFromMap();
//
//		return allShelfs.stream().filter(s -> s.getProdID() == prodId).collect(Collectors.toList());
//	}
//	
//	
//
//	
//	public void deleteProdInShelf(long shelfId, long prodId) throws Exception {
//		
//		try {
//
//			Shelf shelf = repository.getObj(shelfId);
//			Product prod = PRODUCT_SERVICE.getObject(prodId);
//					
//			removeShelfInProds(shelfId, prod); // Actualiza esta shelf removida nos produtos
//			
//			shelf.setProdID(0); // Set a 0 do prodId nesta shelf
//			
//
//		} catch (Exception e) {
//			throw new Exception("Introduza um valor de produto valido para a prateleira");
//		}
//		
//	}

}
