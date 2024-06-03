package others;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;

public class MaHoaAES {
    private SecretKey secretKey;
    private IvParameterSpec ivParameterSpec;

    public MaHoaAES() {
        try {
            secretKey = taoKhoaAES();
            ivParameterSpec = taoIV();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SecretKey taoKhoaAES() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256); // Kích thước khóa AES là 256 bit
        return keyGenerator.generateKey();
    }

    private IvParameterSpec taoIV() {
        byte[] iv = new byte[16]; // Kích thước vector khởi tạo (IV) là 16 byte
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public byte[] maHoa(byte[] duLieu) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        return cipher.doFinal(duLieu);
    }

    public byte[] giaiMa(byte[] duLieuDaMaHoa) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        return cipher.doFinal(duLieuDaMaHoa);
    }

	public SecretKey getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(SecretKey secretKey) {
		this.secretKey = secretKey;
	}

	public IvParameterSpec getIvParameterSpec() {
		return ivParameterSpec;
	}

	public void setIvParameterSpec(IvParameterSpec ivParameterSpec) {
		this.ivParameterSpec = ivParameterSpec;
	}

    
    
}