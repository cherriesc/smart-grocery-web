-- ===== STORES =====
INSERT INTO stores(name) VALUES
                             ('Tesco'),
                             ('Aldi'),
                             ('Lidl');

-- ===== PRODUCTS =====
-- unit_unit: G | ML | PCS
INSERT INTO products(name, category, unit_size, unit_unit) VALUES
                                                               ('Milk',               'Dairy',               1000, 'ML'),
                                                               ('Bread',              'Bakery',               500,  'G'),
                                                               ('Chicken Breast',     'Meat & Fish',          500,  'G'),
                                                               ('Salmon Fillet',      'Meat & Fish',          300,  'G'),
                                                               ('Apples',             'Fruits & Vegetables', 1000,  'G'),
                                                               ('Tomatoes',           'Fruits & Vegetables',  500,  'G'),
                                                               ('Pasta',              'Grains & Pasta',       500,  'G'),
                                                               ('Rice',               'Grains & Pasta',      1000,  'G'),
                                                               ('Cola',               'Drinks',              1500, 'ML'),
                                                               ('Detergent',          'Household',           1000, 'ML');

-- ===== PRICES =====
-- дата одна, "последняя"
-- product_id идут по порядку вставки продуктов (1..10)
-- store_id: Tesco=1, Aldi=2, Lidl=3
-- date: 2026-02-11

-- Tesco
INSERT INTO prices(product_id, store_id, price, date) VALUES
                                                          (1,  1, 1.30, '2026-02-11'),
                                                          (2,  1, 1.10, '2026-02-11'),
                                                          (3,  1, 3.90, '2026-02-11'),
                                                          (4,  1, 4.50, '2026-02-11'),
                                                          (5,  1, 2.20, '2026-02-11'),
                                                          (6,  1, 1.60, '2026-02-11'),
                                                          (7,  1, 1.25, '2026-02-11'),
                                                          (8,  1, 2.10, '2026-02-11'),
                                                          (9,  1, 1.80, '2026-02-11'),
                                                          (10, 1, 5.40, '2026-02-11');

-- Aldi
INSERT INTO prices(product_id, store_id, price, date) VALUES
                                                          (1,  2, 1.10, '2026-02-11'),
                                                          (2,  2, 0.90, '2026-02-11'),
                                                          (3,  2, 3.60, '2026-02-11'),
                                                          (4,  2, 4.20, '2026-02-11'),
                                                          (5,  2, 2.00, '2026-02-11'),
                                                          (6,  2, 1.40, '2026-02-11'),
                                                          (7,  2, 1.10, '2026-02-11'),
                                                          (8,  2, 1.95, '2026-02-11'),
                                                          (9,  2, 1.55, '2026-02-11'),
                                                          (10, 2, 4.90, '2026-02-11');

-- Lidl
INSERT INTO prices(product_id, store_id, price, date) VALUES
                                                          (1,  3, 1.20, '2026-02-11'),
                                                          (2,  3, 1.00, '2026-02-11'),
                                                          (3,  3, 3.70, '2026-02-11'),
                                                          (4,  3, 4.10, '2026-02-11'),
                                                          (5,  3, 2.10, '2026-02-11'),
                                                          (6,  3, 1.35, '2026-02-11'),
                                                          (7,  3, 1.15, '2026-02-11'),
                                                          (8,  3, 2.05, '2026-02-11'),
                                                          (9,  3, 1.60, '2026-02-11'),
                                                          (10, 3, 5.10, '2026-02-11');
