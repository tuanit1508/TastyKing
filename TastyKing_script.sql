Create database TastyKing
Use TastyKing
CREATE TABLE users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    FullName VARCHAR(255) ,
    UserName VARCHAR(50) ,
    Email VARCHAR(100) ,
    Phone VARCHAR(15) ,
    Password VARCHAR(255) ,
    Role VARCHAR(50) ,
    Active boolean,
    Otp varchar(10),
    GenerateOtpTime DATETIME

);
ALTER TABLE users
ADD Provider VARCHAR(50),
ADD ProviderId varchar(100);

CREATE TABLE rewardpoint(
    RewardPointID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    Balance FLOAT NOT NULL,
    FOREIGN KEY (UserID) REFERENCES users(UserID)
);
CREATE TABLE slide (
    SlideID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    Image VARCHAR(500) NOT NULL,
    SlidePosition INT NOT NULL,
    FOREIGN KEY (UserID) REFERENCES users(userID)
);

CREATE TABLE voucher (
    VoucherID VARCHAR(50) PRIMARY KEY,
    VoucherTitle VARCHAR(100) NOT NULL,
    VoucherDiscount INT NOT NULL,
    VoucherQuantity INT NOT NULL,
    VoucherExchangeValue DOUBLE NOT NULL,
    VoucherStartDate DATE NOT NULL,
    VoucherDueDate DATE NOT NULL,
    VoucherImage varchar(2000),
	VoucherDescribe Text
);
CREATE TABLE voucherexchange (
    VoucherExchangeID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    VoucherID INT NOT NULL,
    ExchangeDate DATE NOT NULL,
    FOREIGN KEY (UserID) REFERENCES users(UserID),
    FOREIGN KEY (VoucherID) REFERENCES voucher(VoucherID)
);
CREATE TABLE tableposition (
    TablePositionID INT AUTO_INCREMENT PRIMARY KEY,
    TableQuantity INT NOT NULL,
    TablePosition VARCHAR(50) NOT NULL
);
CREATE TABLE tables (
    TableID INT AUTO_INCREMENT PRIMARY KEY,
    TablePositionID INT NOT NULL,
    TableStatus VARCHAR(50) NOT NULL,
    NumOfChair INT NOT NULL,
    FOREIGN KEY (TablePositionID) REFERENCES tableposition(TablePositionID)
);
CREATE TABLE orders (
    OrderID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    TableID INT NOT NULL,
    Status NVARCHAR(50) NOT NULL,
    OrderDate DATE NOT NULL,
    TotalAmount DECIMAL(18, 2) ,
    Note NVARCHAR(2000),
    NumberOfCustomer int ,
     NumberPhone NVARCHAR(15) NOT NULL,
    CustomerName NVARCHAR(100) NOT NULL,
    DateTime Date NULL;
   Foreign key (VoucherID) references voucher(VoucherID)
    FOREIGN KEY (UserID) REFERENCES users(UserID),
    FOREIGN KEY (TableID) REFERENCES tables(TableID)
);
CREATE TABLE category (
    CategoryID INT AUTO_INCREMENT PRIMARY KEY,
    CategoryName VARCHAR(100) NOT NULL
);
CREATE TABLE food (
    FoodID INT AUTO_INCREMENT PRIMARY KEY,
    CategoryID INT NOT NULL,
    FoodName NVARCHAR(255) NOT NULL,
	FoodPrice DECIMAL(18, 2) NOT NULL,
    FoodCost DECIMAL(18, 2) NOT NULL,
    Description NVARCHAR(10000) NULL,
    Unit VARCHAR(50) NOT NULL,
	FoodImage varchar(2000) not null,
    FOREIGN KEY (CategoryID) REFERENCES category(CategoryID)
);
CREATE TABLE review(
	ReviewID INT AUTO_INCREMENT PRIMARY KEY,
	UserID INT NOT NULL,
	FoodID INT not null,
	ReviewText VARCHAR(5000),
	RewiewDate DATETIME,
	FOREIGN KEY (FoodID) REFERENCES food(FoodIDID)
);
CREATE TABLE combo (
    ComboID INT AUTO_INCREMENT PRIMARY KEY,
     ComboTitle VARCHAR(1000) NULL,
     OldPrice DOUBLE NOT NULL,
    NewPrice DOUBLE NOT NULL,
    OpenDate DATETIme NOT NULL,
    EndDate DATETIME NOT NULL,
    ComboImage VARCHAR(5000) NULL,
    ComboDescription VARCHAR(8000) NULL

);
CREATE TABLE combofood (
    ComboID INT,
    FoodID INT,
    Quantity INT,
    PRIMARY KEY (ComboID, FoodID),
    FOREIGN KEY (ComboID) REFERENCES combo(ComboID) ON DELETE CASCADE,
    FOREIGN KEY (FoodID) REFERENCES food(foodID)
);

SELECT CONSTRAINT_NAME
FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE TABLE_NAME = 'combofood'
AND TABLE_SCHEMA = 'TastyKing';

ALTER TABLE combofood DROP FOREIGN KEY FK7yycjgewhb12b3ldu6q1v0kam;
ALTER TABLE combofood DROP FOREIGN KEY FKtoagacgqrdxhtu4ph9qdwrx52;

ALTER TABLE combofood
ADD CONSTRAINT fk_combofood_combo
FOREIGN KEY (ComboID) REFERENCES combo(ComboID) ON DELETE CASCADE;

ALTER TABLE combofood
ADD CONSTRAINT fk_combofood_food
FOREIGN KEY (FoodID) REFERENCES food(FoodID) ON DELETE CASCADE;
CREATE TABLE orderdetail (
    OrderDetailID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT NOT NULL,
    FoodID INT NOT NULL,

	TotalPrice DECIMAL(18, 2),
    Quantity INT NOT NULL,
	 FOREIGN KEY (OrderID) REFERENCES orders(OrderID),
    FOREIGN KEY (FoodID) REFERENCES food(FoodID)

);
CREATE TABLE bill(
    BillID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT NOT NULL,
    BillStatus NVARCHAR(50) NOT NULL,
    BillReleaseDate DATETIME NOT NULL,

    FOREIGN KEY (OrderID) REFERENCES orders(OrderID)
);
CREATE TABLE payment (
    PaymentID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    BillID INT NOT NULL,

    PaymentMethod NVARCHAR(50) NOT NULL,
    PaymentType NVARCHAR(50) NOT NULL,
    PaymentStatus NVARCHAR(50) NOT NULL,
    PaymentDate DATETIME NOT NULL,
    FOREIGN KEY (UserID) REFERENCES users(UserID),
    FOREIGN KEY (BillID) REFERENCES bill(BillID),

);







