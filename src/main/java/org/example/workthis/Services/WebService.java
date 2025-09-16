package org.example.workthis.Services;

import org.example.workthis.Model.Client;
import org.example.workthis.Repo.WebRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebService {
    @Autowired
    WebRepo repo;

    public void saveData(Client client) {
        repo.save(client);
    }
}
