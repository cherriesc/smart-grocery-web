<template>
  <div class="page-content">
    <main class="container about-page">
      <header class="about-header">
        <h1 class="about-kicker">About Us</h1>
      </header>

      <section class="about-hero">
        <div class="about-hero__left">
          <h2 class="about-title">Stop overpaying for groceries</h2>

          <p class="about-subtext">
            See where your basket is cheapest - in seconds.
          </p>


          <div class="about-pills">
          <div class="about-pill">
            <span class="about-check">✓</span>
            Compare prices across multiple stores
          </div>
          <div class="about-pill">
            <span class="about-check">✓</span>
            Find the cheapest basket instantly
          </div>
          <div class="about-pill">
            <span class="about-check">✓</span>
            Discover better alternatives
          </div>
          <div class="about-pill">
            <span class="about-check">✓</span>
            Make smarter shopping decisions
          </div>
        </div>

        </div>

        <div class="about-hero__right">
          <div class="about-image-frame card">
            <img src="/about-upper-image.png" alt="Groceries and piggy bank representing savings" />
          </div>
        </div>
      </section>

      <section class="about-story">
        <div class="about-story__left">
          <div class="about-image-frame card">
            <img src="/about-lower-image.png" alt="Small student team collaborating together" />
          </div>
        </div>

        <div class="about-story__right">
          <article class="about-block">
            <h3 class="about-subtitle">Built to solve a real problem</h3>
            <p>
              We’re a small team of students who were frustrated by rising grocery prices and the lack of transparency between stores.
            </p>
            <p>
              So we built Smart Basket - a simple tool that helps you find the best deals instantly.
            </p>
            <p>
              This matters because the same basket can cost up to 20-30% more depending on where you shop.
            </p>
          </article>

          <article class="about-block">
            <h3 class="about-subtitle">Our mission</h3>
            <p>
              Our mission is simple: help you spend less on groceries without extra effort.
            </p>
            <p>
              No hidden tricks. No complex tools. Just clear, transparent price comparisons.
            </p>
            <p>
              So you always know where you’re getting the best value.
            </p>
          </article>
        </div>
      </section>
    </main>

    <AppFooter />
  </div>
</template>

<script setup>
import AppFooter from '@/components/AppFooter.vue'
</script>

<style scoped>
.about-page {
  padding-top: var(--space-8);
  padding-bottom: var(--space-12);
}

.about-header {
  text-align: center;
  margin-bottom: var(--space-8);
}

/* Page title — largest, centred, normal casing, moderate letter-spacing */
.about-kicker {
  font-size: var(--font-size-4xl);
  font-family: var(--font-heading);
  font-weight: var(--font-weight-bold);
  letter-spacing: 0.02em;
  color: var(--color-dark);
}

.about-hero {
  display: grid;
  grid-template-columns: minmax(0, 1.1fr) minmax(0, 0.9fr);
  gap: var(--space-8);
  align-items: start;
}

/* Section heading — clearly smaller than page title */
.about-title {
  max-width: 640px;
  font-size: var(--font-size-2xl);
  font-family: var(--font-heading);
  line-height: var(--line-height-tight);
  margin-bottom: var(--space-8);
}

.about-pills {
  display: flex;
  flex-direction: column;
  gap: var(--space-4);
  max-width: 520px;
}

.about-pill {
  display: flex;
  align-items: center;
  gap: 10px;

  background: var(--color-primary-subtle);
  color: var(--color-dark);

  border-radius: var(--radius-md);
  padding: var(--space-3) var(--space-4);

  font-size: var(--font-size-sm);
  font-weight: var(--font-weight-medium);
}


.about-image-frame {
  position: relative;
  overflow: hidden;
  border-radius: var(--radius-lg);
  min-height: 320px;
}

.about-image-frame img {
  width: 100%;
  height: 100%;
  min-height: 320px;
  object-fit: cover;
}

.about-subtext {
  max-width: 520px;
  font-size: var(--font-size-base);
  color: var(--color-grey-600);
  margin-bottom: var(--space-6);
}

.about-check {
  color: var(--color-primary);
  font-weight: bold;
  font-size: 14px;
}


.about-story {
  margin-top: var(--space-12);
  display: grid;
  grid-template-columns: minmax(0, 0.9fr) minmax(0, 1.1fr);
  gap: var(--space-8);
  align-items: start;
}

.about-story__left .about-image-frame {
  min-height: 360px;
}

.about-story__left .about-image-frame img {
  min-height: 360px;
}

.about-story__right {
  display: flex;
  flex-direction: column;
  gap: var(--space-8);
}

/* Sub-heading — one step below section heading */
.about-subtitle {
  font-size: var(--font-size-xl);
  font-family: var(--font-heading);
  font-weight: var(--font-weight-semibold);
  margin-bottom: var(--space-4);
  color: var(--color-dark);
}

/* Body text — readable, not tiny */
.about-block p {
  font-size: var(--font-size-base);
  line-height: var(--line-height-base);
  color: var(--color-grey-700);
}

.about-block p + p {
  margin-top: var(--space-3);
}

@media (max-width: 1024px) {
  .about-hero,
  .about-story {
    grid-template-columns: 1fr;
  }

  .about-story {
    margin-top: var(--space-10);
  }
}

@media (max-width: 768px) {
  .about-page {
    padding-top: var(--space-6);
  }

  .about-kicker {
    font-size: var(--font-size-3xl);
  }

  .about-title {
    font-size: var(--font-size-xl);
  }

  .about-pill {
    font-size: var(--font-size-sm);
  }
}
</style>