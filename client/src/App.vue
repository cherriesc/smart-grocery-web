<template>
  <AppHeader
    :basket-count="basketCount"
    @open-auth="authOpen = true"
  />
  <RouterView />
  <AuthSidebar v-model="authOpen" @login-success="onLoginSuccess" />
</template>

<script setup>
import { ref } from 'vue'
import AppHeader from '@/components/AppHeader.vue'
import AuthSidebar from '@/components/AuthSidebar.vue'
import { useBasket } from '@/composables/useBasket.js'

const { basketCount } = useBasket()
const authOpen = ref(false)

function onLoginSuccess(user) {
  console.log('Logged in:', user)
  // TODO: store user info when backend is ready
}
</script>