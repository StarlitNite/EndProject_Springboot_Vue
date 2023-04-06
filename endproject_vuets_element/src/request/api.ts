import request from "./request";

interface adminLoginData{
    "snum":string
    "password":string
    "role_id":number
}
type PromiseRes<T={}> = Promise<ManageResult<T>>

interface ManageResult<T={}>{
    code:number
    result:T
    message:string
}

//登录返回接口
interface AdminLoginRes{
    token:string
    userName:string
    role_id:number
    snum:string
}

interface UserMenu{
    menus:[]
}

interface user{
    snum:string;
    Page:number;
    limit:number;
}

//权限接口
interface role{
    name:string;
    Page:number;
    limit:number;
}

//获取请假列表接口
interface leave{
    role_id:number;
    snum:string | undefined;
    Page:number;
    limit:number;
}

//会话接口
interface chat {
    snum:string
    tosnum:string
    touserName:string
    sessionId:number
}


//登录
export const adminLogin =(data:adminLoginData):PromiseRes<AdminLoginRes> =>request.post('/user/login',data)

//获取登录用户权限菜单
export const getMenu=():PromiseRes<UserMenu>=>request.get('/menu/Menu')

//获取用户
export const getUser=(data:user):PromiseRes<{list:{}[]}> =>request.get('/user/getUser',{params:data})

//修改用户
export const editUser=(data:user):PromiseRes => request.post('/user/editUser',data)

//获取权限列表
export const getRole=(data:role):PromiseRes<{ list:{}[] }>=>request.get('/role/getRole',{params:data})

//修改角色 (有问题，前端传值为空）
export const UpdateRole=(data:Role):PromiseRes =>request.post('/role/UpdateRole',data)

//获取角色权限
export const getRoldById=(id:number):PromiseRes<{list:{}[]}> => request.get('/role/Role'+id)

//删除角色
/*export const DeleteRole=(data:Role)=>request.delete('/role/DeleteRole',data.id)*/

//获取请假列表
export const getleave=(data:leave):PromiseRes<{list:{}[]}> => request.get('/leave/getleave',{params:data})

//审批通过返回接口
export const updateleave=(data:leave):PromiseRes => request.post('/leave/updateleave',data)

//创建请假
export const addleave=(data:leave):PromiseRes => request.post('/leave/addleave',data)

//删除请假
export const delleave=(id:number):PromiseRes => request.post('/leave/delleave/'+id)

//查询填报
export const gethealth=(data:health):PromiseRes<{list:{}[]}> => request.get('/health/gethealth',{params:data})

//创建填报
export const addhealth=(data:health):PromiseRes => request.post('/health/addhealthclock',data)

//修改填报
export const updatehealthclock=(data:health):PromiseRes => request.post('/health/updatehealthclock',data)

//填报无法自主删除

//获取可建立会话列表
export const getSessionListNot=(data:chat):PromiseRes<{ list:{}[] }> => request.get('/sessionList/not?id='+data.snum);

//获取已存在的会话列表
export const sessionListAlready=(data:chat):PromiseRes<{ list:{}[] }> => request.get('/sessionList/already?id='+data.snum)

//创建会话
export const createSession=(data:chat):PromiseRes => request.get('/createSession?id='+data.snum+'&tosnum='+data.tosnum+'&toUserName='+data.touserName)

//删除会话
export const delSession=(data:chat):PromiseRes=> request.get('/delSession?sessionId='+data.sessionId)

//获取消息数据
export const msgList=(data:chat):PromiseRes<{ list:{}[] }>=> request.get('/msgList?sessionId='+data.sessionId)