package pt.upacademy.JAXRS.controllers;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pt.upacademy.JAXRS.models.Product;
import pt.upacademy.JAXRS.models.Shelf;
import pt.upacademy.JAXRS.repositories.ProductRepository;
import pt.upacademy.JAXRS.services.ProductService;

@Path("products")
public class ProductController extends EntityController<ProductService, ProductRepository, Product> {


	@GET
	@Produces(MediaType.APPLICATION_JSON)

	public Collection<Product> showAllEntities()

	{
		return service.showAllEntities();
	}
	
	/// ATENCAO IMP ///
	
	
	
//	@PUT
//	@Path("{id}/shelves")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.TEXT_PLAIN)
//	public Response addShelfToProduct(@PathParam("id") long id, long shelfId) 
//	// A informação que vai no JSON em string está a ir para o shelfID , VER O DELETE
//	// ABAIXO para ver como se faz
//
//	{
//
//		try {
//			service.addShelfToProduct(id, shelfId);
//			return Response.ok().build(); // Devolve tudo bem quando consegue
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			return Response.status(Response.Status.BAD_REQUEST).entity(e1.getMessage()).build();
//
//		}
//
//	}
	
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//
//	// Neste caso existe apenas um parametro, consultar para ver como me faz com
//	// mais
//
//	public Collection<Product> getProdByShelfId(@QueryParam("shelfId") long shelfId)
//
//	{
//		if (shelfId == 0) {
//			return service.showAllEntities();
//
//		} else {
//			return service.ProdByShelfId(shelfId);
//		}
//	}
	
//	

//
//	
//	/// ATENCAO IMP ///
//	
//	/// Este delete que chama o removeProductInShelf está bem feito, 
//	/// com a informacao toda no endpoint como o Manuel explicou para fazer
//	
//	@DELETE
//	@Path("{prodId}/shelves/{shelfId}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.TEXT_PLAIN)
//	public Response removeShelfInProduct(@PathParam("prodId") long id, @PathParam("shelfId") long shelfId)
//
//	{
//
//		try {
//			service.removeShelfInProduct(id, shelfId);
//			return Response.ok().build(); // Devolve tudo bem quando consegue
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			return Response.status(Response.Status.BAD_REQUEST).entity(e1.getMessage()).build();
//
//		}
//
//	}
	
}

	
	

