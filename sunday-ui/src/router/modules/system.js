/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const systemRouter = {
  path: '/system',
  component: Layout,
  redirect: '/system/userInfo',
  name: 'System',
  meta: {
    title: '系统管理',
    icon: 'table'
  },
  children: [
    {
      path: '/system/userInfo',
      component: () => import('@/views/system/user-info/index'),
      name: 'UserInfo',
      meta: { title: '用户列表' }
    },
    {
      path: '/system/roleInfo',
      component: () => import('@/views/system/role-info/index'),
      name: 'RoleInfo',
      meta: { title: '角色列表' }
    },
    {
      path: '/system/resourceInfo',
      component: () => import('@/views/system/resource-info/index'),
      name: 'ResourceInfo',
      meta: { title: '权限列表' }
    }
  ]
}
export default systemRouter
