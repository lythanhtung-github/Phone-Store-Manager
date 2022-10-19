package utils;

import java.util.regex.Pattern;

public class ValidateUtils {
    private static final String IMAGE_PATTERN ="(https?:\\/\\/.*\\.(?:png|jpg|jpeg))";
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9.]*[A-Za-z0-9]+@[A-Za-z0-9]+(.[A-Za-z0-9]+)$";
    private  static final String PHONE_PATTERN = "^0[1-9][0-9]{8}$";
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-_!@#&()[{}]:;',?/*~$^+=<>\\.]).{8,20}$";

    public static boolean isImageValid(String image) {
        return Pattern.compile(IMAGE_PATTERN).matcher(image).matches();
    }

    public static boolean isEmailValid(String email) {
        return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
    }

    public static boolean isPhoneValid(String phone) {
        return Pattern.compile(PHONE_PATTERN).matcher(phone).matches();
    }
    public static boolean isPasswordValid(String password) {
        return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }
}
