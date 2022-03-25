package com.example.myapplication.ui.psdbook;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DES {
    // 加密
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String desEncrypt(String orign, byte[] keyData) {

        byte[] data = orign.getBytes();
        byte[] encrypt = des(Cipher.ENCRYPT_MODE, data, keyData);
        return Base64.getEncoder().encodeToString(encrypt);
    }

    // 解密
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String desDecrypt(String orign, byte[] keyData) {

        byte[] data = orign.getBytes();
        byte[] encrypt = Base64.getDecoder().decode(data);
        String result = new String(des(Cipher.DECRYPT_MODE, encrypt, keyData));
        return result;
    }

    //DES算法
    public static byte[] des(int mode, byte[] data, byte[] keyData) {
        byte[] ret = null;
        //加密的内容存在并且密钥存在且长度为8个字节
        if (data != null
                && data.length > 0
                && keyData != null
                && keyData.length == 8) {
            try {
                Cipher cipher = Cipher.getInstance("DES");
                DESKeySpec keySpec = new DESKeySpec(keyData);
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                SecretKey key = keyFactory.generateSecret(keySpec);
                cipher.init(mode, key);
                ret = cipher.doFinal(data);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
