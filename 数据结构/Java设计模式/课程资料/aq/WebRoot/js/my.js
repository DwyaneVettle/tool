//创建加解密对象
var encrypt = new JSEncrypt();
//设置公钥
encrypt.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnV7zQ238e0FWp2WqulJTlAQRSLf+af8dCxVBUhDGhRVh1wjZ9d1ms3DWq4orfWRqeRrlzZSfx2BrIK5H/DiCwTZ8n9LTiXN/GKiYDHnC4WgtB7I3zczXJ/X+vQ7irgfgRYS1qrN2YE/PSMUPCGV18yA82Ht8gQiP79PI+CVTY4wIDAQAB");
var signVal;
//signVal = encrypt.encrypt("woniu" + 时间);
function myAjax(sendUrl, sendData, successFunc){
	//向数据中追加签名内容
	sendData.sign = encrypt.encrypt("woniu");
	$.ajax({
		url:sendUrl,
		type:'post',
		data:sendData,
		dataType:'json',
		success:successFunc
	});
}



