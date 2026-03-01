import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '@/views/HomeView.vue'
import ProductListView from '@/views/ProductListView.vue'
import ProductDetailView from '@/views/ProductDetailView.vue'
import BasketView from '@/views/BasketView.vue'
import SearchView from '@/views/SearchView.vue'
import PrivacyPolicyView from '@/views/PrivacyPolicyView.vue'
import HowToUseView from '@/views/HowToUseView.vue'
import AboutUsView from '@/views/AboutUsView.vue'
import ContactUsView from '@/views/ContactUsView.vue'

const routes = [
  { path: '/', name: 'Home', component: HomeView },
  { path: '/search', name: 'Search', component: SearchView },
  { path: '/category/:slug', name: 'ProductList', component: ProductListView },
  { path: '/product/:id', name: 'ProductDetail', component: ProductDetailView },
  { path: '/basket', name: 'Basket', component: BasketView },
  { path: '/about-us', name: 'AboutUs', component: AboutUsView },
  { path: '/contact', name: 'ContactUs', component: ContactUsView },
  { path: '/how-to-use', name: 'HowToUse', component: HowToUseView },
  { path: '/privacy-policy', name: 'PrivacyPolicy', component: PrivacyPolicyView },
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
