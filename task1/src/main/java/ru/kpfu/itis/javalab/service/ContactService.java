package ru.kpfu.itis.javalab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.javalab.model.Contact;
import ru.kpfu.itis.javalab.repo.ContactRepository;

import java.util.List;

@Service
public class ContactService {

    private ContactRepository contactRepository;

    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact getContactById(Long id) {
        return contactRepository.findOne(id);
    }

    public List<Contact> getAll() {
        return contactRepository.findAll();
    }


    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }


    public void deleteContactById(Long id) {
        contactRepository.delete(id);
    }


    public Contact updateContact(Contact oldItem, Contact newItem) {
        newItem.setId(oldItem.getId());
        return contactRepository.save(newItem);
    }

    public void deleteAllContacts() {

        contactRepository.deleteAll();
    }

    public boolean isExist(Long id) {
        return contactRepository.exists(id);
    }


}

