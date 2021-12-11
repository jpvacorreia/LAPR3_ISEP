Create or replace function getParkingSlotsScootersWithCCInUse(idPharmacy int ,p_date Timestamp )
Return SYS_REFCURSOR
As
cursorRet SYS_REFCURSOR;
BEGIN
open cursorRet for select * from ParkingSlot ps
inner join ParkingSlotsScooters pss on ps.id_parkingslot = pss.parkingslotid_parkingslot
inner join ParkingSlotScootersUsage pssu on ps.id_parkingslot = pssu.parkingslotsscootersparkingslotid_parkingslot
where ps.pharmacyid_pharmacy = getParkingSlotsScootersWithCCInUse.idPharmacy and pss.has_charging_capabilities = 1 and getParkingSlotsScootersWithCCInUse.p_date >= pssu.datein and getParkingSlotsScootersWithCCInUse.p_date <= pssu.dateout;
return cursorRet;
end;
/

create or replace function getVacantChargeableScooterParkingSlot(idPharmacy number)
return sys_refcursor
as
cur sys_refcursor;
begin
open cur for select * from parkingslot ps inner join parkingslotsscooters pss on ps.id_parkingslot = pss.parkingslotid_parkingslot
where ps.pharmacyid_pharmacy = getVacantChargeableScooterParkingSlot.idPharmacy and pss.has_charging_capabilities = 1 and pss.state_of_use_typeid_state_of_use = 1;
return cur;
end;
/

CREATE OR REPLACE PROCEDURE addParkingSlot(phID NUMBER)
AS
BEGIN
INSERT INTO ParkingSlot(Pharmacyid_pharmacy)
VALUES(phID);
END;
/

Create or replace function getParkingSlotFromPharmacy(phID number)
return sys_refcursor
as
cur sys_refcursor;
begin
open cur for select * from Parkingslot p where p.pharmacyid_pharmacy = phID order by p.id_parkingslot desc;
return cur;
end;
/

