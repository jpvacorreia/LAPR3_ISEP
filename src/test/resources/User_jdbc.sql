create or replace FUNCTION getPlatformUser(email varchar2)
RETURN SYS_REFCURSOR
AS
  curUser SYS_REFCURSOR;
BEGIN
  OPEN curUser FOR SELECT * FROM PlatformUser u WHERE u.user_email = getPlatformUser.email; 
  RETURN curUser; 
  END;