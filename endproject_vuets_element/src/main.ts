import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import {initRouter} from './router'
import {initStore} from './store/intex'

const app = createApp(App);
initRouter(app)
initStore(app);
app.mount('#app')

