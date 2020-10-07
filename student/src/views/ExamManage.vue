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
        <el-form :model="formData">
          <el-form-item label="考试名称:">
            <el-input autocomplete="off" v-model="formData.question_body"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div>
        <el-form :model="formData">
          <el-form-item label="考试时间:">
            <el-input autocomplete="off" v-model="formData.options"></el-input>
          </el-form-item>
          <el-form-item label="开始时间:" :label-width="formLabelWidth">
            <el-input autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="选择题总分:" :label-width="formLabelWidth">
            <el-input autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="判断题总分:" :label-width="formLabelWidth">
            <el-input autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="填空题总分:" :label-width="formLabelWidth">
            <el-input autocomplete="off" v-model="formData.score"></el-input>
          </el-form-item>
          <el-form-item label="选择题目:">
            <el-select>
              <el-option label="第一题" value="shanghai"></el-option>
              <el-option label="第二题" value="beijing"></el-option>
              <el-option label="第三题" value="beijing"></el-option>
              <el-option label="第四题" value="beijing"></el-option>
            </el-select>
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
          width="80">
      </el-table-column>
      <el-table-column
          prop="question_body"
          label="考试名称"
          width="180">
      </el-table-column>
      <el-table-column
          prop="score"
          label="开始时间">
      </el-table-column>
      <el-table-column
          prop="creator"
          label="创建人">
      </el-table-column>
      <el-table-column
          prop="type_id"
          label="时长">
      </el-table-column>
      <el-table-column
          prop="type_id"
          label="状态">
      </el-table-column>
      <el-table-column
          prop="type_id"
          label="总分">
      </el-table-column>
      <el-table-column
          prop="type_id"
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
    const _this = this
    axios.get('http://localhost:84/question/countAll').then(function (resp) {
      _this.total = resp.data['data']
      console.log(resp.data)
    })
    axios.get('http://localhost:84/question/showAll/1/8').then(function (resp) {
      _this.tableData = resp.data['data']
    })
  },
  data() {
    return {
      updateDialogVisible: false,
      dialogVisible: false,
      centerDialogVisible: false,
      active: 0,
      total: 0,
      options: [{
        value: '选项1',
        label: '选择题'
      }, {
        value: '选项2',
        label: '判断题'
      }, {
        value: '选项3',
        label: '填空题'
      }],
      value: '',
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
        question_body: '',
        score: '',
        creator: '',
        type_id: '',
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
      axios.get('http://localhost:84/question/countAll').then(function (resp) {
        _this.total = resp.data['data']
        console.log(resp.data)
      })
      axios.get('http://localhost:84/question/showAll/' + currentPage + '/8').then(function (resp) {
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
    }
  }
}
</script>

<style scoped>

</style>