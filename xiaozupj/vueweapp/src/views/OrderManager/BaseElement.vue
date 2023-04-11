<template>
  <div class="mainBox">
    <button
        v-for="(item, index) in btnList"
        :key="index"
        @click="item.method"
        class="btn"
    >
      {{ item.label }}
    </button>
  </div>
</template>
<script>
export default {
  data() {
    return {
      btnList: [
        {
          label: "刷新",
          method: () => {
            this.add();
          },
        },
        {
          label: "返回",
          method: () => {
            this.del();
          },
        },
      ],
    };
  },
  methods: {
    add() {
      this.msg ="刷新"
    },
    del() {
      this.msg ="返回"
    },
  },
};
</script>
<style scoped>
.mainBox {
  padding: 20px;
}
.btn {
  background: #009fe9;
  color: #fff;
  border: none;
  padding: 6px;
  width: 100px;
  border-radius: 4px;
  display: inline-block;
  margin-right: 10px;
}
.one{
  background:#009fe9
}
.two{
  background: #009f55;
}
</style>

