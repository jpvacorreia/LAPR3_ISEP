
CREATE OR REPLACE PROCEDURE addScooter(id NUMBER, maxBatteryCapacity NUMBER, stateOfCharge NUMBER,
                                    powerOutput NUMBER, weight NUMBER, maxPayload NUMBER, pharmacyID NUMBER)
AS
BEGIN
  INSERT INTO SCOOTER (ID_SCOOTER, MAX_BATTERY_CAPACITY, STATE_OF_CHARGE, POWEROUTPUT, WEIGHT, MAX_PAYLOAD, PHARMACYID_PHARMACY)
  VALUES(id, maxBatteryCapacity, stateOfCharge, powerOutput, weight, maxPayload, pharmacyID);
END;
/


CREATE OR REPLACE PROCEDURE removeScooter(scooterID NUMBER)
IS
BEGIN
  DELETE FROM Scooter
        WHERE ID_SCOOTER = removeScooter.scooterID;
END;
/

CREATE OR REPLACE PROCEDURE updateDrone(id NUMBER, maxBatteryCapacity NUMBER, stateOfCharge NUMBER,
                                    pwrOutput NUMBER, wght NUMBER, maxPayload NUMBER, pharmacyID NUMBER)
AS
BEGIN
  UPDATE DRONE
  SET MAX_BATTERY_CAPACITY = maxBatteryCapacity, STATE_OF_CHARGE = stateOfCharge, POWEROUTPUT = pwrOutput,
      WEIGHT = wght, MAX_PAYLOAD = maxPayload, PHARMACYID_PHARMACY = pharmacyID
      WHERE ID_DRONE = id;
END;
/

CREATE OR REPLACE FUNCTION getAllDrones
RETURN SYS_REFCURSOR
AS
  curDrone SYS_REFCURSOR;	-- variável de cursor do tipo predefinido SYS_REFCURSOR 
BEGIN
  -- criar um objeto cursor e armazenar a referencia desse objeto na variavel de cursor curScooter
OPEN curDrone FOR SELECT * FROM DRONE;
RETURN curDrone;
END;
/