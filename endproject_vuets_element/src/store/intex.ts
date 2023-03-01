import {createStore} from "vuex";
import {App} from "vue";
import {getMenu} from '../request/api'
/*interface menuObj {
    pid:number
    id:number
    child?:menuObj[]
    path:string
}*/

const store = createStore({
    state(){
        return{
            menus:[]
        }
    },
    getters:{

    },
    mutations:{
        Menus(state,menus){
            state.menus = menus
        }
    },
    actions:{
        getMenu({commit}){
            return new Promise((resolve ,reject)=>{
                getMenu().then(res=>{
                    if (res.code===200){
                        commit('Menus',res.result)
                        resolve(res)
                    }else {
                        reject(res)
                    }
                })
            })
        }

    },
    modules:{}
})

export const initStore = (app: App<Element>)=>{
    app.use(store);
}

export default store