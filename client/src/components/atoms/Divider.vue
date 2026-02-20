<template>
  <div
    class="sb-divider"
    :class="[`sb-divider--${orientation}`, spacing && `sb-divider--spacing-${spacing}`]"
    role="separator"
    :aria-orientation="orientation"
  >
    <span v-if="$slots.default" class="sb-divider__label">
      <slot />
    </span>
  </div>
</template>

<script setup>
/**
 * SbDivider — Atom
 * orientation: horizontal | vertical
 * spacing: none | sm | md | lg
 */
defineOptions({ name: 'SbDivider' })
defineProps({
  orientation: { type: String, default: 'horizontal', validator: v => ['horizontal','vertical'].includes(v) },
  spacing:     { type: String, default: 'md', validator: v => ['none','sm','md','lg'].includes(v) },
})
</script>

<style scoped>
.sb-divider {
  background-color: var(--color-border);
  flex-shrink: 0;
}

/* Horizontal */
.sb-divider--horizontal {
  width: 100%;
  height: 1px;
  display: flex;
  align-items: center;
  gap: var(--space-3);
}
.sb-divider--horizontal.sb-divider--spacing-sm  { margin: var(--space-2) 0; }
.sb-divider--horizontal.sb-divider--spacing-md  { margin: var(--space-4) 0; }
.sb-divider--horizontal.sb-divider--spacing-lg  { margin: var(--space-8) 0; }

/* Vertical */
.sb-divider--vertical {
  height: 100%;
  width: 1px;
  align-self: stretch;
}
.sb-divider--vertical.sb-divider--spacing-sm  { margin: 0 var(--space-2); }
.sb-divider--vertical.sb-divider--spacing-md  { margin: 0 var(--space-4); }
.sb-divider--vertical.sb-divider--spacing-lg  { margin: 0 var(--space-8); }

/* With label */
.sb-divider--horizontal .sb-divider__label {
  background-color: var(--color-bg);
  color: var(--color-text-muted);
  font-size: var(--text-xs);
  font-weight: var(--fw-medium);
  white-space: nowrap;
  padding: 0 var(--space-3);
}
</style>