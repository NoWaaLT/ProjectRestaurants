package com.orioninc.ProjectRestaurants;

import com.orioninc.ProjectRestaurants.permission.CustomPermissionEvaluator;
import io.jsonwebtoken.Jwts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

//      This class used for security key generation

public class JwtSecretMaker {

    private static final Logger logger = LogManager.getLogger(JwtSecretMaker.class);

    @Test
    public void generateSecretKey() {
        SecretKey secretKey = Jwts.SIG.HS512.key().build();     //
        String encodedKey = DatatypeConverter.printHexBinary(secretKey.getEncoded());
        logger.info("Secret Key" + encodedKey);

    }

}
