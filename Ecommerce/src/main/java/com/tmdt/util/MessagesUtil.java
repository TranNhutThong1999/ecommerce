package com.tmdt.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessagesUtil {
	@Autowired
	private MessageSource messageSource;
	
	public Map<String,String> Messages (String parameter){
		Map<String, String> map = new HashMap<String, String>();
		if(parameter.equals("register_SUCCESS")) {
			map.put("message", messageSource.getMessage("user.register.success",null,Locale.US));
		}else if(parameter.equals("login_FAIL")) {
			map.put("message", messageSource.getMessage("user.login.fail",null,new Locale("vi")));
		}else if(parameter.equals("verify_SUCCESS")) {
			map.put("message", messageSource.getMessage("Add.ok",null,new Locale("vi")));
		}else if(parameter.equals("pay_SUCCESS")) {
			map.put("message", messageSource.getMessage("user.pay.success",null,new Locale("vi")));
		}//else if(parameter.equals("timeout")) {
//			map.put("message", messageSource.getMessage("security.timeout",null,new Locale("vi")));
//			map.put("alert", "danger");
//		}else if(parameter.equals("failure")) {
//			map.put("message", messageSource.getMessage("security.failure",null,new Locale("vi")));
//		}else if(parameter.equals("author")) {
//			map.put("message", messageSource.getMessage("security.author",null,new Locale("vi")));
//		}else if(parameter.equals("verify_success")) {
//			map.put("message", messageSource.getMessage("verify.verify_success",null,new Locale("vi")));
//		}else if(parameter.equals("verify_timeout")) {
//			map.put("message", messageSource.getMessage("verify.verify_timeout",null,new Locale("vi")));
//		}else if(parameter.equals("changePW_OK")) {
//			map.put("message", messageSource.getMessage("Password.changePW_OK",null,new Locale("vi")));
//			map.put("alert", "success");
//		}else if(parameter.equals("locked")) {
//			map.put("message", messageSource.getMessage("user.locked",null,new Locale("vi")));
//		}
		return map;
	}
}
