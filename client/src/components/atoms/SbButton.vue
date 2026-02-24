<template>
  <component
    :is="tag"
    class="sb-button"
    :class="[
      `sb-button--${variant}`,
      `sb-button--${size}`,
      { 'sb-button--icon-only': iconOnly, 'sb-button--loading': loading, 'sb-button--full': full }
    ]"
    :disabled="disabled || loading"
    v-bind="$attrs"
  >
    <!-- Leading icon slot -->
    <span v-if="$slots['icon-left'] && !loading" class="sb-button__icon sb-button__icon--left">
      <slot name="icon-left" />
    </span>

    <!-- Spinner -->
    <span v-if="loading" class="sb-button__spinner" aria-hidden="true" />

    <!-- Label -->
    <span v-if="!iconOnly" class="sb-button__label">
      <slot />
    </span>

    <!-- Trailing icon slot -->
    <span v-if="$slots['icon-right'] && !loading" class="sb-button__icon sb-button__icon--right">
      <slot name="icon-right" />
    </span>
  </component>
</template>

<script setup>
/**
 * SbButton — Atom
 *
 * Variants:  primary | secondary | outline | ghost | danger | link
 * Sizes:     xs | sm | md | lg
 */
defineOptions({ name: 'SbButton', inheritAttrs: false })

const props = defineProps({
  /** Visual style */
  variant: {
    type: String,
    default: 'primary',
    validator: v => ['primary','secondary','outline','ghost','danger','link'].includes(v)
  },
  /** Size preset */
  size: {
    type: String,
    default: 'md',
    validator: v => ['xs','sm','md','lg'].includes(v)
  },
  /** Render as a different HTML element / router-link */
  tag: { type: String, default: 'button' },
  /** Disabled state */
  disabled: { type: Boolean, default: false },
  /** Loading / spinner state */
  loading: { type: Boolean, default: false },
  /** Icon-only mode (square, no label) */
  iconOnly: { type: Boolean, default: false },
  /** Full-width block button */
  full: { type: Boolean, default: false },
})
</script>

<style scoped>
/* ── Base ─────────────────────────────────── */
.sb-button {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  gap: var(--space-2);
  font-family: var(--font-body);
  font-weight: var(--fw-semibold);
  letter-spacing: 0.01em;
  border: 1.5px solid transparent;
  border-radius: var(--radius-md);
  cursor: pointer;
  transition:
    background-color var(--transition-fast),
    border-color var(--transition-fast),
    color var(--transition-fast),
    box-shadow var(--transition-fast),
    transform var(--transition-fast);
  white-space: nowrap;
  user-select: none;
  text-decoration: none;
  position: relative;
  outline: none;
}
.sb-button:focus-visible {
  box-shadow: 0 0 0 3px rgba(108, 99, 199, 0.35);
}
.sb-button:active:not(:disabled) {
  transform: scale(0.97);
}
.sb-button:disabled {
  cursor: not-allowed;
  opacity: 0.48;
}
.sb-button--full { width: 100%; }

/* ── Sizes ────────────────────────────────── */
.sb-button--xs { font-size: var(--text-xs);  padding: var(--space-1) var(--space-3); min-height: 28px; border-radius: var(--radius-sm); }
.sb-button--sm { font-size: var(--text-sm);  padding: var(--space-2) var(--space-4); min-height: 36px; }
.sb-button--md { font-size: var(--text-base);padding: var(--space-3) var(--space-6); min-height: 44px; }
.sb-button--lg { font-size: var(--text-md);  padding: var(--space-4) var(--space-8); min-height: 52px; border-radius: var(--radius-lg); }

/* icon-only: make square */
.sb-button--icon-only.sb-button--xs { padding: var(--space-1); width: 28px; }
.sb-button--icon-only.sb-button--sm { padding: var(--space-2); width: 36px; }
.sb-button--icon-only.sb-button--md { padding: var(--space-3); width: 44px; }
.sb-button--icon-only.sb-button--lg { padding: var(--space-4); width: 52px; }

/* ── Variants ─────────────────────────────── */

/* primary */
.sb-button--primary {
  background-color: var(--color-primary);
  border-color: var(--color-primary);
  color: var(--color-white);
}
.sb-button--primary:hover:not(:disabled) {
  background-color: var(--color-primary-hover);
  border-color: var(--color-primary-hover);
  box-shadow: var(--shadow-sm);
}

/* secondary */
.sb-button--secondary {
  background-color: var(--color-accent);
  border-color: var(--color-accent);
  color: var(--color-white);
}
.sb-button--secondary:hover:not(:disabled) {
  background-color: var(--color-accent-hover);
  border-color: var(--color-accent-hover);
  box-shadow: var(--shadow-sm);
}

/* outline */
.sb-button--outline {
  background-color: transparent;
  border-color: var(--color-primary);
  color: var(--color-primary);
}
.sb-button--outline:hover:not(:disabled) {
  background-color: var(--color-primary-light);
}

/* ghost */
.sb-button--ghost {
  background-color: transparent;
  border-color: transparent;
  color: var(--color-primary);
}
.sb-button--ghost:hover:not(:disabled) {
  background-color: var(--color-primary-light);
}

/* danger */
.sb-button--danger {
  background-color: var(--color-danger);
  border-color: var(--color-danger);
  color: var(--color-white);
}
.sb-button--danger:hover:not(:disabled) {
  filter: brightness(0.9);
}

/* link */
.sb-button--link {
  background: transparent;
  border-color: transparent;
  color: var(--color-accent);
  padding-left: 0;
  padding-right: 0;
  text-decoration: underline;
  text-underline-offset: 2px;
}
.sb-button--link:hover:not(:disabled) {
  color: var(--color-accent-hover);
}

/* ── Icons ────────────────────────────────── */
.sb-button__icon { display: inline-flex; align-items: center; flex-shrink: 0; }

/* ── Spinner ──────────────────────────────── */
.sb-button__spinner {
  width: 1em;
  height: 1em;
  border: 2px solid currentColor;
  border-top-color: transparent;
  border-radius: 50%;
  animation: sb-spin 0.65s linear infinite;
  flex-shrink: 0;
}
@keyframes sb-spin { to { transform: rotate(360deg); } }

.sb-button--loading .sb-button__label { opacity: 0.6; }
</style>
