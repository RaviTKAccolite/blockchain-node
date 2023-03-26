package com.example.demo.service.util;

import com.example.demo.config.AppConfig;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;

public class NotaryIntegrationUtil {

  private Cipher encryptionCipher;

  private final int DATA_LENGTH = 128;

  public String decrypt(String data, String keyString) throws Exception {
    SecretKey key = secretKeyGenerator(keyString);

    byte[] dataInBytes = Base64.getDecoder().decode(data);
    Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
    GCMParameterSpec spec = new GCMParameterSpec(DATA_LENGTH, encryptionCipher.getIV());
    decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
    byte[] decryptedBytes = decryptionCipher.doFinal(dataInBytes);
    return new String(decryptedBytes);
  }

  private SecretKey secretKeyGenerator(String keyString) throws Exception {
    byte[] encodedKey = Base64.getDecoder().decode(keyString);
    SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    return key;
  }
}
