package com.onlineexam.controller;

import com.onlineexam.entities.Question;
import com.onlineexam.entities.User;
import com.onlineexam.utils.CommonResult;
import com.onlineexam.service.InformationService;
import io.swagger.annotations.ApiParam;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class InformationController {
    @Resource
    InformationService service;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    private RestHighLevelClient client;


    @GetMapping(value = "/provider/information/show/{email}")
    public CommonResult showUserInformation(@PathVariable String email){
        String userName = service.showInformation(email);
        if (userName==null){
            return new CommonResult(510,"用户名为空","用户名");
        } else {
            return new CommonResult(464,"查询成功",userName);
        }
    }

    @GetMapping(value = "/provider/information/update/{username}/{email}")
    public CommonResult updateUserInformation(@PathVariable String username,@PathVariable String email){
        service.updateInformation(username,email);
        return new CommonResult(510,"修改成功","8500");
    }

    @GetMapping(value = "/provider/information/showAllUsers/{currentPage}/{pageSize}")
    public CommonResult showAllUsers(@PathVariable int currentPage,@PathVariable int pageSize){
        List<User> userList = service.showAllUsers(currentPage, pageSize);
        return new CommonResult(510,"修改成功",userList);
    }

    @GetMapping(value = "/provider/information/countAllUsers")
    public CommonResult countAllUsers(){
        return new CommonResult(510,"查询成功",service.countAll());
    }

    @GetMapping(value = "/provider/information/pageView")
    public CommonResult pageView(){
        return new CommonResult(510,"查询成功",stringRedisTemplate.opsForValue().get("views"));
    }

    @GetMapping(value = "/provider/information/getUserName/{email}")
    public CommonResult getUserName(@PathVariable String email){
        return new CommonResult(200,"查询成功",service.getUserName(email));
    }

    @GetMapping(value = "/provider/information/getAdminInfo")
    public CommonResult getAdminInfo(){
        return new CommonResult(200,"查询成功",service.getAdminInfo());
    }

    @GetMapping(value = "/provider/information/searchUserInfo/{key}/{currentPage}/{pageSize}")
    public CommonResult searchUserInfo(@PathVariable String key,
                                       @PathVariable int currentPage,
                                       @PathVariable int pageSize) throws IOException {
        SearchRequest searchRequest = new SearchRequest("users");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //分页,currentPage从0开始
        searchSourceBuilder.from(currentPage-1);
        searchSourceBuilder.size(pageSize);

        //精准匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("username", key);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("aaaa"+ Arrays.toString(response.getHits().getHits()));

        List<String> emails = new ArrayList<String>();
        //解析结果
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        int i=0;
        for (SearchHit hit : response.getHits().getHits()) {
            list.add(hit.getSourceAsMap());
            emails.add((String) list.get(i).get("email"));
            System.out.println(list);
            i+=1;
        }

        List<User> users = service.search(emails,currentPage,pageSize);
        return new CommonResult(12,String.valueOf(users.size()),users);
    }


}
