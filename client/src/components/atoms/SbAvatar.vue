<template>
  <div
    class="sb-avatar"
    :class="[`sb-avatar--${size}`, `sb-avatar--${variant}`]"
    :title="alt"
  >
    <img v-if="src" :src="src" :alt="alt" class="sb-avatar__img" @error="onError" />
    <span v-else class="sb-avatar__initials">{{ initials }}</span>
  </div>
</template>

<script setup>
/**
 * SbAvatar — Atom
 * Sizes: xs | sm | md | lg | xl
 * Variants: circle | square
 */
import { computed, ref } from 'vue'
defineOptions({ name: 'SbAvatar' })

const props = defineProps({
  src:     { type: String, default: null },
  alt:     { type: String, default: '' },
  name:    { type: String, default: '' },
  size:    { type: String, default: 'md', validator: v => ['xs','sm','md','lg','xl'].includes(v) },
  variant: { type: String, default: 'circle', validator: v => ['circle','square'].includes(v) },
})

const imgFailed = ref(false)
const initials = computed(() => {
  if (!props.name) return '?'
  return props.name.split(' ').slice(0, 2).map(w => w[0]).join('').toUpperCase()
})
function onError() { imgFailed.value = true }
</script>

<style scoped>
.sb-avatar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  flex-shrink: 0;
  background-color: var(--color-primary-light);
  color: var(--color-primary);
  font-family: var(--font-body);
  font-weight: var(--fw-semibold);
  user-select: none;
}

/* sizes */
.sb-avatar--xs  { width: 24px; height: 24px; font-size: 9px;  }
.sb-avatar--sm  { width: 32px; height: 32px; font-size: var(--text-xs); }
.sb-avatar--md  { width: 40px; height: 40px; font-size: var(--text-sm); }
.sb-avatar--lg  { width: 52px; height: 52px; font-size: var(--text-base); }
.sb-avatar--xl  { width: 72px; height: 72px; font-size: var(--text-xl); }

/* variants */
.sb-avatar--circle { border-radius: 50%; }
.sb-avatar--square { border-radius: var(--radius-md); }

.sb-avatar__img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.sb-avatar__initials { line-height: 1; }
</style>