package com.xjtu.tdchaindemo.controller.viewobject;

import lombok.Data;

@Data
public class TransactionVO {
    private Integer id;

    private String name;

    private String url;
    // 记录是什么类型的交易：0：京东链，1：天德链，2：以太坊
    private Integer type;
}
