package com.emazon.api_user.domain.usecase;

import com.emazon.api_user.domain.api.IUserServicePort;
import com.emazon.api_user.domain.exception.MinorInvalidException;
import com.emazon.api_user.domain.exception.PhoneNumberinvalidException;
import com.emazon.api_user.domain.exception.UserAlreadyExistsException;
import com.emazon.api_user.domain.exception.UserEmailInvalidException;
import com.emazon.api_user.domain.model.UserSave;
import com.emazon.api_user.domain.spi.IUserPersistencePort;
import com.emazon.api_user.domain.util.Constans;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUser(UserSave userSave) {
        validateEmail(userSave.getEmail());
        validPhoneNumber(userSave.getCellPhone());
        validationYear(userSave.getBirthdate());
        validatedEmailPresent(userSave.getEmail());
        userPersistencePort.saveUser(userSave);
    }

    private void validateEmail(String email){
        Matcher matcher = Constans.EMAIL_PATTERN.matcher(email);

        if (!matcher.matches()) {
            throw new UserEmailInvalidException();
        }
    }

    public void validPhoneNumber(String phoneNumber) {
        Matcher matcher = Constans.PHONE_PATTERN.matcher(phoneNumber);

        if (!matcher.matches()) {
            throw new PhoneNumberinvalidException();
        }
    }

    public void validationYear(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        int age = Period.between(birthDate, today).getYears();

        if(age < 18){
            throw new MinorInvalidException();
        }
    }

    protected void validatedEmailPresent(String email){
        if(this.userPersistencePort.getUserByEmail(email)) {
            throw new UserAlreadyExistsException();
        }
    }


}
