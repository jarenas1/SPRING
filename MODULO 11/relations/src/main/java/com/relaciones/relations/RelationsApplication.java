package com.relaciones.relations;

import com.relaciones.relations.entities.AdressEntity;
import com.relaciones.relations.entities.ClientEntity;
import com.relaciones.relations.entities.InvoiceEntity;
import com.relaciones.relations.repositories.ClientRepository;
import com.relaciones.relations.repositories.InvoiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

@SpringBootApplication
public class RelationsApplication implements CommandLineRunner {


	//INYECTAMOS DEPENDENCIAS PARA ACCEDER A SUS METODOS
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(RelationsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		manyToOne();
//		manyToOneFindClient();
//		oneToMany();
//		createById();
//		removeAdress();
		removeAdressCreated();
	}

	@Transactional
	public void removeAdressCreated() {
		Optional<ClientEntity> clientEntity = clientRepository.findById(1L);

		clientEntity.ifPresentOrElse(clientEntity1 -> {
			//creamos la direccion a añadir
			AdressEntity adressEntity = new AdressEntity(778654, "boff");
			//COMO ES UN OBJETO QUE VIENE DESDE LA PERSISTENCIA LA AÑADIMOS ASI:
//			clientEntity1.getAdresses().add(adressEntity);
			clientEntity1.setAdresses(Arrays.asList(adressEntity));
			System.out.println("added");
			//GUARDAMOS
			clientRepository.save(clientEntity1);

			Optional<ClientEntity> optionalClient = clientRepository.findById(1L);

			//verificar si lo trajo
			optionalClient.ifPresentOrElse(clientEntity2 -> {
				//ELIMINAR DE LA LISTA
				//RECORDAR AÑADIR EL EQUALS EN LA CLASE
				clientEntity2.getAdresses().remove(adressEntity);

				//ACTUALIZAR
				clientRepository.save(clientEntity2);
				System.out.println(clientEntity2);
			},()->{});
		}, () -> {
			System.out.println("THE ADRESS CANOT BE ADDED");
		});
	}
	@Transactional
	public void removeAdress(){
		ClientEntity clientEntity = new ClientEntity("Juancho", "Gaviria");

		//Creacion de las direcciones que se le asignaran al cliente
		AdressEntity adressEntity1 = new AdressEntity(123, "El rincon");
		AdressEntity adressEntity2 = new AdressEntity(543, "La mota");
		AdressEntity adressEntity3 = new AdressEntity(88483, "Belen");

		//PARA AÑADIR PRIMERO DEBEMOS HACER UN GET, PARA QUE SE NOS TRAIGA LA LISTA Y LUEGO HACEMOS EL ADD
		clientEntity.getAdresses().add(adressEntity1);
		clientEntity.getAdresses().add(adressEntity2);
		clientEntity.getAdresses().add(adressEntity3);

		//PERSISTIMOS, GRACIAS AL CASCADE, LAS DIRECCIONES SE CREAN AUTOMATICAMENTE, NO TOCA CREARLAS PRIMERO
		clientRepository.save(clientEntity);

		System.out.println(clientEntity);

		//TRAEL CLIENTE POR ID PARA ELIMINAR DIRECCION:

		Optional<ClientEntity> optionalClient = clientRepository.findById(3L);

		//verificar si lo trajo
		optionalClient.ifPresentOrElse(clientEntity1 -> {
			//ELIMINAR DE LA LISTA
			//RECORDAR AÑADIR EL EQUALS EN LA CLASE
			clientEntity1.getAdresses().remove(adressEntity1);

			//ACTUALIZAR
			clientRepository.save(clientEntity1);
			System.out.println(clientEntity1);
		},()->{});
	}


	public void manyToOne(){
		ClientEntity clientEntity = new ClientEntity("Juan", "Arenas");
		clientRepository.save(clientEntity);
		System.out.println("created client");

		InvoiceEntity invoiceEntity = new InvoiceEntity("Compras casa", 25000L);
		//ASIGNAMOS CLIENTE
		invoiceEntity.setClientEntity(clientEntity);
		InvoiceEntity invoiceEntityDB = invoiceRepository.save(invoiceEntity);
		System.out.println(invoiceEntityDB);
	}

	public void manyToOneFindClient(){
		//TRAEMOS AL CLIENTE
		Optional<ClientEntity> clientEntity = clientRepository.findById(1L);

		//VERIFICAMOS SU EXISTENCIA
		clientEntity.ifPresentOrElse(
				clientEntity1 -> {
					ClientEntity optionalClient = clientEntity1;

					InvoiceEntity invoiceEntity = new InvoiceEntity("Compras casa", 25000L);
					//ASIGNAMOS CLIENTE
					invoiceEntity.setClientEntity(optionalClient);
					InvoiceEntity invoiceEntityDB = invoiceRepository.save(invoiceEntity);
					System.out.println(invoiceEntityDB);
				},
				() -> {
					System.out.println("aaaaa");
				}
		);
	}
	@Transactional
	public void oneToMany(){
		ClientEntity clientEntity = new ClientEntity("Juancho", "Gaviria");

		//Creacion de las direcciones que se le asignaran al cliente
		AdressEntity adressEntity1 = new AdressEntity(123, "El rincon");
		AdressEntity adressEntity2 = new AdressEntity(543, "La mota");
		AdressEntity adressEntity3 = new AdressEntity(88483, "Belen");

		//PARA AÑADIR PRIMERO DEBEMOS HACER UN GET, PARA QUE SE NOS TRAIGA LA LISTA Y LUEGO HACEMOS EL ADD
		clientEntity.getAdresses().add(adressEntity1);
		clientEntity.getAdresses().add(adressEntity2);
		clientEntity.getAdresses().add(adressEntity3);

		//PERSISTIMOS, GRACIAS AL CASCADE, LAS DIRECCIONES SE CREAN AUTOMATICAMENTE, NO TOCA CREARLAS PRIMERO
		clientRepository.save(clientEntity);

		System.out.println(clientEntity);
	}
	@Transactional
	public void createById(){
		Optional<ClientEntity> clientEntity = clientRepository.findById(1L);

		clientEntity.ifPresentOrElse(clientEntity1 -> {
			//creamos la direccion a añadir
			AdressEntity adressEntity = new AdressEntity(777, "brrr");
			//COMO ES UN OBJETO QUE VIENE DESDE LA PERSISTENCIA LA AÑADIMOS ASI:
//			clientEntity1.getAdresses().add(adressEntity);
			clientEntity1.setAdresses(Arrays.asList(adressEntity));
			System.out.println("added");
			//GUARDAMOS
			clientRepository.save(clientEntity1);
		}, ()->{
			System.out.println("THE ADRESS CANOT BE ADDED");
		});

	}
}
