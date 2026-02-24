<template>
  <component
    :is="href ? 'a' : 'div'"
    class="sb-logo"
    :class="[`sb-logo--${size}`, { 'sb-logo--light': light }]"
    :href="href || undefined"
  >
    <!-- Icon mark -->
    <span class="sb-logo__mark">
      <svg viewBox="0 0 36 36" fill="none" xmlns="http://www.w3.org/2000/svg">
        <!-- Shopping basket icon -->
        <rect width="36" height="36" rx="9" fill="currentColor" class="sb-logo__bg"/>
        <path d="M10 14h16l-2 10H12L10 14z" stroke="white" stroke-width="1.8" fill="none" stroke-linecap="round" stroke-linejoin="round"/>
        <path d="M14 14l2-4M22 14l-2-4" stroke="white" stroke-width="1.8" stroke-linecap="round"/>
        <path d="M15 19h6M15 22h4" stroke="white" stroke-width="1.5" stroke-linecap="round"/>
      </svg>
    </span>

    <!-- Wordmark -->
    <span class="sb-logo__wordmark">
      <span class="sb-logo__word-smart">smart</span><span class="sb-logo__word-basket">basket</span>
    </span>
  </component>
</template>

<script setup>
/**
 * SbLogo — Atom
 * Sizes: sm | md | lg
 * light: renders on dark backgrounds
 */
defineOptions({ name: 'SbLogo' })
defineProps({
  size:  { type: String, default: 'md', validator: v => ['sm','md','lg'].includes(v) },
  light: { type: Boolean, default: false },
  href:  { type: String, default: '/' },
})
</script>

<style scoped>
.sb-logo {
  display: inline-flex;
  align-items: center;
  gap: var(--space-2);
  text-decoration: none;
  user-select: none;
  flex-shrink: 0;
}

/* sizes */
.sb-logo--sm  { gap: 6px; }
.sb-logo--sm  .sb-logo__mark { width: 26px; height: 26px; }
.sb-logo--sm  .sb-logo__wordmark { font-size: 14px; }

.sb-logo--md  .sb-logo__mark { width: 32px; height: 32px; }
.sb-logo--md  .sb-logo__wordmark { font-size: 17px; }

.sb-logo--lg  .sb-logo__mark { width: 44px; height: 44px; }
.sb-logo--lg  .sb-logo__wordmark { font-size: 22px; }

/* mark */
.sb-logo__mark {
  display: flex;
  align-items: center;
  color: var(--color-primary);
  flex-shrink: 0;
}
.sb-logo__mark svg { width: 100%; height: 100%; }
.sb-logo__bg { fill: var(--color-primary); }

/* wordmark */
.sb-logo__wordmark {
  font-family: var(--font-heading);
  font-weight: var(--fw-bold);
  letter-spacing: -0.03em;
  line-height: 1;
  color: var(--color-text-primary);
}
.sb-logo__word-smart   { color: var(--color-primary); }
.sb-logo__word-basket  { color: var(--color-accent); }

/* light variant */
.sb-logo--light .sb-logo__mark { color: var(--color-white); }
.sb-logo--light .sb-logo__bg   { fill: rgba(255,255,255,0.2); }
.sb-logo--light .sb-logo__word-smart  { color: var(--color-white); }
.sb-logo--light .sb-logo__word-basket { color: rgba(255,255,255,0.75); }
</style>