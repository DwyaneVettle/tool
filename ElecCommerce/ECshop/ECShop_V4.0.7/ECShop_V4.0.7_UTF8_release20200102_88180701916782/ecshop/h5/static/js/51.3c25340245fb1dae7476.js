webpackJsonp([51],{T4uu:function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});i("vAZe"),i("Au9i");var r={data:function(){return{frameHeight:0,title:""}},computed:{getUrl:function(){return this.$route.query.url},getFrameStyle:function(){return{height:this.frameHeight+"px"}}},methods:{goBack:function(){this.$router.go(-1)}},mounted:function(){var t=this;this.$nextTick(function(){t.frameHeight=document.body.scrollHeight-44;var e=document.getElementById("iframe"),r=t.$route.query.title;r&&r.length?t.title=r:t.title=e.contentWindow.document.title;i("wGiD")})}},n={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"container"},[e("mt-header",{staticClass:"header",attrs:{title:this.title}},[e("header-item",{attrs:{slot:"left",isBack:!0},on:{onclick:this.goBack},slot:"left"})],1),this._v(" "),e("iframe",{staticClass:"content-wrapper",style:this.getFrameStyle,attrs:{id:"iframe",src:this.getUrl}})],1)},staticRenderFns:[]};var a=i("VU/8")(r,n,!1,function(t){i("vS74")},"data-v-ae747818",null);e.default=a.exports},vS74:function(t,e){}});
//# sourceMappingURL=51.3c25340245fb1dae7476.js.map