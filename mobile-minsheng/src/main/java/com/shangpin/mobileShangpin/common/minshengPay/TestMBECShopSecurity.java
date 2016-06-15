package com.shangpin.mobileShangpin.common.minshengPay;

import org.apache.log4j.Logger;

public class TestMBECShopSecurity {
	private static Logger logger = Logger.getLogger(TestMBECShopSecurity.class);

	public static void main(String[] args) throws Exception {
		String timestamp = System.nanoTime() + "0";
		System.out.println(timestamp + ",timestamp:" + timestamp.length());
		String orderPlain = "010011091166745533217|1.23|01|20120309|154512|01001|商户名称|备注1|备注2|0|http://192.168.137.43:8000/ecshopClient/ECShopProcessResult.shtml";
		orderPlain = "01315"
				+ timestamp
				+ "|55.00|01|20130626|142715|01278|ELTC|2042780|13601268686|0|http://cmbchina.wangpiao.com/cmbcwap/DefrayResult.aspx|jdsfdsf";
		orderPlain = "01315"
				+ timestamp
				+ "|4.00|01|20121207|151913|01315|上海电银|||0|http://112.65.46.59:5186/app/cmbcNotify|";
		// orderPlain = "01315"
		// + timestamp
		// +
		// "|56.0|01|20130626|155032|01315|aa|aa|aa|0|http://106.3.46.121:8081/shop/topic/topicAction!payBack.action|11";
		// 商户(ecshop)对订单明文进行加密
		String orderEncrypt = new MBECShopSecurity().encryptOrder(orderPlain);
		System.out.println(orderEncrypt);
		 //bank work...
		 String tradeResultEncrypt ="EpgEAACAACtAgnJWOnSp2YPwRXnYeRO0mH16IrP8lae/WItomJP6WhtgUB+2R4kI7fRSxT3Nc9yD5ubuuBrL2ywovdCx5BXzxJL2w/588MQ3ReXCSEFW2gcONN+SQyT1rXbqXEBe3io6n2NrjwOKjJrk67RM1+3nAMm/Z6DBWVby5p7i0UUWE6mIdVXQsqDTWuqlBkeJlqtTC3yl4qaeG3mom1Yh6Okley9+i9+q+60phtCr7Sr0O/ArC2qwbt8xVwA4oEiZqeGBgerLB/PmY9lWNStUVgeioK+L19kMdzX9f034LAdUQMKjYvm78uRyliZ/uLbsXPBMN9u4lv88MSxUikGsKby3AvaJehPaiE/JjrIihD7rpUp4/XQuW5FDbQ9XC935GHcCZOOYBLbJizMLmvTySVEhnPPnXtmVD0/ZhqMk9qFzHc7czL3Eeps12QMb9bV2Ip3naLgrwkFFKVT78NU7a8z8Ek2n7t8+67iNU2KHpHSNJu7TcFFbRlSMiU9it5VklwBGEH2CK8gue+59G4Rwj7SaVDJHSZng+nweCn3f8DM9pusgkVoxL8OYm5VaMyWxC9/z9UoafgqH5A6S+vDPf4H87XRvcay7Wn4mblSXoMwtnVqxQfss0ylCuqfLga9IJd7Up4sf66ms78agK+GwBKjm5Kdw3wT4A8WI2eXvBCCoFwFuh+kJHR9y+++GbpPJ2o0aRVgV25pWSl9h30iBtID47SpbZt7VaOdRkhz2+77dCtzTCTS8YUGTPgYjfKYYyk1eVspRWin/V7C1esehSpJzuFhdKc5ZBvrRDgegqhtFvaoz6CMXk4MyG6B6UGpFKTUPzlzeBWav0gF8aEfKvXsXfbE5OATLCz+9tdBrDAwewa9RrEMWxcGxJ7CRoVAZpe6G1CZ+ZKC7kp+imzAM7DiNFqgE6S2BcKsUYJx3iJZ9HsJvaj+vgotHa3qw9xQr8rMLPLSyFhJLdTKHqdCGGcyjlY2AUzMmcytplE0J67AARLn9CiX39AybRwJOVQpWA/CmeQYazKHyqT+efd61UpYuEVRlG817P0ib2FeffG6jnZ03q400VhbtwJQ/BdNDT+k1YowHn0UpRp4jz+YzaN48zoBfGKbN6YLv7m5uNXTOYJoPnSRsi1DkYxlMQkqjQ7T3h7fufbl6Dtc1z1i8gMDzhEKqEZYnKrLtTBwmh4sSUV2tdLDAOY5mkm9bx2dqWHtc3u45c9pybhTEbaEzGftNXOc5HPRR3p9C5xUl+LKwsbeleU8N3kglwv/4xqN48n+A0FLwCfcOCsqDofcIawaPY6ZtlfYc/haJPjm5tegvQz1w/A4e4d+dNLSkMMTRuJApqVezw0jQUanbvaGt1fk8S8T9Cg3aIqu3GC8MqSHrggc5Khc74hBpF7qlGME/pmdVqIpfw5nuEvC4AhK9WzwLVgCXb7hf2uxqDmAxbIK6P+bM56f8rKTwkZscRVmzZqnZNDgl6WDOyz+/Lk+PBYLONsU54w7HT17bA5Jab9S15y7YOdPBgOEFu+CU7LDzYFCdXIwy/SZcf1IFSrrOuoJPMHA50IzEknBip+sej1YkMPd52QT8dRIWRb0WqGEv5ESnS+L34M3sTLpM5vAJWVxaip2ozpbmut4Pfk66h+QzafsqeCia8t/5gGuvdIboIcFH51oQS0D6cQCBTb12CPKB8lW9mwkoWDNS+g1EWq+ULPLhYFK9iXL7CQShfKEV6r2Cy3Sqk1SN";
		// // 商户(ecshop) 对交易结果进行解密
		 
		System.out.print(new MBECShopSecurity().decryptTradeResult(tradeResultEncrypt));
	}
}
