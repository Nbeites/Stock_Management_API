package pt.upacademy.JAXRS.models;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entity_ implements Serializable{


		// Isto significa que o Id vai ser  gerado peal base de dados
		// e a strategy é a maneira como vao ser gerados os IDS, neste caso,
		// é para garantir que esta é a primary Key dos objetos

		//Metodo obrigatorio do serializable
		//Este metodo converte o objecto de java em binario, para colocar na base de dados
	
		//Este tipo de serializacao vem de default, podia ser 2L etc
	
	
		private static final long serialVersionUID = 1L;
	
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;


		public long getId() 
		
		{
			return id;
		}


		public void setId(long id) 
		
		{
			this.id = id;
		} 
		

	

}
