package co.gov.ideam.sirh.seguridad.util;

import co.gov.ideam.sirh.util.IdeamException;

import java.security.InvalidKeyException;

import java.security.NoSuchAlgorithmException;

import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.HexDumpEncoder;


public class EncrypClass{
    //La clave para encripcion debe tener minimo 8 caracteres
    public static String encript(String clave) throws IdeamException { 
        String scheme = "DES/ECB/NoPadding";
        String provider = "SunJCE";
        String message = "EncryNet";
        String chiave = clave;
        
        try{
            DESKeySpec desKeySpec = new DESKeySpec(chiave.getBytes()); 
            
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES"); 
            
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec); 
            
            Cipher cipher = Cipher.getInstance(scheme, provider); 
            
            //System.out.println(cipher.getAlgorithm() + "/" + cipher.getProvider()); 
            
            cipher.init(Cipher.ENCRYPT_MODE, secretKey); 
            
            byte[] ctext = cipher.doFinal(message.getBytes()); 
            return new HexDumpEncoder().encode(ctext);
        }catch(InvalidKeyException e){
            throw new IdeamException("InvalidKeyException: " + e.getMessage());
        }catch(NoSuchAlgorithmException e){
            throw new IdeamException("NoSuchAlgorithmException: " + e.getMessage());
        }catch(InvalidKeySpecException e){
            throw new IdeamException("InvalidKeySpecException: " + e.getMessage());
        }catch(NoSuchPaddingException e){
            throw new IdeamException("NoSuchPaddingException: " + e.getMessage());
        }catch(NoSuchProviderException e){
            throw new IdeamException("NoSuchProviderException: " + e.getMessage());
        }catch(BadPaddingException e){
            throw new IdeamException("BadPaddingException: " + e.getMessage());
        }catch(IllegalBlockSizeException e){
            throw new IdeamException("IllegalBlockSizeException: " + e.getMessage());
        }
    } 
}