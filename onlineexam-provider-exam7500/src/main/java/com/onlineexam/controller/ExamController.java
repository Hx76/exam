package com.onlineexam.controller;

import com.alibaba.druid.pool.DataSourceClosedException;
import com.alibaba.druid.pool.ha.DataSourceCreator;
import com.onlineexam.entities.*;
import com.onlineexam.service.ExamService;
import com.onlineexam.utils.CommonResult;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
public class ExamController {
    @Resource
    private ExamService service;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RestHighLevelClient client;

    //获取考试时间
    @GetMapping(value = "/provider/exam/showExamTime/{examId}")
    public CommonResult showExamTime(@PathVariable int examId){
        Date examTime = service.showExamTime(examId);
        return new CommonResult(10,"yes",examTime);
    }

    //分页展示全部考试
    @GetMapping(value = "/provider/exam/showAll/{currentPage}/{pageSize}")
    public CommonResult showAllExam(@PathVariable int currentPage, @PathVariable int pageSize) throws ParseException {
        if (currentPage==1){
            List<Exam> exams = new ArrayList<>();
            for (int i=1;i<=8;i++){
                System.out.println(i);
                Map<Object, Object> pages = stringRedisTemplate.opsForHash().entries("exam:"+i);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                System.out.println(pages.get("startDate").toString()+" "+pages.get("startTime").toString());
                Date date = simpleDateFormat.parse(pages.get("startDate").toString()+" "+pages.get("startTime").toString());
                System.out.println(date);
                Exam exam = new Exam(i,Integer.valueOf(pages.get("exam_serial_number").toString()),pages.get("name").toString(),
                        pages.get("duration").toString(),date,pages.get("creator").toString(),
                        pages.get("state").toString(),pages.get("totalPoints").toString(),"2020-10-03",
                        0);
                exams.add(exam);
            }
            if(exams!=null){
                return new CommonResult(10,"yes",exams);
            }else{
                List<Exam> exams1 = service.showAllExam(currentPage,pageSize);
                return new CommonResult(10,"yes",exams1);
            }
        }else {
            List<Exam> exams1 = service.showAllExam(currentPage,pageSize);
            return new CommonResult(10,"yes",exams1);
        }

    }

    //分页展示我参加的考试
    @GetMapping(value = "/provider/exam/showMyJoinExam/{currentPage}/{pageSize}/{email}")
    public CommonResult showMyJoinExam(@PathVariable int currentPage, @PathVariable int pageSize,@PathVariable String email){
        List<UserScore> exams = service.showMyJoinExam(currentPage,pageSize,email);
        return new CommonResult(10,"yes",exams);
    }

    //查询全部考试的数量
    @GetMapping(value = "/provider/exam/countAll")
    public CommonResult countAllExam(){
        stringRedisTemplate.opsForValue().increment("views");
        Integer num = service.countAll();
        System.out.println(num);
        return new CommonResult(12,"yes",num);
    }

    //查询我参加的考试的数量
    @GetMapping(value = "/provider/exam/countMyJoinExam/{email}")
    public CommonResult countMyJoinExam(@PathVariable String email){
        Integer num = service.countMyJoinExam(email);
        System.out.println(num);
        return new CommonResult(12,"yes",num);
    }

    //分页展示我创建的考试
    @GetMapping(value = "/provider/exam/showMyCreatedExam/{currentPage}/{pageSize}/{email}")
    public CommonResult showMyCreatedExam(@PathVariable int currentPage,@PathVariable int pageSize,@PathVariable String email){
        List<Exam> exams = service.showMyCreatedExam(currentPage,pageSize,email);
        return new CommonResult(10,"",exams);
    }

    //查询我创建考试的数量
    @GetMapping(value = "/provider/exam/countMyCreatedExam/{email}")
    public CommonResult countMyCreatedExam(@PathVariable String email){
        Integer num = service.countMyCreatedExam(email);
        System.out.println(num);
        return new CommonResult(12,"yes",num);
    }

    //获取用户名
    @GetMapping(value = "/provider/exam/getUserName/{email}")
    public CommonResult getUserName(@PathVariable String email){
        String userName = service.getUserName(email);
        return new CommonResult(12,"yes",userName);
    }

    //提交试卷
    @PostMapping(value = "/provider/exam/submit/{examId}/{email}")
    public CommonResult submit(@PathVariable String examId,@PathVariable String email,@RequestBody SubmitQuestion[] question){
        service.submit(question, email, examId);
        return new CommonResult(12,"yes",1);
    }

    //添加考试
    @PostMapping("/provider/exam/addExam/{value}")
    public CommonResult addExam(@RequestBody AddExamInfo examInfo,@PathVariable List<String> value) throws ParseException {
        Integer id = service.createExam(examInfo);
        System.out.println("id"+id);
        service.addExamQuestion(id,value);
        return new CommonResult(12,"yes",id);
    }

    //添加题目到考试里面
    @GetMapping("/provider/exam/addExamQuestion/{examIds}/{value}")
    public CommonResult addExamQuestion(@PathVariable Integer examIds,@PathVariable List<String> value){
        System.out.println("examID: "+examIds);
        service.addExamQuestion(examIds,value);
        return new CommonResult(12,"题目添加成功",0);
    }

    @GetMapping(value = "/provider/exam/searchExam/{key}/{currentPage}/{pageSize}")
    public CommonResult searchUserInfo(@PathVariable String key,
                                       @PathVariable int currentPage,
                                       @PathVariable int pageSize) throws IOException {
        SearchRequest searchRequest = new SearchRequest("exams");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //分页,currentPage从0开始
        searchSourceBuilder.from(currentPage-1);
        searchSourceBuilder.size(pageSize);

        //精准匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", key);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("aaaa"+ Arrays.toString(response.getHits().getHits()));

        List<Integer> examIds = new ArrayList<Integer>();
        //解析结果
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        int i=0;
        for (SearchHit hit : response.getHits().getHits()) {
            list.add(hit.getSourceAsMap());
            examIds.add((Integer) list.get(i).get("serial_number"));
            i+=1;
            System.out.println();
        }

        List<Exam> exams = service.search(examIds,currentPage,pageSize);
        return new CommonResult(12,String.valueOf(exams.size()),exams);
    }

    @GetMapping("/provider/exam/updateExam/{examId}/{name}/{id}")
    public CommonResult updateExam(@PathVariable String name,@PathVariable Integer id){
        if (id<=8){
            Map<Object, Object> pages = stringRedisTemplate.opsForHash().entries("exam:"+id);
            stringRedisTemplate.opsForHash().put("exam:"+id,"name",name);
            service.updateExam(name,id);
            return new CommonResult(200,"yes");
        }else {
            service.updateExam(name,id);
            return new CommonResult(200,"yes");
        }
    }




}
