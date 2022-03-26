--DROP TABLE IF EXISTS easy_drinks;
CREATE TABLE groceries 
(
  grocId   INT NOT NULL Primary Key AUTO_INCREMENT,
  grocName VARCHAR(30) NOT NULL,
  price DEC(5,2) NOT NULL,
  category VARCHAR(30) NOT NULL
);

INSERT INTO groceries(grocName, price, category) 
VALUES ('Apples',10,'fruits');
INSERT INTO groceries(grocName, price, category) 
VALUES ('Bananas',8,'fruits');
INSERT INTO groceries(grocName, price, category) 
VALUES ('Mangoes',15,'fruits');
INSERT INTO groceries(grocName, price, category) 
VALUES ('Cherries',25,'fruits');
INSERT INTO groceries(grocName, price, category) 
VALUES ('Strawberries',20,'fruits');
INSERT INTO groceries(grocName, price, category) 
VALUES ('Melons',9,'fruits');

INSERT INTO groceries(grocName, price, category) 
VALUES ('Coca Cola',2,'drinks');
INSERT INTO groceries(grocName, price, category) 
VALUES ('Canada Dry',2,'drinks');
INSERT INTO groceries(grocName, price, category) 
VALUES ('Sprite',2,'drinks');
INSERT INTO groceries(grocName, price, category) 
VALUES ('Diet Coke',2,'drinks');

INSERT INTO groceries(grocName, price, category) 
VALUES ('Chicken',20,'meat');
INSERT INTO groceries(grocName, price, category) 
VALUES ('Beef',18,'meat');
INSERT INTO groceries(grocName, price, category) 
VALUES ('Pork',15,'meat');
INSERT INTO groceries(grocName, price, category) 
VALUES ('Fish',25,'meat');

INSERT INTO groceries(grocName, price, category) 
VALUES ('Chips',3,'snacks');
INSERT INTO groceries(grocName, price, category) 
VALUES ('Biscuits',4,'snacks');
INSERT INTO groceries(grocName, price, category) 
VALUES ('Crackers',2,'snacks');
INSERT INTO groceries(grocName, price, category) 
VALUES ('Candy',1,'snacks');
