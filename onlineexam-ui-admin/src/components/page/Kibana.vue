<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> kibana
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <iframe src="http://49.232.4.183:5601/app/kibana#/dev_tools/console" style="height: 800px;width: 100%"></iframe>
    </div>
  </div>
</template>

<script>
export default {
  name: "Kibana"
}
</script>

<style scoped>

</style>