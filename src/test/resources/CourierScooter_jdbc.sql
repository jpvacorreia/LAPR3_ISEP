--delimiter /


Create or replace procedure addCourierScooter(p_dateIn Timestamp, p_dateOut Timestamp, scID number, courID number)
as
begin
insert into CourierScooter (dateIn, dateOut, courierId_courier, scooterId_scooter)
values (p_dateIn, p_dateOut, courID, scID);
end;
/

create or replace FUNCTION getCourierScooterByScooterId(id NUMBER)
RETURN SYS_REFCURSOR
AS
  curCourier SYS_REFCURSOR;
BEGIN

OPEN curCourier FOR SELECT * FROM CourierScooter cs
    WHERE cs.Scooterid_scooter = getCourierScooterByScooterId.id
    order by cs.dateIn desc;
RETURN curCourier;
END;
/

