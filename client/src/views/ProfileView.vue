<template>
  <div class="page-content">
    <div class="container">
      <div class="profile-wrap">

        <div class="profile-card card">
          <div class="profile-avatar">
            {{ avatarInitial }}
          </div>
          <h1 class="profile-username">{{ user.username }}</h1>
          <p class="profile-email">{{ user.email }}</p>
          <button class="btn btn-outline logout-btn" @click="handleLogout">
            Log out
          </button>
        </div>

        <div class="profile-section">
          <h2 class="profile-section-title">Your Basket</h2>
          <p class="profile-hint" v-if="!basketItems.length">
            Your basket is empty.
            <RouterLink to="/">Start browsing</RouterLink>
          </p>
          <div v-else class="profile-basket-list">
            <div
              v-for="item in basketItems"
              :key="item.id"
              class="profile-basket-item card"
            >
              <div class="item-img img-placeholder">
                <img v-if="item.imageUrl" :src="item.imageUrl" :alt="item.name" />
                <svg v-else viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5" style="width:24px;height:24px">
                  <rect x="3" y="3" width="18" height="18" rx="2"/>
                </svg>
              </div>
              <div class="item-info">
                <p class="item-brand">{{ item.brand }}</p>
                <p class="item-name">{{ item.name }}</p>
              </div>
              <div class="item-qty">× {{ item.quantity }}</div>
            </div>
          </div>
        </div>

      </div>
    </div>
    <AppFooter />
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useRouter } from 'vue-router'
import AppFooter from '@/components/AppFooter.vue'
import { useAuth } from '@/composables/useAuth.js'
import { useBasket } from '@/composables/useBasket.js'

const router = useRouter()
const { user, logout } = useAuth()
const { items: basketItems } = useBasket()

const avatarInitial = computed(() =>
  user.value?.username?.charAt(0).toUpperCase() || '?'
)

function handleLogout() {
  logout()
  router.push('/')
}
</script>

<style scoped>
.profile-wrap {
  display: grid;
  grid-template-columns: 280px 1fr;
  gap: var(--space-10);
  padding: var(--space-10) 0 var(--space-16);
  align-items: start;
}

.profile-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: var(--space-3);
  padding: var(--space-8);
  text-align: center;
  position: sticky;
  top: calc(var(--header-height) + var(--space-6));
}

.profile-avatar {
  width: 80px;
  height: 80px;
  border-radius: var(--radius-full);
  background: var(--color-primary);
  color: var(--color-white);
  font-family: var(--font-heading);
  font-size: var(--font-size-2xl);
  font-weight: var(--font-weight-extrabold);
  display: flex;
  align-items: center;
  justify-content: center;
}

.profile-username {
  font-size: var(--font-size-xl);
  color: var(--color-dark);
}

.profile-email {
  font-size: var(--font-size-sm);
  color: var(--color-grey-500);
  margin-top: calc(-1 * var(--space-1));
}

.logout-btn {
  margin-top: var(--space-4);
  width: 100%;
  justify-content: center;
  padding: var(--space-2) var(--space-4);
  border: 1.5px solid var(--color-border);
  border-radius: var(--radius-full);
  background: none;
  color: var(--color-danger);
  font-family: var(--font-body);
  font-size: var(--font-size-sm);
  font-weight: var(--font-weight-medium);
  cursor: pointer;
  transition: all var(--transition-fast);
}
.logout-btn:hover {
  background: #fef2f2;
  border-color: var(--color-danger);
}

.profile-section-title {
  font-size: var(--font-size-xl);
  color: var(--color-dark);
  margin-bottom: var(--space-5);
}

.profile-hint {
  font-size: var(--font-size-sm);
  color: var(--color-grey-500);
}
.profile-hint a {
  color: var(--color-primary);
}

.profile-basket-list {
  display: flex;
  flex-direction: column;
  gap: var(--space-3);
}

.profile-basket-item {
  display: flex;
  align-items: center;
  gap: var(--space-4);
  padding: var(--space-3) var(--space-4);
}

.item-img {
  width: 48px;
  height: 48px;
  flex-shrink: 0;
  border-radius: var(--radius-sm);
  overflow: hidden;
}
.item-img img {
  width: 100%;
  height: 100%;
  object-fit: contain;
}

.item-brand {
  font-size: var(--font-size-sm);
  font-weight: var(--font-weight-semibold);
  color: var(--color-dark);
}
.item-name {
  font-size: var(--font-size-xs);
  color: var(--color-grey-500);
}

.item-qty {
  margin-left: auto;
  font-size: var(--font-size-sm);
  font-weight: var(--font-weight-semibold);
  color: var(--color-grey-700);
}

@media (max-width: 768px) {
  .profile-wrap { grid-template-columns: 1fr; }
  .profile-card { position: static; }
}
</style>