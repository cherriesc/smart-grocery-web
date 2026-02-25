/**
 * api.js — Central API service
 *
 * How to connect to your Spring Boot backend:
 *   1. Set VITE_API_BASE_URL in your .env file:
 *        VITE_API_BASE_URL=http://localhost:8080/api
 *   2. All requests below go through this base URL.
 *   3. If you add auth (JWT), set the token in the Authorization header here.
 */

const BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api'

async function request(path, options = {}) {
  const url = `${BASE_URL}${path}`
  const headers = {
    'Content-Type': 'application/json',
    // Uncomment when JWT is implemented:
    // 'Authorization': `Bearer ${localStorage.getItem('token')}`
    ...options.headers
  }

  const response = await fetch(url, { ...options, headers })

  if (!response.ok) {
    const error = await response.text()
    throw new Error(error || `HTTP error ${response.status}`)
  }

  return response.json()
}

// --- Product endpoints ---
export const productService = {
  /** GET /products/top-deals */
  getTopDeals() {
    return request('/products/top-deals')
  },

  /** GET /products/:id */
  getById(id) {
    return request(`/products/${id}`)
  },

  /**
   * GET /products?category=&page=&size=&sort=&search=
   * Returns Spring Page object: { content: [], totalPages, totalElements }
   */
  getByCategory(categorySlug, { page = 1, size = 12, sort = 'price_asc', search = '' } = {}) {
    const params = new URLSearchParams({
      category: categorySlug,
      page: page - 1,   // Spring uses 0-indexed pages
      size,
      sort,
      search
    })
    return request(`/products?${params}`)
  },

  /** GET /products/search?q= */
  search(query) {
    return request(`/products/search?q=${encodeURIComponent(query)}`)
  }
}

// --- Basket endpoints (optional backend persistence) ---
export const basketService = {
  /** GET /basket */
  getBasket() {
    return request('/basket')
  },

  /** POST /basket/items  body: { productId, quantity } */
  addItem(productId, quantity = 1) {
    return request('/basket/items', {
      method: 'POST',
      body: JSON.stringify({ productId, quantity })
    })
  },

  /** DELETE /basket/items/:productId */
  removeItem(productId) {
    return request(`/basket/items/${productId}`, { method: 'DELETE' })
  },

  /** PATCH /basket/items/:productId  body: { quantity } */
  updateQuantity(productId, quantity) {
    return request(`/basket/items/${productId}`, {
      method: 'PATCH',
      body: JSON.stringify({ quantity })
    })
  }
}