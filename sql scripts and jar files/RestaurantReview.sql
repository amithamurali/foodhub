 CREATE SCHEMA restaurant_review;
 
 CREATE TABLE IF NOT EXISTS user (
		user_id int not null AUTO_INCREMENT,
        USER_NAME varchar(50) NOT NULL,
        PASSWORD  varchar(50) DEFAULT NULL,
		AGE     datetime DEFAULT NULL,
		Gender varchar(50) DEFAULT NULL,
		Full_NAME varchar(50) NOT NULL,
		PHONE_NUMBER varchar(50) NOT NULL,
		EMAIL varchar(50) NOT NULL,
		ADDRESS varchar(50) NOT NULL,
		USER_TYPE varchar(50) NOT NULL,
         PRIMARY KEY(user_id)
       );  
  
create table if not exists user_payment_details(
user_payment_details_id int not null auto_increment,
PRIMARY KEY(user_payment_details_id),
card_number varchar(50) not null,
cvv varchar(30) not null,
expire_date varchar(30) not null,
card_type varchar(50) not null,
user_payment_details_user_id int, 
    FOREIGN KEY (user_payment_details_user_id) REFERENCES user(user_id)
);


CREATE TABLE IF NOT exists restaurant(
	restaurant_id int not null auto_increment,
	PRIMARY KEY(restaurant_id),
	restaurant_name varchar(50) not null,
	location varchar(50) default null,
	cuisuine varchar(50) default null,
	phone_number varchar(50) default null,
	price varchar(50) default null,
	status varchar(50) default null,
	restaurant_user_id int, 
    FOREIGN KEY (restaurant_user_idresume) REFERENCES user(user_id)

);
  Create table IF NOT EXISTS review(
  review_id int not null AUTO_INCREMENT,
  PRIMARY KEY(review_id),
  comments varchar(50) default null,
  review_date datetime DEFAULT NULL,
  review_user_id int, 
  FOREIGN KEY (review_user_id) REFERENCES user(user_id),
  review_restaurant_id int, 
  FOREIGN KEY (review_restaurant_id) REFERENCES restaurant(restaurant_id)
  );
  
  Create table IF NOT EXISTS review_reply(
  reply_id int not null AUTO_INCREMENT,
  PRIMARY KEY(reply_id),
  comments varchar(50) default null,
  review_date datetime DEFAULT NULL,
  reply_user_id int, 
  FOREIGN KEY (reply_user_id) REFERENCES user(user_id),
  reply_restaurant_id int, 
  FOREIGN KEY (reply_restaurant_id) REFERENCES restaurant(restaurant_id),
  reply_review_id int, 
  FOREIGN KEY (reply_review_id) REFERENCES review(review_id)
  );

CREATE table if not exists menu_items(
menu_items_id int not null auto_increment,
PRIMARY KEY(menu_items_id),
item_name varchar(50) default null,
description varchar(50) default null,
price varchar(50) default null,
category varchar(50) default null,
menu_items_restaurant_id int, 
  FOREIGN KEY (menu_items_restaurant_id) REFERENCES restaurant(restaurant_id)

);

create table if not exists restaurant_order(
	order_id int not null auto_increment, 	
PRIMARY KEY(order_id), 
menu_item1 int, 
  FOREIGN KEY (menu_item1) REFERENCES menu_items(menu_items_id),	 
menu_item2 int, 
  FOREIGN KEY (menu_item2) REFERENCES menu_items(menu_items_id),
menu_item3 int, 
  FOREIGN KEY (menu_item3) REFERENCES menu_items(menu_items_id),	
	amount varchar(20) default null,
	order_date date default null,
	status varchar(50) default null,
	payment_type varchar(50) default null,
	order_user_id int, 
  FOREIGN KEY (order_user_id) REFERENCES user(user_id),
  order_restaurant_id int, 
  FOREIGN KEY (order_restaurant_id) REFERENCES restaurant(restaurant_id)
  
);

