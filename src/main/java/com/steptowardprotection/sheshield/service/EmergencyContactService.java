package com.steptowardprotection.sheshield.service;

import com.steptowardprotection.sheshield.model.EmergencyContact;
import com.steptowardprotection.sheshield.repository.EmergencyContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmergencyContactService {

    private final EmergencyContactRepository contactRepository;

    public EmergencyContact saveContact(EmergencyContact contact) {
        return contactRepository.save(contact);
    }

    public List<EmergencyContact> getContactsByUserId(Long userId) {
        return contactRepository.findByUserId(userId);
    }
}
