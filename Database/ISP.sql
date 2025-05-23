-- CREATE DATABASE ISP_System;

USE ISP_System;

CREATE TABLE Users (
    UserID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    UserType VARCHAR(50) NOT NULL,
    FullName VARCHAR(50),
    Email VARCHAR(100) UNIQUE,
    Password VARCHAR(50),
    Phone VARCHAR(10) NOT NULL,
    Address VARCHAR(255),
    SignupDate DATE,
    CONSTRAINT chk_phone_format CHECK (Phone REGEXP '^[0-9]{9}$')
);

CREATE TABLE Services (
    ServiceID INT AUTO_INCREMENT PRIMARY KEY,
    ServiceName VARCHAR(100),
    Description TEXT,
    MonthlyCost DECIMAL(10, 2)
);

CREATE TABLE InternetPackages (
    PackageID INT AUTO_INCREMENT PRIMARY KEY,
    PackageName VARCHAR(100),
    Speed VARCHAR(20),
    DataLimit VARCHAR(20),
    MonthlyCost DECIMAL(10, 2)
);

CREATE TABLE ChargePackages (
    ChargeID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    PackageID INT,
    Amount DECIMAL(10,2),
    ChargeDateTime DATETIME,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE Subscriptions (
    SubscriptionID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    ServiceID INT,
    SubscriptionDate DATE,
    ExpirationDate DATE,
    Roaming BOOLEAN DEFAULT TRUE,
    Voicemail BOOLEAN DEFAULT FALSE,
    CallerTune BOOLEAN DEFAULT FALSE,
    FreeUsage BOOLEAN DEFAULT FALSE,
    SMS_Notification BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (ServiceID) REFERENCES Services(ServiceID)
);

CREATE TABLE Invoices (
    InvoiceID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    SubscriptionID INT,
    PackageID INT,
    ChargeID INT,
    Amount DECIMAL(10, 2),
    InvoiceDate DATETIME,
    DueDateTime DATETIME,
    DueDate DATE,
    Paid BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (SubscriptionID) REFERENCES Subscriptions(SubscriptionID),
    FOREIGN KEY (PackageID) REFERENCES InternetPackages(PackageID),
    FOREIGN KEY (ChargeID) REFERENCES ChargePackages(ChargeID)
);

CREATE TABLE Payments (
    PaymentID INT AUTO_INCREMENT PRIMARY KEY,
    InvoiceID INT,
    PaymentDate DATE,
    Amount DECIMAL(10, 2),
    FOREIGN KEY (InvoiceID) REFERENCES Invoices(InvoiceID)
);

CREATE TABLE UsageReports (
    ReportID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    ServiceID INT,
    UsageData TEXT,
    ReportDate DATE,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (ServiceID) REFERENCES Services(ServiceID)
);

CREATE TABLE SupportTickets (
    TicketID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    Subject VARCHAR(255),
    Description TEXT,
    Status VARCHAR(50),
    CreatedDate DATE,
    UpdatedDate DATE,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE Complaints (
    ComplaintID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    ComplaintSubject VARCHAR(100),
    ComplaintDetails TEXT,
    ComplaintDate DATE,
    Status VARCHAR(20),
    ResolutionDate DATE,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE DiscountPlans (
    DiscountID INT AUTO_INCREMENT PRIMARY KEY,
    DiscountType VARCHAR(50),
    DiscountCode VARCHAR(10),
    PlanName VARCHAR(100),
    Description TEXT,
    DiscountPercentage DECIMAL(5, 2),
    StartDate DATE,
    EndDate DATE
);

CREATE TABLE UserDiscounts (
    UserDiscountID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    DiscountID INT,
    PackageID INT,
    AssignedDate DATE,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (DiscountID) REFERENCES DiscountPlans(DiscountID),
    FOREIGN KEY (PackageID) REFERENCES InternetPackages(PackageID)
);

CREATE TABLE UserEquipment (
    EquippmentID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    EquipmentType VARCHAR(50),
    SerialNumber VARCHAR(50),
    InstallationDate DATE,
    Status VARCHAR(20),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE CallHistory (
    CallHistoryID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    ContactNumber VARCHAR(9) NOT NULL,
    CallDateTime DATETIME,
    Duration TIME,
    Resolution TEXT,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    CONSTRAINT chk_contact_number CHECK (ContactNumber REGEXP '^[0-9]{9}$')
);

CREATE TABLE SentMessages (
    SentMessageID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    RecipientID INT,
    MessageContext TEXT,
    SentDateTime DATETIME,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (RecipientID) REFERENCES Users(UserID)
);

CREATE TABLE ReceivedMessages (
    ReceivedMessageID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    SenderID INT,
    MessageContext TEXT,
    ReceivedDateTime DATETIME,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (SenderID) REFERENCES Users(UserID)
);

CREATE TABLE NotificationMessages (
    MessageID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    MessageContext TEXT,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);