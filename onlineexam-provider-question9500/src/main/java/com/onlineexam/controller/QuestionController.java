package com.onlineexam.controller;

import com.onlineexam.entities.Options;
import com.onlineexam.entities.Question;
import com.onlineexam.service.QuestionService;
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
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class QuestionController {
    @Resource
    private QuestionService service;
    @Resource
    private RestHighLevelClient client;

    //展示所有选择题
    @GetMapping(value = "/provider/question/showSelectionQuestion/{examId}")
    public CommonResult showSelectionQuestion(@PathVariable int examId){
        List<Question> questions = service.showSelectionQuestion(examId);
        return new CommonResult(12,"yes",questions);
    }

    //展示所有判断题
    @GetMapping(value = "/provider/question/showJudgeQuestion/{examId}")
    public CommonResult showJudgeQuestion(@PathVariable int examId){
        List<Question> questions = service.showJudgeQuestion(examId);
        return new CommonResult(12,"yes",questions);
    }

    //展示所有填空题
    @GetMapping(value = "/provider/question/showFillingQuestion/{examId}")
    public CommonResult showFillingQuestion(@PathVariable int examId){
        List<Question> questions = service.showFillingQuestion(examId);
        return new CommonResult(12,"yes",questions);
    }

    //分页展示所有题
    @GetMapping("/provider/question/showAll/{currentPage}/{pageSize}")
    public CommonResult showAll(@PathVariable int currentPage,@PathVariable int pageSize){
        List<Question> questions = service.showAll(currentPage, pageSize);
        return new CommonResult(12,"yes",questions);
    }

    //通过id展示题目
    @GetMapping(value = "/provider/question/showQuestionById/{questionId}")
    public CommonResult showQuestionById(@PathVariable Integer questionId){
        List<Question> questions = service.showQuestionByExam(questionId);
        return new CommonResult(12,"yes",questions);
    }

    //修改题目
    @PostMapping(value = "/provider/question/update")
    public CommonResult updateQuestion(@RequestBody Question question){
        service.updateQuestion(question);
        return new CommonResult(12,"yes","");
    }

    //删除题目
    @GetMapping(value = "/provider/question/delete/{questionId}")
    public CommonResult deleteQuestion(@PathVariable Integer questionId){
        service.deleteQuestion(questionId);
        return new CommonResult(12,"yes","");
    }

    //根据考试统计所有题目
    @GetMapping(value = "/provider/question/countAll/{email}")
    public CommonResult countQuestion(@PathVariable String email){
        Integer num = service.countAll();
        System.out.println(num);
        String str = service.getPerm(email);
        return new CommonResult(12,str,num);
    }

    //统计所有题目
    @GetMapping(value = "/provider/question/countAll")
    public CommonResult countAll(){
        Integer num = service.countAll();
        System.out.println(num);
        return new CommonResult(12,"123",num);
    }

    //添加题目
    @PostMapping("/provider/question/addQuestion")
    public CommonResult addQuestion(@RequestBody Question question) throws IOException {
        System.out.println("添加问题啊");
        if (question.getType_id().equals("选择题")){
            question.setType_id("1");
        }else if (question.getType_id().equals("判断题")){
            question.setType_id("2");
        }else {
            question.setType_id("3");
        }
        Integer id = service.addQuestion(question.getQuestion_body(), question.getType_id(), question.getCreator(), question.getScore(), question.getAnswer());
        String type="";
        System.out.println(question.getType_id());
        System.out.println(question.getAnswer());
        IndexRequest indexRequest = new IndexRequest("questions")
                .id(id.toString())
                .source("question_id",id,
                        "question_body",question.getQuestion_body(),
                        "creator",question.getCreator(),
                        "score",question.getScore());
        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
        return new CommonResult(12,"123",id);
    }

    //添加选项
    @PostMapping("/provider/question/addOptions")
    public CommonResult addOptions( @RequestBody List<Options> options){
        return new CommonResult(12,"123",service.addOptions(options));
    }

    //搜索！！！！！
    @GetMapping("/provider/question/search/{key}/{currentPage}/{pageSize}")
    public CommonResult search(@PathVariable String key,@PathVariable int currentPage,@PathVariable int pageSize) throws IOException {
        SearchRequest searchRequest = new SearchRequest("questions");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //分页,currentPage从0开始
        searchSourceBuilder.from(currentPage-1);
        searchSourceBuilder.size(pageSize);

        //精准匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("question_body", key);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println("aaaa"+Arrays.toString(response.getHits().getHits()));

        List<Integer> question_ids = new ArrayList<Integer>();
        //解析结果
        int i=0;
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : response.getHits().getHits()) {
            list.add(hit.getSourceAsMap());
            question_ids.add((Integer) list.get(0).get("question_id"));
            System.out.println(list);
            i+=1;
        }

        List<Question> questions = service.search(question_ids,currentPage,pageSize);
        return new CommonResult(12,String.valueOf(questions.size()),questions);
    }

    // 选择所有题目
    @GetMapping(value = "/provider/question/showAll")
    public CommonResult showAll(){
        List<Question> questions = service.showAll();
        return new CommonResult(12,"123",questions);
    }
}
