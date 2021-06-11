package com.xjtu.tdchaindemo.service.impl;

import com.xjtu.tdchaindemo.dao.TransactionDOMapper;
import com.xjtu.tdchaindemo.dataobject.TransactionDO;
import com.xjtu.tdchaindemo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDOMapper transactionDOMapper;

    @Override
    public List<TransactionDO> queryAllTransaction() {
        return transactionDOMapper.listTransaction();
    }

    @Override
    public List<TransactionDO> queryTransactionByType(int type) {
        return null;
    }

    @Override
    public void insertTransaction(TransactionDO record) {
        transactionDOMapper.insert(record);
    }
}
