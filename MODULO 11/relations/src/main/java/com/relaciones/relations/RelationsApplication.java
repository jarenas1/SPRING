package com.relaciones.relations;

import com.relaciones.relations.entities.AdressEntity;
import com.relaciones.relations.entities.ClientDetails;
import com.relaciones.relations.entities.ClientEntity;
import com.relaciones.relations.entities.InvoiceEntity;
import com.relaciones.relations.repositories.ClientDetailsRepository;
import com.relaciones.relations.repositories.ClientRepository;
import com.relaciones.relations.repositories.InvoiceRepository;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;
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
	private ClientDetailsRepository clientDetailsRepository;

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
//		removeAdressCreated();
//		oneToManyInvoiceBidirectional();
//		oneToManyInvoiceBidirectionalDELETE();
//		OneToOne();
		OneToOneBiFind();
	}
		//================================0NE TO ONE ==========================================================
	@Transactional
	public void OneToOne(){
		//creamos cliente
		ClientEntity clientEntity = new ClientEntity("EJEMPLO", "ONETOONE");

		//creamos detalle
		ClientDetails clientDetails = new ClientDetails(100,true);

		clientDetails.setClientEntity(clientEntity);

		//guardamos el cliente y automaricamente se guarda el detail debido al cascade //SE GUARDA CLIENTE YA QUE ES LA CLASE PADRE
		clientRepository.save(clientEntity);

		System.out.println(clientEntity);


	}

	@Transactional
	public void OneToOneBi(){
		//creamos cliente
		ClientEntity clientEntity = new ClientEntity("EJEMPLO", "ONETOONE");
		//guardamos
		clientRepository.save(clientEntity);
		//creamos detalle
		ClientDetails clientDetails = new ClientDetails(100,true);
		clientDetails.setClientEntity(clientEntity);
		//GUARDAMOS
		clientDetailsRepository.save(clientDetails);
	}

	@Transactional
	public void OneToOneBiFind(){
		//buscamos al cliente
		Optional<ClientEntity> optionalClientEntity = clientRepository.findById(2L);

		//VERIFICAMOS
		optionalClientEntity.ifPresent(clientEntity -> {
			//creamos detalle
			ClientDetails clientDetails = new ClientDetails(3330,true);
			//asignamos al client
			clientEntity.setClientDetails(clientDetails);

			//guardamos
			clientRepository.save(clientEntity);

			System.out.println(clientEntity);
		});
	}



	//===========================================================================================

	@Transactional
	public void oneToManyInvoiceBidirectionalDELETE() {
		Optional<ClientEntity> optionalClient = clientRepository.findById(1L);

		optionalClient.ifPresentOrElse(clientEntity -> {
			//creamos las facturas
			InvoiceEntity invoiceEntity1 = new InvoiceEntity("factura 5555", 100L);
			InvoiceEntity invoiceEntity2 = new InvoiceEntity("factura 66666", 200L);

			//Asignamos factura al cliente
			// clientEntity.setInvoices(Arrays.asList(invoiceEntity1,invoiceEntity2)); //recordar que recibe una lista
			//Como es una relacion bidireccional, pasamos los datos al del cliente a la factura
			invoiceEntity1.setClientEntity(clientEntity);
			invoiceEntity2.setClientEntity(clientEntity);

			//Persisitimos, en este caso solo lo hacemos con el cliente debido a que el cascade guarda invoices automaticamente
			clientRepository.save(clientEntity);
			System.out.println(clientEntity);
		},()->{});
		//LLAMAMOS AL USUARIO PARA ELIMINAR FACTURAS
		Optional<ClientEntity> optionalClientDB = clientRepository.findById(1L);

		optionalClientDB.ifPresentOrElse(clientEntity -> {
			Optional<InvoiceEntity> optionalInvoiceEntity = invoiceRepository.findById(9L);
			optionalInvoiceEntity.ifPresent(invoiceEntity -> {
				clientEntity.getInvoices().remove(invoiceEntity);
				invoiceEntity.setClientEntity(null);

				clientRepository.save(clientEntity);
				System.out.println(clientEntity);
			});

		},()->{});


	}

	@Transactional
	public void oneToManyInvoiceBidirectionalFindId() {
		Optional<ClientEntity> optionalClient = clientRepository.findById(1L);

		optionalClient.ifPresentOrElse(clientEntity -> {
			//creamos las facturas
			InvoiceEntity invoiceEntity1 = new InvoiceEntity("factura 1", 100L);
			InvoiceEntity invoiceEntity2 = new InvoiceEntity("factura 2", 200L);

			//Asignamos factura al cliente
			clientEntity.setInvoices(Arrays.asList(invoiceEntity1,invoiceEntity2)); //recordar que recibe una lista
			//Como es una relacion bidireccional, pasamos los datos al del cliente a la factura
			invoiceEntity1.setClientEntity(clientEntity);
			invoiceEntity2.setClientEntity(clientEntity);

			//Persisitimos, en este caso solo lo hacemos con el cliente debido a que el cascade guarda invoices automaticamente
			clientRepository.save(clientEntity);
			System.out.println(clientEntity);
		},()->{});


	}

	@Transactional
	public void oneToManyInvoiceBidirectional() {
		ClientEntity clientEntity = new ClientEntity("Juancho", "Gaviria");

		//creamos las facturas
		InvoiceEntity invoiceEntity1 = new InvoiceEntity("factura 1", 100L);
		InvoiceEntity invoiceEntity2 = new InvoiceEntity("factura 2", 200L);

		//Asignamos factura al cliente
		clientEntity.setInvoices(Arrays.asList(invoiceEntity1,invoiceEntity2)); //recordar que recibe una lista
		//Como es una relacion bidireccional, pasamos los datos al del cliente a la factura
		invoiceEntity1.setClientEntity(clientEntity);
		invoiceEntity2.setClientEntity(clientEntity);

		//Persisitimos, en este caso solo lo hacemos con el cliente debido a que el cascade guarda invoices automaticamente
		clientRepository.save(clientEntity);
		System.out.println(clientEntity);
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
