package com.jpa.JPA;

import com.jpa.JPA.entities.PersonEntity;
import com.jpa.JPA.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner
{
	@Autowired //INYECTAMOS EL REPOSITORY PARA ACCEDER A SUS METODOS
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		update();
		//Metodo de traer todos los usuarios
		//List<PersonEntity> persons = personRepository.findByProgrammingLanguage("java");

//		for(PersonEntity p: persons){
//			System.out.println(p.toString());
//		}

//		//CREAR REGISTRO
//		public void create(){
//			PersonEntity person = new PersonEntity(null, "Perez", "PEDRO", "JAVA")
//			boolean createNewUser(person){
//			return true;
//		}
//		}




//		//METODO CON JPQL QUE BUSVA UN USUARIO POR MEDIO DE SU NOMBRE Y SU APELLIDO
//			 List<PersonEntity> pers =personRepository.personByNameQuery("Juan", "Arenas");
//
//			for(PersonEntity p: pers){
//				System.out.println(p.getName()+p.getLastname());
//
//		}
//	}

//		List<PersonEntity> persons = personRepository.findByProgrammingLanguageAndName("Java", "Juan");
//
//		for (PersonEntity p : persons) {
//			System.out.println(p);
//		}

		//se nos devuelve un lista de arrays, donde cada array es un regidtro y este contiene cada valor solicitado en su respetiva posicion
//		List<Object[]> dataPersons = personRepository.dataUser();
//
//		for(Object[] pe : dataPersons){
//			System.out.println("Lenguaje"+pe[1]+"nombre"+pe[0]); //ACCEDEMOS A CADA POSICION DEL ARRAY LA CUAL CONTIENE UN VALOR
//		}
	}
	//UPDATE
	public void update(){
		//BUSCAMOS CON EL OPTIONAL
		Optional<PersonEntity> personToUpdate = personRepository.findById(1L);
		//VERIFICAMOS SI TRAJO EL USUARIO y con un present entramos a modificarlo
		personToUpdate.ifPresent(personEntity -> {
			//COSA A MODIFICAR
			String lenguaje = "NextJS";
			System.out.println(personEntity.getProgrammingLanguage());
			//AÑADIMOS EL NUEVO LENGUAJE
			personEntity.setProgrammingLanguage(lenguaje);
			//Mandamos el registro para que se actualice
			personRepository.save(personEntity);
		});
	}
}
