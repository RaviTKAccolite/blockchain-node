package com.accolitedigital.blockchain.node.service.util;

import com.accolitedigital.blockchain.node.service.impl.NotaryIntegrationService;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotaryIntegrationUtil {

  static Logger log = Logger.getLogger(NotaryIntegrationUtil.class.getName());

  private Cipher encryptionCipher;

  private final int DATA_LENGTH = 128;

  @Autowired
  private NotaryIntegrationService notaryIntegrationService;

  public String decrypt(String data, String keyString) throws Exception {
    SecretKey key = secretKeyGenerator(keyString);

    log.info("Started Decryption");
    byte[] dataInBytes = Base64.getDecoder().decode(data);
    Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
    GCMParameterSpec spec = new GCMParameterSpec(DATA_LENGTH, encryptionCipher.getIV());
    decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
    byte[] decryptedBytes = decryptionCipher.doFinal(dataInBytes);
    return new String(decryptedBytes);
  }

  private SecretKey secretKeyGenerator(String keyString) throws Exception {
    byte[] encodedKey = Base64.getDecoder().decode(keyString);
    return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");

  }

}

