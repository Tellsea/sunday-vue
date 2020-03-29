import {asyncRoutes, constantRoutes} from '@/router'
import Layout from "@/layout/index";

/**
 * Use meta.role to determine if the current user has permission
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
  if (route.meta && route.meta.roles) {
    return roles.some(role => route.meta.roles.includes(role))
  } else {
    return true
  }
}

/**
 * Filter asynchronous routing tables by recursion
 * @param routes asyncRoutes
 * @param roles
 */
export function filterAsyncRoutes(routes, roles) {
  const res = []
  routes.forEach(route => {
    const tmp = {...route}
    if (hasPermission(roles, tmp)) {
      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, roles)
      }
      res.push(tmp)
    }
  })
  return res
}

/**
 * 通过当前权限构建动态路由
 * @param menus
 */
export function filterAsyncMenus(pid, menus) {
  const menuList = []
  menuList.push({
    path: '/button',
    component: Layout,
    redirect: '/button',
    hidden: true,
    children: []
  })
  // 处理菜单
  menus.forEach(item => {
    if (item.component && pid == item.pid && item.type == 1) {
      menuList.push({
        path: item.url,
        name: 'menu_' + item.id,
        component: item.pid == 0 ? Layout : () => import(`@/views${item.component}`),
        meta: {title: item.name, icon: item.icon},
        children: filterAsyncMenus(item.id, menus)
      })
    }
  })
  // 处理按钮路由，可以挂载到静态路由上、测试是否生成tag
  menus.forEach(item => {
    if (item.component && item.type == 2) {
      menuList[0].children.push({
        path: item.url,
        component: () => import(`@/views${item.component}`),
        meta: {title: item.name}
      })
    }
  })
  return menuList
}

const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {
  // 根据当前角色生成异步路由
  generateRoutes({commit}, roles) {
    return new Promise(resolve => {
      let accessedRoutes = filterAsyncRoutes(asyncRoutes, roles)
      commit('SET_ROUTES', accessedRoutes)
      resolve(accessedRoutes)
    })
  },
  // 异步挂载路由
  generateMenus({commit}, menus) {
    return new Promise(resolve => {
      let accessedRoutes = filterAsyncMenus(0, menus)
      accessedRoutes.push({path: '*', redirect: '/404', hidden: true})
      commit('SET_ROUTES', accessedRoutes)
      resolve(accessedRoutes)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
