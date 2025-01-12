--CREATE DATABASE ISP_System;

USE ISP_System;

CREATE TABLE [dbo].[Users]
(
    [UserID] INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	[UserType] VARCHAR(50) NOT NULL,
    [FullName] NVARCHAR(50), 
    [Email] NVARCHAR(100) UNIQUE, 
    [Password] VARCHAR(50),
    [Phone] VARCHAR(10) NOT NULL CHECK (LEN([Phone]) = 9 AND [Phone] NOT LIKE '%[^0-9]%%'),
    [Address] NVARCHAR(255) NULL,
    [SignupDate] DATE NULL,

);

CREATE TABLE [dbo].[Services]
(
    [ServiceID] INT PRIMARY KEY IDENTITY(1,1),
    [ServiceName] VARCHAR(100),
    [Description] TEXT NULL,
    [MonthlyCost] DECIMAL(10, 2)
);

CREATE TABLE [dbo].[InternetPackages]
(
	[PackageID] INT PRIMARY KEY IDENTITY(1,1),
	[PackageName] NVARCHAR(100),
	[Speed] VARCHAR(20),
	[DataLimit] VARCHAR(20),
	[MonthlyCost] DECIMAL(10, 2)
);

CREATE TABLE [dbo].[ChargePackages]
(
	[ChargeID] INT PRIMARY KEY IDENTITY(1,1),
	[UserID] INT,
	[PackageID] INT,
	[Amount] DECIMAL(10,2),
	[ChargeDateTime] DATETIME,
	FOREIGN KEY ([UserID]) REFERENCES [dbo].[Users]([UserID])
);

CREATE TABLE [dbo].[Subscriptions]
(
	[SubscriptionID] INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	[UserID] INT,
	[ServiceID] INT,
	[SubscriptionDate] DATE,
	[ExpirationDate] DATE,
	[Roaming] BIT DEFAULT 1,
	[Voicemail] BIT DEFAULT 0,
	[CallerTune] BIT DEFAULT 0,
	[FreeUsage] BIT DEFAULT 0,
	[SMS_Notification] BIT DEFAULT 1,
	FOREIGN KEY ([UserID]) REFERENCES [dbo].[Users]([UserID]),
	FOREIGN KEY ([ServiceID]) REFERENCES [dbo].[Services]([ServiceID])
);

CREATE TABLE [dbo].[Invoices]
(
	[InvoiceID] INT PRIMARY KEY IDENTITY(1,1),
	[UserID] INT,
    [SubscriptionID] INT,
	[PackageID] INT,
	[ChargeID] INT,
    [Amount] DECIMAL(10, 2),
    [InvoiceDate] DATETIME,
	[DueDateTime] DATETIME,
    [DueDate] DATE,
    [Paid] BIT DEFAULT 0,
	FOREIGN KEY ([UserID]) REFERENCES [dbo].[Users]([UserID]),
    FOREIGN KEY ([SubscriptionID]) REFERENCES [dbo].[Subscriptions]([SubscriptionID]),
	FOREIGN KEY ([PackageID]) REFERENCES [dbo].[InternetPackages]([PackageID]),
	FOREIGN KEY ([ChargeID]) REFERENCES [dbo].[ChargePackages]([ChargeID])
);

CREATE TABLE [dbo].[Payments]
(
	[PaymentID] INT PRIMARY KEY IDENTITY(1,1),
    [InvoiceID] INT,
    [PaymentDate] DATE,
    [Amount] DECIMAL(10, 2),
    FOREIGN KEY ([InvoiceID]) REFERENCES [dbo].[Invoices]([InvoiceID])
);

CREATE TABLE [dbo].[UsageReports]
(
	[ReportID] INT PRIMARY KEY IDENTITY(1,1),
    [UserID] INT,
    [ServiceID] INT,
    [UsageData] TEXT,
    [ReportDate] DATE,
    FOREIGN KEY ([UserID]) REFERENCES [dbo].[Users]([UserID]),
    FOREIGN KEY ([ServiceID]) REFERENCES [dbo].[Services]([ServiceID])
);

CREATE TABLE [dbo].[SupportTickets]
(
	[TicketID] INT PRIMARY KEY IDENTITY(1,1),
    [UserID] INT,
    [Subject] NVARCHAR(255),
    [Description] TEXT NULL,
    [Status] NVARCHAR(50),
    [CreatedDate] DATE,
    [UpdatedDate] DATE,
    FOREIGN KEY ([UserID]) REFERENCES [dbo].[Users]([UserID])
);

CREATE TABLE [dbo].[Complaints]
(
	[ComplaintID] INT PRIMARY KEY IDENTITY(1,1),
	[UserID] INT,
	[ComplaintSubject] NVARCHAR(100),
	[ComplaintDetails] TEXT,
	[ComplaintDate] DATE,
	[Status] VARCHAR(20),
	[ResolutionDate] DATE,
	FOREIGN KEY ([UserID]) REFERENCES [dbo].[Users]([UserID])
);

CREATE TABLE [dbo].[DiscountPlans]
(
	[DiscountID] INT PRIMARY KEY IDENTITY(1,1),
	[DiscountType] VARCHAR(50),
	[DiscountCode] VARCHAR(10),
    [PlanName] NVARCHAR(100),
    [Description] TEXT NULL,
    [DiscountPercentage] DECIMAL(5, 2),
    [StartDate] DATE,
    [EndDate] DATE
);

CREATE TABLE [dbo].[UserDiscounts]
(
	[UserDiscountID] INT PRIMARY KEY IDENTITY(1,1),
    [UserID] INT,
    [DiscountID] INT,
	[PackageID] INT NULL,
    [AssignedDate] DATE,
    FOREIGN KEY ([UserID]) REFERENCES [dbo].[Users]([UserID]),
    FOREIGN KEY ([DiscountID]) REFERENCES [dbo].[DiscountPlans]([DiscountID]),
	FOREIGN KEY ([PackageID]) REFERENCES [dbo].[InternetPackages]([PackageID])
);

CREATE TABLE [dbo].[UserEquipment]
(
	[EquippmentID] INT PRIMARY KEY IDENTITY(1,1),
	[UserID] INT,
	[EquipmentType] VARCHAR(50),
	[SerialNumber] VARCHAR(50),
	[InstallationDate] DATE,
	[Status] VARCHAR(20),
	FOREIGN KEY ([UserID]) REFERENCES [dbo].[Users]([UserID])
);

CREATE TABLE [dbo].[CallHistory]
(
	[CallHistoryID] INT PRIMARY KEY IDENTITY(1,1),
	[UserID] INT,
	[ContactNumber] INT NOT NULL CHECK (LEN([ContactNumber]) = 9 AND [ContactNumber] NOT LIKE '%[^0-9]%%'),
	[CallDateTime] DATETIME,
	[Duration] TIME,
	[Resolution] TEXT,
	FOREIGN KEY ([UserID]) REFERENCES [dbo].[Users]([UserID])
);

CREATE TABLE [dbo].[SentMessages]
(
	[SentMessageID] INT PRIMARY KEY IDENTITY(1,1),
	[UserID] INT,
	[RecipientID] INT,
	[MessageContext] TEXT,
	[SentDateTime] DATETIME,
	FOREIGN KEY ([UserID]) REFERENCES [dbo].[Users]([UserID]),
	FOREIGN KEY ([RecipientID]) REFERENCES [dbo].[Users]([UserID])
);

CREATE TABLE [dbo].[ReceivedMessages]
(
	[ReceivedMessageID] INT PRIMARY KEY IDENTITY(1,1),
	[UserID] INT,
	[SenderID] INT,
	[MessageContext] TEXT,
	[ReceivedDateTime] DATETIME,
	FOREIGN KEY ([UserID]) REFERENCES [dbo].[Users]([UserID]),
	FOREIGN KEY ([SenderID]) REFERENCES [dbo].[Users]([UserID])
);

CREATE TABLE [dbo].[NotificationMessages]
(
	[MessageID] INT PRIMARY KEY IDENTITY(1,1),
	[UserID] INT,
	[MessageContext] TEXT,
	FOREIGN KEY ([UserID]) REFERENCES [dbo].[Users]([UserID])
);