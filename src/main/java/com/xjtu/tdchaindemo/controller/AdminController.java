package com.xjtu.tdchaindemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xjtu.tdchaindemo.controller.viewobject.CompanyVO;
import com.xjtu.tdchaindemo.dataobject.TransactionDO;
import com.xjtu.tdchaindemo.httpcall.HttpRequest;
import com.xjtu.tdchaindemo.reponse.CommonReturnType;
import com.xjtu.tdchaindemo.service.TransactionService;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = {"*"}, allowCredentials = "true")
public class AdminController extends BaseController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/transaction")
    @ResponseBody
    public CommonReturnType queryAllTransaction() {
        List<TransactionDO> transactions = transactionService.queryAllTransaction();
        return CommonReturnType.create(transactions);
    }

    @PostMapping(value = "addTx", consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    @Transactional
    public CommonReturnType AddTransaction(@RequestParam(name = "name") String name,
                                           @RequestParam(name = "hash") String hash,
                                           @RequestParam(name = "url") String url,
                                           @RequestParam(name = "type") Integer type) {
        TransactionDO record = new TransactionDO();
        record.setName(name);
        record.setHash(hash);
        record.setUrl(url);
        record.setType(type);
        transactionService.insertTransaction(record);
        return CommonReturnType.create("success");
    }

    @GetMapping("/company")
    @ResponseBody
    public CommonReturnType queryAllCompanies() throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("organization", "user03");
        params.put("channel", "testone");
        params.put("ccname", "fabcarnew");
        params.put("function", "queryAllCompanys");
        JSONObject res = HttpRequest.post("http://211.151.11.130:31970", "/chaincode/query", params);
        JSONArray array = JSON.parseArray(res.getJSONObject("data").getString("payload"));
        List<CompanyVO> companyVOS = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            com.alibaba.fastjson.JSONObject record = array.getJSONObject(i).getJSONObject("Record");
            CompanyVO companyVO = new CompanyVO();
            companyVO.setCompanyName(record.getString("CompanyName"));
            companyVO.setLegalRepresentative(record.getString("LegalRepresentative"));
            companyVO.setCreditCode(record.getString("CreditCode"));
            companyVO.setRegisteredCapital(record.getString("RegisteredCapital"));
            companyVO.setPaidCapital(record.getString("PaidCapital"));
            companyVO.setEstablishedData(record.getString("EstablishedData"));
            companyVO.setApprovedDate(record.getString("ApprovedDate"));
            companyVO.setOwnRisk(record.getString("OwnRisk"));
            companyVO.setAssociatedRisk(record.getString("AssociatedRisk"));
            companyVO.setStaffSize(record.getString("StaffSize"));
            companyVO.setBusinessScope(record.getString("BusinessScope"));
            companyVO.setCredit(record.getString("Credit"));
            companyVO.setAmount(record.getString("Amount"));
            companyVOS.add(companyVO);
        }
        return CommonReturnType.create(companyVOS);
    }

}
