package com.erikalves.application.x509certificate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.Enumeration;

public class X509Certificate {


    public static void main(String[] args) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {


            File file = new File("C:\\Users\\Erik_Pereira_Alves\\IdeaProjects\\server.keystore");
            FileInputStream is = new FileInputStream(file);
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            String password = "27uece";
            keystore.load(is, password.toCharArray());
            Enumeration enumeration = keystore.aliases();
            while(enumeration.hasMoreElements()) {
                String alias = (String)enumeration.nextElement();
                Certificate certificate = keystore.getCertificate(alias);
                PublicKey publicKey = keystore.getCertificate(alias).getPublicKey();
                byte[] encodedCertKey = certificate.getEncoded();
                byte[] encodedPublicKey = publicKey.getEncoded();
                String b64PublicKey = Base64.getMimeEncoder().encodeToString(encodedPublicKey);
                String b64CertKey = Base64.getMimeEncoder().encodeToString(encodedCertKey);
                String publicKeyString = "-----BEGIN CERTIFICATE-----\n"
                        + b64PublicKey
                        + "\n-----END CERTIFICATE-----";

                String certKeyString = "-----BEGIN CERTIFICATE-----\n"
                        + b64CertKey
                        + "\n-----END CERTIFICATE-----";

                System.out.println("THE PUBLIC KEY IS: ");
                System.out.println(publicKeyString);

                System.out.println("THE CERTIFICATE KEY IS: ");
                System.out.println(certKeyString);
            }


    }

}
