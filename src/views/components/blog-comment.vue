<template>
  <fieldset>
    <legend v-if="!isResponse" class="comment-head">留下你的评论</legend>
    <legend v-if="isResponse" class="comment-head">回复 {{responseName}}</legend>
    <form>
    <div class="comment-body">

      <textarea placeholder="在此写下你的想法"  style="color:#000;" v-model="textareaData"></textarea>
    </div>
    <div class="comment-foot">
      <button @click="confirm" class="comment-btn" type="button">提交</button>
    </div>
    </form>
  </fieldset>
</template>

<script>
export default {
  props: ['isResponse', 'responseName','count'],
  name: 'blogComment',
  data () {
    return {
      textareaData: '',
      isResponseData: true,

    }
  },
  created: function () {

  },
  computed: {

  },
  methods: {

    confirm: function () {

      this.$emit('submit-comment', this.textareaData);
      this.textareaData = '';

    },

  },
  directives: {
    focus: {
      update: function (el, {value}) {
        if (value) {
          el.focus();
        }
      }
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
fieldset{
  padding: .35em .625em .75em;
  border: none;
  border-top: 1px solid #e2e2e2;
  margin: 0 2px;
}
legend{
  display: block;
  width: auto;
  border: none;
  margin-left: 20px;
  padding-left: 8px;
  padding-right: 8px;
  line-height: inherit;
}
.comment{
		overflow:unset;
		margin-bottom: 20px;
}
.comment-head{
		font-size: 18px;
		font-weight: 400;
    color: #666;
}

.comment-body textarea{
		width: 100%;
		height: auto;
		min-height: 100px;
		padding:6px 10px;
		border:1px solid #eee;
		line-height:28px;
		box-shadow: 0 1px 2px rgba(0,0,0,.075) inset;
		-webkit-box-shadow: 0 1px 2px rgba(0,0,0,.075) inset;
		-moz-box-shadow: 0 1px 2px rgba(0,0,0,.075) inset;
		background-color:#fafafa;
		border-radius: 2px;
}
.comment-body textarea:focus,
.comment-name input{
	outline: none;
}
.emotions{
		display: inline-block;
}
.em00, .em01, .em02, .em03, .em04, .em05, .em06, .em07, .em08, .em09, .em10, .em11, .em12, .em13, .em14{
		cursor: pointer;
		background-image: url(../../assets/image/emotions/emotions.png);
		background-size: 120px;
		float: left;
		height: 24px;
		margin-right: 5px;
		width: 24px;
		transition: all .2s ease-out;
		-webkit-transition: all .2s ease-out;
}
.em01{background-position: -24px 0;}
.em02{background-position: -48px 0;}
.em03{background-position: -72px 0;}
.em04{background-position: -96px 0;}
.em05{background-position: 0px -24px;}
.em06{background-position: -24px -24px;}
.em07{background-position: -48px -24px;}
.em08{background-position: -72px -24px;}
.em09{background-position: -96px -24px;}
.em10{background-position: 0px -48px;}
.em11{background-position: -24px -48px;}
.em12{background-position: -48px -48px;}
.em13{background-position: -72px -48px;}
.em14{background-position: -96px -48px;}

.comment-name{
	margin-bottom: 10px;
	position: relative;
}
.comment-name input{
	width: 60%;
	padding: 8px;
	border:1px solid #eee;
	background-color: #FAFAFA;
	box-shadow: 0 1px 2px rgba(0,0,0,.075) inset;
	-webkit-box-shadow: 0 1px 2px rgba(0,0,0,.075) inset;
	-moz-box-shadow: 0 1px 2px rgba(0,0,0,.075) inset;
	border-radius: 2px;
}
.comment-btn{
	height: 38px;
	line-height: 38px;
	padding: 0 18px;
	background-color: #337ab7;
	color: #fff;
	white-space: nowrap;
	text-align: center;
	font-size: 14px;
	border: none;
	border-radius: 2px;
	cursor: pointer;
	opacity: .9;
}
.comment-foot{
	margin-top: 10px;
}
@media(max-width: 767px){
  .comment-name input{
		width: 100%;
	}
}
</style>
