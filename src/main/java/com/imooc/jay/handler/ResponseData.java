package com.imooc.jay.handler;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Author:   jay
 * Date:     2018年07月09日18:39:13
 * Description: ${DESCRIPTION}
 */
@ApiModel
public class ResponseData<T> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ResponseData.class);

    private static final long serialVersionUID = 7917345507074842804L;

    /**
     * success/fail
     */
    @ApiModelProperty("返回结果，success or fail")
    private String result;

    /**
     * 0成功 非0具体错误原因
     */
    @ApiModelProperty("返回码，0成功 非0具体错误原因")
    private int code;

    /**
     * 具体错误描述or成功描述
     */
    @ApiModelProperty("具体错误描述or成功描述")
    private String message;

    /**
     * 存放业务数据
     */
    @ApiModelProperty("存放业务数据")
    private T data;

    public static class Builder {
        @SuppressWarnings("rawtypes")
        public static ResponseData SUCC() {
            ResponseData vo = new ResponseData();
            vo.setResult("success");
            vo.setCode(0);
            return vo;
        }

        @SuppressWarnings("rawtypes")
        public static ResponseData FAIL() {
            ResponseData vo = new ResponseData();
            vo.setResult("fail");
            return vo;
        }
    }

    public ResponseData<T> initErrCodeAndMsg(int code, String message) {
        logger.info("code=" + code + " message=" + message);
        this.code = code;
        this.message = message;
        return this;
    }

    public ResponseData<T> initSuccDataAndMsg(int code, String message) {
        logger.info("code=" + code + " message=" + message);
        this.code = code;
        this.message = message;
        return this;
    }

    public ResponseData<T> initSuccData(T data) {
        logger.info(JSON.toJSONString(data));
        this.data = data;
        return this;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}