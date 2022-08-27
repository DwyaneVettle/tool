package util;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;


/**
 * RSA编码工具
 * @author waver
 */
public class RSACodeUtil {
	/**加解密方式*/
	private static final String ENCRYPT = "RSA";
	/**密钥长度默认1024位。 密钥长度必须是64的整数倍，范围在512~1024之间*/
	private static final int ENCRYPTION_LENGTH = 1024;
	
	/**私钥对象*/
	private static PrivateKey privateKey;
	/**公钥对象*/
	private static PublicKey publicKey;
	
	/**生成密钥对象*/
	static{
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ENCRYPT);
			//初始化密钥
			keyPairGen.initialize(ENCRYPTION_LENGTH);
			//获取密钥对对象
			KeyPair keyPair = keyPairGen.generateKeyPair();
			privateKey = keyPair.getPrivate();
			publicKey = keyPair.getPublic();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 获取私钥字符串
     * @return
     */
    public static String getPrivateKeyStr(){
    	String str = new String(Base64.getEncoder().encode( privateKey.getEncoded() ));
    	return str;
    }
    
    /**
     * 获取公钥字符串
     * @return
     */
    public static String getPublicKeyStr(){
    	String str = new String(Base64.getEncoder().encode( publicKey.getEncoded() ));
    	return str;
    }
	
	
	/**
	 * 公钥加密
	 * @param data		明文
	 * @param key		公钥字符串
	 * @return			密文
	 * @throws Exception
	 */
    public static String encoder2PublicKey(String data, String key) throws Exception {
        //对公钥解密
        byte[] keyBytes = Base64.getDecoder().decode(key);
        //获取ASN编码X509标准公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ENCRYPT);
        Key publicKey = keyFactory.generatePublic( x509KeySpec );
        //对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init( Cipher.ENCRYPT_MODE, publicKey );
        byte[] byts = cipher.doFinal( data.getBytes() );
        return new String(Base64.getEncoder().encode(byts));
    }
    
    /**
     * 私钥加密
     * @param data		明文
     * @param key		私钥字符串
     * @return			密文
     * @throws Exception
     */
    public static String encoder2PrivateKey(String data, String key)throws Exception {
        //对密钥解密
        byte[] keyBytes = Base64.getDecoder().decode(key);
        //获取ASN编码PKCS#8标准私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ENCRYPT);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] byts = cipher.doFinal(data.getBytes());
        return new String( Base64.getEncoder().encode(byts) );
    }
    
    
    /**
     * 私钥解密
     * @param data		密文
     * @param key		私钥字符串
     * @return			明文
     * @throws Exception
     */
    public static String decoder2PrivateKey(String data, String key) throws Exception{
        //对私钥解密
        byte[] keyBytes = Base64.getDecoder().decode(key);
        //获取ASN编码PKCS#8标准私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ENCRYPT);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataByts = Base64.getDecoder().decode(data);
        return new String(cipher.doFinal(dataByts));
    }
    
    /**
     * 公钥解密
     * @param data		密文
     * @param key		公钥字符串
     * @return			明文
     * @throws Exception
     */
    public static String decoder2PublicKey(String data, String key) throws Exception {
        //对密钥解密
        byte[] keyBytes = Base64.getDecoder().decode(key);
        //获取ASN编码PKCS#8标准公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ENCRYPT);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        //对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] dataByte = Base64.getDecoder().decode(data);
        return new String( cipher.doFinal(dataByte) );
    }
    
    
    
}
