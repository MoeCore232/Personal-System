package com.example.personal_website.Core_System.Contact;

import com.example.personal_website.Shared.ErrorHandling.GlobalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController (ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/get-all-contacts")
    public ResponseEntity<GlobalResponse<List<Contact>>> getAllContacts () {
        List<Contact> contacts = contactService.getAllContacts();
        return new ResponseEntity<>(new GlobalResponse<>(contacts), HttpStatus.OK);
    }

    @PostMapping("/create-contact")
    public ResponseEntity<GlobalResponse<String>> createContact (@RequestBody ContactDto.CreateContact createContact) {
        String contact = contactService.createContact(createContact);
        return new ResponseEntity<>(new GlobalResponse<>(contact), HttpStatus.CREATED);
    }

    @PutMapping("/update-contact/{contactId}")
    public ResponseEntity<GlobalResponse<String>> updateContact (
            @PathVariable UUID contactId, @RequestBody ContactDto.UpdateContact updateContact)
    {
        String contact = contactService.updateContact(contactId, updateContact);
        return new ResponseEntity<>(new GlobalResponse<>(contact), HttpStatus.OK);
    }

    @DeleteMapping("/delete-contact/{contactId}")
    public ResponseEntity<GlobalResponse<String>> deleteContact (@PathVariable UUID contactId) {
        String contact = contactService.deleteContact(contactId);
        return new ResponseEntity<>(new GlobalResponse<>(contact), HttpStatus.OK);
    }

}
