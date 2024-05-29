CReate database TastyKing
USE TastyKing
CREATE TABLE Users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    FullName VARCHAR(255) NOT NULL,
    UserName VARCHAR(50) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    Phone VARCHAR(15) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Role int NOT NULL,
    Active boolean,
    Otp varchar(10),
    GenerateOtpTime DATETIME
   
);
CREATE TABLE RewardPoint(
    RewardPointID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    Balance FLOAT NOT NULL,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);
CREATE TABLE Slide (
    SlideID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    Image VARCHAR(500) NOT NULL,
    SlidePosition INT NOT NULL,
    FOREIGN KEY (UserID) REFERENCES Users(userID)
);

CREATE TABLE Voucher (
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
CREATE TABLE VoucherExchange (
    VoucherExchangeID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    VoucherID INT NOT NULL,
    ExchangeDate DATE NOT NULL,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (VoucherID) REFERENCES Voucher(VoucherID)
);
CREATE TABLE TablePosition (
    TablePositionID INT AUTO_INCREMENT PRIMARY KEY,
    Position NVARCHAR(50) NOT NULL
);
CREATE TABLE Tables (
    TableID INT AUTO_INCREMENT PRIMARY KEY,
    TablePositionID INT NOT NULL,
    TableStatus NVARCHAR(50) NOT NULL,
    TableAmount INT NOT NULL,
    FOREIGN KEY (TablePositionID) REFERENCES TablePosition(TablePositionID)
);
CREATE TABLE Orders (
    OrderID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    TableID INT NOT NULL,
    Status NVARCHAR(50) NOT NULL,
    OrderDate DATE NOT NULL,
    TotalAmount DECIMAL(18, 2) NOT NULL,
    Note NVARCHAR(2000) NOT NULL,
    NumberOfCustomer int ,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (TableID) REFERENCES Tables(TableID)
);
CREATE TABLE Category (
    CategoryID INT AUTO_INCREMENT PRIMARY KEY,
    CategoryName VARCHAR(100) NOT NULL
);
CREATE TABLE Food (
    FoodID INT AUTO_INCREMENT PRIMARY KEY,
    CategoryID INT NOT NULL,
    FoodName NVARCHAR(255) NOT NULL,
	FoodPrice DECIMAL(18, 2) NOT NULL,
    FoodCost DECIMAL(18, 2) NOT NULL,
    Description NVARCHAR(10000) NULL,
    Unit NVARCHAR(50) NOT NULL,
	FoodImage Nvarchar(10000) not null,
    FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID)
);
CREATE TABLE Combo (
    ComboID INT AUTO_INCREMENT PRIMARY KEY,
    
    OldPrice DECIMAL(18, 2) NOT NULL,
    NewPrice DECIMAL(18, 2) NOT NULL,
    OpenDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    ComboImage NVARCHAR(10000) NULL,
    ComboDescription NVARCHAR(10000) NULL
    
);
CREATE TABLE Combo_Food (
    ComboID INT,
    FoodID INT,
    Quantity int,
    PRIMARY KEY (ComboID, FoodID),
    FOREIGN KEY (ComboID) REFERENCES Combo(ComboID),
    FOREIGN KEY (FoodID) REFERENCES Food(FoodID)
);

CREATE TABLE OrderDetail (
    OrderDetailID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT NOT NULL,
    FoodID INT NOT NULL,
    CustomerName NVARCHAR(100) NOT NULL,
    Date DATE NOT NULL,
    Time TIME NOT NULL,
 	 NumberPhone NVARCHAR(15) NOT NULL,
	TotalPrice DECIMAL(18, 2) NOT NULL,
    Quantity INT NOT NULL,
	 FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (FoodID) REFERENCES Food(FoodID)
    
);
CREATE TABLE Bill(
    BillID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT NOT NULL,
    BillStatus NVARCHAR(50) NOT NULL,
    BillReleaseDate DATETIME NOT NULL,
    
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
);
CREATE TABLE Payment (
    PaymentID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    BillID INT NOT NULL,
    VoucherID INT NULL,
    PaymentMethod NVARCHAR(50) NOT NULL,
    PaymentType NVARCHAR(50) NOT NULL,
    PaymentStatus NVARCHAR(50) NOT NULL,
    PaymentDate DATETIME NOT NULL,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (BillID) REFERENCES Bill(BillID),
    FOREIGN KEY (VoucherID) REFERENCES Voucher(VoucherID)
);

