<template>
  <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <h1 align="center" style="font-size: 40px;margin-top: 5%">线上考试系统</h1>
    <div style="width: 30%;margin-left: 35%;margin-top: 5%">
      <el-form-item label="邮箱:" prop="email" :rules="[
      { required: true, message: '请输入邮箱地址', trigger: 'blur' },
      { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
    ]">
        <el-input type="email" v-model="ruleForm.email" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码:" prop="pass">
        <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="验证码:" prop="code">
        <el-input style="width: 74%;float: left" v-model="ruleForm.code"></el-input>
        <div>
          <el-button style="font-size: 15px;width: 25%;text-align: center;float: right" @click="test()">{{ruleForm.codeEmail}}</el-button>
        </div>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
        <el-button type="primary" @click="goToRegister()">注册</el-button>
      </el-form-item>
    </div>
  </el-form>
</template>

<script>
export default {
  name: "Login",
  data() {
    var checkEmail = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入邮箱'));
      }
    };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      }
    };
    var validateCode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'));
      }else if (value !== this.ruleForm.codeEmail) {
        callback(new Error('验证码输入有误'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        email: '',
        pass: '',
        code: '',
        codeEmail: '点击刷新',
        test: ''
      },
      rules: {
        email: [
          { validator: checkEmail, trigger: 'blur' }
        ],
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        code: [
          {validator: validateCode, trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm() {
      if (this.ruleForm.code===''||this.ruleForm.code!==this.ruleForm.codeEmail){
        return false
      }
          const _this = this
          axios.get('http://localhost:80/login/' + _this.ruleForm.email + '/' + _this.ruleForm.pass).then(function (resp) {
            _this.ruleForm.test = resp.data['message']
            if (resp.data['message'] === '该邮箱未注册') {
              alert("该邮箱未注册！")
            } else if (resp.data['message'] === "密码错误") {
              alert("密码错误")
            } else {
              alert("登陆成功")
              _this.$router.replace('/home')
            }
          })

    },
    goToRegister(){
      this.$router.replace('/register')
    },
    test(){
      const _this = this
      axios.get('http://localhost:80/login/getCode').then(function (resp) {
        _this.ruleForm.codeEmail = resp.data['data']
      })
    }
  }
}
</script>

<style scoped>

</style>