package utils;

import java.util.Random;

//Random Username and Passcode Generation:
//This logic is a utility function and should be placed in a Utils class, like TestUtils.java under a utils package. 
//This will allow you to reuse the random generation logic across multiple tests or pages.

public class TestUtils {

	public static String generateRandomUsername() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder username = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++)
        {
            username.append(chars.charAt(random.nextInt(chars.length())));
        }
        return username.toString();
    }

    public static String generateRandomPasscode()
    {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000)); // Ensures exactly 6 digits
    }

}
