<template>
  <div
    class="sb-search"
    :class="[`sb-search--${size}`, { 'sb-search--focused': isFocused, 'sb-search--filled': modelValue }]"
  >
    <!-- Search icon -->
    <span class="sb-search__icon" aria-hidden="true">
      <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
        <circle cx="11" cy="11" r="8"/>
        <line x1="21" y1="21" x2="16.65" y2="16.65"/>
      </svg>
    </span>

    <!-- Input -->
    <input
      ref="inputRef"
      class="sb-search__input"
      type="search"
      :placeholder="placeholder"
      :value="modelValue"
      :disabled="disabled"
      autocomplete="off"
      v-bind="$attrs"
      @input="$emit('update:modelValue', $event.target.value)"
      @focus="isFocused = true"
      @blur="isFocused = false"
      @keydown.enter="$emit('search', modelValue)"
    />

    <!-- Clear button -->
    <button
      v-if="modelValue && clearable"
      class="sb-search__clear"
      type="button"
      aria-label="Clear search"
      @mousedown.prevent="$emit('update:modelValue', ''); $emit('clear')"
    >
      <svg width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" stroke-linecap="round">
        <line x1="18" y1="6" x2="6" y2="18"/>
        <line x1="6" y1="6" x2="18" y2="18"/>
      </svg>
    </button>

    <!-- Optional inline search button -->
    <button
      v-if="showButton"
      class="sb-search__btn"
      type="button"
      @click="$emit('search', modelValue)"
    >
      Search
    </button>
  </div>
</template>

<script setup>
/**
 * SbSearchBar — Atom
 *
 * Sizes: sm | md | lg
 */
import { ref } from 'vue'

defineOptions({ name: 'SbSearchBar', inheritAttrs: false })

defineProps({
  modelValue:  { type: String, default: '' },
  placeholder: { type: String, default: 'Search for products…' },
  size:        { type: String, default: 'md', validator: v => ['sm','md','lg'].includes(v) },
  clearable:   { type: Boolean, default: true },
  showButton:  { type: Boolean, default: false },
  disabled:    { type: Boolean, default: false },
})

defineEmits(['update:modelValue', 'search', 'clear'])

const isFocused = ref(false)
const inputRef  = ref(null)

function focus() { inputRef.value?.focus() }
defineExpose({ focus })
</script>

<style scoped>
.sb-search {
  position: relative;
  display: flex;
  align-items: center;
  background-color: var(--color-white);
  border: 1.5px solid var(--color-border);
  border-radius: var(--radius-pill);
  transition:
    border-color var(--transition-fast),
    box-shadow var(--transition-fast);
  overflow: hidden;
}

/* Focus ring */
.sb-search--focused {
  border-color: var(--color-accent);
  box-shadow: 0 0 0 3px rgba(108, 99, 199, 0.15);
}

/* Sizes */
.sb-search--sm { min-height: 36px; }
.sb-search--md { min-height: 44px; }
.sb-search--lg { min-height: 54px; }

/* Icon */
.sb-search__icon {
  display: flex;
  align-items: center;
  padding-left: var(--space-4);
  color: var(--color-text-muted);
  flex-shrink: 0;
  pointer-events: none;
}
.sb-search--focused .sb-search__icon { color: var(--color-accent); }

/* Input */
.sb-search__input {
  flex: 1;
  border: none;
  background: transparent;
  outline: none;
  font-family: var(--font-body);
  font-size: var(--text-base);
  color: var(--color-text-primary);
  padding: var(--space-2) var(--space-3);
  min-width: 0;
}
.sb-search--lg .sb-search__input { font-size: var(--text-md); }
.sb-search--sm .sb-search__input { font-size: var(--text-sm); }

.sb-search__input::placeholder { color: var(--color-placeholder); }

/* remove native search clear */
.sb-search__input::-webkit-search-cancel-button { display: none; }

/* Clear button */
.sb-search__clear {
  display: flex;
  align-items: center;
  background: none;
  border: none;
  cursor: pointer;
  padding: var(--space-2);
  color: var(--color-text-muted);
  border-radius: 50%;
  transition: color var(--transition-fast), background-color var(--transition-fast);
  flex-shrink: 0;
}
.sb-search__clear:hover {
  color: var(--color-text-primary);
  background-color: var(--color-primary-light);
}

/* Inline search button */
.sb-search__btn {
  background-color: var(--color-primary);
  color: var(--color-white);
  border: none;
  cursor: pointer;
  font-family: var(--font-body);
  font-size: var(--text-sm);
  font-weight: var(--fw-semibold);
  padding: 0 var(--space-5);
  height: 100%;
  transition: background-color var(--transition-fast);
  white-space: nowrap;
  flex-shrink: 0;
}
.sb-search__btn:hover { background-color: var(--color-primary-hover); }
</style>
