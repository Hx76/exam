<template>
  <div>
    <Navigation></Navigation>
    <Search></Search>
    <ExamList></ExamList>
  </div>

</template>

<script>


import Navigation from "@/views/Navigation";
import ExamList from "@/views/ExamList";
import Login from "@/views/Login";
import Search from "@/views/Search";
export default {
  name: 'Home',
  components: {Search, Login, ExamList, Navigation},
}
</script>
<style>
  body{
    margin:0;
    padding:0;
  }
</style>