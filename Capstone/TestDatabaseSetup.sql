DROP DATABASE IF EXISTS imagina_painting_test;

CREATE DATABASE imagina_painting_test;

USE imagina_painting_test;

CREATE TABLE state (
    state_id VARCHAR(2) PRIMARY KEY,
    `name` VARCHAR(256) NOT NULL
);

CREATE TABLE category (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(256) NOT NULL
);

CREATE TABLE style (
    style_id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(256) NOT NULL
);

CREATE TABLE `subject` (
    subject_id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(256) NOT NULL
);

CREATE TABLE `medium` (
    medium_id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(256) NOT NULL
);

CREATE TABLE material (
    material_id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(256) NOT NULL
);

CREATE TABLE buyer_cart (
	buyer_cart_id INT PRIMARY KEY AUTO_INCREMENT,
    total DECIMAL(12,2) NOT NULL
);

CREATE TABLE favorite_list (
	favorite_list_id INT PRIMARY KEY AUTO_INCREMENT
);

CREATE TABLE `user` (
	user_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(256) NOT NULL,
    last_name VARCHAR(256) NOT NULL,
    street_address VARCHAR(256) NULL,
    apt_unit VARCHAR(256) NULL,
    city VARCHAR(256) NULL,
    zip VARCHAR(5) NULL,
    username VARCHAR(256) NOT NULL UNIQUE,
    `password` VARCHAR(256) NOT NULL,
    acct_status INT NOT NULL,
    user_role VARCHAR(256) NOT NULL,
    state_id VARCHAR(2) NULL,
    CONSTRAINT fk_buyer_state 
		FOREIGN KEY (state_id)
		REFERENCES state (state_id)
);

CREATE TABLE buyer (
	buyer_id INT PRIMARY KEY AUTO_INCREMENT,
	user_id INT NOT NULL UNIQUE,
    CONSTRAINT fk_buyer_user 
		FOREIGN KEY (user_id)
		REFERENCES `user` (user_id),
	buyer_cart_id INT NOT NULL,
    CONSTRAINT fk_buyer_buyer_cart 
		FOREIGN KEY (buyer_cart_id)
		REFERENCES buyer_cart (buyer_cart_id),
	favorite_list_id INT NOT NULL,
    CONSTRAINT fk_buyer_favorite_list 
		FOREIGN KEY (favorite_list_id)
		REFERENCES favorite_list (favorite_list_id)
);

CREATE TABLE `order` (
	order_id INT PRIMARY KEY AUTO_INCREMENT,
    `date` DATE NOT NULL, 
    total DECIMAL(12,2) NOT NULL,
    first_name VARCHAR(256) NOT NULL,
    last_name VARCHAR(256) NOT NULL,
    street_address VARCHAR(256) NOT NULL,
    apt_unit VARCHAR(256) NOT NULL,
    city VARCHAR(256) NOT NULL,
    zip VARCHAR(5) NOT NULL,
    state_id VARCHAR(2) NOT NULL,
    CONSTRAINT fk_order_state 
		FOREIGN KEY (state_id)
		REFERENCES state (state_id),
	buyer_id INT NOT NULL,
    CONSTRAINT fk_order_buyer 
		FOREIGN KEY (buyer_id)
		REFERENCES buyer (buyer_id)
);

CREATE TABLE seller (
	seller_id INT PRIMARY KEY AUTO_INCREMENT,
	user_id INT NOT NULL UNIQUE,
    CONSTRAINT fk_seller_user 
		FOREIGN KEY (user_id)
		REFERENCES `user` (user_id),
    public_name VARCHAR(256) NULL,
    public_company VARCHAR(256) NULL,
    public_introduction VARCHAR(5000) NULL,
    photo_url VARCHAR(5000) NULL    
);

CREATE TABLE product (
	product_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(256) NOT NULL,
    `description` VARCHAR(5000) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL,
    photo_url VARCHAR(5000) NOT NULL,
    product_status INT NOT NULL,
    category_id INT,
    CONSTRAINT fk_product_category 
		FOREIGN KEY (category_id)
		REFERENCES category (category_id),
	style_id INT,
    CONSTRAINT fk_product_style 
		FOREIGN KEY (style_id)
		REFERENCES style (style_id),
	subject_id INT,
    CONSTRAINT fk_product_subject 
		FOREIGN KEY (subject_id)
		REFERENCES `subject` (subject_id),
	medium_id INT,
    CONSTRAINT fk_product_medium 
		FOREIGN KEY (medium_id)
		REFERENCES `medium` (medium_id),
	material_id INT,
    CONSTRAINT fk_product_material 
		FOREIGN KEY (material_id)
		REFERENCES material (material_id),
	seller_id INT,
    CONSTRAINT fk_product_seller 
		FOREIGN KEY (seller_id)
		REFERENCES seller (seller_id)
);

CREATE TABLE buyer_cart_product (
	buyer_cart_id INT NOT NULL,
    product_id INT NOT NULL,
    PRIMARY KEY pk_buyer_cart_product (buyer_cart_id, product_id),
    CONSTRAINT fk_buyer_cart_product_buyer_cart 
		FOREIGN KEY (buyer_cart_id)
		REFERENCES buyer_cart (buyer_cart_id),
	CONSTRAINT fk_buyer_cart_product_product 
		FOREIGN KEY (product_id)
		REFERENCES product (product_id)
);

CREATE TABLE order_product (
	order_id INT NOT NULL,
    product_id INT NOT NULL,
    PRIMARY KEY pk_order_product (order_id, product_id),
    CONSTRAINT fk_order_product_order 
		FOREIGN KEY (order_id)
		REFERENCES `order` (order_id),
	CONSTRAINT fk_order_product_product
		FOREIGN KEY (product_id)
		REFERENCES product (product_id)
);

CREATE TABLE favorite_list_product (
	favorite_list_id INT NOT NULL,
    product_id INT NOT NULL,
    PRIMARY KEY pk_favorite_list_product (favorite_list_id, product_id),
    CONSTRAINT fk_favorite_list_product_favorite_list
		FOREIGN KEY (favorite_list_id)
		REFERENCES favorite_list (favorite_list_id),
	CONSTRAINT fk_favorite_list_product_product
		FOREIGN KEY (product_id)
		REFERENCES product (product_id)
);

CREATE TABLE seller_order (
	seller_id INT NOT NULL,
    order_id INT NOT NULL,
    PRIMARY KEY pk_seller_order (seller_id, order_id),
    CONSTRAINT fk_seller_order_seller
		FOREIGN KEY (seller_id)
		REFERENCES seller (seller_id),
	CONSTRAINT fk_seller_order_order
		FOREIGN KEY (order_id)
		REFERENCES `order` (order_id)
);

INSERT INTO state(state_id, `name`) 
	VALUES
    ('AK', 'Alaska'),
	('AL', 'Alabama'),
	('AZ', 'Arizona'),
	('AR', 'Arkansas'),
	('CA', 'California'),
	('CO', 'Colorado'),
	('CT', 'Connecticut'),
	('DE', 'Delaware'),
	('DC', 'District of Columbia'),
	('FL', 'Florida'),
	('GA', 'Georgia'),
	('HI', 'Hawaii'),
	('ID', 'Idaho'),
	('IL', 'Illinois'),
	('IN', 'Indiana'),
	('IA', 'Iowa'),
	('KS', 'Kansas'),
	('KY', 'Kentucky'),
	('LA', 'Louisiana'),
	('ME', 'Maine'),
	('MD', 'Maryland'),
	('MA', 'Massachusetts'),
	('MI', 'Michigan'),
	('MN', 'Minnesota'),
	('MS', 'Mississippi'),
	('MO', 'Missouri'),
	('MT', 'Montana'),
	('NE', 'Nebraska'),
	('NV', 'Nevada'),
	('NH', 'New Hampshire'),
	('NJ', 'New Jersey'),
	('NM', 'New Mexico'),
	('NY', 'New York'),
	('NC', 'North Carolina'),
	('ND', 'North Dakota'),
	('OH', 'Ohio'),
	('OK', 'Oklahoma'),
	('OR', 'Oregon'),
	('PA', 'Pennsylvania'),
	('PR', 'Puerto Rico'),
	('RI', 'Rhode Island'),
	('SC', 'South Carolina'),
	('SD', 'South Dakota'),
	('TN', 'Tennessee'),
	('TX', 'Texas'),
	('UT', 'Utah'),
	('VT', 'Vermont'),
	('VA', 'Virginia'),
	('WA', 'Washington'),
	('WV', 'West Virginia'),
	('WI', 'Wisconsin'),
	('WY', 'Wyoming');
    
INSERT INTO category(`name`)
	VALUES
    ('Painting'),
    ('Sculpture'),
    ('Ceramic'),
    ('Printmaking'),
    ('Installation'),
    ('Photography');
    
INSERT INTO style(`name`)
	VALUES
    ('Photorealism'),
    ('Realism'),
    ('Fine Art'),
    ('Figurative'),
    ('Modern'),
    ('Impressionism'),
    ('Surrealism'),
    ('Abstract'),
    ('Futurism'),
    ('Illustration');
    
INSERT INTO `subject`(`name`)
	VALUES
    ('Landscape'),
    ('Still Life'),
    ('Portrait'),
    ('Floral'),
    ('Architecture'),
    ('Outer Space'),
    ('Light'),
    ('Seascape'),
    ('Cities'),
    ('Religious');
    
INSERT INTO `medium`(`name`)
	VALUES
    ('Oil'),
    ('Acrylic'),
    ('Watercolor'),
    ('Gesso'),
    ('Pencil'),
    ('Digital'),
    ('Resin'),
    ('Ink'),
    ('Wax'),
    ('Fabric');
    
INSERT INTO material(`name`)
	VALUES
    ('Aluminum'),
    ('Bronze'),
    ('Canvas'),
    ('Carbon Fiber'),
    ('Cardboard'),
    ('Glass'),
    ('Paper'),
    ('Plastic'),
    ('Steel'),
    ('Wood');
    
INSERT INTO `user`(
	user_id,
    first_name,
    last_name,
    street_address,
    apt_unit,
    city,
    zip,
    username,
    `password`,
    acct_status,
    user_role,
    state_id
	) VALUES (
    1,
    "Daniel",
    "Bart",
    "123 Fake Lane Drive West",
    "Apt 100",
    "Charlotte",
    "28500",
    "danielvbart@yahoo.com",
    "$2a$10$3y9LDwEi2F8/OC6FqQRjquD2uRH.HHIxa4VdOxgu0XyDymdMSt15q",
    1,
    "ROLE_ADMIN",
    "NC"
    );
    
INSERT INTO `user`(
	user_id,
    first_name,
    last_name,
    street_address,
    apt_unit,
    city,
    zip,
    username,
    `password`,
    acct_status,
    user_role,
    state_id
	) VALUES (
    2,
    "Burt",
	"Reynolds",
	"5000 Awesome Lane",
	"Condo 5000",
	"Hollywood",
	"90210",
	"breynolds@yahoo.com",
    "$2a$10$3y9LDwEi2F8/OC6FqQRjquD2uRH.HHIxa4VdOxgu0XyDymdMSt15q",
    1,
    "ROLE_BUYER",
    "CA"
    );
    
INSERT INTO `user`(
	user_id,
    first_name,
    last_name,
    street_address,
    apt_unit,
    city,
    zip,
    username,
    `password`,
    acct_status,
    user_role,
    state_id
	) VALUES (
    3,
    "Anthony",
    "Fisher",
    "123 Artist Drive",
    "Condo 0100",
    "Seattle",
    "68686",
    "afisher@yahoo.com",
    "$2a$10$3y9LDwEi2F8/OC6FqQRjquD2uRH.HHIxa4VdOxgu0XyDymdMSt15q",
    1,
    "ROLE_SELLER",
    "WA"
    );
    
INSERT INTO `user`(
	user_id,
    first_name,
    last_name,
    street_address,
    apt_unit,
    city,
    zip,
    username,
    `password`,
    acct_status,
    user_role,
    state_id
	) VALUES (
    4,
    "Pending",
    "Approval",
    "123 Artist Drive",
    "Condo 0100",
    "Seattle",
    "68686",
    "bfisher@yahoo.com",
    "$2a$10$3y9LDwEi2F8/OC6FqQRjquD2uRH.HHIxa4VdOxgu0XyDymdMSt15q",
    4,
    "ROLE_SELLER",
    "WA"
    );
    
INSERT INTO buyer_cart(buyer_cart_id, total)
	VALUES
    (1, 5000.00);
    
INSERT INTO favorite_list(favorite_list_id)
	VALUES
    (1);
    
INSERT INTO buyer (
	buyer_id,
    user_id,
    buyer_cart_id,
    favorite_list_id
	) VALUES (
	1,
    2,
    1,
    1
    );
    
INSERT INTO `order` (
	order_id,
	`date`,
    total, 
    first_name,
    last_name,
    street_address,
    apt_unit,
    city,
    zip,
    state_id,
    buyer_id
	) VALUES (
	1001,
    "2020-02-07",
    5000.00,
    "John",
    "Doe",
    "5678 Faker Lane",
    "Apt 001",
    "Boston",
    "10101",
    "MA",
    1
    );
    
INSERT INTO seller (
	seller_id,
    user_id,
    public_name,
    public_company,
    public_introduction,
    photo_url
	) VALUES (
    1,
    3,
    "AntTheArtMan",
    "Fisher Artworks",
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    "/uploaded-photos/seller-example-photo.jpg"
    );
    
INSERT INTO seller (
	seller_id,
    user_id,
    public_name,
    public_company,
    public_introduction,
    photo_url
	) VALUES (
    2,
    4,
    "PendingApproval",
    "PendingApprovalCompany",
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    "/uploaded-photos/seller-example-photo.jpg"
    );
    
INSERT INTO product (
	product_id,
    title,
    `description`,
    price,
    quantity,
    photo_url,
    product_status,
    category_id,
    style_id,
    subject_id,
    medium_id,
    material_id,
    seller_id
    ) VALUES (
    1,
    "Fragments of Imagination",
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    2500.00,
    1,
    "/uploaded-photos/buyer-registration-form-background.jpg",
    1,
    1,
    1,
    1,
    1,
    1,
    1
    );
    
INSERT INTO buyer_cart_product (buyer_cart_id, product_id) 
	VALUES 
    (1, 1);
    
INSERT INTO order_product (order_id, product_id)
	VALUES
    (1001, 1);
    
INSERT INTO favorite_list_product (favorite_list_id, product_id)
	VALUES
	(1, 1);
    
INSERT INTO seller_order (seller_id, order_id)
	VALUES
    (1, 1001);