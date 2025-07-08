import Vue from 'vue'
import VueRouter from 'vue-router'
// Importing Vue and VueRouter

Vue.use(VueRouter)

const routes = [
  {
    path: '/emp',
    name: 'emp',
    component: () => import('../views/Employee/EmployeeView.vue')
  },
  {
    path: '/ele',
    name: 'ele',
    component: () => import('../views/Element/ElementView.vue')
  },
  {
    path: '/',
    redirect: '/emp'
  }
]

const router = new VueRouter({  routes
})

export default router
