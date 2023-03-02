import request from "./request";

interface adminLoginData{
    "snum":string
    "password":string
    "role_id":number
}
type PromiseRes<T> = Promise<ManageResult<T>>

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
}

interface UserMenu{
    menus:[]
}

//权限接口
interface role{
    name:string
    Page:number
    limit:number
}


//登录
export const adminLogin =(data:adminLoginData):PromiseRes<AdminLoginRes> =>request.post('/user/login',data)

//获取登录用户权限菜单
export const getMenu=():PromiseRes<UserMenu>=>request.get('/menu/Menu')

//获取权限列表
export const gerRole=(data:role):PromiseRes<{ list:{}[] }>=>request.get('/role/getRole',{params:data})