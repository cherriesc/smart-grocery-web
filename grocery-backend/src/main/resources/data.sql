-- ===== STORES =====
INSERT INTO stores(name) VALUES
                             ('Tesco'),
                             ('Aldi'),
                             ('Lidl');

-- ===== PRODUCTS =====
INSERT INTO products(name, category) VALUES
                                         ('Milk', 'Dairy'),
                                         ('Bread', 'Bakery'),
                                         ('Eggs', 'Dairy'),
                                         ('Pasta', 'Grains'),
                                         ('Cheese', 'Dairy');

-- ===== PRICES =====
-- дата: 2026-02-11

-- Tesco
INSERT INTO prices(product_id, store_id, price, date) VALUES
                                                          (1, 1, 1.30, '2026-02-11'),
                                                          (2, 1, 1.10, '2026-02-11'),
                                                          (3, 1, 2.50, '2026-02-11'),
                                                          (4, 1, 0.95, '2026-02-11'),
                                                          (5, 1, 3.40, '2026-02-11');

-- Aldi
INSERT INTO prices(product_id, store_id, price, date) VALUES
                                                          (1, 2, 1.10, '2026-02-11'),
                                                          (2, 2, 0.90, '2026-02-11'),
                                                          (3, 2, 2.20, '2026-02-11'),
                                                          (4, 2, 0.85, '2026-02-11'),
                                                          (5, 2, 3.00, '2026-02-11');

-- Lidl
INSERT INTO prices(product_id, store_id, price, date) VALUES
                                                          (1, 3, 1.20, '2026-02-11'),
                                                          (2, 3, 1.00, '2026-02-11'),
                                                          (3, 3, 2.30, '2026-02-11'),
                                                          (4, 3, 0.80, '2026-02-11'),
                                                          (5, 3, 3.20, '2026-02-11');
