package com.relaciones.relations;

import com.relaciones.relations.entities.ClientEntity;
import com.relaciones.relations.entities.InvoiceEntity;
import com.relaciones.relations.repositories.ClientRepository;
import com.relaciones.relations.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		manyToOneFindClient();
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
}
