<template>
  <span
    class="sb-badge"
    :class="[`sb-badge--${variant}`, `sb-badge--${size}`]"
    v-bind="$attrs"
  >
    <slot />
  </span>
</template>

<script setup>
/**
 * SbBadge — Atom
 *
 * Small indicator for counts, statuses, etc.
 * Variants: default | primary | accent | success | danger | warning
 * Sizes:    xs | sm | md
 */
defineOptions({ name: 'SbBadge', inheritAttrs: false })

defineProps({
  variant: {
    type: String,
    default: 'primary',
    validator: v => ['default','primary','accent','success','danger','warning'].includes(v)
  },
  size: {
    type: String,
    default: 'sm',
    validator: v => ['xs','sm','md'].includes(v)
  }
})
</script>

<style scoped>
.sb-badge {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  border-radius: var(--radius-pill);
  font-family: var(--font-body);
  font-weight: var(--fw-semibold);
  line-height: 1;
  white-space: nowrap;
}

/* Sizes */
.sb-badge--xs { font-size: 10px; min-width: 16px; height: 16px; padding: 0 4px; }
.sb-badge--sm { font-size: var(--text-xs); min-width: 20px; height: 20px; padding: 0 6px; }
.sb-badge--md { font-size: var(--text-sm); min-width: 24px; height: 24px; padding: 0 8px; }

/* Variants */
.sb-badge--default {
  background-color: var(--color-primary-light);
  color: var(--color-primary);
}
.sb-badge--primary {
  background-color: var(--color-primary);
  color: var(--color-white);
}
.sb-badge--accent {
  background-color: var(--color-accent);
  color: var(--color-white);
}
.sb-badge--success {
  background-color: #E8F8F0;
  color: var(--color-success);
}
.sb-badge--danger {
  background-color: #FDECEA;
  color: var(--color-danger);
}
.sb-badge--warning {
  background-color: #FEF6E4;
  color: var(--color-warning);
}
</style>
