--delimiter /

create or replace FUNCTION getCourier(courierNIF NUMBER)
RETURN SYS_REFCURSOR
AS
  curCourier SYS_REFCURSOR;	-- variavel de cursor do tipo predefinido SYS_REFCURSOR 
BEGIN
  -- criar um objeto cursor e armazenar a referencia desse objeto na variavel de cursor curCourier
  OPEN curCourier FOR SELECT * FROM COURIER
    WHERE COURIER.NIF_COURIER = getCourier.courierNIF; 
  RETURN curCourier; 
  END;
/

create or replace PROCEDURE addCourier(cname VARCHAR, niss DOUBLE PRECISION,
nif NUMBER, weight DOUBLE PRECISION, scooterId NUMBER, pharmacyId NUMBER, email VARCHAR)
AS 
BEGIN
  INSERT INTO COURIER (NAME_COURIER, NISS, NIF_COURIER, WEIGHT, SCOOTERID_SCOOTER, PHARMACYID_PHARMACY, USERUSER_EMAIL)
  VALUES(cname, niss, nif, weight, scooterId, pharmacyid, email);
END;

/
create or replace PROCEDURE removeCourier(courierNIF NUMBER)
IS
BEGIN
  DELETE FROM COURIER WHERE nif_courier = removeCourier.courierNIF;
END;

/

create or replace FUNCTION getCourierNIFByEmail(courierEmail VARCHAR)
RETURN int
AS
  nifCourier int;	-- variavel de cursor do tipo predefinido SYS_REFCURSOR
BEGIN
  -- criar um objeto cursor e armazenar a referencia desse objeto na variavel de cursor curCourier
SELECT NIF_courier INTO nifCourier FROM COURIER
    WHERE getCourierNIFByEmail.courierEmail = COURIER.Useruser_email;
RETURN nifCourier;
END;
/

create or replace FUNCTION getCourierById(id NUMBER)
RETURN SYS_REFCURSOR
AS
  curCourier SYS_REFCURSOR;	-- variavel de cursor do tipo predefinido SYS_REFCURSOR
BEGIN
  -- criar um objeto cursor e armazenar a referencia desse objeto na variavel de cursor curCourier
OPEN curCourier FOR SELECT * FROM COURIER
    WHERE COURIER.ID_COURIER = getCourierById.id;
RETURN curCourier;
END;
/

