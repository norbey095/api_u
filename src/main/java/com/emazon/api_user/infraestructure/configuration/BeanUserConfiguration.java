package com.emazon.api_user.infraestructure.configuration;

import com.emazon.api_user.domain.api.IUserServicePort;
import com.emazon.api_user.domain.spi.IUserPersistencePort;
import com.emazon.api_user.domain.usecase.UserUseCase;
import com.emazon.api_user.infraestructure.output.jpa.adapter.UserJpaAdapter;
import com.emazon.api_user.infraestructure.output.jpa.mapper.UserEntityMapper;
import com.emazon.api_user.infraestructure.output.jpa.reposiroty.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanUserConfiguration {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository,userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort());
    }
}
