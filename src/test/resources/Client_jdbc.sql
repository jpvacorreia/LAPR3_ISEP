--delimiter /

-- Função retorna a referência de um objeto cursor
CREATE OR REPLACE FUNCTION getClient(clientNIF NUMBER)
RETURN SYS_REFCURSOR
AS
  curClient SYS_REFCURSOR;	-- variável de cursor do tipo predefinido SYS_REFCURSOR 
BEGIN
  -- criar um objeto cursor e armazenar a referencia desse objeto na variavel de cursor curClient
  OPEN curClient FOR SELECT * FROM CLIENT WHERE CLIENT.NIF_CLIENT = getClient.clientNIF; RETURN curClient; END;
/

CREATE OR REPLACE PROCEDURE addClient(cname VARCHAR, nif NUMBER, credits NUMBER,
                            credCard NUMBER, coordX NUMBER, coordY NUMBER, email VARCHAR)
AS
BEGIN
  INSERT INTO CLIENT (name_client, nif_client, credits, CREDITCARDNUMBER, ADDRESSCOORDINATEX, ADDRESSCOORDINATEY, USERUSER_EMAIL)
  VALUES(cname, nif, credits, credCard, coordX, coordY, email);
END;
/

CREATE OR REPLACE PROCEDURE removeClient(clientNIF NUMBER)
IS
BEGIN
  DELETE FROM CLIENT WHERE CLIENT.NIF_CLIENT = removeClient.clientNIF;
END;
/

CREATE OR REPLACE FUNCTION getClientIdByEmail(email varchar2)
RETURN int
AS 
id_cliente int;
BEGIN
select id_client into id_cliente from client where client.useruser_email = getClientIdByEmail.email;
return id_cliente;
End;
/

CREATE OR REPLACE FUNCTION getClientById(client_Id NUMBER)
RETURN SYS_REFCURSOR
AS
  curClient SYS_REFCURSOR;	-- variável de cursor do tipo predefinido SYS_REFCURSOR
BEGIN
  -- criar um objeto cursor e armazenar a referencia desse objeto na variavel de cursor curClient
OPEN curClient FOR SELECT * FROM CLIENT WHERE CLIENT.ID_CLIENT = getClientById.client_Id; RETURN curClient; END;
/

CREATE OR REPLACE PROCEDURE updateCredits(client_ID NUMBER, newCredits NUMBER)
AS
BEGIN
  UPDATE CLIENT
  SET CREDITS = newCredits
      WHERE ID_CLIENT = client_ID;
END;
/