<template>
  <div class="main">
    <div class="main_up">
      <div v-if="curUserName !== ''">
        <el-button @click="start">连接<el-icon><Link /></el-icon></el-button>
      </div>
    </div>
    <div class="main_down">
      <div class="left">
        <div class="left_up">
          <div class="label_title">
            已建立会话
          </div>
          <div :class="curSessionId == item.id ? 'box_select' : 'box'" v-for="item in state.sessionList_already" :key="item.id">
            <div class="box_left"  @click="startSession(item.id)">
              {{item.listName}}
            </div>
            <div class="right_left">
              <div class="right_left_count">
                {{item.unReadCount}}
              </div>
              <div class="right_left_del">
                <el-icon @click="delSession2(item.id)"><Close /></el-icon>
              </div>
            </div>
          </div>
        </div>

        <div class="left_down">
          <div class="label_title">
            可建立会话
          </div>
          <div v-for="item in state.sessionList_not" :key="item.snum" class="box" @click="createSession2(item.snum, item.username)">
            <div class="box_left">
              {{item.username}}
            </div>
          </div>
        </div>

      </div>
      <div class="right">
        <div class="up" ref="element" id="msg_end">
          <div v-for="(item,i) in list" :key="i" :class="item.fromUserId === curUserId ? 'msg_right' : 'msg_left'">
            <div class="msg_right_up">
              {{item.fromUserName}}
            </div>
            <div :class="item.fromUserId === curUserId ? 'msg_right_down' : 'msg_left_down'">
              {{item.content}}
            </div>
          </div>
        </div>
        <div class="down">
          <el-input
              type="textarea"
              :rows="9"
              placeholder="请输入内容，回车发送！"
              @keyup.enter.native = "sendMsg"
              v-model="textarea">
          </el-input>
        </div>
      </div>
    </div>

  </div>
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref, onUpdated} from 'vue'
import Cookie from "js-cookie";
import {getSessionListNot,sessionListAlready,createSession,delSession,msgList} from '../../request/api'
import {log} from "echarts/types/src/util/log";
import { Close,Link } from "@element-plus/icons-vue";

/*
 list:{}[],
  textarea:string,
  curUserId:string,
  curUserName:string,
  curSessionId:number,
  sessionList_already:{}[],
  sessionList_not:{}[],
  *
  target
  list:[],
  textarea:'',
  curUserId:'',
  curUserName:'',
  curSessionId:99999,
  sessionList_already:[],
  sessionList_not:[]*/
const state = reactive({
  list:[],
  textarea:'',
  curUserId:'',
  curUserName:'',
  curSessionId:99999,
  sessionList_already:[],
  sessionList_not:[]
})
let {textarea,list,curSessionId} = toRefs(state)//sessionList_already,sessionList_not
const curUserId = Cookie.get("snum")
//console.log(curUserId)
const curUserName  = Cookie.get("userName")
//console.log(curUserName)
let websock:any = null;
onUpdated(()=>{
  var elmnt = document.getElementById("msg_end");
  elmnt.scrollTop = elmnt.scrollHeight;
})

const initWebSocket =(snum:string,sessionId:number)=>{
  websock = new WebSocket("ws://localhost:8000/api/websocket/"+snum+"/"+sessionId);
  console.log("websock")
  console.log(websock)
  websock.onopen = websocketonopen;
  websock.onerror = websocketonerror;
  websock.onmessage = websocketonmessage;
  websock.onclose = websocketclose;
}
const websocketonopen = ()=>{
  console.log("WebSocket连接成功");
}
const websocketonerror = (e:Event)=>{
  console.log("WebSocket连接发生错误",e);
}
const websocketonmessage = (e:MessageEvent)=>{
  let data = JSON.parse(e.data);
  console.log("websocketonmessage-------data")
  console.log(data)
  if(data instanceof Array){
    // 列表数据
    sessionList_already.value = data
    console.log("sessionList_already--------------websocketonmessage")
  }else{
    // 消息数据
    list.value.push(data)
  }
}
const websocketclose = (e:CloseEvent)=>{
  if(curUserId != null){
    if(curSessionId != null){
      console.log("initWebSocket(curUserId, curSessionId)")
      initWebSocket(curUserId, curSessionId)
    }else{
      console.log("initWebSocket(curUserId, 99999999)")
      initWebSocket(curUserId, 99999999)
    }
  }
  console.log("connection closed",e);
  console.log(e);
}


const sendMsg = ()=>{
  console.log("curSessionId------------sendMsg")
  console.log(curSessionId)
  if(curSessionId == ''){
    return onmessage.error("请选择左边的对话框开始聊天!")
  }
  let data = {
    "fromUserId": curUserId,
    "fromUserName": curUserName,
    "content": textarea.value
  }
  console.log("data")
  console.log(data)
  list.value.push(data)
  console.log("textarea")
  console.log(textarea.value)
  websock.send(textarea.value)
  textarea.value=''
}
//
const start=() =>{

  console.log("curUserName")
  console.log(curUserName)
  console.log("curUserId")
  console.log(curUserId)
  getSessionListNot2(curUserId)
  sessionListAlready2(curUserId)
  startSession(99999)
}


/*getSessionListNot({
  snum:curUserId
}).then(res=>{
  sessionList_not = res.result
  console.log("获取可建立会话列表")
  console.log(sessionList_not)
})*/
// 获取可建立会话列表
const getSessionListNot2=async(curUserId:string)=>{
  console.log("getSessionListNot2---------curUserId")
  console.log(curUserId)


  const res = await getSessionListNot({snum:curUserId});
  console.log("sessionList_not")
  console.log(res.result)
  state.sessionList_not = res.result
  console.log(state.sessionList_not)
}
/*.then(res=>{

  sessionList_not = res.result
  console.log("获取可建立会话列表")
  console.log(sessionList_not)
})*/
//方法（）{api}


/*sessionListAlready({
  snum:curUserId
}).then(res=>{
  sessionList_already = res.result
  console.log("获取已存在的会话列表")
  console.log(sessionList_already)
})*/
// 获取已存在的会话列表
const sessionListAlready2=async (curUserId:string)=>{
  console.log("sessionListAlready2---------curUserId")
  console.log(curUserId)
  const res = await sessionListAlready({snum:curUserId});
  state.sessionList_already = res.result
}
// .then(res=>{
//   sessionList_already = res.result
//   console.log("获取已存在的会话列表")
//   console.log(sessionList_already)
// })
// 创建会话
/*createSession({snum:curUserId,tosnum:'',toUserName:''}).then(res=>{

})*/
// 创建会话
const createSession2 = (tosnum:string,touserName:string) =>{
  console.log(curUserId)
  console.log(tosnum)
  console.log(touserName)
  createSession({
    snum:curUserId,
    tosnum:tosnum,
    touserName:touserName
  }).then(res=>{
    console.log(res)
    getSessionListNot2(curUserId);
    sessionListAlready2(curUserId);
  })


}
/**
* @Description: 开始会话有问题  已解决
* @date 2023/5/14 18:11
* @author WangNaiLinn
**/

// 开始会话
const startSession = (sessionId:number)=>{
  //console.log(websock);
  if(websock != undefined){
    websock.close()
  }
  curSessionId = sessionId
  console.log("curSessionId")
  console.log(curSessionId)
  initWebSocket(curUserId, sessionId)
  msgList2(sessionId)
}

//删除会话
/*delSession({
  sessionId:curSessionId as number
}
).then(res=>{
  console.log("-----delSessionIng");
  getSessionListNot(curUserId);
  sessionListAlready(curUserId);
})*/
const delSession2 =async (sessionId:number)=>{
  console.log(sessionId)
  await delSession({sessionId:sessionId}).then(res=>{
    getSessionListNot2(curUserId);
    sessionListAlready2(curUserId);
  })
}

const msgList2 = (sessionId:number) =>{
  console.log(sessionId)
  msgList({sessionId:sessionId}).then(res=>{
    list.value = res.result
    sessionListAlready2(curUserId)
  })
}

/*msgList({
  sessionId:''
}).then(res=>{
  list.value = res.result
  console.log("list.value")
  console.log(list.value)
  sessionListAlready(curUserId)
})*/
</script>

<style lang="less" scoped>
.main{
  width: 980px;
  /* border: 1px #1890ff solid; */
  margin-top: 50px;
  height: 790px;
}
.main_up{
  width: 980px;
  height: 40px;
  /* border:1px red solid; */
}
.main_down{
  width: 981px;
  height: 750px;
  border: 1px #1890ff solid;
  display: flex;
  justify-self: space-between;
}
.left{
  width: 300px;
  height: 750px;
  border-right: 1px #1890ff solid;
}
.left_up{
  width: 300px;
  height: 450px;
  overflow-y: auto;
  /* border: 1px red solid; */
}
.label_title{
  width: 282px;
  height: 25px;
  background-color: #f8f8f8;
  font-weight: 600;
  padding: 8px;
}
.left_down{
  width: 300px;
  height: 300px;
  overflow-y: auto;
  /* border: 1px green solid; */
}
.right{
  width: 680px;
  height: 750px;
  /* border-right: 1px #1890ff solid; */
}
.box{
  width: 250px;
  height: 22px;
  padding: 10px 25px 10px 25px;
  display: flex;
  justify-self: flex-end;
  /* border: 1px red solid; */
}
.box:hover{
  background-color: gainsboro;
  cursor: pointer;
}
.box_select{
  width: 250px;
  height: 22px;
  padding: 10px 25px 10px 25px;
  display: flex;
  justify-self: flex-end;
  background-color: gainsboro;
}
.box_left{
  width: 230px;
  height: 22px;
}
.right_left{
  width: 50px;
  height: 22px;
  display: flex;
  justify-content: space-between;
  /* border: 1px red solid; */
}
.right_left_count{
  /* border: 1px blue solid; */
  padding-left: 10px;
  width: 20px;
}
.right_left_del{
  width: 20px;
  padding-left: 10px;
}
.up{
  width: 680px;
  height: 550px;
  overflow-y: scroll;
  overflow-x: hidden;
  /* padding-bottom: 40px; */
  border-bottom: 1px #1890ff solid;
}
.msg_left{
  width: 675px;
  /* padding-left: 5px; */
  margin-top: 5px;
  /* border: 1px #1890ff solid; */
}
.msg_left_up{
  height: 25px;
  margin-top: 5px;
}
.msg_left_down{
  height: 25px;
  /* border: 1px #1890ff solid; */
  padding-left: 10px;
}
.msg_right{
  width: 660px;
  /* padding-right: 20px; */
  margin-top: 5px;
  /* border: 1px #1890ff solid; */
  text-align: right;
}
.msg_right_up{
  height: 25px;

}
.msg_right_down{
  height: 25px;
  /* border: 1px #1890ff solid; */
  padding-right: 10px;
}
.down{
  width: 680px;
  height: 200px;
  /* border: 1px red solid; */
}
</style>