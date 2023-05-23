<template>
  <el-card :body-style="{ padding: '0px'}">
    <div style="display: flex; justify-content: center;">
      <img src="https://www.meet0208.icu:9092/o9pnjJ.jpg" style="width: 750px; height: 100%;" alt="">
    </div>
    <div style="padding: 14px;">
      <span>感谢您的捐助</span>
      <div class="bottom clearfix">
        <time class="time">{{ currentDate }}</time>
        <el-button type="text" class="button">操作按钮</el-button>
      </div>
    </div>
  </el-card>
</template>

<style>
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
</style>

<script>
export default {
  data() {
    return {
      currentDate: new Date()
    }
  }
}
</script>
