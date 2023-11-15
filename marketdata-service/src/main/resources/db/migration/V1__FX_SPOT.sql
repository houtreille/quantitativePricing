USE marketdata_service;
GO

-- =============
-- === Universe ===
-- =============

create table dbo.fx_spot (
                              KEY_ID bigint IDENTITY not null,
                              domestic_curr varchar(255) not null,
                              foreign_curr varchar(255) not null,
                              value float(53),
                              value_date date not null,
                              CONSTRAINT PK_FX_SPOT PRIMARY KEY (KEY_ID),
                              CONSTRAINT UQ_FX_SPOT UNIQUE (domestic_curr, foreign_curr, value_date)
);

GO





