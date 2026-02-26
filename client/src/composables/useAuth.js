import { ref, computed } from 'vue'

const STORAGE_KEY = 'sb_user'

function loadUser() {
  try {
    const raw = localStorage.getItem(STORAGE_KEY)
    return raw ? JSON.parse(raw) : null
  } catch {
    return null
  }
}

const user = ref(loadUser())

export function useAuth() {
  const isLoggedIn = computed(() => user.value !== null)

  function setUser(userData) {
    user.value = userData
    localStorage.setItem(STORAGE_KEY, JSON.stringify(userData))
  }

  function logout() {
    user.value = null
    localStorage.removeItem(STORAGE_KEY)
  }

  return { user, isLoggedIn, setUser, logout }
}