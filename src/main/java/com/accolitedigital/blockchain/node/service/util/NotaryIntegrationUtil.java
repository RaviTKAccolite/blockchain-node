package com.accolitedigital.blockchain.node.service.util;

import com.accolitedigital.blockchain.node.service.impl.NotaryIntegrationService;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
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

  private final int DATA_LENGTH = 192;

  @Autowired
  private NotaryIntegrationService notaryIntegrationService;

  public String decrypt(String data, String keyString, String publicKey) throws Exception {
    SecretKey key = secretKeyGenerator(keyString);

    log.info("Started Decryption");
    byte[] dataInBytes = Base64.getDecoder().decode(data);
    Cipher decryptionCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    GCMParameterSpec spec = new GCMParameterSpec(DATA_LENGTH, generateIv(publicKey));
    decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
    byte[] decryptedBytes = decryptionCipher.doFinal(dataInBytes);
    return new String(decryptedBytes);
  }

  private SecretKey secretKeyGenerator(String keyString) throws Exception {
    byte[] encodedKey = keyString.getBytes(StandardCharsets.UTF_8);
    return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");

  }

  private byte[] generateIv(String publicKey) throws UnsupportedEncodingException {
    return Base64.getDecoder().decode(publicKey.getBytes("UTF-8"));
  }

}

