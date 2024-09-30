package com.example.factura.controllers;

import com.example.factura.entities.ClientEntity;
import com.example.factura.entities.InvoiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invoices")
public class InvoiceController {

    @Autowired
    private InvoiceEntity invoiceEntity; //INYECTAMOS UNA FACTURA

//    @GetMapping("/show")
//    public InvoiceEntity show(){
//        return invoiceEntity;
//    }

    //METODO MODIFICADO PARA MANEJAR LOS ERRORES DEL LOS SCOPES Y EL JSON

    @GetMapping("/show")
    public InvoiceEntity show(){
        InvoiceEntity i = new InvoiceEntity();
        //---------------------------------------------------------------
        //i.setClientEntity(invoiceEntity.getClientEntity()); NO SE PUEDE DEVOLVER ASÍ DEVIDO A SU SCOPE
        ClientEntity c = new ClientEntity();
        c.setName(invoiceEntity.getClientEntity().getName());
        c.setLastname(invoiceEntity.getClientEntity().getLastname());
        //---------------------------------------------------------------
        i.setClientEntity(c);
        i.setDescription(invoiceEntity.getDescription());
        i.setItems(invoiceEntity.getItems());
        return i;
    }
}
