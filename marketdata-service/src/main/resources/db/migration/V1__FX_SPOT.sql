USE marketdata_service;
GO

create table dbo.fx_spot (
                              KEY_ID bigint IDENTITY not null,
                              domestic_curr varchar(3) not null,
                              foreign_curr varchar(3) not null,
                              value float(53),
                              high float(53),
                              low float(53),
                              volume float(53),
                              exchange_name varchar(52) not null,
                              value_date date not null,
                              CONSTRAINT PK_FX_SPOT PRIMARY KEY (KEY_ID),
                              CONSTRAINT UQ_FX_SPOT UNIQUE (domestic_curr, foreign_curr, value_date, exchange_name)
);

GO





