import request from "./request";

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

//获取所有菜单用于展示
export const getAllMenus=():PromiseRes<{ list:{}[] }>=>request.get('/menu/getAllMenu')

//获取所有菜单用于权限修改
export const getAllMenu=():PromiseRes<UserMenu>=>request.get('/role/getAllMenu')

//添加菜单
export const addMenu = (data:menu):PromiseRes =>request.post('/menu/addMenu',data)

//删除菜单
export const delMenu = (id:number):PromiseRes =>request.delete('/menu/delMenu/'+id)

//修改菜单
export const editMenu = (data:menu):PromiseRes => request.post('/menu/editMenu',data) //+data.id+'&pid='+data.pid+'&title='+data.title+'&path='+data.path

//修改角色菜单权限
export const SaveRole=(data:roleMenu):PromiseRes =>request.post('/role/SaveRole?rid='+data.rid+'&mids='+data.mid)

//获取饼状图
 export const covidData = ():PromiseRes<IndexData> =>request.get('/covid/covidData')

export const getPie = ():PromiseRes<IndexData> =>request.get('/covid/covidData')