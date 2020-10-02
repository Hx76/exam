<template>
  <div style="float: left;margin-top: 2%;margin-left: 10%;font-size: 30px">
    <h4>单选题</h4>
    <label>1+1=?</label>
    <br>
    <el-radio-group style="margin-top: 10%;font-size: 30px">
      <el-radio :label="3">A. 1</el-radio>
      <br>
      <el-radio :label="6">B. 2</el-radio>
      <br>
      <el-radio :label="9">C. 3</el-radio>
      <br>
      <el-radio :label="9">D. 4</el-radio>
      <br>
    </el-radio-group>
  </div>
</template>

<script>
export default {
  name: "Question"
}
</script>

<style scoped>

</style>