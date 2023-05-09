import Vue from 'vue'
import Router from 'vue-router'
/* Layout */
import Layout from '@/layout'

Vue.use(Router)

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },

  {
    path: '/user',
    component: Layout,
    redirect: '/user/admin',
    name: 'userManage',
    meta: { title: '用户管理', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'customer',
        name: 'customer',
        component: () => import('@/views/user/customer'),
        meta: { title: '客户管理', icon: 'table' }
      },
      {
        path: 'admin',
        name: 'admin',
        component: () => import('@/views/user/admin'),
        meta: { title: '管理员管理', icon: 'tree' }
      }
    ]
  },

  {
    path: '/gift',
    component: Layout,
    redirect: '/gift/crud',
    name: 'giftManage',
    meta: { title: '礼品管理', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'gift',
        name: 'gift',
        component: () => import('@/views/gift/curd'),
        meta: { title: '礼品列表', icon: 'table' }
      },
      {
        path: 'inventory',
        name: 'inventory',
        component: () => import('@/views/gift/inventory'),
        meta: { title: '库存管理', icon: 'tree' }
      }
    ]
  },

  {
    path: '/order',
    component: Layout,
    redirect: '/order',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/order'),
      meta: { title: '订单管理', icon: 'dashboard' }
    }]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://www.meet0208.icu:9092/o9pnjJ.jpg',
        meta: { title: '捐助我', icon: 'link' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
