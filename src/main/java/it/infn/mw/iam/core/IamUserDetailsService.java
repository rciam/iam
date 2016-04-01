package it.infn.mw.iam.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import it.infn.mw.iam.persistence.model.Authority;
import it.infn.mw.iam.persistence.model.IamAccount;
import it.infn.mw.iam.persistence.repository.IamAccountRespository;

public class IamUserDetailsService implements UserDetailsService {

  @Autowired
  private IamAccountRespository repo;

  
  List<GrantedAuthority> convertAuthorities(IamAccount a){
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (Authority auth: a.getAuthorities()){
      authorities.add(new SimpleGrantedAuthority(auth.getAuthority()));
    }
    return authorities;
  }
  
  
  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {

    Optional<IamAccount> account = repo.findByUsername(username);
    if (account.isPresent()) {
      IamAccount a = account.get();
      
      User u = new User(a.getUsername(), a.getPassword(),
        convertAuthorities(a));
      
      return u;
    }

    return null;
  }

}
