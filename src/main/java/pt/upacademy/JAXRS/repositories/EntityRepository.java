package pt.upacademy.JAXRS.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Path;

import pt.upacademy.JAXRS.models.Entity_;



// E nas classes ProductRepository e ShelfRepository tamb�m s�o feitos
// os imports do model.Product e model.Entity

// Esta e uma classe abstrata, e o que esta dentro dos <> é uma validacao 
// de que isto so vai aceitar classes genericas T que extendam entity

// este é um template de "base de dados", e as bases de dados sao criadas ao instanciar
// classes que extendem esta, como o caso da ProductRepository e ShelfRepository

//Isto quer dizer que a classe  EntityRepository s� vai aceitar
//um tipo de objecto gen�rico mas que seja um dos modelos que 
//s�o extens�es da Entity

public abstract class EntityRepository<T extends Entity_>

{
	
// Isto vai definir que qualquer transação para SQL seja a partir desta classe ou do que extender a classe
	
	@PersistenceContext
	protected EntityManager entityManager;

//	public long nextId() {
//
//	}



	public T save(T entity)

	{
		return entityManager.merge(entity); //merge para introduzir ou editar,
		// nao se usou o .persist, porque nao retorna nada, e o merge retorna
	}

	public T update(long id, T entity)

	// Ao se passar o id da entity a alterar, vai-se buscar
	// o objecto correspondente e aplica-se o put
	// O entity.getId() � um m�todo comum ao product e � shelf
	// Coment�rios acima n�o validos para a solu�ao abaixo

	{
		T newObject = entity;
		
		// é necessario acrescentar um id ao que vem do postman para o merge substituir,
		// senão só acrescenta
		
		newObject.setId(id);
		entityManager.merge(newObject);
		
		return newObject;
	}

	public void remove(long removeId)

	{
		T entity = entityManager.find(getEntityClass(), removeId);
		entityManager.remove(entity);
	}


	public T getObj(long id)

	{
		return entityManager.find(getEntityClass(), id) ;
	}


	public Collection<T> getAll()

	{ 
		// é assim que o entityManager vai criar a named query que é definida do Product ou Shelf
		return entityManager.createNamedQuery(getAllEntityQueryName(), getEntityClass()).getResultList();
		// Serve para Product e Shelf, ver os overrides que estao nos repositorios de Shelf e Product
		// .getResultList para retornar uma Collection
	}

	public List<Long> getAllKeys()

	{
		return entityManager.createNamedQuery(getAllIdsQueryName(), Long.class).getResultList();
	}

	// para "direccionar" a query para a tabela de products ou shelfs
	public abstract Class <T> getEntityClass();
	
	public abstract String getAllEntityQueryName();
	
	protected abstract String getAllIdsQueryName();
	

	/// As que faltam implementar - Servico , Controller e Repo
	

	
	
	
//	public void show(long showID)
//
//	{
//
//		System.out.println(map.get(showID).toString());
//
//	}	

	
//	public long getObjId(long objKey)
//
//	{
//
//		T obj = map.get(objKey);
//
//		return obj.getId();
//
//	}

//	public long getMapSize()
//
//	{
//
//		return map.size();
//
//	}

//	public Collection<T> getObjsFromMap()
//
//	{ // Esta funcao e necessaria para instanciar a collection quando se vai iterar
//		// as shelves para as actualizar em termos de product id
//
//		// Esta fun�ao tamb�m serve para retornar os objectos todos do servidor
//		// quando se faz um get request no Postman � API por exemplo
//		
//		return map.values();
//	}
	
	


}
