<template>
  <div class="sb-tabs" :class="`sb-tabs--${variant}`" role="tablist">
    <button
      v-for="tab in tabs"
      :key="tab.value"
      class="sb-tabs__tab"
      :class="{ 'sb-tabs__tab--active': modelValue === tab.value }"
      role="tab"
      :aria-selected="modelValue === tab.value"
      :tabindex="modelValue === tab.value ? 0 : -1"
      @click="$emit('update:modelValue', tab.value)"
      @keydown.right="focusNext"
      @keydown.left="focusPrev"
    >
      <span v-if="tab.icon" class="sb-tabs__icon" v-html="tab.icon" />
      {{ tab.label }}
      <span v-if="tab.count !== undefined" class="sb-tabs__count">{{ tab.count }}</span>
    </button>

    <!-- Sliding indicator (pill variant) -->
    <span v-if="variant === 'pill'" class="sb-tabs__indicator" />
  </div>
</template>

<script setup>
/**
 * SbTabs — Atom
 *
 * Variants: underline | pill | boxed
 *
 * tabs prop: Array<{ label: string; value: string; icon?: string; count?: number }>
 */
import { useTemplateRef } from 'vue'
defineOptions({ name: 'SbTabs' })

const props = defineProps({
  tabs: {
    type: Array,
    required: true,
    // each item: { label, value, icon?, count? }
  },
  modelValue: { type: String, required: true },
  variant: {
    type: String,
    default: 'underline',
    validator: v => ['underline', 'pill', 'boxed'].includes(v)
  }
})

defineEmits(['update:modelValue'])
</script>

<style scoped>
/* ── Wrapper ── */
.sb-tabs {
  display: flex;
  align-items: center;
  gap: var(--space-1);
}

/* ── Shared tab button ── */
.sb-tabs__tab {
  display: inline-flex;
  align-items: center;
  gap: var(--space-2);
  cursor: pointer;
  font-family: var(--font-body);
  font-size: var(--text-sm);
  font-weight: var(--fw-medium);
  color: var(--color-text-secondary);
  background: none;
  border: none;
  white-space: nowrap;
  transition:
    color var(--transition-fast),
    background-color var(--transition-fast),
    border-color var(--transition-fast);
  outline: none;
}
.sb-tabs__tab:focus-visible {
  box-shadow: 0 0 0 3px rgba(108, 99, 199, 0.3);
  border-radius: var(--radius-sm);
}

/* ── Count badge ── */
.sb-tabs__count {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  min-width: 18px;
  height: 18px;
  padding: 0 5px;
  border-radius: var(--radius-pill);
  background-color: var(--color-primary-light);
  color: var(--color-primary);
  font-size: var(--text-xs);
  font-weight: var(--fw-semibold);
  line-height: 1;
}
.sb-tabs__tab--active .sb-tabs__count {
  background-color: var(--color-primary);
  color: var(--color-white);
}

/* ──────────────────────────────────────────
   VARIANT: underline
────────────────────────────────────────── */
.sb-tabs--underline {
  border-bottom: 2px solid var(--color-border);
  gap: 0;
}
.sb-tabs--underline .sb-tabs__tab {
  padding: var(--space-3) var(--space-5);
  border-bottom: 2px solid transparent;
  margin-bottom: -2px;
  border-radius: var(--radius-sm) var(--radius-sm) 0 0;
}
.sb-tabs--underline .sb-tabs__tab:hover {
  color: var(--color-primary);
  border-bottom-color: var(--color-border);
}
.sb-tabs--underline .sb-tabs__tab--active {
  color: var(--color-primary);
  font-weight: var(--fw-semibold);
  border-bottom-color: var(--color-primary);
}

/* ──────────────────────────────────────────
   VARIANT: pill
────────────────────────────────────────── */
.sb-tabs--pill {
  background-color: var(--color-primary-light);
  border-radius: var(--radius-pill);
  padding: var(--space-1);
  gap: var(--space-1);
}
.sb-tabs--pill .sb-tabs__tab {
  padding: var(--space-2) var(--space-4);
  border-radius: var(--radius-pill);
}
.sb-tabs--pill .sb-tabs__tab:hover {
  color: var(--color-primary);
}
.sb-tabs--pill .sb-tabs__tab--active {
  background-color: var(--color-white);
  color: var(--color-primary);
  font-weight: var(--fw-semibold);
  box-shadow: var(--shadow-xs);
}

/* ──────────────────────────────────────────
   VARIANT: boxed
────────────────────────────────────────── */
.sb-tabs--boxed {
  border: 1.5px solid var(--color-border);
  border-radius: var(--radius-md);
  overflow: hidden;
  gap: 0;
}
.sb-tabs--boxed .sb-tabs__tab {
  padding: var(--space-2) var(--space-5);
  border-right: 1.5px solid var(--color-border);
}
.sb-tabs--boxed .sb-tabs__tab:last-child { border-right: none; }
.sb-tabs--boxed .sb-tabs__tab:hover { background-color: var(--color-primary-light); color: var(--color-primary); }
.sb-tabs--boxed .sb-tabs__tab--active {
  background-color: var(--color-primary);
  color: var(--color-white);
  font-weight: var(--fw-semibold);
}

.sb-tabs__icon { display: inline-flex; }
</style>
