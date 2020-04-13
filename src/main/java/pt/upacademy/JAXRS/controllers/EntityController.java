package pt.upacademy.JAXRS.controllers;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import pt.upacademy.JAXRS.models.Entity_;
import pt.upacademy.JAXRS.repositories.EntityRepository;
import pt.upacademy.JAXRS.services.EntityService;

public abstract class EntityController<S extends EntityService<R, E>, R extends EntityRepository<E>, E extends Entity_> {

	// O "R" e o "E" repositorio (R) e o product ou shelf (E)

	@Inject
	protected S service; // O S vai ser substituido por Product ou Shelf

	// Ver as Classes ProductController e ShelfController para ver como isto se
	// relaciona


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response save(E object) // o object � que o a API recebe em JSON atrav�s do POST
 
	// Nos servicos as excepcoes estao a ser throwed, e estao a ser apanhadas aqui,
	// boa pratica pelo que percebi
	{
		try {
			E saveObject = service.save(object);
			return Response.ok(saveObject).build(); // Devolve tudo bem quando consegue
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();

			// Quando da erro, retornar a mensagem que esta definida quando se da throw a
			// mensagem
		}

	}
	
	
	// Esta funcao foi comentada porque se implementou tanto no ProductService
	// como no ShelfService um metodo especifico para procurar ou por ID
	// e no caso de falta de informacao vai buscar todas

	//	@GET
	//	@Produces(MediaType.APPLICATION_JSON)
	//
	//	public Collection<E> showAllEntities()
	//
	//	{
	//		return service.showAllEntities();
	//	}
	
	@GET
	@Path("allIds")
	@Produces(MediaType.APPLICATION_JSON)

	public List<Long> getAllIds()

	{
		return service.getAllKeys();
	}
	
	// Se o consultar, editar e remover t�m o mesmo caminho, no FE � que decido
	// se � par apagar, editar ou consultar !!! � l� que defino o tipo de pedido
	// para este caminho !!! (Dependendo do metodo, o servidor sabe o que fazer com o
	// mesmo URL !! )

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)

	public E getEntity(@PathParam("id") long id)

	{
		return service.getObject(id);
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public void updateEntity(@PathParam("id") long id, E object)

	
	//Try Catch com mau retorno , ver exemplo do delete.
	// Nao foi alterado para ficar como exemplo
	
	{
		try {
			service.updateObj(id, object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response removeEntity(@PathParam("id") long id)

	{

		//Try Catch com um bom retorno da Response
		
		try {
			service.deleteObj(id);
			return Response.ok().build(); // Devolve tudo bem quando consegue
		} catch (Exception e1) {
			e1.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).entity(e1.getMessage()).build();

		}

	}
	

}













