<template>
  <AppHeader :basket-count="basketCount" />
  <RouterView />
</template>

<script setup>
import { ref } from 'vue'
import AppHeader from '@/components/AppHeader.vue'

// basketCount can be replaced with a Pinia store getter later
const basketCount = ref(0)
</script>