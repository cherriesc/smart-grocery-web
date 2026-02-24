<template>
  <div
    class="sb-input"
    :class="[
      `sb-input--${size}`,
      {
        'sb-input--focused': isFocused,
        'sb-input--error': error,
        'sb-input--disabled': disabled,
        'sb-input--has-prefix': $slots.prefix,
        'sb-input--has-suffix': $slots.suffix,
      }
    ]"
  >
    <!-- Label -->
    <label v-if="label" class="sb-input__label" :for="inputId">
      {{ label }}
      <span v-if="required" class="sb-input__required" aria-hidden="true">*</span>
    </label>

    <div class="sb-input__wrapper">
      <!-- Prefix slot (icon / text) -->
      <span v-if="$slots.prefix" class="sb-input__affix sb-input__affix--prefix">
        <slot name="prefix" />
      </span>

      <!-- The actual input -->
      <input
        :id="inputId"
        class="sb-input__field"
        :type="type"
        :value="modelValue"
        :placeholder="placeholder"
        :disabled="disabled"
        :required="required"
        :autocomplete="autocomplete"
        v-bind="$attrs"
        @input="$emit('update:modelValue', $event.target.value)"
        @focus="isFocused = true"
        @blur="isFocused = false"
      />

      <!-- Suffix slot (icon / unit) -->
      <span v-if="$slots.suffix" class="sb-input__affix sb-input__affix--suffix">
        <slot name="suffix" />
      </span>
    </div>

    <!-- Helper / Error text -->
    <p v-if="error" class="sb-input__message sb-input__message--error" role="alert">{{ error }}</p>
    <p v-else-if="hint" class="sb-input__message sb-input__message--hint">{{ hint }}</p>
  </div>
</template>

<script setup>
/**
 * SbInput — Atom
 *
 * A styled text input with label, helper/error text,
 * and prefix/suffix slot support.
 *
 * Sizes: sm | md | lg
 */
import { ref, computed } from 'vue'

defineOptions({ name: 'SbInput', inheritAttrs: false })

const props = defineProps({
  modelValue:   { type: [String, Number], default: '' },
  label:        { type: String, default: '' },
  placeholder:  { type: String, default: '' },
  type:         { type: String, default: 'text' },
  size:         { type: String, default: 'md', validator: v => ['sm','md','lg'].includes(v) },
  error:        { type: String, default: '' },
  hint:         { type: String, default: '' },
  required:     { type: Boolean, default: false },
  disabled:     { type: Boolean, default: false },
  autocomplete: { type: String, default: 'off' },
  id:           { type: String, default: null },
})

defineEmits(['update:modelValue'])

const isFocused = ref(false)
const inputId   = computed(() => props.id || `sb-input-${Math.random().toString(36).slice(2,7)}`)
</script>

<style scoped>
.sb-input { display: flex; flex-direction: column; gap: var(--space-1); }

/* ── Label ── */
.sb-input__label {
  font-family: var(--font-body);
  font-size: var(--text-sm);
  font-weight: var(--fw-medium);
  color: var(--color-text-primary);
}
.sb-input__required { color: var(--color-danger); margin-left: 2px; }

/* ── Wrapper ── */
.sb-input__wrapper {
  display: flex;
  align-items: center;
  background-color: var(--color-white);
  border: 1.5px solid var(--color-border);
  border-radius: var(--radius-md);
  transition:
    border-color var(--transition-fast),
    box-shadow var(--transition-fast);
  overflow: hidden;
}

.sb-input--focused .sb-input__wrapper {
  border-color: var(--color-accent);
  box-shadow: 0 0 0 3px rgba(108, 99, 199, 0.15);
}
.sb-input--error .sb-input__wrapper {
  border-color: var(--color-danger);
  box-shadow: 0 0 0 3px rgba(231, 76, 60, 0.12);
}
.sb-input--disabled .sb-input__wrapper {
  background-color: var(--color-primary-light);
  opacity: 0.6;
}

/* ── Sizes ── */
.sb-input--sm .sb-input__field { padding: var(--space-1) var(--space-3); font-size: var(--text-sm); min-height: 32px; }
.sb-input--md .sb-input__field { padding: var(--space-2) var(--space-4); font-size: var(--text-base); min-height: 42px; }
.sb-input--lg .sb-input__field { padding: var(--space-3) var(--space-5); font-size: var(--text-md); min-height: 52px; }

/* ── Field ── */
.sb-input__field {
  flex: 1;
  border: none;
  background: transparent;
  outline: none;
  font-family: var(--font-body);
  color: var(--color-text-primary);
  min-width: 0;
  width: 100%;
}
.sb-input__field::placeholder { color: var(--color-placeholder); }
.sb-input__field:disabled { cursor: not-allowed; }

/* ── Affixes ── */
.sb-input__affix {
  display: flex;
  align-items: center;
  color: var(--color-text-muted);
  font-size: var(--text-sm);
  flex-shrink: 0;
}
.sb-input__affix--prefix { padding-left: var(--space-3); }
.sb-input__affix--suffix { padding-right: var(--space-3); }

/* ── Messages ── */
.sb-input__message {
  font-size: var(--text-xs);
  line-height: var(--leading-snug);
  margin-top: 2px;
}
.sb-input__message--error { color: var(--color-danger); }
.sb-input__message--hint  { color: var(--color-text-muted); }
</style>
