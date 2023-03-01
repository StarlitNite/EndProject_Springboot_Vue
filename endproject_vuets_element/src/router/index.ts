import {createRouter, createWebHashHistory, RouteRecordRaw} from 'vue-router';
import {App, defineAsyncComponent} from 'vue'
import store from '../store/intex'
import Cookie from "js-cookie";
import {getMenu} from '../request/api'

const routes:RouteRecordRaw[] = [
    {
        path:'/login',
        name:'',
        component: () =>import('../views/login.vue')
    },
    /*{
        path:'/home',
        name:'home',
        component:()=>import('../views/home.vue'),
        /!*children:[
            {
                path:'/covid',
                name:'covid',
                component:()=>import('../views/')
            }
        ]*!/
    },*/

]

const router = createRouter({
    history:createWebHashHistory(),
    routes//路由配置
})
const modules = import.meta.glob('../views/**/**.vue')
const getRoutes = ()=>{
    //动态添加菜单
    router.addRoute({
        path:'/home',
        name:'home',
        component:()=> import('../views/home.vue'),
        redirect:'/index',
        children:[{
            path:'/index',
            name:'index',
            component:()=>import('../views/index.vue')
        }
        ]
    })
    //获取菜单
    const Menus= store.state.menus;
    //
    /*const newRoute:RouteRecordRaw[] = [];*/

    //动态生成路由路径  循环菜单对象
    for (let key in Menus){
        /*console.log(Menus[key].path)*/
        const newRoutes: RouteRecordRaw = {
            path:'/'+Menus[key].path,
            name:Menus[key].path,
            component:()=> import('../views/home.vue'),
            children:[]
        };
        /*console.log(newRoutes)
        console.log(Menus[key].child.length)*/
        //仅有一级菜单
        if (Menus[key].child.length==0){
            console.log(Menus[key].path)
            router.addRoute({
                path:'/'+Menus[key].path,
                name:Menus[key].path,
                component:modules['../views/'+Menus[key].path+'/'+Menus[key].path+'.vue']
            })

        }
        // @ts-ignore
        for (let i = 0;i<Menus[key].child.length; i++){
            console.log(i);
            newRoutes.children?.push({
                path:'/'+Menus[key].child[i].path,
                name:Menus[key].child[i].path,
                component:modules['../views/'+Menus[key].path+'/'+Menus[key].child[i].path+'.vue']
            })
        }


        //动态添加固定首页
        console.log(newRoutes)
        //动态添加路由规则
        router.addRoute(newRoutes)
        /*console.log(router)*/
    }
}

//前置导航守卫
router.beforeEach((to,from,next)=>{

    //1.token && vuex中menus为空
    const token = Cookie.get('token')
    /*console.log(store)*/

    if (token && store.state.menus.length===0){

        // console.log("空")
        //异步
        store.dispatch('getMenu').then(()=>{
           /* //获取菜单
            const Menus= store.state.menus;
            //
            /!*const newRoute:RouteRecordRaw[] = [];*!/
            //动态生成路由路径  循环菜单对象
            for (let key in Menus){
                /!*console.log(Menus[key].path)*!/
                const newRoutes: RouteRecordRaw = {
                    path:'/'+Menus[key].path,
                    name:Menus[key].path,
                    component:()=> import('../views/home.vue'),
                    children:[]
                    };
                /!*console.log(newRoutes)
                console.log(Menus[key].child.length)*!/

                // @ts-ignore
                for (let i = 0;i<Menus[key].child.length; i++){
                    newRoutes.children?.push({
                        path:'/'+Menus[key].child[i].path,
                        name:Menus[key].child[i].path,
                        component:modules['../views/'+Menus[key].path+'/'+Menus[key].child[i].path+'.vue']
                    })
                }
            //动态添加固定首页

            //动态添加路由规则
                router.addRoute(newRoutes)
            }*/
            getRoutes()
            next(to.path);
        })

    }
    else if (token && store.state.menus.length!==0 && from.path==='/login'&&to.path==='/home'){
        getRoutes()
        next('/index')
    }else if (!token && to.path !='/login'){
        next('/login');
    }else if (token && to.path == '/login') {
        next(from)
    }else {
        next()
    }
})

export const initRouter = (app: App<Element>) => {
    app.use(router)
}
// export default router