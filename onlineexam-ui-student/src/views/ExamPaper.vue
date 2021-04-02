<template>
  <div>
    <el-menu
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#00aeff"
        text-color="#fff"
        active-text-color="#ffd04b">
      <el-menu-item index="1" class="logo"></el-menu-item>
      <el-menu-item index="2">
        <template slot="title">{{this.examInfo.name}}</template>
      </el-menu-item>
      <el-menu-item index="3" style="float: right"><el-button v-on:click="submitPaper()">退出</el-button></el-menu-item>
      <el-menu-item index="5" style="float: right">
        <el-button type="text" style="color: #ff0000;font-size: large">得分：{{this.userScore.score}}分</el-button>
      </el-menu-item>
      <el-menu-item index="6" style="float: right">
        <el-button type="text" style="color: #ff0000;font-size: large">满分：{{this.userScore.sum_score}}分</el-button>
      </el-menu-item>
      <el-menu-item index="7" style="float: right">
        <el-button type="text" style="color: #ff0000;font-size: large">排名：{{this.examInfo.rank}}名</el-button>
      </el-menu-item>
      <el-menu-item index="8" style="float: right">
        <el-button type="text" style="color: #ff0000;font-size: large">参加人数：{{this.examInfo.number_of_people}}名</el-button>
      </el-menu-item>

    </el-menu>
    <el-container style="border: 0px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu router :default-openeds="['1','2','3']">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>选择题</span>
            </template>
            <el-menu-item v-for="(item2,index1) in this.selectionQuestionNumber" v-on:click="test(index1)">
              第{{index1+1}}题
            </el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>判断题</span>
            </template>
            <el-menu-item v-for="(item2,index2) in this.judgeQuestionNumber" v-on:click="test1(index2)">
              第{{index2+1}}题
            </el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>填空题</span>
            </template>
            <el-menu-item v-for="(item2,index3) in this.fillingQuestionNumber" v-on:click="test2(index3)">
              第{{index3+1}}题
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <div style="float: left;margin-left: 5%;font-size: 30px" >
        <h4>{{this.question_type}}</h4>
        <label>题目：{{this.question_body}}</label>
        <br>
        <br>
        <el-radio-group v-if="selectionQuestionShow" v-model="selectionQuestionNumber[indexOfQuestion].submitAnswer">
          <el-radio :label="0">A.{{this.selectionQuestionOptions.A}}</el-radio>
          <el-radio :label="1" >B.{{this.selectionQuestionOptions.B}}</el-radio>
          <el-radio :label="2">C.{{this.selectionQuestionOptions.C}}</el-radio>
          <el-radio :label="3">D.{{this.selectionQuestionOptions.D}}</el-radio>
        </el-radio-group>
        <el-radio-group v-if="judgeQuestionShow" v-model="judgeQuestionNumber[indexOfQuestion].submitAnswer">
          <el-radio label="✔"></el-radio>
          <el-radio label="✖"></el-radio>
        </el-radio-group>
        <input v-model="fillingQuestionNumber[indexOfQuestion].submitAnswer" v-if="fillingQuestionShow"></input>
      </div>
    </el-container>
    <h2 style="width: 60%">答案：{{answer}}</h2>
  </div>
</template>

<script>
export default {
  name: "Exam",
  created() {
    this.userInfo.email = this.$route.params.email
    this.userInfo.userName = this.$route.params.userName
    this.exam_id = this.$route.params.exam_id
    console.log(this.exam_id)
    const _this = this
    axios.get('http://localhost:82/exam/showExamTime/'+_this.exam_id).then(function (resp) {
      _this.exam_time = resp.data['data']
      console.log('that'+_this.exam_time)
    })
    axios.get('http://localhost:84/question/showSelectionQuestion/'+_this.exam_id).then(function (resp) {
      _this.selectionQuestionNumber = resp.data['data']
    })
    axios.get('http://localhost:84/question/showJudgeQuestion/'+_this.exam_id).then(function (resp) {
      _this.judgeQuestionNumber = resp.data['data']

    })
    axios.get('http://localhost:84/question/showFillingQuestion/'+_this.exam_id).then(function (resp) {
      _this.fillingQuestionNumber = resp.data['data']
    })
    axios.get('http://localhost:85/score/getUserScore/'+this.userInfo.email+'/'+_this.exam_id).then(function (resp) {
      _this.userScore = resp.data['data']
    })
    axios.get('http://localhost:85/score/getExamInfo/'+_this.exam_id+'/'+this.userInfo.email).then(function (resp) {
      _this.examInfo = resp.data['data']
    })

  },
  data() {
    return {
      userInfo: {
        userName: '用户',
        email: '',
      },
      ranking: 0,
      sum_people: 0,
      sum_score: 0,
      color: "#00aeff",
      isCorrect: "✔",
      answer: "C",
      source: 100,
      submitTime: "2021年1月1日21点",
      indexOfQuestion: 0,
      selectionAnswer: {1:''},
      judgeAnswer: {1:''},
      fillingAnswer: {1:''},
      exam_id: 0,
      selectionQuestionShow: true,
      fillingQuestionShow: false,
      judgeQuestionShow: false,
      description: '这是考试，',
      exam_time: '',
      question_type:'选择题',
      question_body:'题目',
      selectionQuestionOptions: {
        A: '1',
        B: '2',
        C: '3',
        D: '4'
      },
      question:[{
        question_id: 1,
        answer: '',
        score: 0,
        submitAnswer: ''
      }],
      selectionQuestionNumber: [{
        id: 1,
        question_body: '',
        options:{A: '',B: '',C: '',D: ''},
        answer: '',
        score: 0,
        submitAnswer: 0
      }],
      judgeQuestionNumber: [
        {id: 1,question_body: '',options:{A: '✔',B: '✖'},answer: '',score:0,submitAnswer: 0},
      ],
      fillingQuestionNumber: [
        {id: 1,question_body: '',options:{A: ''},answer: '',score:0,submitAnswer: 0},
      ],
      desc: '距离考试开始还有10：',
      userScore: {
        exam_id: 0,
        email: '',
        score: 0,
        sum_score: 0,
      },
      examInfo:{
        number_of_people:0,
        rank:0,
        name:''
      }
    };
  },
  methods:{
    test(index){
      this.indexOfQuestion=index
      this.question_type = '选择题'
      console.log(this.question_type)
      this.question_body = this.selectionQuestionNumber[index].question_body
      this.selectionQuestionShow = true
      this.fillingQuestionShow = false
      this.judgeQuestionShow = false
      const _this = this
      axios.get('http://localhost:84/options/show/'+_this.selectionQuestionNumber[index].id).then(function (resp) {
        _this.selectionQuestionOptions.A = resp.data['data'][0].option
        _this.selectionQuestionOptions.B = resp.data['data'][1].option
        _this.selectionQuestionOptions.C = resp.data['data'][2].option
        _this.selectionQuestionOptions.D = resp.data['data'][3].option
        console.log(_this.selectionQuestionOptions.A)
      })
      this.getAnswer(_this.selectionQuestionNumber[index].id);
    },
    test1(index){
      this.indexOfQuestion=index
      console.log(this.selectionAnswer)
      this.question_type = '判断题'
      console.log(this.question_type)
      this.question_body = this.judgeQuestionNumber[index].question_body
      this.selectionQuestionShow = false
      this.fillingQuestionShow = false
      this.judgeQuestionShow = true
    },
    test2(index){
      this.indexOfQuestion=index
      this.question_type = '填空题'
      console.log(this.question_type)
      this.question_body = this.fillingQuestionNumber[index].question_body
      this.selectionQuestionShow = false
      this.fillingQuestionShow = true
      this.judgeQuestionShow = false
    },
    submitPaper(){
      const _this = this
      axios.post('http://localhost:82/exam/sumbmit/'+_this.exam_id+"/"
          +_this.selectionAnswer+"/"
          +_this.judgeQuestionShow+"/"
          +_this.fillingAnswer,{
        params:{
          token: sessionStorage.getItem('token')
        }
      }).then(function (resp) {
        _this.exam_time = resp.data['data']
        console.log('that'+_this.exam_time)
      })
    },
    getAnswer(id){
      const _this = this
      axios.get('http://localhost:85/score/getAnswer/'+id).then(function (resp) {
        _this.answer = resp.data['data']
      })
    }
  }
}
</script>

<style scoped>
.el-radio /deep/ .el-radio__label{
  font-size:30px !important;
}
</style>