package com.xjtu.tdchaindemo.httpcall;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    public static String token = "Basic dGVzdDpBZG1pbkAxNDdAIQ==";

    public static String jdchainURL = "http://211.151.11.130:31970";
    public static JSONObject commomRequest(String urlStr, String path, String type, Map<String, String> params) throws IOException {
        URL url = new URL(urlStr + path);
        StringBuilder sb = new StringBuilder();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(type);
        conn.setRequestProperty("authorization", token);
        if (params != null) {
            conn.setDoOutput(true);
            conn.setDoInput(true);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(ParameterStringBuilder.getParamsString(params));
            out.flush();
            out.close();
        } else {
            conn.setRequestProperty("Content-Type", "application/json");
        }
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String res = "";
        while ((res = br.readLine()) != null) {
            sb.append(res);
        }
        br.close();
        return new JSONObject(sb.toString());
    }

    public static JSONObject get(String urlStr, String path) throws IOException {
        return commomRequest(urlStr, path, "GET", null);
    }

    public static JSONObject post(String urlStr, String path, Map<String, String> params) throws IOException {
        return commomRequest(urlStr, path, "POST", params);
    }

    public static void setToken() throws IOException {
        JSONObject res = post(jdchainURL, "/login", null);
        token = "Bearer " + res.getString("token");
    }

    //test
    public static void main(String[] args) throws IOException {
        setToken();
        Map<String, String> params = new HashMap<>();
        params.put("organization", "user03");
        params.put("channel", "testone");
        params.put("ccname", "fabcarnew");
        params.put("function", "queryAllCompanys");
        JSONObject res = post(jdchainURL, "/chaincode/query", params);
        System.out.println(res.getJSONObject("data").getString("payload"));
        String json = "[{\"Key\":\"Company0\", \"Record\":{\"CompanyName\":\"西安纸贵互联网科技有限公司\",\"LegalRepresentative\":\"唐凌\",\"CreditCode\":\"91610103MA6TYECUX3\",\"RegisteredCapital\":\"1443\",\"PaidCapital\":\"未知\",\"EstablishedData\":\"2016/7/15\",\"ApprovedDate\":\"2021/2/3\",\"OwnRisk\":\"2\",\"AssociatedRisk\":\"6\",\"StaffSize\":\"少于50\",\"BusinessScope\":\"互联网信息 服务\\t\\t；技术推广服务；知识产权服务；经济信息咨询（不含金融、证券、保险 、期货等限制审批项目）；软件服务；广告的设计、制作、代理及发布； 文化\\t\\t艺术交流活动组织策划（不含演出）；会议服务、市场营销策划；电子、\",\"Credit\":\"\",\"Amount\":\"\"}},{\"Key\":\"Company1\", \"Record\":{\"CompanyName\":\"杭州趣链科技有限公司\",\"LegalRepresentative\":\"李伟\",\"CreditCode\":\"91330108MA27Y5XH5G\",\"RegisteredCapital\":\"4869\",\"PaidCapital\":\"未知\",\"EstablishedData\":\"2016/7/11\",\"ApprovedDate\":\"2021/5/27\",\"OwnRisk\":\"8\",\"AssociatedRisk\":\"82\",\"StaffSize\":\"200-299人\",\"BusinessScope\":\"第一类增值\\t\\t电信业务；第二类增值电信业务；技术进出口(依法须经批准的项目，经相关部门批准后方可开展经营活动，具体经营项目以审批结果为准)。一般项目\\t\\t：技术服务、技术开发、技术咨询、技术交流、技术转让、技术推广；软 。\",\"Credit\":\"\",\"Amount\":\"\"}},{\"Key\":\"Company2\", \"Record\":{\"CompanyName\":\"台州万邦置业有限公司\",\"LegalRepresentative\":\"邵为军\",\"CreditCode\":\"91331082781825805L\",\"RegisteredCapital\":\"14800\",\"PaidCapital\":\"14800万\",\"EstablishedData\":\"2005/11/11\",\"ApprovedDate\":\"2021/4/13\",\"OwnRisk\":\"1552\",\"AssociatedRisk\":\"2131\",\"StaffSize\":\"100-499人\",\"BusinessScope\":\"房地产开发\",\"Credit\":\"\",\"Amount\":\"\"}},{\"Key\":\"Company3\", \"Record\":{\"CompanyName\":\"华信信用管理有限公司\",\"LegalRepresentative\":\"刘传廷\",\"CreditCode\":\"913701007317278000\",\"RegisteredCapital\":\"5160\",\"PaidCapital\":\"5160万\",\"EstablishedData\":\"2001/8/6\",\"ApprovedDate\":\"2021/4/29\",\"OwnRisk\":\"3\",\"AssociatedRisk\":\"16\",\"StaffSize\":\"少于50人\",\"BusinessScope\":\"信用管理及信息咨询服 务；资信评估，信用评级评定，信用环境评估；信用担保；商账代理；财务管理、咨询服务；非学历短期技能培训。(依法须经批准的项目，经相关部门\",\"Credit\":\"\",\"Amount\":\"\"}},{\"Key\":\"Company4\", \"Record\":{\"CompanyName\":\"长风国际信用评价（集团）有限公司\",\"LegalRepresentative\":\"余宝林\",\"CreditCode\":\"91110000067262961L\",\"RegisteredCapital\":\"5000\",\"PaidCapital\":\"1100万\",\"EstablishedData\":\"2013/5/3\",\"ApprovedDate\":\"2017/7/20\",\"OwnRisk\":\"0\",\"AssociatedRisk\":\"0\",\"StaffSize\":\"少于50人\",\"BusinessScope\":\"企业部门批准\",\"Credit\":\"\",\"Amount\":\"\"}}]\n";

    }
}
