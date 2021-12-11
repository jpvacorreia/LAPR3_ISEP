--delimiter /


-- Função retorna a referência de um objeto cursor
CREATE OR REPLACE FUNCTION getPlatformUser(email VARCHAR)
RETURN SYS_REFCURSOR
AS
  curClient SYS_REFCURSOR;	-- variável de cursor do tipo predefinido SYS_REFCURSOR 
BEGIN
  -- criar um objeto cursor e armazenar a referencia desse objeto na variavel de cursor curClient
  OPEN curClient FOR SELECT * FROM PLATFORMUSER PU
      WHERE PU.USER_EMAIL = getPlatformUser.email;
RETURN curClient; END;
/

CREATE OR REPLACE PROCEDURE addPlatformUser(email VARCHAR, pass VARCHAR, userType NUMBER)
AS
BEGIN
  INSERT INTO PLATFORMUSER (USER_EMAIL, PASSWORD, USERTYPEID_USER_TYPE)
  VALUES(email, pass, userType);
END;
/

CREATE OR REPLACE PROCEDURE removePlatformUser(email VARCHAR)
IS
BEGIN
  DELETE FROM PLATFORMUSER PU
        WHERE PU.USER_EMAIL = email;
END;
/