CREATE OR REPLACE PROCEDURE addInvoiceLine(idInvoice NUMBER, idProduct NUMBER, idOrder NUMBER)
AS
    BEGIN
        INSERT INTO INVOICELINE (INVOICEID_INVOICE, ORDERLINEPRODUCTID_PRODUCT, ORDERLINEORDERID_ORDER) VALUES(idInvoice, idProduct, idOrder);
    end;
/

CREATE OR REPLACE FUNCTION getInvoiceLinesFromInvoiceID(invoiceID NUMBER)
RETURN SYS_REFCURSOR
AS
    invLine SYS_REFCURSOR;
    BEGIN
        OPEN invLine FOR SELECT * FROM INVOICELINE WHERE INVOICEID_INVOICE = invoiceID;
        RETURN invLine;
    end;
    /