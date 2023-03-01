import {createRouter, createWebHashHistory, RouteRecordRaw} from 'vue-router';
import {App} from 'vue'
import store from '../store/intex'
import Cookie from "js-cookie";
import {getMenu} from '../request/api'
import component from "element-plus/es/components/tree-select/src/tree-select-option";

const routes:RouteRecordRaw[] = [
    {
        path:'/login',
        name:'',
        component: () =>import('../views/login.vue')
    },
    {
        path:'/home',
        name:'home',
        component:()=>import('../views/home.vue'),
        /*children:[
            {
                path:'/covid',
                name:'covid',
                component:()=>import('../views/covid.vue')
            }
        ]*/
    },

]

const router = createRouter({
    history:createWebHashHistory(),
    routes//路由配置
})

//前置导航守卫
router.beforeEach((to,from,next)=>{
    //1.token && vuex中menus为空
    const token = Cookie.get('token')
    console.log(store)
    if (token && store.state.menus.length===0){
        // console.log("空")
        //异步
        store.dispatch('getMenu').then(()=>{
            //获取菜单
            const Menus= store.state.menus;
            //
            /*const newRoute:RouteRecordRaw[] = [];*/
            //动态生成路由路径
            for (let key in Menus){
                /*console.log(Menus[key].path)*/
                const newRoutes: RouteRecordRaw = {
                    path:'/'+Menus[key].path,
                    name:Menus[key].path,
                    component:()=> import('../views/home.vue'),
                    children:[]
                    };
                /*console.log(newRoutes)*/
                console.log(Menus[key].child.length)

                for (let i = 0;i<Menus[key].child.length; i++){
                    newRoutes.children?.push({
                        path:'/'+Menus[key].child[i].path,
                        name:Menus[key].child[i].path,
                        component:()=>import('../views/'+Menus[key].child[i].path+'.vue')
                    })
                }
            //动态添加路由规则
                /*console.log(newRoutes)*/

                router.addRoute(newRoutes)
            }
            /*getMenu().then(res=>{
                if (res.code===200){
                    store.commit('Menus',res.result);
                }
            })*/
        })

    }
    next();
})

export const initRouter = (app: App<Element>) => {
    app.use(router)
}
// export default router