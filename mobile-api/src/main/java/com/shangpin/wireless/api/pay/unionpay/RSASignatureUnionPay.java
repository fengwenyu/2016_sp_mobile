package com.shangpin.wireless.api.pay.unionpay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;

import org.bouncycastle.openssl.PEMReader;

public class RSASignatureUnionPay {

    
    /**
     * RSA加密
     * @param origin 原始串
     * @param test	是否测试环境
     * @return	加密后的串
     * @throws Exception
     */
    public static String SHA1withRSA (String origin, boolean test)
    		throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
    	
    	Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        PrivateKey priKey = null;
        InputStream is = RSASignatureUnionPay.class.getResourceAsStream("/unionpaykey/" + (test?"TESTMERCHANT.key":"shangpin.key"));
        Reader fRd = new BufferedReader(new InputStreamReader(is));
        PEMReader pemReader = new PEMReader(fRd);

        Object o;
        KeyPair pair;
        while ((o = pemReader.readObject()) != null) {
            if (o instanceof KeyPair) {
                pair = (KeyPair) o;
                priKey = pair.getPrivate();

            } else {
                if (o instanceof PrivateKey) {
                    priKey = (PrivateKey) o;
                }

            }
        }
       
        byte[] mesDigest;

        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initSign(priKey);
        sig.update(origin.getBytes("utf-8"));
        mesDigest = sig.sign();
        String hexArray = "0123456789abcdef";
        StringBuilder sb = new StringBuilder(mesDigest.length * 2);
        for (byte b : mesDigest) {
            int bi = b & 0xff;
            sb.append(hexArray.charAt(bi >> 4));
            sb.append(hexArray.charAt(bi & 0xf));
        }
    	return sb.toString();
    }
}
