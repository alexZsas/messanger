package com.messanger.auth.common.security.service.key;

import org.springframework.util.Base64Utils;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public interface KeyPairProvider {
    KeyPair get();

    default String getPublicKeyString() {
        PublicKey key = get().getPublic();
        return "-----BEGIN PUBLIC KEY-----\n" + new String(Base64Utils.encode(key.getEncoded()))
                + "\n-----END PUBLIC KEY-----";
    }

    default PublicKey getPublicKey() {
        return get().getPublic();
    }

    default PrivateKey getPrivateKey() {
        return get().getPrivate();
    }
}
