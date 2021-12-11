CREATE OR REPLACE FUNCTION getClientOrders(clientID NUMBER)
RETURN SYS_REFCURSOR
AS
  curClientOrder SYS_REFCURSOR;	-- variável de cursor do tipo predefinido SYS_REFCURSOR 
BEGIN
  -- criar um objeto cursor e armazenar a referencia desse objeto na variavel de cursor curClient
  OPEN curClientOrder FOR SELECT * FROM CLIENTORDER WHERE CLIENTORDER.Clientid_client = getClientOrders.clientID Order by order_date desc; RETURN curClientOrder; END;
/

CREATE OR REPLACE FUNCTION getLastClientOrderID (clientID NUMBER)
RETURN sys_refcursor
AS
    lastOrderID sys_refcursor;
BEGIN
    open lastOrderID for select id_order from clientorder where clientorder.clientid_client = getLastClientOrderID.clientID order by order_date desc; 
    Return lastOrderID;
    END;
/    

create or replace FUNCTION getAllOrders
RETURN SYS_REFCURSOR
AS
  curOrders SYS_REFCURSOR;    -- vari�vel de cursor do tipo predefinido SYS_REFCURSOR
BEGIN
  OPEN curOrders FOR SELECT * FROM ClientOrder Order by id_order desc;
  RETURN curOrders;
  END;
/  

/*
create or replace FUNCTION getAllReadyOrders
RETURN SYS_REFCURSOR
AS
  curOrders SYS_REFCURSOR;    -- vari�vel de cursor do tipo predefinido SYS_REFCURSOR
BEGIN
  OPEN curOrders FOR SELECT * FROM ClientOrder where state_orderid_state_order = 2;
  RETURN curOrders;
  END;
/
*/

CREATE OR REPLACE PROCEDURE addClientOrder(clientid number, dateOrder Timestamp, orderStateID number)
AS
BEGIN
  INSERT INTO CLIENTORDER (CLIENTID_CLIENT, ORDER_DATE, STATE_ORDERID_STATE_ORDER)
  VALUES(clientid, dateOrder, orderStateID);
END;
/

create or replace FUNCTION getAllOrdersWithState(state NUMBER)
RETURN SYS_REFCURSOR
AS
  curOrders SYS_REFCURSOR;    -- vari�vel de cursor do tipo predefinido SYS_REFCURSOR
BEGIN
OPEN curOrders FOR SELECT * FROM ClientOrder where state_orderid_state_order = state;
RETURN curOrders;
END;
/

CREATE OR REPLACE PROCEDURE updateOrderStatus(orderID NUMBER, stateNum NUMBER)
AS
BEGIN
UPDATE CLIENTORDER
SET STATE_ORDERID_STATE_ORDER = stateNum
WHERE ID_ORDER = orderID;
END;
/