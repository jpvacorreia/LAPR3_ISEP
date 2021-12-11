
create or replace PROCEDURE updateParkingSlotsScooters(id NUMBER, cc NUMBER, stateofUse NUMBER)
AS
BEGIN
  UPDATE PARKINGSLOTSSCOOTERS
  SET HAS_CHARGING_CAPABILITIES = cc,STATE_OF_USE_TYPEID_STATE_OF_USE = stateofUse
      WHERE PARKINGSLOTID_PARKINGSLOT = id;
END;
/


CREATE OR REPLACE PROCEDURE addParkingSlotsScooters(cc NUMBER, stateUse NUMBER, parkID NUMBER)
AS
BEGIN
INSERT INTO ParkingSlotsScooters(has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot)
VALUES(cc,stateUse,parkID);
END;
/

create or replace function getParkingSlotsScooters(parkID NUMBER)
return sys_refcursor
as
cur sys_refcursor;
begin
open cur for select * from ParkingSlotsScooters p where p.parkingslotid_parkingslot = parkID;
return cur;
end;
/

