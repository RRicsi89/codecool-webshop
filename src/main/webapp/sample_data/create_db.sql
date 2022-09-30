ALTER TABLE IF EXISTS ONLY public.categories DROP CONSTRAINT IF EXISTS fk_department_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.orders DROP CONSTRAINT IF EXISTS fk_user_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.orders DROP CONSTRAINT IF EXISTS fk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product_categories DROP CONSTRAINT IF EXISTS fk_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product_categories DROP CONSTRAINT IF EXISTS fk_product_category_id CASCADE;

DROP TABLE IF EXISTS public.users;
CREATE TABLE public.users (
    id SERIAL NOT NULL PRIMARY KEY,
    name TEXT,
    password TEXT
);

DROP TABLE IF EXISTS public.suppliers;
CREATE TABLE public.suppliers (
    id SERIAL NOT NULL PRIMARY KEY,
    name TEXT,
    description TEXT
);

DROP TABLE IF EXISTS public.products;
CREATE TABLE public.products (
    id SERIAL NOT NULL PRIMARY KEY,
    name TEXT,
    price DOUBLE PRECISION,
    currency TEXT,
    supplier_id INTEGER,
    description TEXT,
    image TEXT
);

DROP TABLE IF EXISTS public.product_categories;
CREATE TABLE public.product_categories (
    product_id INTEGER,
    category_id INTEGER
);

DROP TABLE IF EXISTS public.orders;
CREATE TABLE public.orders (
    id SERIAL NOT NULL PRIMARY KEY,
    user_id INTEGER,
    product_id INTEGER,
    quantity INTEGER,
    checkout_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS public.departments;
CREATE TABLE public.departments (
    id SERIAL NOT NULL PRIMARY KEY,
    name TEXT
);

DROP TABLE IF EXISTS public.categories;
CREATE TABLE public.categories (
    id SERIAL NOT NULL PRIMARY KEY,
    name TEXT,
    department_id INTEGER,
    description TEXT
);

ALTER TABLE ONLY categories
    ADD CONSTRAINT fk_department_id FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE CASCADE;
ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;
ALTER TABLE ONLY orders
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE;
ALTER TABLE ONLY products
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES suppliers(id) ON DELETE CASCADE;
ALTER TABLE ONLY product_categories
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE;
ALTER TABLE ONLY product_categories
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE;