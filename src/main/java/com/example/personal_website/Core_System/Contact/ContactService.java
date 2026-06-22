package com.example.personal_website.Core_System.Contact;

import com.example.personal_website.Shared.ErrorHandling.CustomResponseException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactService {

    private final ContactRepo contactRepo;

    public ContactService (ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    public List<Contact> getAllContacts () {
        try {
            List<Contact> contacts = contactRepo.findAll();
            return contacts;
        } catch (Exception e) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

    public String createContact (ContactDto.CreateContact createContact) {
        try {
            Contact contact = Contact.createContact(createContact);
            contactRepo.save(contact);
            return "Contact created successfully!";
        } catch (Exception e) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

    public String updateContact (UUID contactId, ContactDto.UpdateContact updateContact) {
        Contact findContact = contactRepo.findById(contactId)
                .orElseThrow(() -> CustomResponseException.idIsNotFound(contactId));
        try {
            Contact newContact = Contact.updateContact(findContact, updateContact);
            contactRepo.save(newContact);
            return "Contact updated successfully!";
        } catch (Exception e) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

    public String deleteContact (UUID contactId) {
        Contact findContact = contactRepo.findById(contactId)
                .orElseThrow(() -> CustomResponseException.idIsNotFound(contactId));
        try {
            contactRepo.deleteById(findContact.getId());
            return "Contact deleted successfully!";
        } catch (Exception e) {
            throw CustomResponseException.unExpectedErrorOccurred();
        }
    }

}
