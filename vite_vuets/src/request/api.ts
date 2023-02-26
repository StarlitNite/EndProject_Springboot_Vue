import request from "./request";
import {AxiosPromise} from "axios";
interface AdminLoginData {
    snum:string
    password:string
    role_id:number
}
interface getMenu{
    role_id:number
}

type PromiseRes<T> = Promise<ManageResult<T>>

interface ManageResult<T> {
    code:number;
    result:T;
    message:string;
}
//登录返回接口
interface AdminLoginRes {
    token:string;
}
//当前用户信息
interface UserMenu{
    menus:[]
}
//登录返回TOKEN
export const adminLogin = (data:AdminLoginData):Promise<ManageResult<AdminLoginRes>> => request.post('/user/login',data)
//获取用户信息

// @ts-ignore
export const getMenu = (role_id:getMenu):PromiseRes<UserMenu> => request.get('/Menu',role_id)