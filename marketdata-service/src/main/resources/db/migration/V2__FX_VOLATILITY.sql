USE marketdata_service;
GO

create table dbo.fx_volatility (
                              KEY_ID bigint IDENTITY not null,
                              currencyPair varchar(6) not null,
                              valuationDate date not null,
                              provider varchar(50),
                              tenor date not null,
                              strike float(53),
                              volatility float(53),
                              CONSTRAINT PK_FX_VOLATILITY PRIMARY KEY (KEY_ID),
                              CONSTRAINT UQ_FX_VOLATILITY UNIQUE (currencyPair, valuationDate, provider, tenor, strike)
);

GO
