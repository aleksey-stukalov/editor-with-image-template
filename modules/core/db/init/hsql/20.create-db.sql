-- begin IMAGEFIELD_CUSTOMER
alter table IMAGEFIELD_CUSTOMER add constraint FK_IMAGEFIELD_CUSTOMER_LOGO foreign key (LOGO_ID) references SYS_FILE(ID)^
create index IDX_IMAGEFIELD_CUSTOMER_LOGO on IMAGEFIELD_CUSTOMER (LOGO_ID)^
-- end IMAGEFIELD_CUSTOMER
