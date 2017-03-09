-- begin IMAGEFIELD_CUSTOMER
create table IMAGEFIELD_CUSTOMER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    LOGO_ID varchar(36),
    --
    primary key (ID)
)^
-- end IMAGEFIELD_CUSTOMER
