package com.emazon.api_user.apilication.mapper;

import com.emazon.api_user.apilication.util.ConstantsApplicationTest;
import com.emazon.api_user.application.dto.UserRequestDto;
import com.emazon.api_user.application.mapper.UserMapper;
import com.emazon.api_user.domain.model.UserSave;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void testUserRequestDtoToUserSave() {
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .name(ConstantsApplicationTest.NAME)
                .lastName(ConstantsApplicationTest.LAST_NAME)
                .documentNumber(ConstantsApplicationTest.DOCUMENT)
                .cellPhone(ConstantsApplicationTest.CELLPHONE)
                .birthdate(ConstantsApplicationTest.BIRTHDATE)
                .email(ConstantsApplicationTest.EMAIL)
                .password(ConstantsApplicationTest.PASSWORD)
                .build();

        UserSave userTestSave = userMapper.userRequestDtoToUserSave(userRequestDto);

        assertNotNull(userTestSave);
        assertEquals(userRequestDto.getName(),userTestSave.getName());
        assertEquals(userRequestDto.getLastName(),userTestSave.getLastName());
        assertEquals(userRequestDto.getDocumentNumber(), userTestSave.getDocumentNumber());
        assertEquals(userRequestDto.getCellPhone(), userTestSave.getCellPhone());
        assertEquals(userRequestDto.getBirthdate(), userTestSave.getBirthdate());
        assertEquals(userRequestDto.getEmail(), userTestSave.getEmail());
        assertEquals(userRequestDto.getPassword(), userTestSave.getPassword());

    }

    @Test
    void testUserRequestDtoToUserSaveMap() {
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .name(ConstantsApplicationTest.NAME)
                .lastName(ConstantsApplicationTest.LAST_NAME)
                .documentNumber(ConstantsApplicationTest.DOCUMENT)
                .cellPhone(ConstantsApplicationTest.CELLPHONE)
                .birthdate(ConstantsApplicationTest.BIRTHDATE)
                .email(ConstantsApplicationTest.EMAIL)
                .password(ConstantsApplicationTest.PASSWORD)
                .build();

        UserSave userTestSave = userMapper.map(userRequestDto);

        assertNotNull(userTestSave);
        assertEquals(userRequestDto.getName(),userTestSave.getName());
        assertEquals(userRequestDto.getLastName(),userTestSave.getLastName());
        assertEquals(userRequestDto.getDocumentNumber(), userTestSave.getDocumentNumber());
        assertEquals(userRequestDto.getCellPhone(), userTestSave.getCellPhone());
        assertEquals(userRequestDto.getBirthdate(), userTestSave.getBirthdate());
        assertEquals(userRequestDto.getEmail(), userTestSave.getEmail());
        assertEquals(userRequestDto.getPassword(), userTestSave.getPassword());

    }


}
