package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialWrapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialService {

    @Autowired
    private CredentialMapper credentialMapper;
    @Autowired
    private EncryptionService encryptionService;

    public List<CredentialWrapper> getAllCredentials(Integer userId) {
        return credentialMapper.getAllCredentials(userId).stream()
                .map(credential -> new CredentialWrapper(credential, decryptPassword(credential.getPassword(), credential.getKey())))
                .collect(Collectors.toList());
    }

    public void addCredential(Credentials credentials) {
        if (credentials.getCredentialid() != null) {
            credentialMapper.updateCredential(credentials);
        } else {
            credentialMapper.addCredential(credentials);
        }
    }

    public void deleteCredential(Integer credentialId) {
        credentialMapper.deleteCredential(credentialId);
    }

    public String getPasswordEncryptionKey(Integer credentialId) {
        if (credentialId != null) {
            return credentialMapper.getCredential(credentialId).getKey();
        } else {
            SecureRandom random = new SecureRandom();
            byte[] key = new byte[16];
            random.nextBytes(key);
            return Base64.getEncoder().encodeToString(key);
        }
    }

    public String encryptPassword(String password, String encodedKey) {
        return encryptionService.encryptValue(password, encodedKey);
    }

    public String decryptPassword(String password, String encodedKey) {
        return encryptionService.decryptValue(password, encodedKey);
    }
}
