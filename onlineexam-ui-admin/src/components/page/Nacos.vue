<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 服务配置
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
    <iframe src="http://192.168.191.1:8848/nacos/index.html#/configurationManagement?dataId=&group=&appName=&namespace="
            style="height: 800px;width: 100%"></iframe>
  </div>
  </div>

</template>

<script>
export default {
  methods: {
      goBack(){
          this.$router.go(-1);
      }
  }
}
</script>


<style scoped>
    .error-page{
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        width: 100%;
        height: 100%;
        background: #f3f3f3;
        box-sizing: border-box;
    }
    .error-code{
        line-height: 1;
        font-size: 250px;
        font-weight: bolder;
        color: #2d8cf0;
    }
    .error-code span{
        color: #00a854;
    }
    .error-desc{
        font-size: 30px;
        color: #777;
    }
    .error-handle{
        margin-top: 30px;
        padding-bottom: 200px;
    }
    .error-btn{
        margin-left: 100px;
    }
</style>
