import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/Home'
import Sample from '@/views/Sample'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/sample',
      name: 'Sample',
      component: Sample
    }
  ]
})
