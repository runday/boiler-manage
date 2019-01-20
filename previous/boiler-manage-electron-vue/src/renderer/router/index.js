import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/views/layout/Layout'

Vue.use(Router)

export const constantRouterMap = [
    { path: '/login', component: () => import('@/views/login/index'), hidden: true },
    { path: '/',redirect: '/login', hidden: true },
    {
        path: '',
        component: Layout,
        children: [{
            path: 'home',
            component: () => import('@/views/home/index'),
            name: 'home',
            meta: { title: '首页' }
        }]
    },
    { path: '/product-form',name: 'product-form', component: () => import('@/views/product/product-form'), hidden: true },
    { path: '/product-auxiliary-machine-info-form',name: 'product-auxiliary-machine-info-form', component: () => import('@/views/product/product-auxiliary-machine-info-form'), hidden: true },
    { path: '/map-complete-page',name: 'map-complete-page', component: () => import('@/views/map/map-complete-page'), hidden: true },
    { path: '/controller-run-info',name: 'controller-run-info', component: () => import('@/views/controller-run-info'), hidden: true },
    { path: '/base-run-info-complete-page',name: 'base-run-info-complete-page', component: () => import('@/views/run-info/base-run-info'), hidden: true },


    { path: '/product-map',name: 'product-map', component: () => import('@/views/product/product-map'), hidden: true },
    { path: '/boiler-model-complete-page',name: 'boiler-model-complete-page', component: () => import('@/views/boiler-model/boiler-model-complete-page'), hidden: true },
]

export default new Router({
    routes: constantRouterMap
})
