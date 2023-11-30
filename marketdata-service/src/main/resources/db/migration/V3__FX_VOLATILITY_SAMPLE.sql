USE marketdata_service;
GO

INSERT INTO dbo.fx_volatility (currencyPair, valuationDate, provider, tenor, strike, volatility) VALUES ('EURUSD', '2018-01-01', 'TestProvider', '2018-01-01', 0.9, 0.2);
INSERT INTO dbo.fx_volatility (currencyPair, valuationDate, provider, tenor, strike, volatility) VALUES ('EURUSD', '2018-01-01', 'TestProvider', '2018-01-01', 0.95, 0.18);
INSERT INTO dbo.fx_volatility (currencyPair, valuationDate, provider, tenor, strike, volatility) VALUES ('EURUSD', '2018-01-01', 'TestProvider', '2018-01-01', 1.0, 0.15);
INSERT INTO dbo.fx_volatility (currencyPair, valuationDate, provider, tenor, strike, volatility) VALUES ('EURUSD', '2018-01-01', 'TestProvider', '2018-01-01', 1.1, 0.14);
INSERT INTO dbo.fx_volatility (currencyPair, valuationDate, provider, tenor, strike, volatility) VALUES ('EURUSD', '2018-01-01', 'TestProvider', '2018-01-01', 1.2, 0.18);

INSERT INTO dbo.fx_volatility (currencyPair, valuationDate, provider, tenor, strike, volatility) VALUES ('EURUSD', '2018-01-01', 'TestProvider', '2018-01-08', 0.92, 0.25);
INSERT INTO dbo.fx_volatility (currencyPair, valuationDate, provider, tenor, strike, volatility) VALUES ('EURUSD', '2018-01-01', 'TestProvider', '2018-01-08', 0.96, 0.185);
INSERT INTO dbo.fx_volatility (currencyPair, valuationDate, provider, tenor, strike, volatility) VALUES ('EURUSD', '2018-01-01', 'TestProvider', '2018-01-08', 1.01, 0.155);
INSERT INTO dbo.fx_volatility (currencyPair, valuationDate, provider, tenor, strike, volatility) VALUES ('EURUSD', '2018-01-01', 'TestProvider', '2018-01-08', 1.11, 0.145);
INSERT INTO dbo.fx_volatility (currencyPair, valuationDate, provider, tenor, strike, volatility) VALUES ('EURUSD', '2018-01-01', 'TestProvider', '2018-01-08', 1.21, 0.185);

INSERT INTO dbo.fx_volatility (currencyPair, valuationDate, provider, tenor, strike, volatility) VALUES ('EURUSD', '2018-01-01', 'TestProvider', '2018-02-01', 0.94, 0.259);
INSERT INTO dbo.fx_volatility (currencyPair, valuationDate, provider, tenor, strike, volatility) VALUES ('EURUSD', '2018-01-01', 'TestProvider', '2018-02-01', 0.98, 0.1859);
INSERT INTO dbo.fx_volatility (currencyPair, valuationDate, provider, tenor, strike, volatility) VALUES ('EURUSD', '2018-01-01', 'TestProvider', '2018-02-01', 1.05, 0.1559);
INSERT INTO dbo.fx_volatility (currencyPair, valuationDate, provider, tenor, strike, volatility) VALUES ('EURUSD', '2018-01-01', 'TestProvider', '2018-02-01', 1.14, 0.1459);
INSERT INTO dbo.fx_volatility (currencyPair, valuationDate, provider, tenor, strike, volatility) VALUES ('EURUSD', '2018-01-01', 'TestProvider', '2018-02-01', 1.23, 0.1859);


