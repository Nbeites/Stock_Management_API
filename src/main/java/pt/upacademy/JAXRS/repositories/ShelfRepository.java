package pt.upacademy.JAXRS.repositories;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Path;

import pt.upacademy.JAXRS.models.Shelf;
import pt.upacademy.JAXRS.models.Product;


//Isto é a implementaçao de um singleton, é um bean

@ApplicationScoped
public class ShelfRepository extends EntityRepository<Shelf>

{

	@Override
	public Class<Shelf> getEntityClass()

	{
		return Shelf.class;
	}

	@Override
	public String getAllEntityQueryName()

	{
		return Shelf.GET_ALL_SHELVES_QUERY_NAME;
	}

	@Override
	protected String getAllIdsQueryName()

	{
		return Shelf.GET_ALL_SHELVES_IDS;
	}

	public void editProdInShelf(long shelfId, long prodId)

	{
		String queryUpdateProdInShelf = "UPDATE Shelf s SET s.product = " + prodId + " WHERE s.id =" + shelfId;	
		entityManager.createQuery(queryUpdateProdInShelf).executeUpdate();
	}

	public void deleteProdInShelf()
	// Aqui é fazer como no editProdInShelf mas em vez de alterar para o prodId, alterar para null
	{

	}

	public void updateProdinShelves(long shelfId)

	{
		String query = "UPDATE Shelf s SET s.product = null WHERE s.id =" + shelfId; // No java nao precisa do ";" do
																						// sql no fim
		entityManager.createQuery(query).executeUpdate(); // sem o execute udpate não corre a query, só a cria
	}

	public List<Long> consultShelfByProdId(long prodId)

	{

		// Também se pode definir logo aqui a query, em vez de ir à variável de
		// Prodcut como está no getAllEntityQueryName e getAllIdsQueryName por exemplo

		String query = "SELECT s.id FROM Shelf s WHERE s.product.id = " + prodId; // as vezes a query do JDBC nao é bem
																					// igual ao SQL Workbench

		List<Long> resultList = entityManager.createQuery(query, Long.class).getResultList();
		// se
		// o segundo parametro

		return resultList;
	};

	/// As que faltam implementar - Servico , Controller e Repo ///////

}
