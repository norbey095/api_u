package com.emazon.api_user.infraestructure.output.jpa.adapter;

import com.emazon.api_user.domain.model.UserSave;
import com.emazon.api_user.domain.spi.IUserPersistencePort;
import com.emazon.api_user.infraestructure.output.jpa.mapper.UserEntityMapper;
import com.emazon.api_user.infraestructure.output.jpa.reposiroty.IUserRepository;
import com.emazon.api_user.infraestructure.output.jpa.util.PasswordUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void saveUser(UserSave userSave) {
        userRepository.save(userEntityMapper.userToUserEntity(userSave));
    }

    @Override
    public boolean getUserByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public String encryptedPassword(String password){
        return PasswordUtil.encryptPassword(password);
    }
}
