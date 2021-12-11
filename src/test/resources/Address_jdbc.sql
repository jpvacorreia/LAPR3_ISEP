--delimiter /


-- Função retorna a referência de um objeto cursor
CREATE OR REPLACE FUNCTION getAddress(coordX NUMBER, coordY Number)
RETURN SYS_REFCURSOR
AS
  curClient SYS_REFCURSOR;	-- variável de cursor do tipo predefinido SYS_REFCURSOR 
BEGIN
  -- criar um objeto cursor e armazenar a referencia desse objeto na variavel de cursor curClient
  OPEN curClient FOR SELECT * FROM ADDRESS AD
      WHERE AD.COORDINATEX = getAddress.coordX AND AD.COORDINATEY = getAddress.coordY;
RETURN curClient; END;
/

CREATE OR REPLACE PROCEDURE addAddress(streetName VARCHAR, coordX NUMBER,
            coordY NUMBER, pcID NUMBER, elev NUMBER)
AS
BEGIN
  INSERT INTO ADDRESS (STREET, COORDINATEX, COORDINATEY, POSTALCODEID_POSTAL, ELEVATION)
  VALUES(streetName, coordX, coordY, pcID, elev);
END;
/

CREATE OR REPLACE PROCEDURE removeAddress(coordX NUMBER, coordY NUMBER)
IS
BEGIN
  DELETE FROM ADDRESS AD
        WHERE AD.COORDINATEX = removeAddress.coordX AND
              AD.COORDINATEY = removeAddress.coordY;
END;
/