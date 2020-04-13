package pt.upacademy.JAXRS.services;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Path;

import pt.upacademy.JAXRS.models.Entity_;
import pt.upacademy.JAXRS.repositories.EntityRepository;
import javax.inject.Named;
import javax.transaction.Transactional;

@Transactional // Todos os metodos dentro da classe sao transacoes, em vez de por a anotacao em cada metodo
public abstract class EntityService<R extends EntityRepository<E>, E extends Entity_>

{

	// O entity tem um underscore para nao interferir com o Entity do java

	// O R vai ser subtituido por Product(Repository) ou Shelf(Repository)
	
	// Ver as Classes ProductService e ShelfService para ver como isto se relaciona
	
	// Este inject dá erro quando se muda o codigo mas é só fazer Update Project e esta fino
	
	@Inject
	protected R repository;
	
	
	public EntityService()

	{

	}

	public E save(E object) throws Exception

	{
		return repository.save(object);
	}


	public Collection<E> showAllEntities()

	{
		return repository.getAll();
	}

	public E getObject(long id)

	{
		return repository.getObj(id);
	}

	public E updateObj(long id, E object) throws Exception

	{
		return repository.update(id, object);
	}

	
	public void deleteObj(long id) throws Exception

	{
		repository.remove(id);

	}
	
	public List<Long> getAllKeys()

	{

		return repository.getAllKeys();

	}

}
