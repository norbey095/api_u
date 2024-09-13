package com.emazon.api_user.infraestructure.input.rest;

import com.emazon.api_user.application.ResponseSuccess;
import com.emazon.api_user.application.dto.UserRequestDto;
import com.emazon.api_user.application.handler.IUserHandler;
import com.emazon.api_user.infraestructure.util.Constans;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRestControllerTest {

    @Mock
    private IUserHandler userHandler;

    @Autowired
    private ObjectMapper objectMapper;

    private UserRequestDto userRequestDto;

    @InjectMocks
    private UserRestController userRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userRequestDto = UserRequestDto.builder()
                .name(Constans.NAME)
                .lastName(Constans.LAST_NAME)
                .documentNumber(Constans.DOCUMENT)
                .cellPhone(Constans.CELLPHONE)
                .birthdate(Constans.BIRTHDATE)
                .email(Constans.EMAIL)
                .password(Constans.PASSWORD)
                .build();
    }

    @Test
    void createUser_ShouldReturnStatusCreated(){
        ResponseSuccess responseSuccess = new ResponseSuccess(Constans.MESSAGESS_SUCCESS);
        Mockito.when(userHandler.saveUser(Mockito.any(UserRequestDto.class)))
                .thenReturn(responseSuccess);

        ResponseEntity<ResponseSuccess> response = userRestController.createUser(userRequestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}