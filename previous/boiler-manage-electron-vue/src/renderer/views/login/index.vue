<template>
  <div id="login">
    <div class="login-btn">
      <div class="sys-control-box">
        <el-dropdown trigger="click">
          <span class="el-dropdown-link">
            <button class="sys-btn sys-btn-set" title="设置"></button>
          </span>
          <el-dropdown-menu slot="dropdown" style="-webkit-app-region: no-drag;">
            <el-dropdown-item>
              <a :href="helpDocumentHref">帮助文档</a>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <button class="sys-btn sys-btn-mini" title="最小化" @click="hanleMinimizable"></button><button class="sys-btn sys-btn-close" title="关闭" @click="closeLoginWindow"></button>
      </div>
    </div>
    <div class="login-form">
      <el-form ref="ruleForm" label-position="left" label-width="0px">
        <span class="title">锅炉远程监控平台</span>
        <el-form-item prop="account">
          <el-input type="text" name="username" v-model="loginForm.username" autoComplete="on" placeholder="账号"></el-input>
        </el-form-item>
        <el-form-item prop="checkPass">
          <el-input  name="password" type="password" @keyup.enter.native="handleLogin" v-model="loginForm.password" autoComplete="on" placeholder="密码"></el-input>
        </el-form-item>
        <el-form-item style="width:100%;">
          <el-button type="primary" style="width:100%;" :loading="loading" @click.native.prevent="handleLogin">登陆</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
    import { config } from '@/config/index'
    import {openElectronWindow} from '@/utils/windowsOperate'
    const {ipcRenderer} = require('electron')
    export default {
        name: 'login',
        data() {
            return {
                helpDocumentHref:config.helpDocumentHref,
                loginForm: {
                    username: '',
                    password: ''
                },
                loading: false
            }
        },

        methods: {
            hanleMinimizable(){
                require('electron').remote.getCurrentWindow().minimize()
            },
            closeLoginWindow(){
                require('electron').remote.getCurrentWindow().close()
                require('electron').remote.getCurrentWindow().destroy()
            },
            handleLogin() {
                let self=this
                this.loading = true
                this.$store.dispatch('LoginByUsername', this.loginForm).then((data) => {
                    this.loading = false
                    this.$store.state.user.websock.onmessage=function (e) {
                        if(e.data=="false"){
                            self.$message.error("当前账号已经在其它地方登陆，不可再登陆");
                        }else{
                            self.$store.dispatch('setUserToken',data)
                            ipcRenderer.send('closeMainWindow')
                            let newWindow=openElectronWindow("/home",{title:"智能锅炉管理平台",webPreferences: {webSecurity: false}},false)
                            newWindow.maximize()
                        }
                    }
                }).catch((msg) => {
                    this.$message.error(msg);
                    this.loading = false
                })
            }
        },
    }
</script>

<style rel="stylesheet/scss" lang="scss">
  body {
    -webkit-app-region: drag;
  }
  #login {
    left: 0px;
    top: 0px;
    right: 0px;
    bottom: 0px;
    background-color: #ebf2f9;
    position: absolute;

  }
  #login .login-btn {
    box-shadow: 0 0 10px rgba(0, 0, 0, 1);
    width: 100%;
    height: 100%;
    background: url(./imgs/login-back.gif) no-repeat;
    background-size: 100% 180px;

  }
  .sys-control-box {
    float:right;
    width:100px;
    border-radius: 0 4px 0 0;
    -webkit-app-region: no-drag;
  }
  .sys-btn {
    width: 28px;
    height: 28px;
    border: none;
    outline: none;
    margin: 0;
    -webkit-app-region: no-drag;
  }
  .sys-btn-set {
    background: url(./imgs/btn_set_normal.png) 1px 0 no-repeat;
  }

  .sys-btn-set:hover {
    background: url(./imgs/btn_set_hover.png) 1px 0 no-repeat;
  }

  .sys-btn-set:active {
    background: url(./imgs/btn_set_press.png) 1px 0 no-repeat;
  }

  .sys-btn-mini {
    background: url(./imgs/btn_mini_normal.png) no-repeat;
  }

  .sys-btn-mini:hover {
    background: url(./imgs/btn_mini_highlight.png) no-repeat;
  }

  .sys-btn-mini:active {
    background: url(./imgs/btn_mini_down.png) no-repeat;
  }

  .sys-btn-close {
    border-radius: 0 4px 0 0;
    background: url(./imgs/btn_close_normal.png) no-repeat;
  }

  .sys-btn-close:hover {
    background: url(./imgs/btn_close_highlight.png) no-repeat;
  }

  .sys-btn-close:active {
    background: url(./imgs/btn_close_down.png) no-repeat;
  }
  .login-form{
    -webkit-app-region: no-drag;
    font-weight: bold;
    color: #303133;
    line-height: 30px;
    position: absolute;     /* 相对定位或绝对定位均可 */
    width: 70%;
    top: 75%;
    left: 50%;
    transform: translate(-50%, -50%);
    text-align: center;
    .title{
      font-size: 20px;
    }
  }
  .el-dropdown-menu{
    top: 12px!important;
  }
</style>
