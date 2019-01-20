<template>
  <div>
   <boiler-model-manage width="50%"></boiler-model-manage>
  </div>
</template>

<script>
    import boilerModelManage from './components/index'
    export default {
        name:'boiler-model',
        components:{
            boilerModelManage
        },
        created(){
            document.title="锅炉型号管理"
        }
    }
</script>
