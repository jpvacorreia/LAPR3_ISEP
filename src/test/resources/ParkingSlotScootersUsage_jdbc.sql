
Create or replace function getParkingSlotScootersUsage(parkingSlotID number, scooterID number, dateIn Timestamp)
RETURN SYS_REFCURSOR
AS
cur sys_refcursor;
begin
open cur for select * from Parkingslotscootersusage p where getParkingSlotScootersUsage.parkingSlotID = p.parkingslotsscootersparkingslotid_parkingslot and getParkingSlotScootersUsage.scooterID = p.scooterid_scooter and getParkingSlotScootersUsage.datein = p.datein;
return cur;
end;
/

Create or replace function getParkingSlotScootersLastUsage(parkingSlotID number)
return sys_refcursor
as
cur sys_refcursor;
begin
open cur for select * from Parkingslotscootersusage p where p.parkingslotsscootersparkingslotid_parkingslot = getParkingSlotScootersLastUsage.parkingSlotID order by p.datein desc;
return cur;
end;
/

CREATE OR REPLACE PROCEDURE removeParkingSlotScootersUsage(parkingSlotID number, scooterID number, dateIn Timestamp)
IS
BEGIN
DELETE FROM ParkingSlotScootersUsage p where removeParkingSlotScootersUsage.parkingSlotID = p.parkingslotsscootersparkingslotid_parkingslot and removeParkingSlotScootersUsage.scooterID = p.scooterid_scooter and removeParkingSlotScootersUsage.datein = p.datein;
END;
/

CREATE OR REPLACE PROCEDURE addParkingSlotScootersUsage(parkingSlotID number, scooterID number, p_dateIn Timestamp, p_dateOut Timestamp)
AS
BEGIN
INSERT INTO ParkingSlotScootersUsage (datein,dateout,scooterid_scooter,parkingslotsscootersparkingslotid_parkingslot)
VALUES(p_dateIn, p_dateOut, scooterID, parkingSlotID);
END;
/

Create or replace function getParkingSlotScootersLastUsageByScooterId (scooterId number)
return sys_refcursor
as
cur sys_refcursor;
begin
open cur for select * from Parkingslotscootersusage p where p.scooterid_scooter = getParkingSlotScootersLastUsageByScooterId.scooterId order by p.datein desc;
return cur;
end;
/
