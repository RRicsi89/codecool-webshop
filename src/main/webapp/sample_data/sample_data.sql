INSERT INTO public.suppliers (name, description)
VALUES ('RicsiShop', 'Life essentials'),
       ('DominikShop', 'Barely used bodyparts'),
       ('GaborShop', 'Junk');

INSERT INTO public.departments (name)
VALUES ('Common'), ('Rare'), ('Epic'), ('Legendary');

INSERT INTO public.categories (name, department_id, description)
VALUES ('Big', 2, 'Something big'),
       ('Small', 1, 'Something small'),
       ('Expensive', 3, 'Something expensive'),
       ('Cheap', 1, 'Something cheap'),
       ('Useless', 4, 'Something useless'),
       ('Useful', 1, 'Something useful'),
       ('Ugly', 1, 'Something ugly'),
       ('Beautiful', 4, 'Something beautiful'),
       ('Booster', 3, 'Epic boost'),
       ('Tiny', 1, 'Something tiny'),
       ('Colorful', 3, 'Something colorful');

INSERT INTO public.products (name, price, currency, supplier_id, description, image)
VALUES ('Something Stupid', 0.99, 'USD', 3, 'Stupid', '/static/img/product_1.jpg'),
       ('Something Ordinary', 49.99, 'USD', 3, 'Ordinary', '/static/img/product_2.jpg'),
       ('Something Extraordinary', 499.99, 'USD', 3, 'Extra-Ordinary', '/static/img/product_3.jpg'),
       ('Something Big', 449.99, 'USD', 2, 'Big', '/static/img/product_4.jpg'),
       ('Something Funny', 1199.99, 'USD', 2, 'Funny', '/static/img/product_5.jpg'),
       ('Something Delightful', 48.99, 'USD', 2, 'Delightful', '/static/img/product_6.jpg'),
       ('Something Lifesaving', 99.99, 'USD', 1, 'Saves life', '/static/img/product_7.jpg'),
       ('Something Sickening', 199.99, 'USD', 1, 'Sickens life', '/static/img/product_8.jpg'),
       ('Something Healthy', 999.99, 'USD', 1, 'Prolongs life', '/static/img/product_9.jpg');

INSERT INTO public.product_categories
VALUES (1, 1), (1, 5), (1, 4),
        (2, 6), (2, 4), (2, 2),
        (3, 6), (3, 3), (3, 1),
        (4, 10), (4, 3), (4, 8),
        (5, 3), (5, 7), (5, 5),
        (6, 8), (6, 9), (6, 6), (6, 4),
        (7, 6), (7, 4), (7, 9), (7, 2),
        (8, 6), (8, 3), (8, 11), (8, 2),
        (9, 6), (9, 3), (9, 8), (9, 2);