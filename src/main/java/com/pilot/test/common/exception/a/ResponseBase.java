package com.pilot.test.common.exception.a;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@ApiModel(value = "JsonData",description = "返回的数据类型")
public class ResponseBase {

	//返回状态信息
    @ApiModelProperty(name = "code",value = "状态code",notes = "返回信息的状态")
	private Integer rtnCode;

  //返回携带的信息内容
    @ApiModelProperty(name = "msg",value = "状态信息",notes = "返回信息的内容")
	private String msg;
	
  //返回对象
    @ApiModelProperty(name = "data",value = "查询数据",notes = "返回数据的内容")
	private Object data;
	
	public ResponseBase() {
		super();
	}

	public ResponseBase(Integer rtnCode, String msg, Object data) {
		super();
		this.rtnCode = rtnCode;
		this.msg = msg;
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseBase [rtnCode=" + rtnCode + ", msg=" + msg + ", data=" + data + "]";
	}
	
}
