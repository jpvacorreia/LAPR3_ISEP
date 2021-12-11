
-- Procedimento adicionar pharmacy
CREATE OR REPLACE PROCEDURE addPharmacy(phname VARCHAR, phnif NUMBER, idAdmin NUMBER, coordX FLOAT, coordY FLOAT, voltageInp NUMBER, currentInp NUMBER, maxScooters NUMBER)
AS
BEGIN
INSERT INTO PHARMACY (name_pharmacy, nif_pharmacy, administratorid_administrator,ADDRESSCOORDINATEX, ADDRESSCOORDINATEY, park_input_voltage, park_input_current, max_number_scooter)
VALUES(phname, phnif, idAdmin, coordX, coordY, voltageInp, currentInp, maxScooters);
END;
/

-- Função obter (get) pharmacy
CREATE OR REPLACE FUNCTION getPharmacy(pharmacyNIF NUMBER)
RETURN SYS_REFCURSOR
AS
  curPh SYS_REFCURSOR;	-- variável de cursor do tipo predefinido SYS_REFCURSOR
BEGIN
  -- criar um objeto cursor e armazenar a referencia desse objeto na variavel de cursor curPh
OPEN curPh FOR SELECT * FROM PHARMACY WHERE PHARMACY.NIF_PHARMACY = getPharmacy.pharmacyNIF; RETURN curPh; END;
/

CREATE OR REPLACE FUNCTION getPharmacyByID(pharmacyID NUMBER)
RETURN SYS_REFCURSOR
AS
  curPh SYS_REFCURSOR;	-- variável de cursor do tipo predefinido SYS_REFCURSOR
BEGIN
  -- criar um objeto cursor e armazenar a referencia desse objeto na variavel de cursor curPh
OPEN curPh FOR SELECT * FROM PHARMACY WHERE PHARMACY.ID_PHARMACY = getPharmacyByID.pharmacyID; RETURN curPh; END;
/

-- Função remover pharmacy
CREATE OR REPLACE PROCEDURE removePharmacy(pharmacyNIF NUMBER)
AS
BEGIN
DELETE FROM PHARMACY WHERE PHARMACY.NIF_PHARMACY = removePharmacy.pharmacyNIF;
END;
/

CREATE OR REPLACE FUNCTION getAllPharmacies
RETURN SYS_REFCURSOR
AS
    phAll SYS_REFCURSOR;
        BEGIN
            OPEN phAll FOR SELECT * FROM PHARMACY;
            RETURN phAll;
        end;
/


create or replace PROCEDURE updatePharmacy(id NUMBER, namePh varchar2, nif NUMBER, idadmin number, inputV number, inputC number, maxScoots number)
AS
BEGIN
  UPDATE PHARMACY
  SET name_pharmacy = namePh, nif_pharmacy = nif, administratorid_administrator = idadmin,
  park_input_voltage = inputV, park_input_current = inputC, max_number_scooter = maxScoots
  WHERE id_pharmacy = id;
END;
/

