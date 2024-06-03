package test;

import others.MaHoaAES;

public class testRSA {
	public static void main(String[] args) {
		MaHoaAES rsa = new MaHoaAES();

		try {
			String in = "Hello world!";
			byte[] inByte = rsa.maHoa(in.getBytes(), rsa.getKhoaCongKhai());

			byte[] outByte = rsa.giaiMa(inByte, rsa.getKhoaBiMat());
			System.out.println(new String(outByte));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
