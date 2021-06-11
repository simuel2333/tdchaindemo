package com.xjtu.tdchaindemo.service;

import com.xjtu.tdchaindemo.dataobject.TransactionDO;

import java.util.List;

public interface TransactionService {
    List<TransactionDO> queryAllTransaction();

    List<TransactionDO> queryTransactionByType(int type);

    void insertTransaction(TransactionDO record);
}
