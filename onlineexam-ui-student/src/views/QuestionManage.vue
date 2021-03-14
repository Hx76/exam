<template>
  <div style="background: #f0f2f5;">
    <Navigation></Navigation>
    <div style="width: 70%;margin-left: 15%;margin-top: 1%;background: #fff;height: 900px">
      <label style="width: 10%;margin-top: 2%;margin-left: 5%">问题编号：</label>
      <el-input style="width: 37%;margin-top: 2%;margin-left: 1%"></el-input>
      <el-button style="width: 10%;margin-left: 1%">搜索</el-button>
      <el-button style="margin-left: 1%;margin-top: 2%;background: #00aeff;color: #e4f0ef;width: 10%"
                 @click="dialogVisible = true">+ 新建
      </el-button>

      <el-dialog
          title="创建问题"
          :visible.sync="dialogVisible"
          width="40%"
          :before-close="handleClose">
        <div>
          <el-form :model="dialogData">
            <el-form-item label="题干:">
              <el-input autocomplete="off" v-model="dialogData.body"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <div>
          <el-form>
            <el-form-item label="题目类型:">
              <el-select v-model="dialogData.type" placeholder="请选择题目类型">
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
            <el-form-item
                v-for="item in dialogData.option"
                :key="item.option_id"
                :value="item.option_body">
              <el-input v-model="item.option_body" v-bind:disabled="item.disable">
                <template slot="prepend">{{item.value}}</template>
              </el-input>
            </el-form-item>
          </el-form>
          <el-form :model="dialogData">
            <el-form-item label="答案:" :label-width="formLabelWidth">
              <el-select placeholder="请选择答案" v-model="dialogData.answers">
                <el-option
                    v-for="item in answer_options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                    :disabled="item.disabled">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="分值:">
              <el-input autocomplete="off" v-model="dialogData.scores"></el-input>
            </el-form-item>
          </el-form>
        </div>

        <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible = true">确 定</el-button>
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
            width="180">
        </el-table-column>
        <el-table-column
            prop="question_body"
            label="题干"
            width="180">
        </el-table-column>
        <el-table-column
            prop="score"
            label="分数">
        </el-table-column>
        <el-table-column
            prop="creator"
            label="创建人">
        </el-table-column>
        <el-table-column
            prop="type_id"
            label="题型">
        </el-table-column>
        <el-table-column
            label="操作">
          <template slot-scope="scope">
          <el-button type="text" @click="updateQuestion(scope.row)">详情</el-button>
            <el-dialog
                title="题目详情"
                :visible.sync="updateDialogVisible"
                width="40%"
                :before-close="handleClose">
              <div>
                <el-form :model="dialogData">
                  <el-form-item label="题干:">
                    <el-input autocomplete="off" v-model="dialogData.body"></el-input>
                  </el-form-item>
                </el-form>
              </div>
              <div>
                <el-form>
                  <el-form-item label="题目类型:">
                    <el-select v-model="dialogData.type" placeholder="请选择题目类型">
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
                  <el-form-item
                      v-for="item in dialogData.option"
                      :key="item.option_id"
                      :value="item.option_body">
                    <el-input v-model="item.option_body" v-bind:disabled="item.disable">
                      <template slot="prepend">{{item.value}}</template>
                    </el-input>
                  </el-form-item>
                </el-form>
                <el-form :model="dialogData">
                  <el-form-item label="答案:" :label-width="formLabelWidth">
                    <el-select placeholder="请选择答案" v-model="dialogData.answers">
                      <el-option
                          v-for="item in answer_options"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value"
                          :disabled="item.disabled">
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="分值:">
                    <el-input autocomplete="off" v-model="dialogData.scores"></el-input>
                  </el-form-item>
                </el-form>
              </div>

              <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible = true">确 定</el-button>
              </span>
            </el-dialog>
          <el-button type="text" @click="centerDialogVisible = true">删除</el-button>
          </template>
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
import Navigation from "@/views/Navigation";
import Footer from "@/views/Footer";

export default {
  name: "QuestionManage",
  components: {Footer, Navigation},
  created() {
    console.log("邮箱："+this.userInfo.email)
    console.log(this.userInfo.userName)
    const _this = this
    axios.get('http://localhost:9527/question/countAll',
        {
          params:{
            token: sessionStorage.getItem('token')
          }
        }).then(function (resp) {
          if (resp.data['message']!=="没有权限"){
            console.log(resp.data['message'])
            _this.total = resp.data['data']
          }else {
            alert("没有权限！")
          }

      console.log(resp.data)
    })
    axios.get('http://localhost:9527/question/showAll/1/8',
        {
          params:{
            token: sessionStorage.getItem('token')
          }
        }).then(function (resp) {
      _this.tableData = resp.data['data']
    })
  },
  data() {
    return {
      userInfo: {
        userName: '用户',
        email: '',
      },
      formLabelWidth: 10,
      test: true,
      updateDialogVisible: false,
      dialogVisible: false,
      centerDialogVisible: false,
      active: 0,
      total: 0,
      option_number: 0,
      options: [{
        value: '选择题',
        label: '选择题'
      }, {
        value: '判断题',
        label: '判断题'
      }, {
        value: '填空题',
        label: '填空题'
      }],
      answer_options: [{
        value: '选项1',
        label: '选项1',
        disabled: false
      }, {
        value: '选项2',
        label: '选项2',
        disabled: false
      }, {
        value: '选项3',
        label: '选项3',
        disabled: false
      },{
        value: '选项4',
        label: '选项4',
        disabled: false
      }],
      value: '',
      formData: [{
        question_body: '',
        score: '',
        creator: '',
        type_id: '',
      }],
      tableData: [{
        id: '',
        question_body: '',
        score: '',
        creator: '',
        type_id: '',
      }],
      dialogData:{
        body: '123456',
        type: '',
        option: [{
            value: '选项1',
            option_id:'',
            option_body: '',
            disable: false
          },
          {
            value: '选项2',
            option_id:'',
            option_body: '',
            disable: false
          },
          {
            value: '选项3',
            option_id:'',
            option_body: '',
            disable: false
          },
          {
            value: '选项4',
            option_id:'',
            option_body: '',
            disable: false
          }],
        answers: '',
        scores: ''
      }
    }
  },
  watch: {
    'dialogData.answers'(val,oldVal){
      if (val!==""){
        if (this.dialogData.type===""){
          alert("请先选择题目类型！");
          this.dialogData.answers = "";
        }
      }
    },
    'dialogData.type'(val, oldVal) {
      console.log("inputVal = " + val + " , oldValue = " + oldVal)
      if (val==='判断题'){
        this.dialogData.option[0].option_body="对"
        this.dialogData.option[1].option_body="错"
        this.dialogData.option[1].disable=false;
        this.dialogData.option[2].disable=true;
        this.dialogData.option[3].disable=true;
        this.answer_options[2].disabled=true
        this.answer_options[3].disabled=true
        this.answer_options[1].disabled=false
      }else if(val==='选择题'){
        this.dialogData.body = '456'
        this.dialogData.option[0].option_body=""
        this.dialogData.option[1].option_body=""
        this.dialogData.option[2].disable=false;
        this.dialogData.option[3].disable=false;
        this.dialogData.option[0].disable=false;
        this.dialogData.option[1].disable=false;
        this.answer_options[2].disabled=false
        this.answer_options[3].disabled=false
        this.answer_options[1].disabled=false
        this.answer_options[0].disabled=false
      }else{
        this.dialogData.body = '789'
        this.dialogData.option[0].option_body=""
        this.dialogData.option[1].option_body=""
        this.dialogData.option[1].disable=true;
        this.dialogData.option[2].disable=true;
        this.dialogData.option[3].disable=true;
        this.answer_options[2].disabled=true
        this.answer_options[3].disabled=true
        this.answer_options[1].disabled=true
        this.answer_options[0].disabled=true
      }
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
      axios.get('http://localhost:84/question/countAll').then(function (resp) {
        _this.total = resp.data['data']
        console.log("zhelizheli"+resp.data)
      })
      axios.get('http://localhost:84/question/showAll/' + currentPage + '/8').then(function (resp) {
        _this.tableData = resp.data['data']

      })
    },
    deleteQuestion(){
      this.centerDialogVisible = false
    },
    updateQuestion(e){
      this.updateDialogVisible = true
      var rowInfo = JSON.stringify(e);
      var json = eval('(' + rowInfo + ')');
      console.log(json.question_body)
      this.dialogData.body=json.question_body;
      this.dialogData.type=json.type_id;
      this.dialogData.scores=json.score;
    },
    createQuestion(){

    }

  }

}
</script>

<style scoped>

</style>