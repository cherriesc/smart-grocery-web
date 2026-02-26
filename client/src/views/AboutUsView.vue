<template>
  <div class="page-content">
    <main class="container about-page">
      <RouterLink to="/" class="back-btn">Back</RouterLink>

      <section class="about-hero">
        <div class="about-hero__left">
          <p class="about-kicker">About Us</p>
          <h1 class="about-title">Helping You Save Up to 25% on Your Weekly Groceries</h1>

          <div class="about-pills">
            <p class="about-pill">Compare grocery prices across multiple stores</p>
            <p class="about-pill">Organise your basket where it&apos;s cheapest</p>
            <p class="about-pill">Discover alternative products at lower prices</p>
            <p class="about-pill">Make smarter shopping decisions in seconds.</p>
          </div>
        </div>

        <div class="about-hero__right">
          <div class="about-image-frame card">
            <img src="/about-upper-image.png" alt="Groceries and piggy bank representing savings" />

            <div class="about-image-actions" aria-hidden="true">
              <span class="image-action image-action--filled">+</span>
              <span class="image-action image-action--outline">
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <circle cx="18" cy="5" r="3" />
                  <circle cx="6" cy="12" r="3" />
                  <circle cx="18" cy="19" r="3" />
                  <line x1="8.59" y1="13.51" x2="15.42" y2="17.49" />
                  <line x1="15.41" y1="6.51" x2="8.59" y2="10.49" />
                </svg>
              </span>
            </div>
          </div>
        </div>
      </section>

      <section class="about-story">
        <div class="about-story__left">
          <div class="about-image-frame card">
            <img src="/about-lower-image.png" alt="Small student team collaborating together" />

            <div class="about-image-actions" aria-hidden="true">
              <span class="image-action image-action--filled">+</span>
              <span class="image-action image-action--outline">
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <circle cx="18" cy="5" r="3" />
                  <circle cx="6" cy="12" r="3" />
                  <circle cx="18" cy="19" r="3" />
                  <line x1="8.59" y1="13.51" x2="15.42" y2="17.49" />
                  <line x1="15.41" y1="6.51" x2="8.59" y2="10.49" />
                </svg>
              </span>
            </div>
          </div>
        </div>

        <div class="about-story__right">
          <article class="about-block">
            <h2 class="about-subtitle">Created by a small team of students</h2>
            <p>
              Smart Basket was created by a small team of students who were frustrated by rising grocery prices. We
              realised that most shoppers don&apos;t overpay intentionally, they simply lack visibility.
            </p>
            <p>
              Prices vary significantly between supermarkets, yet comparing them manually is time-consuming and
              inefficient. We decided to build a solution.
            </p>
          </article>

          <article class="about-block">
            <h2 class="about-subtitle">Our mission: Helping you save more.</h2>
            <p>
              Our mission is to make grocery shopping more affordable by providing clear, comparable pricing
              information across stores. No hidden complexity. Just simple, transparent tools that help you spend less,
              save more and shop smarter.
            </p>
          </article>

          <article class="about-block">
            <h3 class="about-why-title">Why Smart Basket?</h3>
            <ul class="about-list">
              <li>We focus on clarity</li>
              <li>We prioritise savings, not advertising pressure</li>
              <li>We aim to simplify</li>
            </ul>
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

.back-btn {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  min-width: 92px;
  padding: var(--space-2) var(--space-5);
  border-radius: var(--radius-full);
  background: var(--color-dark-soft);
  color: var(--color-white);
  font-size: var(--font-size-base);
  font-weight: var(--font-weight-semibold);
}

.about-hero {
  margin-top: var(--space-8);
  display: grid;
  grid-template-columns: minmax(0, 1.1fr) minmax(0, 0.9fr);
  gap: var(--space-8);
  align-items: start;
}

.about-kicker {
  text-transform: uppercase;
  letter-spacing: 2px;
  font-size: var(--font-size-md);
  font-weight: var(--font-weight-bold);
  color: var(--color-dark-soft);
  margin-bottom: var(--space-6);
}

.about-title {
  max-width: 640px;
  font-size: var(--font-size-3xl);
  line-height: var(--line-height-tight);
  margin-bottom: var(--space-8);
}

.about-pills {
  display: flex;
  flex-direction: column;
  gap: var(--space-4);
  max-width: 620px;
}

.about-pill {
  border-radius: var(--radius-full);
  background: var(--color-dark-soft);
  color: var(--color-white);
  font-size: var(--font-size-lg);
  font-weight: var(--font-weight-semibold);
  line-height: var(--line-height-snug);
  padding: var(--space-3) var(--space-5);
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

.about-image-actions {
  position: absolute;
  top: var(--space-4);
  right: var(--space-4);
  display: flex;
  flex-direction: column;
  gap: var(--space-2);
}

.image-action {
  width: 42px;
  height: 42px;
  border-radius: 50%;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  font-size: var(--font-size-xl);
  font-weight: var(--font-weight-semibold);
}

.image-action--filled {
  background: var(--color-dark-soft);
  color: var(--color-white);
}

.image-action--outline {
  border: 1.5px solid var(--color-dark-soft);
  background: rgba(255, 255, 255, 0.82);
  color: var(--color-dark-soft);
  font-size: var(--font-size-base);
}

.image-action--outline svg {
  width: 18px;
  height: 18px;
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

.about-block p {
  font-size: var(--font-size-sm);
  color: var(--color-grey-700);
}

.about-block p + p {
  margin-top: var(--space-3);
}

.about-subtitle {
  font-size: var(--font-size-3xl);
  margin-bottom: var(--space-4);
}

.about-why-title {
  font-size: var(--font-size-xl);
  margin-bottom: var(--space-3);
}

.about-list {
  list-style: disc;
  padding-left: 1.2rem;
}

.about-list li {
  color: var(--color-grey-700);
  font-size: var(--font-size-sm);
  margin-bottom: var(--space-2);
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

  .about-title {
    font-size: var(--font-size-2xl);
  }

  .about-pill {
    font-size: var(--font-size-base);
  }
}
</style>
