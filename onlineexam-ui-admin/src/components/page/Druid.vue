<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 数据源管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <el-tabs type="border-card">
        <el-tab-pane label="登录模块">
          <iframe src="http://localhost:8001/druid/datasource.html" style="height: 800px;width: 100%"></iframe>
        </el-tab-pane>
        <el-tab-pane label="注册模块">
          <iframe src="http://localhost:7001/druid/datasource.html" style="height: 800px;width: 100%"></iframe>
        </el-tab-pane>
        <el-tab-pane label="个人信息模块">
          <iframe src="http://localhost:7500/druid/datasource.html" style="height: 800px;width: 100%"></iframe>
        </el-tab-pane>
        <el-tab-pane label="考试模块">
          <iframe src="http://localhost:9001/druid/datasource.html" style="height: 800px;width: 100%"></iframe>
        </el-tab-pane>
        <el-tab-pane label="成绩模块">
          <iframe src="http://localhost:9001/druid/datasource.html" style="height: 800px;width: 100%"></iframe>
        </el-tab-pane>
        <el-tab-pane label="问题模块">
          <iframe src="http://localhost:9001/druid/datasource.html" style="height: 800px;width: 100%"></iframe>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
export default {
  name: "Durid"
}
</script>

<style scoped>

</style>