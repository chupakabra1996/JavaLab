package ru.kpfu.itis.javalab.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.kpfu.itis.javalab.model.Contact;
import ru.kpfu.itis.javalab.service.ContactService;

import java.util.List;


@RestController
@RequestMapping("/api")
public class RESTController {

    private ContactService contactService;

    private static final Logger log  = LogManager.getLogger(RESTController.class);

    //Inject service through setter, it's not safe and convenient to test field injection
    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.GET)
    public ResponseEntity<Contact> getContact(@PathVariable Long id) {

        log.debug("DEBUG");

        log.info("INFO");

        log.error("ERROR");


        if (id == null || !contactService.isExist(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(contactService.getContactById(id), HttpStatus.OK);
    }


    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ResponseEntity<List<Contact>> getAllContacts() {

        List<Contact> items = contactService.getAll();

        if (items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(contactService.getAll(), HttpStatus.OK);
    }


    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact, UriComponentsBuilder builder) {

        contact = contactService.saveContact(contact);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/contact/{id}").buildAndExpand(contact.getId()).toUri());

        return new ResponseEntity<>(contact, headers, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/contact/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact item) {

        Contact currentItem = contactService.getContactById(id);

        if (currentItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(contactService.updateContact(currentItem, item), HttpStatus.OK);
    }


    @RequestMapping(value = "/contact/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteContact(@PathVariable("id") Long id) {

        if (!contactService.isExist(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        contactService.deleteContactById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/contact", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllContacts() {

        contactService.deleteAllContacts();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
