import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '@/views/HomeView.vue'
import ProductListView from '@/views/ProductListView.vue'
import ProductDetailView from '@/views/ProductDetailView.vue'
import BasketView from '@/views/BasketView.vue'
import SearchView from '@/views/SearchView.vue'

const routes = [
  { path: '/', name: 'Home', component: HomeView },
  { path: '/search', name: 'Search', component: SearchView },
  { path: '/category/:slug', name: 'ProductList', component: ProductListView },
  { path: '/product/:id', name: 'ProductDetail', component: ProductDetailView },
  { path: '/basket', name: 'Basket', component: BasketView },
  { path: '/:pathMatch(.*)*', redirect: '/' }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior() {
    return { top: 0 }
  }
})

export default router