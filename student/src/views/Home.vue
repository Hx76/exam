<template>
  <div style="background: #e4f0ef">
    <Navigation></Navigation>
    <Search></Search>
    <ExamList></ExamList>
    <Footer></Footer>
  </div>

</template>

<script>


import Navigation from "@/views/Navigation";
import ExamList from "@/views/ExamList";
import Login from "@/views/Login";
import Search from "@/views/Search";
import Footer from "@/views/Footer";
export default {
  name: 'Home',
  components: {Footer, Search, Login, ExamList, Navigation},
  methods:{
    create(){

    },
    student(){
      const _this = this
      axios.get('http://localhost:82/exam').then(function (resp) {
        _this.ruleForm.test = resp.data['message']
        if (resp.data['message'] === '查询成功') {
          alert("Yes")
        }else {
          alert("No")
        }
      })
    },
    teacher(){
      const _this = this
      axios.get('http://localhost:84/question/').then(function (resp) {
        _this.ruleForm.test = resp.data['message']
        if (resp.data['message'] === '查询成功') {
          alert("查询成功")
        } else {
          alert("查询失败")
        }
      })
    }
  }
}
</script>
<style>
  body{
    margin:0;
    padding:0;
  }

</style>