<template>
  <div class="preview">
    <header class="preview__header">
      <SbLogo size="lg" />
      <p class="preview__subtitle">Atom Component Library — Design System Preview</p>
    </header>

    <main class="preview__body">

      <!-- ── LOGO ── -->
      <section class="preview__section">
        <h2 class="preview__section-title">Logo</h2>
        <div class="preview__row">
          <SbLogo size="sm" />
          <SbLogo size="md" />
          <SbLogo size="lg" />
        </div>
        <div class="preview__row preview__row--dark">
          <SbLogo size="md" :light="true" />
        </div>
      </section>

      <!-- ── BUTTONS ── -->
      <section class="preview__section">
        <h2 class="preview__section-title">Buttons — Variants</h2>
        <div class="preview__row">
          <SbButton variant="primary">Primary</SbButton>
          <SbButton variant="secondary">Secondary</SbButton>
          <SbButton variant="outline">Outline</SbButton>
          <SbButton variant="ghost">Ghost</SbButton>
          <SbButton variant="danger">Danger</SbButton>
          <SbButton variant="link">Link</SbButton>
        </div>

        <h2 class="preview__section-title" style="margin-top:24px">Buttons — Sizes</h2>
        <div class="preview__row" style="align-items:center">
          <SbButton size="xs">Extra Small</SbButton>
          <SbButton size="sm">Small</SbButton>
          <SbButton size="md">Medium</SbButton>
          <SbButton size="lg">Large</SbButton>
        </div>

        <h2 class="preview__section-title" style="margin-top:24px">Buttons — States</h2>
        <div class="preview__row">
          <SbButton :loading="true">Loading</SbButton>
          <SbButton :disabled="true">Disabled</SbButton>
          <SbButton variant="outline" :icon-only="true">
            <template #icon-left><SbIcon name="plus" size="sm" /></template>
          </SbButton>
        </div>

        <h2 class="preview__section-title" style="margin-top:24px">Buttons — With Icons</h2>
        <div class="preview__row">
          <SbButton variant="primary">
            <template #icon-left><SbIcon name="cart" size="sm" /></template>
            Add to Basket
          </SbButton>
          <SbButton variant="outline">
            <template #icon-right><SbIcon name="chevron-right" size="sm" /></template>
            See More
          </SbButton>
        </div>
      </section>

      <!-- ── TAGS ── -->
      <section class="preview__section">
        <h2 class="preview__section-title">Tags — Variants</h2>
        <div class="preview__row">
          <SbTag variant="default">Default</SbTag>
          <SbTag variant="primary">Primary</SbTag>
          <SbTag variant="accent">Accent</SbTag>
          <SbTag variant="success">In Stock</SbTag>
          <SbTag variant="warning">Low Stock</SbTag>
          <SbTag variant="danger">Out of Stock</SbTag>
          <SbTag variant="muted">Muted</SbTag>
        </div>

        <h2 class="preview__section-title" style="margin-top:24px">Tags — Interactive & Dismissible</h2>
        <div class="preview__row">
          <SbTag :clickable="true" :active="activeTag === 'a'" @click="activeTag='a'">Breakfast</SbTag>
          <SbTag :clickable="true" :active="activeTag === 'b'" @click="activeTag='b'">Lunch</SbTag>
          <SbTag :clickable="true" :active="activeTag === 'c'" @click="activeTag='c'">Dinner</SbTag>
          <SbTag variant="accent" :dot="true" :dismissible="true" @dismiss="() => {}">Gluten Free</SbTag>
        </div>
      </section>

      <!-- ── BADGES ── -->
      <section class="preview__section">
        <h2 class="preview__section-title">Badges</h2>
        <div class="preview__row" style="align-items:center">
          <SbBadge variant="primary" size="xs">3</SbBadge>
          <SbBadge variant="primary" size="sm">12</SbBadge>
          <SbBadge variant="primary" size="md">99+</SbBadge>
          <SbBadge variant="success">In Stock</SbBadge>
          <SbBadge variant="warning">Low</SbBadge>
          <SbBadge variant="danger">Out</SbBadge>
          <SbBadge variant="accent">New</SbBadge>
        </div>
      </section>

      <!-- ── SEARCH BAR ── -->
      <section class="preview__section">
        <h2 class="preview__section-title">Search Bar</h2>
        <div class="preview__col">
          <SbSearchBar v-model="search1" size="sm" placeholder="Small search…" />
          <SbSearchBar v-model="search2" size="md" placeholder="Search for products, brands…" />
          <SbSearchBar v-model="search3" size="lg" placeholder="What are you looking for?" :show-button="true" />
        </div>
      </section>

      <!-- ── INPUT ── -->
      <section class="preview__section">
        <h2 class="preview__section-title">Input</h2>
        <div class="preview__col" style="max-width:480px">
          <SbInputField v-model="inputVal" label="Product name" placeholder="e.g. Heinz Baked Beans" hint="Enter the full product name" />
          <SbInputField v-model="inputVal2" label="Quantity" type="number" placeholder="1">
            <template #suffix>units</template>
          </SbInputField>
          <SbInputField v-model="inputVal3" label="Search" error="This field is required" />
          <SbInputField v-model="inputVal4" label="Disabled" :disabled="true" placeholder="Cannot edit" />
        </div>
      </section>

      <!-- ── TABS ── -->
      <section class="preview__section">
        <h2 class="preview__section-title">Tabs — Underline</h2>
        <SbTabs v-model="tab1" :tabs="tabs1" variant="underline" />

        <h2 class="preview__section-title" style="margin-top:24px">Tabs — Pill</h2>
        <SbTabs v-model="tab2" :tabs="tabs2" variant="pill" />

        <h2 class="preview__section-title" style="margin-top:24px">Tabs — Boxed</h2>
        <SbTabs v-model="tab3" :tabs="tabs3" variant="boxed" />
      </section>

      <!-- ── TEXT ── -->
      <section class="preview__section">
        <h2 class="preview__section-title">Typography Scale</h2>
        <div class="preview__col" style="gap:12px">
          <SbText variant="display">Display — Sora Bold 48px</SbText>
          <SbText variant="h1">Heading 1 — Sora Bold 38px</SbText>
          <SbText variant="h2">Heading 2 — Sora SemiBold 30px</SbText>
          <SbText variant="h3">Heading 3 — Sora SemiBold 24px</SbText>
          <SbText variant="h4">Heading 4 — Sora Medium 20px</SbText>
          <SbDivider />
          <SbText variant="body">Body — DM Sans Regular 15px. The quick brown fox jumps over the lazy dog.</SbText>
          <SbText variant="body-sm" color="secondary">Body Small — DM Sans Regular 13px. Supporting text and descriptions.</SbText>
          <SbText variant="caption">Caption — 11px muted text for metadata</SbText>
          <SbText variant="overline">Overline Label</SbText>
        </div>

        <h2 class="preview__section-title" style="margin-top:24px">Text — Colors</h2>
        <div class="preview__row">
          <SbText color="primary">Primary</SbText>
          <SbText color="secondary">Secondary</SbText>
          <SbText color="muted">Muted</SbText>
          <SbText color="accent">Accent</SbText>
          <SbText color="success">Success</SbText>
          <SbText color="danger">Danger</SbText>
          <SbText color="warning">Warning</SbText>
        </div>
      </section>

      <!-- ── ICONS ── -->
      <section class="preview__section">
        <h2 class="preview__section-title">Icons</h2>
        <div class="preview__row" style="flex-wrap:wrap; gap:16px">
          <div v-for="name in iconNames" :key="name" class="preview__icon-item">
            <SbIcon :name="name" size="lg" />
            <span class="preview__icon-label">{{ name }}</span>
          </div>
        </div>

        <h2 class="preview__section-title" style="margin-top:24px">Icon Sizes</h2>
        <div class="preview__row" style="align-items:center">
          <SbIcon name="cart" size="xs" />
          <SbIcon name="cart" size="sm" />
          <SbIcon name="cart" size="md" />
          <SbIcon name="cart" size="lg" />
          <SbIcon name="cart" size="xl" />
        </div>
      </section>

      <!-- ── AVATAR ── -->
      <section class="preview__section">
        <h2 class="preview__section-title">Avatar</h2>
        <div class="preview__row" style="align-items:center">
          <SbAvatar name="Jane Doe" size="xs" />
          <SbAvatar name="Jane Doe" size="sm" />
          <SbAvatar name="Jane Doe" size="md" />
          <SbAvatar name="Jane Doe" size="lg" />
          <SbAvatar name="Jane Doe" size="xl" />
          <SbAvatar name="AB" size="md" variant="square" />
        </div>
      </section>

      <!-- ── IMAGE PLACEHOLDER ── -->
      <section class="preview__section">
        <h2 class="preview__section-title">Image Placeholder</h2>
        <div class="preview__row" style="align-items:flex-start">
          <SbImagePlaceholder width="140" height="140" variant="default" />
          <SbImagePlaceholder width="140" height="140" variant="subtle" />
          <SbImagePlaceholder width="140" height="140" variant="dark" label="No image" />
        </div>
      </section>

      <!-- ── DIVIDER ── -->
      <section class="preview__section">
        <h2 class="preview__section-title">Divider</h2>
        <SbDivider />
        <SbDivider><span style="font-size:12px;color:var(--color-text-muted)">OR</span></SbDivider>
      </section>

    </main>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import {
  SbButton, SbTag, SbSearchBar, SbTabs,
  SbText, SbBadge, SbInputField, SbIcon,
  SbAvatar, SbDivider, SbImagePlaceholder, SbLogo
} from '.'

const activeTag = ref(null)
const search1 = ref('')
const search2 = ref('')
const search3 = ref('')
const inputVal  = ref('')
const inputVal2 = ref('')
const inputVal3 = ref('')
const inputVal4 = ref('')

const tabs1 = [
  { label: 'Ingredients & Nutrition', value: 'ingredients' },
  { label: 'Instructions', value: 'instructions' },
]
const tabs2 = [
  { label: 'Breakfast', value: 'breakfast' },
  { label: 'Lunch', value: 'lunch' },
  { label: 'Dinner', value: 'dinner' },
  { label: 'Snacks', value: 'snacks' },
]
const tabs3 = [
  { label: 'ASDA', value: 'asda', count: 12 },
  { label: 'ALDI', value: 'aldi', count: 8 },
  { label: 'Tesco', value: 'tesco', count: 15 },
  { label: 'ASDA', value: 'asda2' },
]

const tab1 = ref('ingredients')
const tab2 = ref('breakfast')
const tab3 = ref('asda')

const iconNames = [
  'search','cart','user','menu','close',
  'plus','minus','trash','heart','chevron-right',
  'chevron-left','chevron-down','star','arrow-left',
  'info','check','filter','tag','clock','users'
]
</script>

<style scoped>
.preview {
  min-height: 100vh;
  background-color: var(--color-bg);
  font-family: var(--font-body);
}

.preview__header {
  background-color: var(--color-white);
  border-bottom: 1.5px solid var(--color-border);
  padding: var(--space-6) var(--space-8);
  display: flex;
  flex-direction: column;
  gap: var(--space-2);
  position: sticky;
  top: 0;
  z-index: 10;
}

.preview__subtitle {
  font-size: var(--text-sm);
  color: var(--color-text-muted);
}

.preview__body {
  max-width: 1100px;
  margin: 0 auto;
  padding: var(--space-8);
  display: flex;
  flex-direction: column;
  gap: var(--space-10);
}

.preview__section {
  background-color: var(--color-white);
  border: 1.5px solid var(--color-border);
  border-radius: var(--radius-lg);
  padding: var(--space-6);
}

.preview__section-title {
  font-family: var(--font-heading);
  font-size: var(--text-sm);
  font-weight: var(--fw-semibold);
  color: var(--color-text-secondary);
  text-transform: uppercase;
  letter-spacing: var(--tracking-wider);
  margin-bottom: var(--space-4);
}

.preview__row {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  gap: var(--space-3);
}
.preview__row--dark {
  background-color: var(--color-primary);
  padding: var(--space-4);
  border-radius: var(--radius-md);
  margin-top: var(--space-3);
}

.preview__col {
  display: flex;
  flex-direction: column;
  gap: var(--space-4);
}

.preview__icon-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 6px;
}
.preview__icon-label {
  font-size: 10px;
  color: var(--color-text-muted);
  white-space: nowrap;
}
</style>