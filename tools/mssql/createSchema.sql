
CREATE DATABASE marketdata_service
GO

IF NOT EXISTS(SELECT name FROM syslogins WHERE name = N'marketdata_service_user')
    BEGIN
        CREATE LOGIN marketdata_service_user WITH PASSWORD = 'marketdata_service_pwd123'
    END;
GO

Use marketdata_service;
GO

IF NOT EXISTS(SELECT * FROM sys.database_principals WHERE name = N'marketdata_service_user')
    BEGIN
        CREATE USER [marketdata_service_user] FOR LOGIN [marketdata_service_user]
        EXEC sp_addrolemember N'db_owner', N'marketdata_service_user'
    END;
GO

