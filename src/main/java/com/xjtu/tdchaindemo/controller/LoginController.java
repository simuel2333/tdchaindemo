package com.xjtu.tdchaindemo.controller;

import cn.tdchain.Trans;
import cn.tdchain.jbcc.Result;
import com.xjtu.tdchaindemo.TDChainConnection;
import com.xjtu.tdchaindemo.httpcall.HttpRequest;
import com.xjtu.tdchaindemo.reponse.CommonReturnType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = {"*"}, allowCredentials = "true")
public class LoginController extends BaseController {

    @PostMapping(value = "jd", consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType loginJd(@RequestParam(name = "user") String user,
                                    @RequestParam(name = "password") String password) {
        try {
            HttpRequest.setToken();
        } catch (IOException e) {
            e.printStackTrace();
            return CommonReturnType.create("fail","error");
        }
        return CommonReturnType.create(HttpRequest.token);
    }

}
