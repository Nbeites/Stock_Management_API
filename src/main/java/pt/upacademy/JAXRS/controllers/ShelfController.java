package pt.upacademy.JAXRS.controllers;

import java.util.Collection;
import java.util.List;

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
import pt.upacademy.JAXRS.repositories.ShelfRepository;
import pt.upacademy.JAXRS.services.ShelfService;

@Path("shelves")
public class ShelfController extends EntityController<ShelfService, ShelfRepository, Shelf> {



	// Aqui define-se um query parameter para fazer o filtro,
	// poe-se no ProductService porque trata-se de uma funcionalidade
	// exclusiva do servico de produtos
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)

	// Basicamente se nao levar um ?prodId = ... no endpoint, executa o if (default é prodID = 0), se nao executa o else,
	// ou seja vai buscar todas as shelves onde está o prodId que foi metido no endpoint

	public Response getShelvesByProdId(@QueryParam("prodId") long prodId)

	{
		if(prodId == 0) {
			return Response.status(200).entity(service.showAllEntities()).build();
		} else {
			return Response.status(200).entity(service.getShelvesByProdId(prodId)).build();
		}
			
	}
	
	
	@PUT
	@Path("{id}/product")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeProdInShelf(@PathParam("id") long id, Long prodId) {
		// A informação que vai no JSON em string está a ir para o shelfID , VER O DELETE
		// ABAIXO para ver como se faz
		
		try {
			service.changeProdInShelf(id, prodId);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		
	}
	
	
	/// ATENCAO IMP ///
	
	/// Este put que chama o changeProdInShelf NAO esta bem feito, 
	/// a informacao do prodId DEVE IR NO ENDPOINT (VER EXEMPLO DO DELETE ABAIXO)
	/// FICA AQUI A TITULO DE UM MAU EXEMPLO
	
//	

//	
//	
//	/// ATENCAO IMP ///
//	
//	/// Este delete que chama o removeProductInShelf está bem feito, 
//	/// com a informacao toda no endpoint como o Manuel explicou para fazer
//	
//	@DELETE
//	@Path("{shelfId}/product/{prodId}")
//	@Produces(MediaType.TEXT_PLAIN)
//	public Response removeProductInShelf(@PathParam("shelfId") long shelfId, @PathParam("prodId") long prodId)
//
//	{
//
//		try {
//			service.deleteProdInShelf(shelfId, prodId);
//			return Response.ok().build(); // Devolve tudo bem quando consegue
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			return Response.status(Response.Status.BAD_REQUEST).entity(e1.getMessage()).build();
//
//		}
//
//	}
	
	
	

}
