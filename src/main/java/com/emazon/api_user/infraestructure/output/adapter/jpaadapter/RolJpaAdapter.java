package com.emazon.api_user.infraestructure.output.adapter.jpaadapter;


import com.emazon.api_user.domain.model.RolSave;
import com.emazon.api_user.domain.spi.IRolPersistencePort;
import com.emazon.api_user.infraestructure.output.mapper.RolEntityMapper;
import com.emazon.api_user.infraestructure.output.reposiroty.IRolRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RolJpaAdapter implements IRolPersistencePort {

    private final IRolRepository rolRepository;
    private final RolEntityMapper rolEntityMapper;

    @Override
    public RolSave getRolByName(String name) {
        return rolEntityMapper.rolEntityToRolSave(rolRepository.getRolByName(name));
    }
}
