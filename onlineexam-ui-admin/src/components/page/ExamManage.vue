<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 考试管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box" align="center">
        <label style="width: 10%;margin-top: 2%;margin-left: 5%">考试名称：</label>
        <el-input style="width: 37%;margin-top: 2%;margin-left: 1%" v-model="searchKey"></el-input>
        <el-button style="width: 10%;margin-left: 1%" @click="search">搜索</el-button>
        <el-button style="margin-left: 1%;margin-top: 2%;background: #00aeff;color: #e4f0ef;width: 10%"
                   @click="dialogVisible = true">+ 新建
        </el-button>
      </div>

      <el-dialog
          title="创建考试"
          :visible.sync="dialogVisible"
          width="40%"
          :before-close="handleClose">
        <div>
          <el-form :model="examInfo">
            <el-form-item label="考试名称:">
              <el-input autocomplete="off" v-model="examInfo.name"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <div>
          <el-form :model="examInfo">
            <el-form-item label="考试时长:">
              <el-input autocomplete="off" v-model="examInfo.duration"></el-input>
            </el-form-item>
            <el-form-item label="开始时间:">
              <div class="block">
                <el-date-picker
                    v-model="examInfo.date"
                    type="date"
                    placeholder="选择日期">
                </el-date-picker>
              </div>
              <el-time-picker
                  v-model="examInfo.time"
                  :picker-options="{}"
                  placeholder="选择时间"
                  style="margin-left: 12%">
              </el-time-picker>
            </el-form-item>
            <el-form-item label="选择题目:">
              <el-select v-model="value" multiple filterable placeholder="请选择">
                <el-option
                    v-for="item in options"
                    :key="item.key"
                    :label="item.label"
                    :value="item.value"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </div>

        <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="createExam()">确 定</el-button>
  </span>
      </el-dialog>
      <el-divider style="margin-top: 1%"></el-divider>
      <el-table
          :data="tableData"
          style="width: 100%">
        <el-table-column
            type="selection">
          <el-checkbox></el-checkbox>
        </el-table-column>
        <el-table-column
            prop="id"
            label="序号"
            width="80">
        </el-table-column>
        <el-table-column
            prop="name"
            label="考试名称"
            width="180">
        </el-table-column>
        <el-table-column
            prop="start_time"
            label="开始时间">
        </el-table-column>
        <el-table-column
            prop="creator"
            label="创建人">
        </el-table-column>
        <el-table-column
            prop="duration"
            label="时长">
        </el-table-column>
        <el-table-column
            prop="state"
            label="状态">
        </el-table-column>
        <el-table-column
            prop="total_points"
            label="总分">
        </el-table-column>
        <el-table-column
            prop="number_of_people"
            label="参加人数">
        </el-table-column>
        <el-table-column
            label="操作">
          <template slot-scope="scope">
          <el-button type="text" @click="viewReport(scope.row)">详情</el-button>
          <el-dialog
              title="考试详情"
              :visible.sync="updateDialogVisible"
              width="80%"
              :before-close="handleClose">
            <div>
              <el-form :model="dialogData">
                <el-form-item label="成绩单:">
                </el-form-item>
              </el-form>
            </div>
            <el-table
                :data="tableData1"
                style="width: 100%">
              <el-table-column
                  prop="rank"
                  label="名次"
                  width="180">
              </el-table-column>
              <el-table-column
                  prop="name"
                  label="用户名"
                  width="180">
              </el-table-column>
              <el-table-column
                  prop="email"
                  label="邮箱">
              </el-table-column>
              <el-table-column
                  prop="score"
                  label="分数">
              </el-table-column>
              <el-table-column
                  label="操作">
                <template slot-scope="scope">
                  <el-button type="text" @click="viewExamPaper(scope.row)">查看试卷</el-button>
                </template>
              </el-table-column>
            </el-table>

            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible = true">确 定</el-button>
              </span>
          </el-dialog>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination style="margin-top: 2%"
                     align="center"
                     background
                     layout="prev, pager, next"
                     page-size=8
                     :total="total"
                     @current-change="page">
      </el-pagination>
    </div>
    <Footer/>
  </div>
</template>

<script>
    export default  {
      name: "ExamManage",
      created() {
        const _this = this
        axios.get('http://localhost:82/exam/countAll').then(function (resp) {
          _this.total = resp.data['data']
          console.log(resp.data)
        })
        axios.get('http://localhost:82/exam/showAll/1/8').then(function (resp) {
          _this.tableData = resp.data['data']
        })
        axios.get('http://localhost:84/question/showAll').then(function (resp) {
          console.log("长度："+resp.data['data'].length)
          for(let i=0; i<resp.data['data'].length; i++){
            console.log(i+","+resp.data['data'].length)
            _this.options.push({value: resp.data['data'][i].question_body,key: resp.data['data'][i].id})
          }
        })
      },
      data() {
        return {
          searchKey: '',
          updateDialogVisible: false,
          dialogVisible: false,
          centerDialogVisible: false,
          examNumber: 0,
          active: 0,
          total: 0,
          examInfo:{
            name: '',
            duration: '',
            date: '',
            time: '',
            creator: '',
          },
          options: [],

          formData: [{
            question_body: '',
            score: '',
            creator: '',
            type_id: '',
            option: [],
            answer: ''
          }],
          value: [],
          dialogData:[{
            question_body: '1',
            score: '5',
            creator: '4',
            type_id: '5',
            option: [],
            answer: '7'
          }],
          tableData1: [{
            rank: '',
            name: '',
            email: '',
            score: '',
          }],
          tableData: [{
            id: '',
            name: '',
            duration: '',
            start_time: '',
            creator: '',
            state: '',
            total_points: '',
            creation_time: '',
            number_of_people: ''
          }]
        }
      },
      methods: {
        handleClose(done) {
          this.$confirm('确认关闭？')
              .then(_ => {
                done();
              })
              .catch(_ => {
              });
        },

        page(currentPage) {
          const _this = this
          axios.get('http://localhost:82/exam/countAll').then(function (resp) {
            _this.total = resp.data['data']
            console.log(resp.data)
          })
          axios.get('http://localhost:82/exam/showAll/' + currentPage + '/8').then(function (resp) {
            _this.tableData = resp.data['data']

            if (resp.data['data']['type_id'] === 1) {

              _this.tableData.type_id = '选择题'
            } else if (_this.tableData.type_id === 2) {
              _this.tableData.type_id = '判断题'
            } else {
              _this.tableData.type_id = '填空题'
            }
          })
        },
        deleteQuestion(){
          this.centerDialogVisible = false
        },
        viewReport(e){
          this.updateDialogVisible = true
          var rowInfo = JSON.stringify(e);
          var json = eval('(' + rowInfo + ')');
          console.log("邀请码"+json.exam_serial_number)
          this.examNumber=json.exam_serial_number
          const _this = this
          axios.get('http://localhost:85/score/getReport/'+json.exam_serial_number).then(function (resp) {
            _this.tableData1 = resp.data['data']
            console.log(resp.data)
          })
        },
        viewExamPaper(e){
          var rowInfo = JSON.stringify(e);
          var json = eval('(' + rowInfo + ')');
          console.log("邮箱："+json.email)
          console.log("考试邀请码："+this.examNumber)
          this.$router.push({
            path: "/examPaper",
            name: 'examPaper',
            params: {email1: json.email,
              exam_id: this.examNumber}
          });
          this.$router.replace('/examPaper')
        },
        createExam(){
          console.log("输出data："+this.value)
          this.examInfo.creator=this.userInfo.email
          let dates=this.examInfo.date.getFullYear()+"-"+(this.examInfo.date.getMonth()+1)+"-"+this.examInfo.date.getDate()
          this.examInfo.date=dates.toString()
          let times=this.examInfo.time.getHours()+":"+(this.examInfo.time.getMinutes())+":"+this.examInfo.time.getSeconds()
          this.examInfo.time=times.toString()
          const _this = this
          console.log(_this.examId)
          console.log(typeof this.examInfo.date)
          console.log(typeof this.examInfo.time)
          console.log(typeof this.examInfo.name)
          console.log(typeof this.examInfo.duration)
          console.log(typeof this.examInfo.creator)
          axios.post('http://localhost:82/exam/addExam/'+this.value,this.examInfo).then(function (resp) {
            console.log("进这里了")
          })
          _this.dialogVisible=false
        },
        search(){
          if (this.searchKey!==''){
            const _this = this
            axios.get('http://localhost:82/exam/searchExam/'+this.searchKey+"/"+1+"/"+8).then(function (resp) {
              _this.total = Number(resp.data['message'])
              _this.tableData=resp.data['data']
            })
          }else {
            console.log("搜索为空")
            const _this = this
            axios.get('http://localhost:82/exam/countAll').then(function (resp) {
              _this.total = resp.data['data']
              console.log(resp.data)
            })
            axios.get('http://localhost:82/exam/showAll/1/8').then(function (resp) {
              _this.tableData = resp.data['data']
            })
          }
        }
      }
    }
</script>

<style scoped>

</style>