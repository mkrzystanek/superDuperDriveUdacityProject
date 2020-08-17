package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    @Autowired
    private CredentialMapper credentialMapper;

    public List<Credentials> getAllCredentials(Integer userId) {
        return credentialMapper.getAllCredentials(userId);
    }

    public void addCredential(Credentials credentials) {
        if (credentials.getCredentialid() != null) {
            credentialMapper.updateCredential(credentials);
        } else {
            credentialMapper.addCredential(credentials);
        }
    }
}
