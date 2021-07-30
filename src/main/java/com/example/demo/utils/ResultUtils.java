package com.example.demo.utils;

import com.example.demo.enm.MsgId;
import com.example.demo.entity.Result;

/**
 * @Author 赵思阳
 * @Date 2021/7/13 9:37
 * @Version 1.0
 */

//返回结果的工具类
public class ResultUtils {
    //带code和参数的成功
    public static Result success(MsgId err, Object data){
        Result result = new Result();
        result.setCode(err.getCode());
        result.setMsg(err.getMsg());
        result.setData(data);
        return result;
    }
    //带参数成功
    public static Result success(Object data){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功！！");
        result.setData(data);
        return result;
    }
    //不带参数成功
    public static Result success(){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功！！");
        return result;
    }

    //错误码和错误信息
    public static Result error(MsgId err){
        Result result = new Result();
        result.setCode(err.getCode());
        result.setMsg(err.getMsg());
        return result;
    }
}
