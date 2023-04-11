<template>
  <div class="sidebar">
    <ul class="menu" v-for="menu in navTree" :key="menu.id">
<!--      给子组件TreeMenu 绑定menu属性，传给子组件使用-->
      <tree-menu :menu="menu"></tree-menu>
    </ul>
  </div>
</template>
<script>
import TreeMenu from "@/views/sideBar/TreeMenu";
export default {
  name: "SideBar",
  components: {TreeMenu},

  computed: {
    //从store里面拿到菜单
    navTree() {
      let tree = this.$store.getters.getNavTree;
      return tree;
    }
  },

}
</script>

<style scoped>
.sidebar {
  width: 13%;
  height: 90%;
  background-color: rgb(241, 241, 241);
  float: left;
}


.fa {
  display: inline-block;
  margin-left: 14px;
  margin-right: 14px;
  font-family: 'FontAwesome';
  font-weight: normal;
  font-style: normal;
}

.submenuHide {
  display: none;
  position: relative;
}

.submenuShow {
  display: block;
  position: relative;
}


.submenu:before {
  content: "";
  display: block;
  position: absolute;
  z-index: 1;
  left: 18px;
  top: 0;
  bottom: 0;
  border: 1px dotted;
  border-width: 0 0 0 1px; /* 上 右 下 左 */
}



.active {
  color: red
}
</style>