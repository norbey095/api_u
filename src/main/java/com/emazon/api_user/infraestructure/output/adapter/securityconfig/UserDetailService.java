package com.emazon.api_user.infraestructure.output.adapter.securityconfig;

import com.emazon.api_user.infraestructure.output.entity.UserDetailsEntity;
import com.emazon.api_user.infraestructure.output.reposiroty.IUserRepository;
import com.emazon.api_user.infraestructure.output.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(UserDetailsEntity::new)
                .orElseThrow(() -> new UsernameNotFoundException(Constants.USER_NOT_FOUND_POINTS + username));
    }
}