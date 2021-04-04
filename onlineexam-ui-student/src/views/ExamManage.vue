<template>
  <div>
  <Navigation></Navigation>
  <div style="width: 70%;margin-left: 15%;margin-top: 1%;background: #fff;height: 900px">
    <label style="width: 10%;margin-top: 2%;margin-left: 5%">考试编号：</label>
    <el-input style="width: 37%;margin-top: 2%;margin-left: 1%"></el-input>
    <el-button style="width: 10%;margin-left: 1%">搜索</el-button>
    <el-button style="margin-left: 1%;margin-top: 2%;background: #00aeff;color: #e4f0ef;width: 10%"
               @click="dialogVisible = true">+ 新建
    </el-button>
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
        <el-button type="text" @click="updateDialogVisible = true">详情</el-button>
        <el-dialog title="题目详情" :visible.sync="updateDialogVisible" append-to-body>
          <div>
            <el-form :model="dialogData">
              <el-form-item  label="题干:">
                <el-input autocomplete="off" v-model="dialogVisible.question_body"></el-input>
              </el-form-item>
            </el-form>
          </div>
          <div>
            <el-form>
              <el-form-item label="题目类型:">
                <el-select v-model="dialogData.type_id" placeholder="请选择题目类型">
                  <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-form>
          </div>
          <div>
            <el-form :model="dialogData">
              <el-form-item label="选项1:">
                <el-input autocomplete="off" v-model="dialogData.options"></el-input>
              </el-form-item>
              <el-form-item label="选项2:" :label-width="formLabelWidth">
                <el-input autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="选项3:" :label-width="formLabelWidth">
                <el-input autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="选项4:" :label-width="formLabelWidth">
                <el-input autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="答案:" :label-width="formLabelWidth">
                <el-select placeholder="请选择答案">
                  <el-option label="选项1" value="shanghai"></el-option>
                  <el-option label="选项2" value="beijing"></el-option>
                  <el-option label="选项3" value="beijing"></el-option>
                  <el-option label="选项4" value="beijing"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="分值:">
                <el-input autocomplete="off" v-model="dialogData.score"></el-input>
              </el-form-item>
            </el-form>
          </div>
          <div slot="footer" class="dialog-footer">
            <el-button @click="updateDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="updateDialogVisible = false">确 定</el-button>
          </div>
        </el-dialog>
        <el-button type="text" @click="centerDialogVisible = true">删除</el-button>

        <el-dialog
            title="提示"
            :visible.sync="centerDialogVisible"
            width="30%"
            append-to-body
        >
          <span>确定要删除该条数据吗？</span>
          <span slot="footer" class="dialog-footer">
              <el-button @click="centerDialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="deleteQuestion()">确 定</el-button>
             </span>
        </el-dialog>
      </el-table-column>
    </el-table>
    <el-pagination style="margin-top: 2%"
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
import Footer from "@/views/Footer";
import Navigation from "@/views/Navigation";

export default {
  name: "ExamManage",
  components: {Footer, Navigation},
  created() {
    this.userInfo.email = this.$route.params.email
    this.userInfo.userName = this.$route.params.userName
    console.log("用户名："+this.userInfo.userName)
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
      userInfo: {
        userName: '用户',
        email: '',
      },
      updateDialogVisible: false,
      dialogVisible: false,
      centerDialogVisible: false,
      active: 0,
      total: 0,
      examIds: 0,
      examInfo:{
        name: '',
        duration: '',
        date: '',
        time: '',
        creator: '',
        },
      options: [],
      value: [],
      values: [],
      formData: [{
        question_body: '',
        score: '',
        creator: '',
        type_id: '',
        option: [],
        answer: ''
      }],
      dialogData:[{
        question_body: '1',
        score: '5',
        creator: '4',
        type_id: '5',
        option: [],
        answer: '7'
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
  }
}
</script>

<style scoped>

</style>