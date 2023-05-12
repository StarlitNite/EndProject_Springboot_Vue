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
