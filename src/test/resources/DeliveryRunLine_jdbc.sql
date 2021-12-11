--delimiter /

CREATE OR REPLACE PROCEDURE addDeliveryRunLine(orderID number, deliveryRunID number)
AS
BEGIN
INSERT INTO DELIVERYRUNLINE (CLIENTORDERID_ORDER, DELIVERYRUNID_delivery_run)
VALUES(orderID, deliveryRunID);
END;
/