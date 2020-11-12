<template>
  <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <h1 align="center" style="font-size: 40px;margin-top: 5%">线上考试系统</h1>
    <div style="width: 30%;margin-left: 35%;margin-top: 5%">
      <el-form-item label="邮箱:" prop="email">
        <el-input type="email" v-model="ruleForm.email" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码:" prop="pass">
        <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="确认密码:" prop="checkPass">
        <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="验证码:" prop="code">
        <el-input style="width: 65%;float: left" v-model="ruleForm.code"></el-input>
        <el-button type="primary" @click="getVerificationCode" style="width: 30%;float: right">{{ruleForm.content}}</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm()">立即注册</el-button>
        <el-link type="primary" @click="goToLogin" style="margin-left: 3%">使用已有账号登录</el-link>
      </el-form-item>
    </div>
  </el-form>
</template>

<script>
export default {
  name: "Register",
  data() {
    var validateEmail = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入邮箱'));
      }
    };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    var validateCode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'));
      }
    };
    return {
      ruleForm: {
        email: '',
        pass: '',
        checkPass: '',
        code: '',
        codeFromEmail: '',
        message: '',
        content: '发送验证码',
        totalTime: 60
      },
      rules: {
        email: [
          { validator: validateEmail, trigger: 'blur' }
        ],
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        code: [
          {validator: validateCode, trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm() {
      this.ruleForm.message = this.ruleForm.code
      if (this.ruleForm.code===this.ruleForm.codeFromEmail){
            const _this = this
            axios.get('http://localhost:81/register/'+_this.ruleForm.email+'/'+_this.ruleForm.pass).then(function (resp) {
            })
            alert('注册成功！');
            _this.$router.replace('/')
          }else {
            alert("验证码错误！")
          }
    },
    getVerificationCode(){
      let clock = window.setInterval(() => {
        this.ruleForm.totalTime--
        this.ruleForm.content = this.ruleForm.totalTime + 's后重新发送'
        if (this.ruleForm.totalTime < 0) {     //当倒计时小于0时清除定时器
          window.clearInterval(clock)
          this.ruleForm.content = '重新发送验证码'
          this.ruleForm.totalTime = 60
        }
      },1000)
      const _this = this
      axios.get('http://localhost:81/register/send/'+_this.ruleForm.email).then(function (resp) {
        _this.ruleForm.message = resp.data['message']
        if (resp.data['message']==="验证码已经发送"){
          _this.ruleForm.codeFromEmail = resp.data['data']
        }else {
          alert("该邮箱已被注册！")
          window.clearInterval(clock)
          _this.ruleForm.content = '发送验证码'
          _this.ruleForm.totalTime = 60
        }
      })
    },
    goToLogin(){
      this.$router.replace('/')
    }
  }
}
</script>

<style scoped>

</style>