package com.pilot.test.common.exception.a;

import org.springframework.stereotype.Component;

@Component
public class BaseApiService {
	
	//返回失败信息可传msg
    public ResponseBase setResultError(String msg) {
    	return setResult(Constants.HTTP_RES_CODE_500,msg,null);
		
    }
   //返回失败信息可传code
    public ResponseBase setResultError(Integer code, String msg) {
    	return setResult(code,msg,null);
		
    }
	
	//返回成功可以传data值
	public ResponseBase setResultSuccessWithData(Object data) {
		return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE,data);
		
	}
	
	//返回成功可以传msg值
	public ResponseBase setResultSuccess(String msg) {
		return setResult(Constants.HTTP_RES_CODE_200,msg,null);
		
	}
	
	//返回成功但是没有data值
	public ResponseBase setResultSuccess() {
		return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE,null);
		
	}
	
	
	//通用封装
	public ResponseBase setResult(Integer code, String msg, Object data) {
		return new ResponseBase(code,msg,data);
	}
}
