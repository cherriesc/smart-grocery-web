<template>
  <div class="sb-img-placeholder" :class="`sb-img-placeholder--${variant}`" :style="styleObj">
    <svg class="sb-img-placeholder__icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.2" stroke-linecap="round" stroke-linejoin="round">
      <rect x="3" y="3" width="18" height="18" rx="2" ry="2"/>
      <circle cx="8.5" cy="8.5" r="1.5"/>
      <polyline points="21 15 16 10 5 21"/>
    </svg>
    <span v-if="label" class="sb-img-placeholder__label">{{ label }}</span>
  </div>
</template>

<script setup>
/**
 * SbImagePlaceholder — Atom
 * A placeholder element rendered when a product image is unavailable.
 * variants: default | subtle | dark
 */
import { computed } from 'vue'
defineOptions({ name: 'SbImagePlaceholder' })

const props = defineProps({
  width:   { type: [String, Number], default: '100%' },
  height:  { type: [String, Number], default: '160px' },
  variant: { type: String, default: 'default', validator: v => ['default','subtle','dark'].includes(v) },
  label:   { type: String, default: '' },
})

const styleObj = computed(() => ({
  width:  typeof props.width  === 'number' ? `${props.width}px`  : props.width,
  height: typeof props.height === 'number' ? `${props.height}px` : props.height,
}))
</script>

<style scoped>
.sb-img-placeholder {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: var(--space-2);
  border-radius: var(--radius-md);
  overflow: hidden;
  flex-shrink: 0;
}

.sb-img-placeholder--default {
  background-color: var(--color-primary-light);
  color: var(--color-text-muted);
}
.sb-img-placeholder--subtle {
  background-color: #F0EFF6;
  color: var(--color-border);
}
.sb-img-placeholder--dark {
  background-color: var(--color-primary);
  color: rgba(255,255,255,0.35);
}

.sb-img-placeholder__icon {
  width: 40px;
  height: 40px;
  opacity: 0.6;
}
.sb-img-placeholder__label {
  font-size: var(--text-xs);
  color: inherit;
  opacity: 0.7;
}
</style>