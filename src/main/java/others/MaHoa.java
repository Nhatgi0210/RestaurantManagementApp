package others;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MaHoa {
	public static String mahoa(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] mes = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, mes);
			String result = number.toString(16);
			while(result.length() < 32) {
				result = "0"+ result;
			}
			return result;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
}
