<template>
  <component
    :is="resolvedTag"
    class="sb-text"
    :class="[
      sizeClass,
      weightClass,
      colorClass,
      { 'sb-text--truncate': truncate, 'sb-text--heading': isHeading }
    ]"
  >
    <slot />
  </component>
</template>

<script setup>
/**
 * SbText — Atom
 *
 * A flexible typography primitive that maps semantic variants
 * to the correct HTML element and design-token classes.
 *
 * variant: display | h1 | h2 | h3 | h4 | body | body-sm | caption | overline | label
 */
import { computed } from 'vue'
defineOptions({ name: 'SbText' })

const props = defineProps({
  /** Semantic / visual variant */
  variant: {
    type: String,
    default: 'body',
    validator: v => [
      'display','h1','h2','h3','h4',
      'body','body-sm','caption','overline','label'
    ].includes(v)
  },
  /** Override the HTML tag (optional) */
  as: { type: String, default: null },
  /** Color role */
  color: {
    type: String,
    default: null,
    validator: v => ['primary','secondary','muted','accent','success','danger','warning','white',null].includes(v)
  },
  /** Truncate with ellipsis */
  truncate: { type: Boolean, default: false },
})

const variantTagMap = {
  display: 'h1',
  h1: 'h1',
  h2: 'h2',
  h3: 'h3',
  h4: 'h4',
  body: 'p',
  'body-sm': 'p',
  caption: 'span',
  overline: 'span',
  label: 'label',
}

const resolvedTag = computed(() => props.as || variantTagMap[props.variant] || 'p')

const isHeading = computed(() =>
  ['display','h1','h2','h3','h4'].includes(props.variant)
)

const sizeClass  = computed(() => `sb-text--${props.variant}`)
const weightClass = computed(() => null)
const colorClass = computed(() => props.color ? `sb-text--color-${props.color}` : null)
</script>

<style scoped>
.sb-text { margin: 0; }

/* ── Variants ── */
.sb-text--display {
  font-family: var(--font-heading);
  font-size: var(--text-4xl);
  font-weight: var(--fw-bold);
  line-height: var(--leading-tight);
  letter-spacing: var(--tracking-tight);
}
.sb-text--h1 {
  font-family: var(--font-heading);
  font-size: var(--text-3xl);
  font-weight: var(--fw-bold);
  line-height: var(--leading-tight);
  letter-spacing: var(--tracking-tight);
}
.sb-text--h2 {
  font-family: var(--font-heading);
  font-size: var(--text-2xl);
  font-weight: var(--fw-semibold);
  line-height: var(--leading-snug);
}
.sb-text--h3 {
  font-family: var(--font-heading);
  font-size: var(--text-xl);
  font-weight: var(--fw-semibold);
  line-height: var(--leading-snug);
}
.sb-text--h4 {
  font-family: var(--font-heading);
  font-size: var(--text-lg);
  font-weight: var(--fw-medium);
  line-height: var(--leading-snug);
}

.sb-text--body {
  font-size: var(--text-base);
  line-height: var(--leading-normal);
}
.sb-text--body-sm {
  font-size: var(--text-sm);
  line-height: var(--leading-normal);
}
.sb-text--caption {
  font-size: var(--text-xs);
  line-height: var(--leading-normal);
  color: var(--color-text-muted);
}
.sb-text--overline {
  font-size: var(--text-xs);
  font-weight: var(--fw-semibold);
  letter-spacing: var(--tracking-wider);
  text-transform: uppercase;
  color: var(--color-text-secondary);
}
.sb-text--label {
  font-size: var(--text-sm);
  font-weight: var(--fw-medium);
  line-height: var(--leading-snug);
}

/* headings use heading font */
.sb-text--heading {
  font-family: var(--font-heading);
}

/* ── Color overrides ── */
.sb-text--color-primary   { color: var(--color-primary); }
.sb-text--color-secondary { color: var(--color-text-secondary); }
.sb-text--color-muted     { color: var(--color-text-muted); }
.sb-text--color-accent    { color: var(--color-accent); }
.sb-text--color-success   { color: var(--color-success); }
.sb-text--color-danger    { color: var(--color-danger); }
.sb-text--color-warning   { color: var(--color-warning); }
.sb-text--color-white     { color: var(--color-white); }

/* ── Truncate ── */
.sb-text--truncate {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
