package com.orioninc.ProjectRestaurants;

import io.jsonwebtoken.Jwts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

// JJWT 2. This class is used for security key generation

public class JwtSecretMaker {

  private static final Logger logger = LogManager.getLogger(JwtSecretMaker.class);

  @Test
  public void generateSecretKey() {
    SecretKey secretKey = Jwts.SIG.HS512.key().build();   // Secret Key generate
    String encodedKey = DatatypeConverter.printHexBinary(secretKey.getEncoded()); // Converts an array of bytes into String
    logger.info("Secret Key" + encodedKey);
  }
}
