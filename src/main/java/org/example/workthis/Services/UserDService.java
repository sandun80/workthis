package org.example.workthis.Services;

import org.example.workthis.Model.Client;
import org.example.workthis.Model.LoginClient;
import org.example.workthis.Repo.WebRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDService implements UserDetailsService {

    @Autowired
    WebRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = repo.findByEmail(username);
        if (client == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(client);
    }
}
