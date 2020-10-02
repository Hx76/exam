<template>
  <div style="background: #f0f2f5;">
    <Navigation></Navigation>
      <div style="width: 70%;margin-left: 15%;margin-top: 1%;background: #fff;height: 900px">
        <label style="width: 10%;margin-top: 2%;margin-left: 5%">问题编号：</label>
        <el-input style="width: 37%;margin-top: 2%;margin-left: 1%"></el-input>
        <el-button style="width: 10%;margin-left: 1%">搜索</el-button>
        <el-button style="margin-left: 1%;margin-top: 2%;background: #00aeff;color: #e4f0ef;width: 10%" @click="dialogVisible = true">+ 新建</el-button>
        <el-dialog
            title="创建问题"
            :visible.sync="dialogVisible"
            width="40%"
            :before-close="handleClose">
          <div>
            <el-form :model="form">
              <el-form-item label="题干:" :label-width="formLabelWidth">
                <el-input  autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
          </div>
          <div>
            <el-form>
              <el-form-item label="题目类型:" :label-width="formLabelWidth">
                <el-select  placeholder="请选择题目类型">
                  <el-option label="选择题" value="shanghai"></el-option>
                  <el-option label="判断题" value="beijing"></el-option>
                  <el-option label="填空题" value="beijing"></el-option>
                </el-select>
              </el-form-item>
            </el-form>
          </div>
          <div>
            <el-form :model="form">
              <el-form-item label="选项1:" :label-width="formLabelWidth">
                <el-input  autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="选项2:" :label-width="formLabelWidth">
                <el-input  autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="选项3:" :label-width="formLabelWidth">
                <el-input  autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="选项4:" :label-width="formLabelWidth">
                <el-input  autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="答案:" :label-width="formLabelWidth">
                <el-select  placeholder="请选择答案">
                  <el-option label="A" value="shanghai"></el-option>
                  <el-option label="B" value="beijing"></el-option>
                  <el-option label="C" value="beijing"></el-option>
                  <el-option label="D" value="beijing"></el-option>
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
              prop="date"
              type="selection">
            <el-checkbox></el-checkbox>
          </el-table-column>
          <el-table-column
              prop="date"
              label="序号"
              width="180">
          </el-table-column>
          <el-table-column
              prop="name"
              label="题干"
              width="180">
          </el-table-column>
          <el-table-column
              prop="address"
              label="分数">
          </el-table-column>
          <el-table-column
              prop="address"
              label="创建人">
          </el-table-column>
          <el-table-column
            prop="address"
            label="题型">
        </el-table-column>
          <el-table-column
              prop="address"
              label="更新时间">
          </el-table-column>
          <el-table-column
              prop="address"
              label="操作">
            <p>详情</p>
            <p>删除</p>
          </el-table-column>
        </el-table>
        <el-pagination style="margin-top: 2%"
                       background
                       layout="prev, pager, next"
                       :total="1000">
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
  data() {
    return {
      dialogVisible: false,
      active: 0,
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }]
    }
  },
  methods: {
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    next() {
      if (this.active++ > 2) this.active = 0;
    }
  }

}
</script>

<style scoped>

</style>