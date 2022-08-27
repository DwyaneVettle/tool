package com.woniuxy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.RSACodeUtil;

public class SignFilter implements Filter{
	//签名明文
	private final String sign = "woniu";
	private final String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnV7zQ238e0FWp2WqulJTlAQRSLf+af8dCxVBUhDGhRVh1wjZ9d1ms3DWq4orfWRqeRrlzZSfx2BrIK5H/DiCwTZ8n9LTiXN/GKiYDHnC4WgtB7I3zczXJ/X+vQ7irgfgRYS1qrN2YE/PSMUPCGV18yA82Ht8gQiP79PI+CVTY4wIDAQAB";
	private final String privateKeyStr = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKdXvNDbfx7QVanZaq6UlOUBBFIt/5p/x0LFUFSEMaFFWHXCNn13WazcNariit9ZGp5GuXNlJ/HYGsgrkf8OILBNnyf0tOJc38YqJgMecLhaC0HsjfNzNcn9f69DuKuB+BFhLWqs3ZgT89IxQ8IZXXzIDzYe3yBCI/v08j4JVNjjAgMBAAECgYB4YL0K4VrIp8V3sVb0zx1svxaEp2I/mbuVZKssoQuyn1fLHjJkfv6bQq24h86ubEKhqO6u/A2IA5UAeZdEir0n61lFgla4geCMsmtbloClpML3RqpuDjsovRg0tmX7nLG8Felh8A6rtQDc9tid6hW3Rb0NwTIalr2n2cWv5ZgQgQJBAOEbZ3HTpf+cP789b2X5hEKurhGUnL3SHTFuJZfi+g9wGX6C3PNhsP5thvDxd4hsDqVaqPVptLhXvMzhmMXWtssCQQC+Tuwxk/tJMChwE+YeZkhDGpF1DR9RNPsp+86p6J9nmSq2t6FaK5pCgUmaIL4EyBEQvZF43snNLJBZ/hZHwAtJAkBT+WUdkVbbg+5D6SclY29zcLZ+2HGgXkGFrxFXF9Uw7SCVkTvbjxX30+QC/hEZUzYjeS6OXmCMZC6KP54+L1sBAkB04c4pCQUtxzke1Onhw9of9KJfrKaOXqbf+zrLmgbFPwo/3HpqAhulOvi0g+WQ1Du+917wSIzSycsCqo6OlMGZAkB16hAL5g2vAFkM0uMHgyAn5T4n4FwuruFacU2Kd7uA3oQ3MW2y5B/WeAv4/HHAeSF391x5Nnyt7SZszS/CxvK+";	
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		//获取签名内容
		String signVal = req.getParameter("sign");
		/**
		 * 1.签名内容无法解密 (非法请求)
		 * 2.可以解密 解密的内容 != sign (非法请求)
		 * 3.可以解密，解密的内容 ==sign,但是超时了
		 * 		页面获取时间：
		 * 			JSP:通过Java脚本去获取服务器时间
		 * 			HTML:使用js获取网络时间
		 * 0.后端没有收到签名信息
		 */
		
		try {
			if( signVal == null || "".equals(signVal) || signVal.length() < 1 ){
				System.out.println("非法请求");
				return;
			}
			//解密
			String decoder = RSACodeUtil.decoder2PrivateKey(signVal, privateKeyStr);
			if( !sign.equals(decoder) ){
				System.out.println("非法请求");
				return;
			}
			//判断是否超时
			
			//通过验证
			fc.doFilter(req, resp);
		} catch (Exception e) {
			System.out.println("非法请求");
			return;
		}
	
		
		
		
		
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
