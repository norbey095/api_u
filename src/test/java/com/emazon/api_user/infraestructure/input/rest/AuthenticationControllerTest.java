package com.emazon.api_user.infraestructure.input.rest;

import com.emazon.api_user.application.authentication.AuthenticationRequest;
import com.emazon.api_user.application.authentication.AuthenticationResponse;
import com.emazon.api_user.infraestructure.output.adapter.securityconfig.AuthenticationService;
import com.emazon.api_user.infraestructure.output.adapter.securityconfig.jwtconfiguration.JwtService;
import com.emazon.api_user.infraestructure.util.Constans;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private JwtService jwtService;

    @Test
    @WithMockUser(username = Constans.USER_NAME, roles = {Constans.ADMIN})
    void createUser_ShouldReturnStatusCreated() throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(Constans.ROL_DESCRIPTION);

        Mockito.when(authenticationService.authenticate(authenticationRequest))
                .thenReturn(authenticationResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(Constans.URL_AUTHENTICATION)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Constans.JSON_REQUEST))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}