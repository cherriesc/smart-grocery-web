import { createRouter, createWebHistory } from 'vue-router'
import AtomPreview from '../components/atoms/AtomPreview.vue'

const routes = [
  {
    path: '/atom-preview',
    name: 'AtomPreview',
    component: AtomPreview
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router