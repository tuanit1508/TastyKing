Create database TastyKing
USE TastyKing
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
    VoucherID INT AUTO_INCREMENT PRIMARY KEY,
    VoucherName NVARCHAR(100) NOT NULL,
    PointValue INT NOT NULL,
    VoucherQuantity INT NOT NULL,
    VoucherValue DECIMAL(18, 2) NOT NULL,
    VoucherStartDate DATE NOT NULL,
    VoucherDueDate DATE NOT NULL,
    VoucherImage varchar(1000),
	VoucherDescribe nvarchar(2000)
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
    Position NVARCHAR(50) NOT NULL
);
CREATE TABLE tables (
    TableID INT AUTO_INCREMENT PRIMARY KEY,
    TablePositionID INT NOT NULL,
    TableStatus NVARCHAR(50) NOT NULL,
    TableAmount INT NOT NULL,
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
    Date DATE NOT NULL,
    Time TIME NOT NULL,
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
    Unit NVARCHAR(50) NOT NULL,
	FoodImage Nvarchar(10000) not null,
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
    
    OldPrice DECIMAL(18, 2) NOT NULL,
    NewPrice DECIMAL(18, 2) NOT NULL,
    OpenDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    ComboImage NVARCHAR(10000) NULL,
    ComboDescription NVARCHAR(10000) NULL
    
);
CREATE TABLE combofood (
    ComboID INT,
    FoodID INT,
    Quantity int,
    PRIMARY KEY (ComboID, FoodID),
    FOREIGN KEY (ComboID) REFERENCES combo(ComboID),
    FOREIGN KEY (FoodID) REFERENCES food(FoodID)
);

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
    VoucherID INT NULL,
    PaymentMethod NVARCHAR(50) NOT NULL,
    PaymentType NVARCHAR(50) NOT NULL,
    PaymentStatus NVARCHAR(50) NOT NULL,
    PaymentDate DATETIME NOT NULL,
    FOREIGN KEY (UserID) REFERENCES users(UserID),
    FOREIGN KEY (BillID) REFERENCES bill(BillID),
    FOREIGN KEY (VoucherID) REFERENCES voucher(VoucherID)
);







