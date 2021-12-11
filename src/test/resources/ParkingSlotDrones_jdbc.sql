CREATE OR REPLACE PROCEDURE addParkingSlotDrones(cc NUMBER, stateUse NUMBER, parkID NUMBER)
AS
BEGIN
INSERT INTO ParkingSlotDrones(has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot)
VALUES(cc,stateUse,parkID);
END;
/
