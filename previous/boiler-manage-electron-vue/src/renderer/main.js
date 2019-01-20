import Vue from 'vue'

import 'normalize.css/normalize.css'
import '@/styles/index.scss'

import App from './App'
import router from './router'
import store from '@/store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import './icons'

import './permission'

import menuContext from '@/components/menuContext'
import menuContextItem from '@/components/menuContext/menuContextItem'

if (!process.env.IS_WEB) Vue.use(require('vue-electron'))
Vue.use(menuContext)
Vue.use(menuContextItem)
Vue.component('menu-context', menuContext)
Vue.component('menu-context-item', menuContextItem)
Vue.use(ElementUI)

Vue.config.productionTip = false

Vue.directive('title', {
    inserted: function (el, binding) {
        document.title = el.dataset.title
    }
})
new Vue({
  components: { App },
  router,
  store,
  template: '<App/>'
}).$mount('#app')
