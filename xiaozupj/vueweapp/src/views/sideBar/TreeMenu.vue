<template>
  <div>
    <el-menu class="muneMain">
    <router-link :to="menu.url" class="submenu22"  v-if="menu.url!=null">{{ menu.name }}</router-link>
      <a v-else @click.prevent="test" class="atext"  >
        <div class="son1">
        <i :class="menu.icon"  id="fa"></i>
        </div>
        <div class="son2">
          {{ menu.name }}
        </div>
        <div class="son3">
          <i :class="arrows" aria-hidden="true" id="fa2"></i>
        </div>
      </a>

      <ul :class="submenu" v-if="menu.children.length>0">
        <tree-menu v-for="childMenu in menu.children" :menu='childMenu' :key="childMenu.id"></tree-menu>
      </ul>
    </el-menu>
  </div>
</template>
<script>
export default {
  name: "TreeMenu",
  props: ['menu'],//�Ӹ������menu�������
  data() {
    return {
      submenu: {
        submenuHide: true,
        submenuShow: false
      },
      arrows:"fa-chevron-left",


    }
  },
  methods: {
    test() {
      // setTimeout(() => {
        if (this.submenu.submenuHide) {


          this.submenu.submenuHide = false;
          this.submenu.submenuShow = true;
          this.arrows = "fa-chevron-down";


        } else {

          this.submenu.submenuHide = true;

          this.submenu.submenuShow = false;
          this.arrows = "fa-chevron-left";
        }
      // }, 300);

    }


  }
}



</script>

<style lang="scss" scoped>

.muneMain {
  /*list-style: none;*/
  position: relative;
  border-style: solid;
  border-width: 1px 0 0;
  border-color: #E5E5E5;
  /*border-color: gray;*/
  background-color: rgb(241, 241, 241);//灰色

}
/*li {*/
/*  list-style: none;*/
/*  position: relative;*/
/*  border-style: solid;*/
/*  border-width: 1px 0 0;*/
/*  border-color: #E5E5E5;*/
/*}*/
.son1{
  width: 30px;
  //width: 200px;
  //height: 300px;
  /* 浮动 */
  float: left;

}
#fa{
  padding-left: 10px;
}

.son2{
  width: 100px;
  //height: 200px;
  /* 浮动 */
  float: left;
  text-align: center;
}
.son3{
  width: 50px;
  //width: 300px;
  //height: 500px; /* 浮动 */
  float: left;
  text-align: center;
}
//#fa2{
//  text-align: right;
//}

.submenu22{
  width: 185px;
  font-size: 13px;
  height: 40px;
  line-height: 40px;
  background-color: white;
  color: rgb(19, 144, 244);
  text-align: center;


}

//
//.submenu > li {
//  position: relative;
//  padding-left: 10px;
//}

//.atext {
//  margin-left: 15px;
//  display: block;
//  width: 189px;
//  height: 40px;
//  /*color: #5d5d5d;*/
//  //color: gray;
//  text-decoration: none;
//  line-height: 40px;
//  font-size: 15px;
//}
a {
  display: block;
  width: 180px;
  height: 40px;
  /*color: #5d5d5d;*/
  //color: gray;
  text-decoration: none;
  //border: #409eff solid;
  line-height: 40px;
  font-size: 15px;
  //text-align: center;
}


//#fa2{
//  display: block;
//  float: left;
//margin-right: 20px;
//
//}

//.fa {
//  display: inline-block;
//  margin-left: 14px;
//  margin-right: 14px;
//  font-family: 'FontAwesome';
//  font-weight: normal;
//  font-style: normal;
//}

.submenuHide {
  display: none;
  position: relative;
  //transition-duration: 5s
}

.submenuShow {
  display: block;
  position: relative;
  //transition-duration: 5s
}


//.submenu:before {
//  content: "";
//  display: block;
//  position: absolute;
//  z-index: 1;
//  left: 18px;
//  top: 0;
//  bottom: 0;
//  border: 1px dotted;
//  border-width: 0 0 0 1px; /* �� �� �� �� */
//}

//.submenu > li:before {
//  content: "";
//  display: block;
//  width: 10px;
//  position: absolute;
//  z-index: 1;
//  left: 20px;
//  top: 17px;
//  border: 1px dotted;
//  border-width: 1px 0 0; /* �� �� ��*/
//}

//.active {
//  color: red
//}
</style>