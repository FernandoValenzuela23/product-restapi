INSERT INTO categories(name) VALUES('Electronics')
INSERT INTO categories(name) VALUES('Fitness')
INSERT INTO categories(name) VALUES('Vehicles')

INSERT INTO products(category_id,name,description,status) VALUES(1,'Laptop','Cat', true)
INSERT INTO products(category_id,name,description,status) VALUES(1,'Oculus Quest 2','Cat', true)
INSERT INTO products(category_id,name,description,status) VALUES(2,'Lifting machine','Cat', true)
INSERT INTO products(category_id,name,description,status) VALUES(3,'Wheels','Cat', true)
INSERT INTO products(category_id,name,description,status) VALUES(2,'Adidas runner shoes MV3','Cat', true)

INSERT INTO prices(product_id,price,valid_from,valid_to) VALUES(1,2000.5,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
INSERT INTO prices(product_id,price,valid_from,valid_to) VALUES(1,400, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
INSERT INTO prices(product_id,price,valid_from,valid_to) VALUES(2,1200.50,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
INSERT INTO prices(product_id,price,valid_from,valid_to) VALUES(3,99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
INSERT INTO prices(product_id,price,valid_from,valid_to) VALUES(2,250.35, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)

INSERT INTO inventories(product_id,available_quantity,reserved_quantity) VALUES(1,10,0)
INSERT INTO inventories(product_id,available_quantity,reserved_quantity) VALUES(1,3,0)
INSERT INTO inventories(product_id,available_quantity,reserved_quantity) VALUES(2,50,12)
INSERT INTO inventories(product_id,available_quantity,reserved_quantity) VALUES(3,300,27)
INSERT INTO inventories(product_id,available_quantity,reserved_quantity) VALUES(2,20,0)

