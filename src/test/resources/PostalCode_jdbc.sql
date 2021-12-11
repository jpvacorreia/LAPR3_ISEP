--delimiter /


-- Função retorna a referência de um objeto cursor
CREATE OR REPLACE FUNCTION getPostalCode(id NUMBER)
RETURN SYS_REFCURSOR
AS
  curClient SYS_REFCURSOR;	-- variável de cursor do tipo predefinido SYS_REFCURSOR 
BEGIN
  -- criar um objeto cursor e armazenar a referencia desse objeto na variavel de cursor curClient
  OPEN curClient FOR SELECT * FROM POSTALCODE PC
      WHERE PC.ID_POSTAL = getPostalCode.id;
RETURN curClient; END;
/

-- Função retorna a referência de um objeto cursor
CREATE OR REPLACE FUNCTION getPostalCodeIdByAtributes(cityid NUMBER, localid Number)
RETURN SYS_REFCURSOR
AS
  curClient SYS_REFCURSOR;	-- variável de cursor do tipo predefinido SYS_REFCURSOR
BEGIN
  -- criar um objeto cursor e armazenar a referencia desse objeto na variavel de cursor curClient
OPEN curClient FOR SELECT * FROM POSTALCODE PC
                    WHERE PC.CITY = getPostalCodeIdByAtributes.cityid AND
                          PC.LOCAL = getPostalCodeIdByAtributes.localid;
RETURN curClient; END;
/


CREATE OR REPLACE PROCEDURE addPostalCode(cityid NUMBER, localid NUMBER)
AS
BEGIN
  INSERT INTO POSTALCODE (CITY, LOCAL)
  VALUES(cityid, localid);
END;
/

CREATE OR REPLACE PROCEDURE removePostalCode(cityid NUMBER, localid NUMBER)
IS
BEGIN
  DELETE FROM POSTALCODE PC
        WHERE PC.CITY = removePostalCode.cityid AND
              PC.LOCAL = removePostalCode.localid;
END;
/

