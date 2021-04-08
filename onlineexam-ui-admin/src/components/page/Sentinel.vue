<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 服务治理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
    <iframe src="http://localhost:9999/#/dashboard/home" style="height: 800px;width: 100%"></iframe>
  </div>
  </div>
</template>

<script>
    export default {
        data: function(){
            return {}
        }
    }
</script>

<style scoped>
h1{
    text-align: center;
    margin: 30px 0;
}
p{
    line-height: 30px;
    margin-bottom: 10px;
    text-indent: 2em;
}
.logout{
    color: #409EFF;
}
</style>