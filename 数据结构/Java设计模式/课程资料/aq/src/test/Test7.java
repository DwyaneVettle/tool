package test;

import util.RSACodeUtil;

public class Test7 {
	public static void main(String[] args) throws Exception {
		String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnV7zQ238e0FWp2WqulJTlAQRSLf+af8dCxVBUhDGhRVh1wjZ9d1ms3DWq4orfWRqeRrlzZSfx2BrIK5H/DiCwTZ8n9LTiXN/GKiYDHnC4WgtB7I3zczXJ/X+vQ7irgfgRYS1qrN2YE/PSMUPCGV18yA82Ht8gQiP79PI+CVTY4wIDAQAB";
		String privateKeyStr = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKdXvNDbfx7QVanZaq6UlOUBBFIt/5p/x0LFUFSEMaFFWHXCNn13WazcNariit9ZGp5GuXNlJ/HYGsgrkf8OILBNnyf0tOJc38YqJgMecLhaC0HsjfNzNcn9f69DuKuB+BFhLWqs3ZgT89IxQ8IZXXzIDzYe3yBCI/v08j4JVNjjAgMBAAECgYB4YL0K4VrIp8V3sVb0zx1svxaEp2I/mbuVZKssoQuyn1fLHjJkfv6bQq24h86ubEKhqO6u/A2IA5UAeZdEir0n61lFgla4geCMsmtbloClpML3RqpuDjsovRg0tmX7nLG8Felh8A6rtQDc9tid6hW3Rb0NwTIalr2n2cWv5ZgQgQJBAOEbZ3HTpf+cP789b2X5hEKurhGUnL3SHTFuJZfi+g9wGX6C3PNhsP5thvDxd4hsDqVaqPVptLhXvMzhmMXWtssCQQC+Tuwxk/tJMChwE+YeZkhDGpF1DR9RNPsp+86p6J9nmSq2t6FaK5pCgUmaIL4EyBEQvZF43snNLJBZ/hZHwAtJAkBT+WUdkVbbg+5D6SclY29zcLZ+2HGgXkGFrxFXF9Uw7SCVkTvbjxX30+QC/hEZUzYjeS6OXmCMZC6KP54+L1sBAkB04c4pCQUtxzke1Onhw9of9KJfrKaOXqbf+zrLmgbFPwo/3HpqAhulOvi0g+WQ1Du+917wSIzSycsCqo6OlMGZAkB16hAL5g2vAFkM0uMHgyAn5T4n4FwuruFacU2Kd7uA3oQ3MW2y5B/WeAv4/HHAeSF391x5Nnyt7SZszS/CxvK+";
		System.out.println("公钥：" + publicKeyStr);
		System.out.println("私钥：" + privateKeyStr);
		
		String mw = "lwsKdLbvK7lhTiSEXEga2kBU+xjnvHwnW6gcG7/ZuNuHejkuSBh60mYxfm2wUJ+E0XQ5oGHy8dB9Vl4WSRDwKxceTr3ozzNVRSj1qY11mo1bslVu7+Dzij4Eu79fypnEl9FP3l+DuCUieggCSpu27K3O0a7JPMVgNYdlmHvHNU5o=";
		
		
		//2.公钥加密  私钥解密
//		String encoder2PublicKey = RSACodeUtil.encoder2PublicKey(str, publicKeyStr);
//		System.out.println("公钥加密：" + encoder2PublicKey);
		
		String decoder2PrivateKey = RSACodeUtil.decoder2PrivateKey(mw, privateKeyStr);
		System.out.println("私钥解密：" + decoder2PrivateKey);
		
	}
}
