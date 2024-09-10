package com.emazon.api_user.infraestructure.output.jpa.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public static String encryptPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
