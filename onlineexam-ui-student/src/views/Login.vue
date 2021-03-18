<template>
  <div style="background-image: url('~@/assets/background.png')">
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm"
             label-width="100px" class="demo-ruleForm" >
      <h1 align="center" style="font-size: 40px;margin-top: 5%">线上考试系统</h1>
      <div style="width: 30%;margin-left: 35%;margin-top: 5%">
        <el-form-item label="邮箱:" prop="email" :rules="[
      { required: true, message: '请输入邮箱地址', trigger: 'blur' },
      { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
    ]">
          <el-input type="email" v-model="ruleForm.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码:" prop="pass">
          <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
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
  </div>

</template>

<script>

import request from "../../../onlineexam-ui-admin/src/utils/request";

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
      this.getBrowserInfo()
      if (this.ruleForm.code===''||this.ruleForm.code!==this.ruleForm.codeEmail){
        return false
      }
          const _this = this
          axios.post('http://localhost:80/login',this.ruleForm).then(function (resp) {
            _this.ruleForm.test = resp.data['message']
            sessionStorage.setItem("token",resp.data['data']);
            if (resp.data['message'] === '该邮箱未注册') {
              alert("该邮箱未注册！")
            } else if (resp.data['message'] === "密码错误") {
              alert("密码错误")
            } else {
              alert("登陆成功")
              _this.$router.push({
                path: "/home",
                name: "home",
                params: { activeIndex: '2',
                          email: _this.ruleForm.email}
              });
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
    },
    GetOs () {
      let sUserAgent = navigator.userAgent.toLocaleLowerCase()
      let isWin = (navigator.platform === 'Win32') || (navigator.platform === 'X64')
      let isMac = (navigator.platform === 'mac68k') || (navigator.platform === 'macppc') || (navigator.platform === 'macintosh') || (navigator.platform === 'macintel')
      if (isMac) return 'Mac'
      var isUnix = (navigator.platform === 'x11') && !isWin && !isMac
      if (isUnix) return 'Unix'
      var isLinux = (String(navigator.platform).indexOf('linux') > -1)
      if (isLinux) return 'Linux'
      if (isWin) {
        var isWin2K = sUserAgent.indexOf('windows nt 5.0') > -1 || sUserAgent.indexOf('windows 2000') > -1
        if (isWin2K) return 'Win2000'
        var isWinXP = sUserAgent.indexOf('windows nt 5.1') > -1 || sUserAgent.indexOf('windows xp') > -1
        if (isWinXP) return 'WinXP'
        var isWin2003 = sUserAgent.indexOf('windows nt 5.2') > -1 || sUserAgent.indexOf('windows 2003') > -1
        if (isWin2003) return 'Win2003'
        var isWinVista = sUserAgent.indexOf('windows nt 6.0') > -1 || sUserAgent.indexOf('windows vista') > -1
        if (isWinVista) return 'WinVista'
        var isWin7 = sUserAgent.indexOf('windows nt 6.1') > -1 || sUserAgent.indexOf('windows 7') > -1
        if (isWin7) return 'Win7'
        var isWin10 = sUserAgent.indexOf('windows nt 10.0') > -1 || sUserAgent.indexOf('windows 10') > -1
        if (isWin10) return 'Win10'
      }
      if (sUserAgent.indexOf('android') > -1) return 'Android'
      if (sUserAgent.indexOf('iphone') > -1) return 'iPhone'
      if (sUserAgent.indexOf('symbianos') > -1) return 'SymbianOS'
      if (sUserAgent.indexOf('windows phone') > -1) return 'Windows Phone'
      if (sUserAgent.indexOf('ipad') > -1) return 'iPad'
      if (sUserAgent.indexOf('ipod') > -1) return 'iPod'
      return 'others'
    },
    getBrowserInfo() {
      let userAgent = window.navigator.userAgent.toLowerCase()
      let browserType = ''
      let browserVersion = ''
      let isIE = userAgent.match(/msie/) != null || userAgent.match(/trident/) != null
      // 浏览器类型-IE
      if (userAgent.match(/msie/) != null || userAgent.match(/trident/) != null) {
        browserType = 'IE'
        browserVersion = this.getIeVersion()
      }
      // 欧朋
      else if (window.opera || (userAgent.indexOf("opr") > 0)) {
        browserType = '欧朋'
        browserVersion =  this.getOperaVersion(userAgent)
      }
      // UC
      else if (userAgent.indexOf("ubrowser") > 0) {
        browserType = 'UC'
        browserVersion =  userAgent.match(/ubrowser\/([\d.]+)/)[1]
      }
      // 百度
      else if (userAgent.indexOf("bidubrowser") > 0) {
        browserType = '百度'
        browserVersion =  userAgent.match(/bidubrowser\/([\d.]+)/)[1]
      }
      // 搜狗
      else if (userAgent.indexOf("metasr") > 0 || userAgent.indexOf( "se 2.x" ) > 0 ) {
        browserType = '搜狗'
        // browserVersion =  userAgent.match(/metasr\/([\d.]+)/)[1]
      }
      // QQ
      else if (userAgent.indexOf("tencenttraveler") > 0) {
        browserType = 'QQ'
        browserVersion =  userAgent.match(/tencenttraveler\/([\d.]+)/)[1]
      }
      // QQ
      else if (userAgent.indexOf("qqbrowser") > 0) {
        browserType = 'QQ'
        browserVersion = userAgent.match(/qqbrowser\/([\d.]+)/)[1]
      }
      // 遨游
      else if (userAgent.indexOf("maxthon") > 0) {
        browserType = '遨游'
        browserVersion =  userAgent.match(/maxthon\/([\d.]+)/)[1]
      }
      // 火狐
      else if (userAgent.indexOf("firefox") > 0) {
        browserType = '火狐'
        browserVersion =  userAgent.match(/firefox\/([\d.]+)/)[1]
      }
      // edge
      else if (userAgent.indexOf("edge") > 0) {
        browserType = 'Edge'
        browserVersion =  userAgent.match(/edge\/([\d.]+)/)[1]
      }
      // 谷歌/360
      else if (userAgent.indexOf("chrome") > 0) {
          browserType = '谷歌浏览器'
          browserVersion =  userAgent.match(/chrome\/([\d.]+)/)[1]
      }
      // 苹果
      else if (userAgent.indexOf("Safari") > -1) {
        browserType = 'Safari'
        browserVersion =  userAgent.match(/version\/([\d.]+)/)[1]
      }
      var IP = returnCitySN['cip'] // ip
      var adress = returnCitySN["cname"] // 地址
      if (this._isMobile()){
        this.ruleForm.device="移动端"
      }else{
        this.ruleForm.device="电脑端"
      }
      this.ruleForm.address=adress;
      this.ruleForm.ip=IP;
      this.ruleForm.browser=browserType;
      this.ruleForm.os=this.GetOs();
    },
    _isMobile() {
      let flag = navigator.userAgent.match(
          /(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i
      );
      return flag;
    },

  }
}
</script>

<style scoped>

</style>