/**
 * useBasket.js — shared basket state persisted to localStorage
 * Import this composable in any component that needs basket access.
 */
import { ref, watch, computed } from 'vue'

const STORAGE_KEY = 'sb_basket'

function loadFromStorage() {
  try {
    const raw = localStorage.getItem(STORAGE_KEY)
    return raw ? JSON.parse(raw) : []
  } catch {
    return []
  }
}

// Module-level reactive state (shared across all components)
const items = ref(loadFromStorage())

watch(items, (val) => {
  localStorage.setItem(STORAGE_KEY, JSON.stringify(val))
}, { deep: true })

export function useBasket() {
  const basketCount = computed(() => items.value.reduce((sum, i) => sum + i.quantity, 0))

  function addItem(product) {
    const existing = items.value.find(i => i.id === product.id)
    if (existing) {
      existing.quantity++
    } else {
      items.value = [...items.value, { ...product, quantity: 1 }]
    }
  }

  function removeItem(id) {
    items.value = items.value.filter(i => i.id !== id)
  }

  function increaseQty(id) {
    const item = items.value.find(i => i.id === id)
    if (item) item.quantity++
  }

  function decreaseQty(id) {
    const item = items.value.find(i => i.id === id)
    if (item && item.quantity > 1) item.quantity--
    else if (item && item.quantity === 1) removeItem(id)
  }

  function clearBasket() {
    items.value = []
  }

  return { items, basketCount, addItem, removeItem, increaseQty, decreaseQty, clearBasket }
}