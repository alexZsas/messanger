package com.messanger.auth.common.security.service.key;

import lombok.SneakyThrows;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.concurrent.TimeUnit;

public class DefaultKeyPairProvider implements KeyPairProvider {
    private static final String ALGORITHM = "RSA";
    private static final int KEY_SIZE = 2048;
    private static final String KEY_FILENAME = "generated_key";
    private static final String PUBLIC_KEY_FILENAME = KEY_FILENAME + ".pub";
    private static final String PRIVATE_KEY_FILENAME = KEY_FILENAME + ".key";

    private final File storeDirectory;
    private final TimeUnit timeUnit;
    private final long amount;
    private final boolean expirable;

    private volatile long expirationMillis;
    private volatile KeyPair keyPair;

    public DefaultKeyPairProvider() {
        this(System.getProperty("java.io.tmpdir"), TimeUnit.DAYS, 2);
    }

    public DefaultKeyPairProvider(TimeUnit timeUnit, long amount) {
        this(System.getProperty("java.io.tmpdir"), timeUnit, amount);
    }

    public DefaultKeyPairProvider(String directory, TimeUnit timeUnit, long amount) {
        this.storeDirectory = Paths.get(directory).toFile();
        this.timeUnit = timeUnit;
        this.amount = amount;
        this.expirable = timeUnit != null && amount > 0;
        this.expirationMillis = computeNextExpirationDate();
    }

    @Override
    public synchronized KeyPair get() {
        File publicKeyFile = getKeyFile(storeDirectory, PUBLIC_KEY_FILENAME);
        File privateKeyFile = getKeyFile(storeDirectory, PRIVATE_KEY_FILENAME);

        if (exists(publicKeyFile, privateKeyFile) && notExpired(publicKeyFile, privateKeyFile)) {
            if (keyPair == null) {
                try {
                    keyPair = loadKeyPair(publicKeyFile, privateKeyFile);
                } catch (IOException | InvalidKeySpecException e) {
                    keyPair = generate(publicKeyFile, privateKeyFile);
                }
            }
        } else {
            keyPair = generate(publicKeyFile, privateKeyFile);
            expirationMillis = computeNextExpirationDate();
        }

        return keyPair;
    }

    private File getKeyFile(File parent, String notEncodedName) {
        return new File(parent, Base64Utils.encodeToString(notEncodedName.getBytes()));
    }

    private boolean exists(File publicKeyFile, File privateKeyFile) {
        return publicKeyFile.exists() && privateKeyFile.exists();
    }

    private boolean notExpired(File publicKeyFile, File privateKeyFile) {
        if (expirable) {
            return expirationMillis > 0
                    && expirationMillis > publicKeyFile.lastModified()
                    && expirationMillis > privateKeyFile.lastModified();
        } else {
            return true;
        }
    }

    @SneakyThrows(NoSuchAlgorithmException.class)
    private KeyPair loadKeyPair(File publicKeyFile, File privateKeyFile) throws IOException, InvalidKeySpecException {
        byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
        byte[] privateKeyBytes = Files.readAllBytes(privateKeyFile.toPath());

        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

        KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
        return new KeyPair(factory.generatePublic(publicKeySpec), factory.generatePrivate(privateKeySpec));
    }

    @SneakyThrows(NoSuchAlgorithmException.class)
    private KeyPair generate(File publicKeyFile, File privateKeyFile) {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);

        generator.initialize(KEY_SIZE);
        KeyPair keyPair = generator.generateKeyPair();

        write(publicKeyFile, keyPair.getPublic());
        write(privateKeyFile, keyPair.getPrivate());

        return keyPair;
    }

    @SneakyThrows
    private void write(File keyFile, Key key) {
        if (keyFile.createNewFile()) {
            Files.write(keyFile.toPath(), key.getEncoded());
        }
    }

    private long computeNextExpirationDate() {
        if (expirable) {
            long previousExpirationMillis = expirationMillis <= 0 ? lastModified() : expirationMillis;

            long millis = timeUnit.toMillis(amount);
            return previousExpirationMillis + millis;
        }

        return -1;
    }

    private long lastModified() {
        File keyFile = getKeyFile(storeDirectory, PUBLIC_KEY_FILENAME);
        if (keyFile.exists()) {
            return keyFile.lastModified();
        } else {
            return System.currentTimeMillis();
        }
    }
}
