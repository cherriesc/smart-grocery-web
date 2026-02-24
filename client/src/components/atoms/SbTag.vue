<template>
  <span
    class="sb-tag"
    :class="[
      `sb-tag--${variant}`,
      `sb-tag--${size}`,
      { 'sb-tag--clickable': clickable, 'sb-tag--active': active }
    ]"
    :role="clickable ? 'button' : undefined"
    :tabindex="clickable ? 0 : undefined"
    @click="clickable && $emit('click', $event)"
    @keydown.enter="clickable && $emit('click', $event)"
  >
    <!-- dot indicator -->
    <span v-if="dot" class="sb-tag__dot" />

    <!-- label -->
    <slot />

    <!-- dismiss button -->
    <button
      v-if="dismissible"
      class="sb-tag__dismiss"
      type="button"
      aria-label="Remove"
      @click.stop="$emit('dismiss')"
    >
      ✕
    </button>
  </span>
</template>

<script setup>
/**
 * SbTag — Atom
 *
 * Variants:  default | primary | accent | success | warning | danger | muted
 * Sizes:     sm | md
 */
defineOptions({ name: 'SbTag' })

defineProps({
  variant: {
    type: String,
    default: 'default',
    validator: v => ['default','primary','accent','success','warning','danger','muted'].includes(v)
  },
  size:        { type: String, default: 'md', validator: v => ['sm','md'].includes(v) },
  clickable:   { type: Boolean, default: false },
  active:      { type: Boolean, default: false },
  dismissible: { type: Boolean, default: false },
  dot:         { type: Boolean, default: false },
})

defineEmits(['click','dismiss'])
</script>

<style scoped>
.sb-tag {
  display: inline-flex;
  align-items: center;
  gap: 5px;
  border-radius: var(--radius-pill);
  font-family: var(--font-body);
  font-weight: var(--fw-medium);
  border: 1.5px solid transparent;
  line-height: 1;
  white-space: nowrap;
  transition: background-color var(--transition-fast), border-color var(--transition-fast);
}

/* sizes */
.sb-tag--sm { font-size: var(--text-xs); padding: 3px 10px; }
.sb-tag--md { font-size: var(--text-sm); padding: 5px 14px; }

/* clickable */
.sb-tag--clickable { cursor: pointer; }
.sb-tag--clickable:focus-visible { box-shadow: 0 0 0 3px rgba(108,99,199,0.35); outline: none; }

/* variants */
.sb-tag--default {
  background-color: var(--color-primary-light);
  color: var(--color-primary);
  border-color: var(--color-border);
}
.sb-tag--default.sb-tag--clickable:hover,
.sb-tag--default.sb-tag--active {
  background-color: var(--color-primary);
  color: var(--color-white);
  border-color: var(--color-primary);
}

.sb-tag--primary {
  background-color: var(--color-primary);
  color: var(--color-white);
}

.sb-tag--accent {
  background-color: var(--color-accent);
  color: var(--color-white);
}

.sb-tag--success {
  background-color: #E8F8F0;
  color: var(--color-success);
  border-color: #A8E6C3;
}

.sb-tag--warning {
  background-color: #FEF6E4;
  color: var(--color-warning);
  border-color: #FAD89A;
}

.sb-tag--danger {
  background-color: #FDECEA;
  color: var(--color-danger);
  border-color: #F5BCBA;
}

.sb-tag--muted {
  background-color: #F0EFF6;
  color: var(--color-text-muted);
  border-color: var(--color-border);
}

/* dot */
.sb-tag__dot {
  width: 6px;
  height: 6px;
  border-radius: 50%;
  background-color: currentColor;
  flex-shrink: 0;
}

/* dismiss button */
.sb-tag__dismiss {
  background: none;
  border: none;
  padding: 0;
  cursor: pointer;
  font-size: 9px;
  color: currentColor;
  opacity: 0.65;
  line-height: 1;
  display: inline-flex;
  align-items: center;
  transition: opacity var(--transition-fast);
}
.sb-tag__dismiss:hover { opacity: 1; }
</style>
