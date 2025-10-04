package com.steptowardprotection.sheshield.controller;

import com.steptowardprotection.sheshield.dto.EmergencyContactRequest;
import com.steptowardprotection.sheshield.model.EmergencyContact;
import com.steptowardprotection.sheshield.model.User;
import com.steptowardprotection.sheshield.service.EmergencyContactService;
import com.steptowardprotection.sheshield.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class EmergencyContactController {

    private final EmergencyContactService contactService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> addContact(@RequestBody EmergencyContactRequest req) {
        // 1) extract userId from either userId or nested user.id
        Long userId = req.getUserId();
        if (userId == null && req.getUser() != null) {
            userId = req.getUser().getId();
        }

        // 2) validate
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("userId is required (provide userId or user:{id:...})");
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with id: " + userId);
        }

        // 3) create and save contact
        EmergencyContact contact = new EmergencyContact();
        contact.setName(req.getName());
        contact.setPhone(req.getPhone());
        contact.setEmail(req.getEmail());
        contact.setUser(user);

        EmergencyContact saved = contactService.saveContact(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EmergencyContact>> getContacts(@PathVariable Long userId) {
        return ResponseEntity.ok(contactService.getContactsByUserId(userId));
    }
}
