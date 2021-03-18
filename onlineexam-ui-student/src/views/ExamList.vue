<template>
  <div style="width: 70%;margin-left: 15%" >
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
                  label="操作">
                <template slot-scope="scope">
                <el-button type="text" @click="joinExam(scope.row)">加入考试</el-button>
                </template>
              </el-table-column>
            </el-table>
            <el-dialog
                title="提示"
                :visible.sync="dialogVisible"
                width="30%"
                append-to-body
                :before-close="handleClose">
              <el-form :model="form">
                <el-form-item label="请输入考试邀请码:">
                  <el-input v-model="inputKey" autocomplete="off"></el-input>
                </el-form-item>
              </el-form>
              <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="goToExam()">确 定</el-button>
            </span>
            </el-dialog>
            <el-pagination style="margin-top: 2%"
                           background
                           layout="prev, pager, next"
                           page-size=9
                           :total="total"
                           @current-change="page">
            </el-pagination>


          </div>
</template>

<script>
import Navigation from "@/views/Navigation";
export default {
  name: "ExamList",
  components: {Navigation},
  created() {
    this.userInfo.email = this.$route.params.email
    this.userInfo.userName = this.$route.params.userName
    console.log(sessionStorage.getItem('token'))
    const _this = this
    axios.get(
          'http://localhost:9527/exam/countAll',
        {
          params: {
            token: sessionStorage.getItem('token')
          }
        }
    ).then(function (resp) {
      console.log("bushibabushiba")
      _this.total = resp.data['data']
      console.log(resp.data)
    }).catch(function (err){

    })
    axios.get('http://localhost:9527/exam/showAll/1/8',
        {
          params: {
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
      currentDate: new Date(),
      total: 0,
      dialogVisible: false,
      key: 0,
      inputKey: 0,
      exam_id: '',
      question_length: 0,
      tableData: [{
        id: '',
        name: '',
        exam_serial_number: 0,
        duration: '',
        start_time: '',
        creator: '',
        state: '',
        total_points: '',
        creation_time: '',
        number_of_people: ''
      }]
    };
  },
  methods:{
    joinExam(e){
      var rowInfo = JSON.stringify(e);
      var json = eval('(' + rowInfo + ')');
      this.key = json.exam_serial_number
      this.exam_id = json.exam_serial_number;
      console.log('dddd'+this.key)
      this.dialogVisible=true

    },
    goToExam(){
      console.log(this.inputKey+"="+this.key)
      const _this = this
      axios.get('http://localhost:9527/exam/showExamQuestion/'+this.inputKey,
          {
            params: {
              token: sessionStorage.getItem('token')
            }
          }).then(function (resp) {
        console.log(resp.data)
        console.log(resp.data['data'].length+'.......')
        _this.question_length=resp.data['data'].length
        console.log('1111'+_this.question_length)
        if (_this.question_length!==0){
          console.log('go to here')
          _this.$router.push({
            path: "/exam",
            name: "exam",
            params: { examId: _this.exam_id,username: _this.userInfo.userName,email: _this.userInfo.email}
          });
          _this.$router.replace('/exam')
        }else {
          alert("邀请码错误");
        }
      })
      console.log(this.question_length)


    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    page(currentPage) {
      const _this = this
      axios.get('http://localhost:9527/exam/countAll',
          {
            params: {
              token: sessionStorage.getItem('token')
            }
          }).then(function (resp) {
        _this.total = resp.data['data']
        console.log(resp.data)
      })
      axios.get('http://localhost:9527/exam/showAll/' + currentPage + '/8',
          {
            params: {
              token: sessionStorage.getItem('token')
            }
          }).then(function (resp) {
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
  }
}
</script>

<style lang="less" scoped>
.card-avatar {
  width: 48px;
  height: 48px;
  border-radius: 48px;
}
.ant-card-actions {
  background: #f7f9fa;
  li {
    float: left;
    text-align: center;
    margin: 12px 0;
    color: rgba(0, 0, 0, 0.45);
    width: 50%;
    &:not(:last-child) {
      border-right: 1px solid #e8e8e8;
    }
    a {
      color: rgba(0, 0, 0, .45);
      line-height: 22px;
      display: inline-block;
      width: 100%;
      &:hover {
        color: #1890ff;
      }
    }
  }
}
.new-btn {
  background-color: #fff;
  border-radius: 2px;
  width: 100%;
  height: 188px;
}
.meta-content {
  position: relative;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  height: 64px;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}
</style>
