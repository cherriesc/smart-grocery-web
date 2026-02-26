<template>
  <AppHeader :basket-count="basketCount" />
  <RouterView />
  <CookieConsentModal
    v-if="showCookieModal"
    @allow="handleCookieConsent('allow')"
    @decline="handleCookieConsent('decline')"
  />
</template>

<script setup>
import { onMounted, ref } from 'vue'
import AppHeader from '@/components/AppHeader.vue'
import CookieConsentModal from '@/components/CookieConsentModal.vue'

// basketCount can be replaced with a Pinia store getter later
const basketCount = ref(0)
const showCookieModal = ref(false)
const COOKIE_CONSENT_KEY = 'cookie_consent'

function handleCookieConsent(choice) {
  localStorage.setItem(COOKIE_CONSENT_KEY, choice)
  showCookieModal.value = false
}

onMounted(() => {
  const savedConsent = localStorage.getItem(COOKIE_CONSENT_KEY)
  showCookieModal.value = !savedConsent
})
</script>
