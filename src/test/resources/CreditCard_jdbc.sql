--delimiter /


-- Função retorna a referência de um objeto cursor
CREATE OR REPLACE FUNCTION getCreditCard(credCardNum NUMBER)
RETURN SYS_REFCURSOR
AS
  curClient SYS_REFCURSOR;	-- variável de cursor do tipo predefinido SYS_REFCURSOR 
BEGIN
  -- criar um objeto cursor e armazenar a referencia desse objeto na variavel de cursor curClient
  OPEN curClient FOR SELECT * FROM CREDITCARD
                    WHERE CREDITCARD.NUMBER_CARD = getCreditCard.credCardNum;
  RETURN curClient; END;
/

CREATE OR REPLACE PROCEDURE addCreditCard(credCardNum NUMBER, validity_year NUMBER, validity_month NUMBER, verifCode NUMBER)
AS
BEGIN
  INSERT INTO CREDITCARD (NUMBER_CARD, VALIDITY_YEAR, VALIDITY_MONTH, CVV)
  VALUES(credCardNum, validity_year, validity_month, verifCode);
END;
/

CREATE OR REPLACE PROCEDURE removeCreditCard(credCardNum NUMBER)
IS
BEGIN
  DELETE FROM CREDITCARD CC WHERE CC.NUMBER_CARD = removeCreditCard.credCardNum;
END;
/