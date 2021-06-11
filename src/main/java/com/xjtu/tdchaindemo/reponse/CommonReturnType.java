package com.xjtu.tdchaindemo.reponse;

import lombok.Data;

@Data
public class CommonReturnType {
    // 表名对应请求的返回处理结果"success" or "fail"
    private String status;
    // 若status == success 则data内返回前端需要的json数据
    // 若status == fail 则data内使用通用的错误格式
    private Object data;

    public static CommonReturnType create(Object result) {
        return create("success", result);
    }

    public static CommonReturnType create(String status, Object data) {
        CommonReturnType type = new CommonReturnType();
        type.setData(data);
        type.setStatus(status);
        return type;
    }
}
