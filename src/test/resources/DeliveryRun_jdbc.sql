--delimiter /

CREATE OR REPLACE FUNCTION getLastDeliveryRunID (courierID NUMBER)
RETURN sys_refcursor
AS
    lastOrderID sys_refcursor;
BEGIN
open lastOrderID for select id_delivery_run from deliveryrun where deliveryrun.courierID_courier = getLastDeliveryRunID.courierID order by delivery_run_date desc;
Return lastOrderID;
END;
/

CREATE OR REPLACE PROCEDURE addDeliveryRun(courierid number, dateDR Timestamp)
AS
BEGIN
INSERT INTO DELIVERYRUN (COURIERID_courier, DELIVERY_RUN_DATE)
VALUES(courierid, dateDR);
END;
/


create or replace function getLastCourierDeliveryRunDate(courierID number)
return sys_refcursor
as
cur sys_refcursor;
begin
open cur for select dr.delivery_run_date from DeliveryRun dr where dr.CourierId_courier = courierID order by dr.delivery_run_date desc;
return cur;
end;
/