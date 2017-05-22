package ir.rendan.util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Amir Shams on 5/22/2017.
 */
public class StringGenerator {

    private static SecureRandom random = new SecureRandom();

    public static String generateValidationCode() {
        return new BigInteger(130, random).toString(32);
    }

}
