package com.emazon.api_user.application.handler;

import com.emazon.api_user.application.ResponseSuccess;
import com.emazon.api_user.application.dto.UserRequestDto;

public interface IUserHandler {

    ResponseSuccess saveUser(UserRequestDto userRequestDto);

}