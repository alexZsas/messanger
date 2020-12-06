package com.messanger.auth.common.security.service;

import com.messanger.auth.common.security.service.key.KeyPairProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.jwt.crypto.sign.InvalidSignatureException;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.security.jwt.crypto.sign.Signer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.security.GeneralSecurityException;
import java.security.Signature;

@SuppressWarnings("deprecation")
public class ReloadingJwtAccessTokenConverter extends JwtAccessTokenConverter {
    private static final String DEFAULT_ALGORITHM = "SHA256withRSA";

    private final KeyPairProvider provider;

    public ReloadingJwtAccessTokenConverter(KeyPairProvider provider) {
        this.provider = provider;
        setVerifier(new ReloadingKeySignatureVerifier());
        setSigner(new ReloadingKeySigner());
        setVerifierKey(provider.getPublicKeyString());
    }

    @RequiredArgsConstructor
    protected class ReloadingKeySignatureVerifier implements SignatureVerifier {
        @Override
        public void verify(byte[] content, byte[] sig) {
            try {
                Signature signature = Signature.getInstance(DEFAULT_ALGORITHM);
                signature.initVerify(provider.getPublicKey());
                signature.update(content);

                if (!signature.verify(sig)) {
                    throw new InvalidSignatureException("RSA Signature did not match content");
                }
            }
            catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String algorithm() {
            return DEFAULT_ALGORITHM;
        }
    }

    @RequiredArgsConstructor
    protected class ReloadingKeySigner implements Signer {
        @Override
        public byte[] sign(byte[] bytes) {
            try {
                Signature signature = Signature.getInstance(DEFAULT_ALGORITHM);
                signature.initSign(provider.getPrivateKey());
                signature.update(bytes);
                return signature.sign();
            }
            catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String algorithm() {
            return DEFAULT_ALGORITHM;
        }
    }
}
