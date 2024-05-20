package com.example.jlopezcustomersupport.site;

import com.example.jlopezcustomersupport.entities.UserPrincipal;
public interface AuthenticationService {
    UserPrincipal authenticate(String username, String password);
    void saveUser(UserPrincipal principal, String newPassword);
}
