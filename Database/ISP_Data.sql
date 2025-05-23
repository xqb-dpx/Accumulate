-- Users
INSERT INTO Users (UserType, FullName, Email, Password, Phone, Address, SignupDate)
VALUES ('Customer', 'Ali Rezaei', 'ali@example.com', 'pass1234', '912345678', 'Tehran, Iran', '2025-01-01');

-- Services
INSERT INTO Services (ServiceName, Description, MonthlyCost)
VALUES ('Mobile Internet', '4G mobile internet service', 150.00);

-- InternetPackages
INSERT INTO InternetPackages (PackageName, Speed, DataLimit, MonthlyCost)
VALUES ('Gold Plan', '50Mbps', '100GB', 200.00);

-- ChargePackages
INSERT INTO ChargePackages (UserID, PackageID, Amount, ChargeDateTime)
VALUES (1, 1, 200.00, NOW());

-- Subscriptions
INSERT INTO Subscriptions (UserID, ServiceID, SubscriptionDate, ExpirationDate, Roaming, Voicemail, CallerTune, FreeUsage, SMS_Notification)
VALUES (1, 1, '2025-01-05', '2025-07-05', TRUE, FALSE, FALSE, FALSE, TRUE);

-- Invoices
INSERT INTO Invoices (UserID, SubscriptionID, PackageID, ChargeID, Amount, InvoiceDate, DueDateTime, DueDate, Paid)
VALUES (1, 1, 1, 1, 200.00, NOW(), NOW(), '2025-01-15', FALSE);

-- Payments
INSERT INTO Payments (InvoiceID, PaymentDate, Amount)
VALUES (1, '2025-01-10', 200.00);

-- UsageReports
INSERT INTO UsageReports (UserID, ServiceID, UsageData, ReportDate)
VALUES (1, 1, 'Used 80GB this month', '2025-01-31');

-- SupportTickets
INSERT INTO SupportTickets (UserID, Subject, Description, Status, CreatedDate, UpdatedDate)
VALUES (1, 'Internet speed issue', 'Speed lower than expected', 'Open', '2025-01-10', '2025-01-10');

-- Complaints
INSERT INTO Complaints (UserID, ComplaintSubject, ComplaintDetails, ComplaintDate, Status, ResolutionDate)
VALUES (1, 'Billing Issue', 'Charged twice this month', '2025-01-12', 'Resolved', '2025-01-14');

-- DiscountPlans
INSERT INTO DiscountPlans (DiscountType, DiscountCode, PlanName, Description, DiscountPercentage, StartDate, EndDate)
VALUES ('NewYear', 'NY2025', 'New Year Offer', '20% off on all plans', 20.00, '2025-01-01', '2025-01-31');

-- UserDiscounts
INSERT INTO UserDiscounts (UserID, DiscountID, PackageID, AssignedDate)
VALUES (1, 1, 1, '2025-01-01');

-- UserEquipment
INSERT INTO UserEquipment (UserID, EquipmentType, SerialNumber, InstallationDate, Status)
VALUES (1, 'Router', 'ABC123456', '2025-01-02', 'Active');

-- CallHistory
INSERT INTO CallHistory (UserID, ContactNumber, CallDateTime, Duration, Resolution)
VALUES (1, '934567890', NOW(), '00:05:20', 'Call completed');

-- SentMessages
INSERT INTO SentMessages (UserID, RecipientID, MessageContext, SentDateTime)
VALUES (1, 1, 'Test message to self', NOW());

-- ReceivedMessages
INSERT INTO ReceivedMessages (UserID, SenderID, MessageContext, ReceivedDateTime)
VALUES (1, 1, 'Test message from self', NOW());

-- NotificationMessages
INSERT INTO NotificationMessages (UserID, MessageContext)
VALUES (1, 'Your internet plan is about to expire.');