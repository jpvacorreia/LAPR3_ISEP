--delimiter /


-- Função retorna a referência de um objeto cursor
CREATE OR REPLACE FUNCTION getProduct(productId NUMBER)
RETURN SYS_REFCURSOR
AS
  curClient SYS_REFCURSOR;	-- variável de cursor do tipo predefinido SYS_REFCURSOR 
BEGIN
  -- criar um objeto cursor e armazenar a referencia desse objeto na variavel de cursor curClient
  OPEN curClient FOR SELECT * FROM PRODUCT P
      WHERE P.ID_PRODUCT = getProduct.productId;
RETURN curClient; END;
/

CREATE OR REPLACE PROCEDURE addProduct (id NUMBER, name VARCHAR, weight NUMBER, price NUMBER, id_type NUMBER)
AS
BEGIN
  INSERT INTO PRODUCT VALUES(id, name, weight, price, id_type);
END;
/

CREATE OR REPLACE PROCEDURE removeProduct(productId NUMBER)
IS
BEGIN
  DELETE FROM PRODUCT P
        WHERE P.ID_PRODUCT = productId;
END;
/

Create or replace function getAllProducts
return sys_refcursor
as
cur sys_refcursor;
begin
open cur for select * from Product;
return cur;
end;
/