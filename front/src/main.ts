import { createApp } from 'vue'
import ElementPlus from 'element-plus'

import '@/styles/customStyle.scss'
import 'element-plus/dist/index.css'

import App from './App.vue'
import VueCookies from 'vue3-cookies'

import './registerServiceWorker'
import router from './router'
import store from './store'

createApp(App)
  .use(store)
  .use(router)
  .use(ElementPlus)
  .use(VueCookies)
  .mount('#app')
