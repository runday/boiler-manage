<template>
  <div class="login-container">
    <webview id="foo" v-loading.fullscreen.lock="fullscreenLoading" :src="homeUrl"  :style="{'width':'100%','height':homeHeight+'px'}" nodeintegration plugins autosize="on" disablewebsecurity></webview>
  </div>
</template>

<script>
    import { config } from '@/config/index'
    export default {
        name: 'home',
        data() {
            return {
                homeUrl:config.default_home_url,
                fullscreenLoading: false,
                homeHeight:document.body.clientHeight-56
            }
        },
        created(){
            require('electron').remote.getCurrentWindow().maximize()
            this.initHomeIndex()
            let self=this
            this.$root.$emit('changeAppMainHeight',56)
            window.onresize = function(){
                self.homeHeight=document.body.clientHeight-56
            }
        },
        mounted(){
            let self=this
            let webview = document.getElementById("foo");
            let loadstart = function() {
                self.fullscreenLoading = true;
            }
            let loadstop = function() {
                self.fullscreenLoading = false
            }
            webview.addEventListener("did-start-loading", loadstart);
            webview.addEventListener("did-stop-loading", loadstop);
        },
        destroyed(){
            this.$root.$emit('changeAppMainHeight',88)
        },
        methods:{
            initHomeIndex(){
                let homeUrl= window.localStorage['homeUrl']
                if(homeUrl){
                    this.homeUrl=window.localStorage['homeUrl']
                }
            }
        }
    }
</script>
