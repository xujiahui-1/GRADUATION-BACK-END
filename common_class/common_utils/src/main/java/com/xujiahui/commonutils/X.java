package com.xujiahui.commonutils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class X {

    private boolean success;
    private String message;
    private Integer code;
    private Map<String ,Object> data=new HashMap<String,Object>();

    private X(){}
    //成功静态方法
    public  static X ok(){
        X r=new X();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return  r;
    }

    //失败静态方法
    public  static X error(){
        X r=new X();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return  r;
    }
    public  X success(boolean success){
        this.setSuccess(success);
        return this;
    }
    public X message(String message){
        this.setMessage(message);
        return  this;
    }
    public X code(Integer code){
        this.setCode(code);
        return this;
    }
    public X data(String key,Object value){
        this.data.put(key, value);
        return this;
    }
    public X data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
