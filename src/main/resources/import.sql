

INSERT INTO PHOTOS(photo_id, title) values(1,  '1a6c554b-5360-4eb1-9fc4-23a9472233f3_pikachu.png');

INSERT INTO CUSTOMERS (customer_id, create_at, customer_at, foto, name, photo_id) VALUES (1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, '1a6c554b-5360-4eb1-9fc4-23a9472233f3_pikachu.png', 'pika', 1);

INSERT INTO BILLS(bill_id, comments, create_at, description, customer_customer_id) VALUES (1, 'lorem',CURRENT_TIMESTAMP, 'lorem', 1 );

INSERT INTO PRODUCTS(product_id, create_at, name, offer_type, price) VALUES(1, CURRENT_TIMESTAMP, 'Hat', 'NA', 100);

INSERT INTO CARS(car_id, create_at, quantity, product_id, bill_id) VALUES(1, CURRENT_TIMESTAMP , 2, 1,1);
