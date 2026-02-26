<template>
  <!-- Overlay -->
  <Transition name="overlay">
    <div v-if="modelValue" class="auth-overlay" @click="$emit('update:modelValue', false)" />
  </Transition>

  <!-- Sidebar panel -->
  <Transition name="sidebar">
    <div v-if="modelValue" class="auth-sidebar" role="dialog" aria-modal="true">
      <button class="auth-close" @click="$emit('update:modelValue', false)" aria-label="Close">
        <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
          <line x1="18" y1="6" x2="6" y2="18"/><line x1="6" y1="6" x2="18" y2="18"/>
        </svg>
      </button>

      <!-- Login -->
      <div v-if="mode === 'login'" class="auth-form">
        <h2 class="auth-title">Welcome Back!</h2>
        <p class="auth-subtitle">
          Don't have an account?
          <button class="auth-switch" @click="mode = 'signup'">Create a new one</button>
        </p>

        <div class="form-group">
          <label class="form-label">Email</label>
          <input
            v-model="email"
            type="email"
            class="form-input"
            placeholder="hello@example.com"
            autocomplete="email"
          />
        </div>

        <div class="form-group">
          <label class="form-label">
            Password
            <button class="form-label-link">Forgot password?</button>
          </label>
          <div class="input-wrap">
            <input
              v-model="password"
              :type="showPassword ? 'text' : 'password'"
              class="form-input"
              placeholder="Enter your password"
              autocomplete="current-password"
              @keyup.enter="handleLogin"
            />
            <button class="input-eye" @click="showPassword = !showPassword" type="button">
              <svg v-if="!showPassword" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/><circle cx="12" cy="12" r="3"/>
              </svg>
              <svg v-else width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24"/>
                <line x1="1" y1="1" x2="23" y2="23"/>
              </svg>
            </button>
          </div>
        </div>

        <p v-if="errorMsg" class="auth-error">{{ errorMsg }}</p>

        <button class="btn btn-primary auth-btn" @click="handleLogin" :disabled="loading">
          <span v-if="loading" class="spinner"></span>
          <span v-else>Login Now</span>
        </button>

        <div class="auth-divider"><span>or</span></div>

        <button class="btn-google" @click="handleGoogle">
          <svg width="18" height="18" viewBox="0 0 24 24">
            <path fill="#4285F4" d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z"/>
            <path fill="#34A853" d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z"/>
            <path fill="#FBBC05" d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l3.66-2.84z"/>
            <path fill="#EA4335" d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z"/>
          </svg>
          Login with Google
        </button>
      </div>

      <!-- Sign Up -->
      <div v-else class="auth-form">
        <h2 class="auth-title">Sign Up</h2>
        <p class="auth-subtitle">
          Already have an account?
          <button class="auth-switch" @click="mode = 'login'">Log in</button>
        </p>

        <div class="form-group">
          <label class="form-label">Name</label>
          <input
            v-model="name"
            type="text"
            class="form-input"
            placeholder="Enter your name"
            autocomplete="name"
          />
        </div>

        <div class="form-group">
          <label class="form-label">Email</label>
          <input
            v-model="email"
            type="email"
            class="form-input"
            placeholder="hello@example.com"
            autocomplete="email"
          />
        </div>

        <div class="form-group">
          <label class="form-label">Password</label>
          <div class="input-wrap">
            <input
              v-model="password"
              :type="showPassword ? 'text' : 'password'"
              class="form-input"
              placeholder="Enter your password"
              autocomplete="new-password"
              @keyup.enter="handleSignup"
            />
            <button class="input-eye" @click="showPassword = !showPassword" type="button">
              <svg v-if="!showPassword" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/><circle cx="12" cy="12" r="3"/>
              </svg>
              <svg v-else width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24"/>
                <line x1="1" y1="1" x2="23" y2="23"/>
              </svg>
            </button>
          </div>
        </div>

        <p v-if="errorMsg" class="auth-error">{{ errorMsg }}</p>

        <button class="btn btn-primary auth-btn" @click="handleSignup" :disabled="loading">
          <span v-if="loading" class="spinner"></span>
          <span v-else>Sign Up</span>
        </button>

        <div class="auth-divider"><span>or</span></div>

        <button class="btn-google" @click="handleGoogle">
          <svg width="18" height="18" viewBox="0 0 24 24">
            <path fill="#4285F4" d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z"/>
            <path fill="#34A853" d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z"/>
            <path fill="#FBBC05" d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l3.66-2.84z"/>
            <path fill="#EA4335" d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z"/>
          </svg>
          Sign up with Google
        </button>
      </div>
    </div>
  </Transition>
</template>

<script setup>
import { ref } from 'vue'

defineProps({ modelValue: Boolean })
const emit = defineEmits(['update:modelValue', 'login-success'])

const mode = ref('login')
const email = ref('')
const password = ref('')
const name = ref('')
const showPassword = ref(false)
const loading = ref(false)
const errorMsg = ref('')

function validate() {
  if (!email.value.trim()) { errorMsg.value = 'Email is required.'; return false }
  if (!password.value) { errorMsg.value = 'Password is required.'; return false }
  errorMsg.value = ''
  return true
}

async function handleLogin() {
  if (!validate()) return
  loading.value = true
  try {
    // TODO: replace with real API call
    // const res = await authService.login({ email, password })
    // localStorage.setItem('sb_token', res.token)
    emit('login-success', { email: email.value })
    emit('update:modelValue', false)
  } catch (e) {
    errorMsg.value = e.message || 'Login failed.'
  } finally {
    loading.value = false
  }
}

async function handleSignup() {
  if (!name.value.trim()) { errorMsg.value = 'Name is required.'; return }
  if (!validate()) return
  loading.value = true
  try {
    // TODO: replace with real API call
    // const res = await authService.signup({ name, email, password })
    emit('login-success', { email: email.value, name: name.value })
    emit('update:modelValue', false)
  } catch (e) {
    errorMsg.value = e.message || 'Sign up failed.'
  } finally {
    loading.value = false
  }
}

function handleGoogle() {
  const clientId = import.meta.env.VITE_GOOGLE_CLIENT_ID
  if (!clientId) {
    errorMsg.value = 'Google login not configured. Add VITE_GOOGLE_CLIENT_ID to .env'
    return
  }
  const redirectUri = `${window.location.origin}/auth/google/callback`
  const scope = 'openid email profile'
  const url = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${clientId}&redirect_uri=${encodeURIComponent(redirectUri)}&response_type=code&scope=${encodeURIComponent(scope)}&prompt=select_account`
  window.location.href = url
}
</script>

<style scoped>
/* Overlay */
.auth-overlay {
  position: fixed;
  inset: 0;
  background: rgba(0,0,0,0.45);
  z-index: 150;
  backdrop-filter: blur(2px);
}

/* Sidebar */
.auth-sidebar {
  position: fixed;
  top: 0;
  right: 0;
  height: 100dvh;
  width: 100%;
  max-width: 380px;
  background: var(--color-white);
  z-index: 200;
  overflow-y: auto;
  padding: var(--space-10) var(--space-8);
  box-shadow: var(--shadow-lg);
  display: flex;
  flex-direction: column;
}

.auth-close {
  position: absolute;
  top: var(--space-5);
  right: var(--space-5);
  width: 32px;
  height: 32px;
  border-radius: var(--radius-full);
  background: var(--color-grey-100);
  border: none;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  color: var(--color-grey-700);
  transition: all var(--transition-fast);
}
.auth-close:hover {
  background: var(--color-grey-300);
}

/* Form */
.auth-form {
  display: flex;
  flex-direction: column;
  gap: var(--space-5);
  margin-top: var(--space-6);
}

.auth-title {
  font-size: var(--font-size-2xl);
  color: var(--color-dark);
}

.auth-subtitle {
  font-size: var(--font-size-sm);
  color: var(--color-grey-500);
  margin-top: calc(-1 * var(--space-3));
}

.auth-switch {
  background: none;
  border: none;
  color: var(--color-primary);
  font-size: var(--font-size-sm);
  cursor: pointer;
  text-decoration: underline;
  font-family: var(--font-body);
  padding: 0;
}

/* Form fields */
.form-group {
  display: flex;
  flex-direction: column;
  gap: var(--space-2);
}

.form-label {
  font-size: var(--font-size-sm);
  font-weight: var(--font-weight-medium);
  color: var(--color-dark);
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.form-label-link {
  background: none;
  border: none;
  color: var(--color-primary);
  font-size: var(--font-size-xs);
  cursor: pointer;
  font-family: var(--font-body);
}

.form-input {
  width: 100%;
  padding: var(--space-3) var(--space-4);
  border: 1.5px solid var(--color-border);
  border-radius: var(--radius-md);
  font-family: var(--font-body);
  font-size: var(--font-size-sm);
  color: var(--color-dark);
  background: var(--color-grey-50);
  outline: none;
  transition: border-color var(--transition-fast), background var(--transition-fast);
}
.form-input:focus {
  border-color: var(--color-primary);
  background: var(--color-white);
}

.input-wrap {
  position: relative;
}
.input-wrap .form-input {
  padding-right: var(--space-10);
}
.input-eye {
  position: absolute;
  right: var(--space-3);
  top: 50%;
  transform: translateY(-50%);
  background: none;
  border: none;
  cursor: pointer;
  color: var(--color-grey-500);
  display: flex;
  align-items: center;
}
.input-eye:hover { color: var(--color-dark); }

.auth-error {
  font-size: var(--font-size-xs);
  color: var(--color-danger);
  background: #fef2f2;
  border: 1px solid #fecaca;
  border-radius: var(--radius-sm);
  padding: var(--space-2) var(--space-3);
}

.auth-btn {
  width: 100%;
  justify-content: center;
  padding: var(--space-3);
  font-size: var(--font-size-base);
}

/* Divider */
.auth-divider {
  display: flex;
  align-items: center;
  gap: var(--space-3);
  color: var(--color-grey-300);
  font-size: var(--font-size-xs);
}
.auth-divider::before,
.auth-divider::after {
  content: '';
  flex: 1;
  height: 1px;
  background: var(--color-border);
}

/* Google button */
.btn-google {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: var(--space-3);
  width: 100%;
  padding: var(--space-3);
  border: 1.5px solid var(--color-border);
  border-radius: var(--radius-full);
  background: var(--color-white);
  font-family: var(--font-body);
  font-size: var(--font-size-sm);
  font-weight: var(--font-weight-medium);
  color: var(--color-dark);
  cursor: pointer;
  transition: all var(--transition-fast);
}
.btn-google:hover {
  background: var(--color-grey-50);
  border-color: var(--color-grey-300);
  box-shadow: var(--shadow-sm);
}

/* Spinner */
.spinner {
  width: 16px;
  height: 16px;
  border: 2px solid rgba(255,255,255,0.4);
  border-top-color: white;
  border-radius: 50%;
  animation: spin 0.6s linear infinite;
  display: inline-block;
}
@keyframes spin { to { transform: rotate(360deg); } }

/* Transitions */
.overlay-enter-active, .overlay-leave-active { transition: opacity var(--transition-base); }
.overlay-enter-from, .overlay-leave-to { opacity: 0; }

.sidebar-enter-active, .sidebar-leave-active { transition: transform var(--transition-slow); }
.sidebar-enter-from, .sidebar-leave-to { transform: translateX(100%); }
</style>