package com.example.jlopezcustomersupport.site;

import com.example.jlopezcustomersupport.entities.UserPrincipal;
public interface UserPrincipalRepository extends GenericRepository<Long, UserPrincipal> {
    UserPrincipal getByUsername(String username);
}
