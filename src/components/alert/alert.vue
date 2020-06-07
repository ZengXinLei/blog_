<template>
  <div  style="background-color: #fef0f0;color: #F56c6c;padding: 5px 15px;border-radius: 8px 8px 8px 8px">{{title}}<i @click="c" class="el-icon-close i" style="color: grey;float:right"></i> </div>
</template>

<script>
    export default {
        name: "alert",
      props:["title","index"],
      methods:{
          c:function () {
            this.$emit("deletetag",this.index)
          }
      }
    }
</script>

<style scoped>
.i:hover{
  cursor: pointer;
}
</style>
