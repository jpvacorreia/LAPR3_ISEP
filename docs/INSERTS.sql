insert into usertype (designation) values ('client');
insert into usertype (designation) values ('admin');
insert into usertype (designation) values ('courier');

insert into state_order(state_order_designation) values ('Pending');
insert into state_order(state_order_designation) values ('Ready To Deliver');
insert into state_order(state_order_designation) values ('In Deliver');
insert into state_order(state_order_designation) values ('Delivered');

insert into postalcode (city, local) values (4000,476);
insert into postalcode (city, local) values (4000,271);

insert into address (street, coordinateX, coordinateY, postalcodeid_postal, elevation) values ('Rua a', 41.152701, -8.638248, 2, 87);
insert into platformuser (user_email,password,usertypeid_user_type) values ('user1@gmail.com', 'user1cli', 1);
insert into creditcard (number_card,CVV, validity_month, validity_year) values (1111111111111112, 724, 04, 2025);
insert into client (name_client,nif_client,credits,creditcardnumber,addresscoordinateX,addresscoordinateY,useruser_email) values ('user1', 264555443, 0, 1111111111111112, 41.152701, -8.638248, 'user1@gmail.com');

insert into address (street, coordinateX, coordinateY, postalcodeid_postal, elevation) values ('Rua b', 41.154723, -8.640657, 2, 91);
insert into platformuser (user_email,password,usertypeid_user_type) values ('user2@gmail.com', 'user2cli', 1);
insert into creditcard (number_card,CVV, validity_month, validity_year) values (1111111111111113, 324, 04, 2025);
insert into client (name_client,nif_client,credits,creditcardnumber,addresscoordinateX,addresscoordinateY,useruser_email) values ('user2', 264555442, 0, 1111111111111113, 41.154723, -8.640657, 'user2@gmail.com');

insert into address (street, coordinateX, coordinateY, postalcodeid_postal, elevation) values ('Rua c', 41.160871, -8.6280746, 2, 87);
insert into platformuser (user_email,password,usertypeid_user_type) values ('user3@gmail.com', 'user3cli', 1);
insert into creditcard (number_card,CVV, validity_month, validity_year) values (1111111111111114, 725, 04, 2025);
insert into client (name_client,nif_client,credits,creditcardnumber,addresscoordinateX,addresscoordinateY,useruser_email) values ('user3', 264555441, 0, 1111111111111114, 41.160871, -8.6280746, 'user3@gmail.com');

insert into address (street, coordinateX, coordinateY, postalcodeid_postal, elevation) values ('Rua d', 41.154331, -8.630914, 2, 82);
insert into platformuser (user_email,password,usertypeid_user_type) values ('user4@gmail.com', 'user4cli', 1);
insert into creditcard (number_card,CVV, validity_month, validity_year) values (1111111111111115, 784, 04, 2025);
insert into client (name_client,nif_client,credits,creditcardnumber,addresscoordinateX,addresscoordinateY,useruser_email) values ('user4', 264555440, 0, 1111111111111115, 41.154331, -8.630914, 'user4@gmail.com');

insert into address (street, coordinateX, coordinateY, postalcodeid_postal, elevation) values ('Rua e', 41.164063, -8.631118, 1, 87);
insert into platformuser (user_email,password,usertypeid_user_type) values ('user5@gmail.com', 'user5cli', 1);
insert into creditcard (number_card,CVV, validity_month, validity_year) values (1111111111111116, 794, 04, 2025);
insert into client (name_client,nif_client,credits,creditcardnumber,addresscoordinateX,addresscoordinateY,useruser_email) values ('user5', 264555447, 0, 1111111111111116, 41.164063, -8.631118, 'user5@gmail.com');
--
insert into address (street, coordinateX, coordinateY, postalcodeid_postal, elevation) values ('Rua f', 41.151000, -8.628600, 2, 87);
insert into platformuser (user_email,password,usertypeid_user_type) values ('user6@gmail.com', 'user6cli', 1);
insert into creditcard (number_card,CVV, validity_month, validity_year) values (1111111111111117, 704, 04, 2025);
insert into client (name_client,nif_client,credits,creditcardnumber,addresscoordinateX,addresscoordinateY,useruser_email) values ('user6', 264555448, 0, 1111111111111117, 41.151000, -8.628600, 'user6@gmail.com');
--
insert into product_type (name_type) values ('vaccine');
insert into product_type (name_type) values ('pill');
insert into product_type (name_type) values ('mask');

insert into postalcode (city, local) values (4000,114);

insert into platformuser (user_email,password,usertypeid_user_type) values ('admin1@gmail.com', 'adminid1', 2);
insert into administrator (name_administrator, useruser_email) values ('Admin1','admin1@gmail.com');

insert into address (street, coordinateX, coordinateY, postalcodeid_postal, elevation) values ('Castelo do Queijo, Porto', 41.158052, -8.629239, 3, 85);
insert into pharmacy (name_pharmacy,nif_pharmacy,administratorid_administrator, addresscoordinateX, addresscoordinateY, Park_input_voltage, park_input_current, max_number_scooter) values ('Farm�cia Queijo', 200200400, 1,  41.158052, -8.629239, 220, 16, 10);

insert into address (street, coordinateX, coordinateY, postalcodeid_postal, elevation) values ('Parque da Trindade, Porto', 41.188888, -8.709297, 3, 104);
insert into pharmacy (name_pharmacy,nif_pharmacy,administratorid_administrator, addresscoordinateX, addresscoordinateY, Park_input_voltage, park_input_current, max_number_scooter) values ('Farm�cia Trindade', 100200400, 1,  41.188888, -8.709297, 220, 16, 10);


insert into product (name, price, weigth, product_typeid_type_product) values ('COVID-19 vaccine 1uni', 50, 0.250, 1);
insert into product (name, price, weigth, product_typeid_type_product) values ('EBOLA vaccine 1uni', 100, 0.350, 1);
insert into product (name, price, weigth, product_typeid_type_product) values ('Brufen 600g 40uni', 4, 0.150, 2);
insert into product (name, price, weigth, product_typeid_type_product) values ('Aspirine 40uni', 3, 0.150, 2);
insert into product (name, price, weigth, product_typeid_type_product) values ('Washable Mask 2uni', 10, 0.200, 3);
insert into product (name, price, weigth, product_typeid_type_product) values ('Surgical Mask 20uni', 15, 0.300, 3);

insert into Stock (pharmacyid_pharmacy) values (1);
insert into Stock (pharmacyid_pharmacy) values (2);

insert into StockLine (stockpharmacyid_pharmacy,productid_product,quantity) values (1,1,10000);
insert into StockLine (stockpharmacyid_pharmacy,productid_product,quantity) values (1,2,15000);
insert into StockLine (stockpharmacyid_pharmacy,productid_product,quantity) values (1,3,5000);
insert into StockLine (stockpharmacyid_pharmacy,productid_product,quantity) values (1,4,30000);
insert into StockLine (stockpharmacyid_pharmacy,productid_product,quantity) values (1,5,1000);
insert into StockLine (stockpharmacyid_pharmacy,productid_product,quantity) values (1,6,5000);
insert into StockLine (stockpharmacyid_pharmacy,productid_product,quantity) values (2,1,1000);
insert into StockLine (stockpharmacyid_pharmacy,productid_product,quantity) values (2,2,5000);
insert into StockLine (stockpharmacyid_pharmacy,productid_product,quantity) values (2,3,55000);
insert into StockLine (stockpharmacyid_pharmacy,productid_product,quantity) values (2,4,300);
insert into StockLine (stockpharmacyid_pharmacy,productid_product,quantity) values (2,5,500);
insert into StockLine (stockpharmacyid_pharmacy,productid_product,quantity) values (2,6,7000);

insert into clientorder (clientid_client,order_date,state_orderid_state_order,pharmacyid_pharmacy) values (1,TO_DATE('2021/01/12 8:30:25', 'YYYY/MM/DD HH:MI:SS'),2,1);
insert into clientorder (clientid_client,order_date,state_orderid_state_order,pharmacyid_pharmacy) values (2,TO_DATE('2021/01/11 9:33:25', 'YYYY/MM/DD HH:MI:SS'),2,1);
insert into clientorder (clientid_client,order_date,state_orderid_state_order,pharmacyid_pharmacy) values (3,TO_DATE('2021/01/12 8:30:25', 'YYYY/MM/DD HH:MI:SS'),2,1);
insert into clientorder (clientid_client,order_date,state_orderid_state_order,pharmacyid_pharmacy) values (4,TO_DATE('2021/01/11 9:33:25', 'YYYY/MM/DD HH:MI:SS'),2,1);
insert into clientorder (clientid_client,order_date,state_orderid_state_order,pharmacyid_pharmacy) values (5,TO_DATE('2021/01/12 8:30:25', 'YYYY/MM/DD HH:MI:SS'),2,1);
insert into clientorder (clientid_client,order_date,state_orderid_state_order,pharmacyid_pharmacy) values (6,TO_DATE('2021/01/11 9:33:25', 'YYYY/MM/DD HH:MI:SS'),2,1);
-- clientid da order acima alterado de 6 para 5 pois não existe um client com id 6.
insert into clientorder (clientid_client,order_date,state_orderid_state_order,pharmacyid_pharmacy) values (1,TO_DATE('2021/01/12 8:30:25', 'YYYY/MM/DD HH:MI:SS'),2,1);
insert into clientorder (clientid_client,order_date,state_orderid_state_order,pharmacyid_pharmacy) values (2,TO_DATE('2021/01/11 9:33:25', 'YYYY/MM/DD HH:MI:SS'),2,1);

insert into orderline (orderid_order, productid_product,quantity) values (1,1,20);
insert into orderline (orderid_order, productid_product,quantity) values (1,3,4);
insert into orderline (orderid_order, productid_product,quantity) values (1,4,1);
insert into orderline (orderid_order, productid_product,quantity) values (2,1,15);
insert into orderline (orderid_order, productid_product,quantity) values (3,1,20);
insert into orderline (orderid_order, productid_product,quantity) values (3,3,4);
insert into orderline (orderid_order, productid_product,quantity) values (4,4,1);
insert into orderline (orderid_order, productid_product,quantity) values (4,1,15);
insert into orderline (orderid_order, productid_product,quantity) values (5,1,20);
insert into orderline (orderid_order, productid_product,quantity) values (6,3,4);
insert into orderline (orderid_order, productid_product,quantity) values (7,4,1);
insert into orderline (orderid_order, productid_product,quantity) values (8,1,15);
--última orderLine alterada de clientid 8 para 7

insert into state_of_use_type(name_state_of_use) values ('Available');
insert into state_of_use_type(name_state_of_use) values ('Occupied');

insert into Scooter(id_scooter,max_battery_capacity,state_of_charge,poweroutput,weight,max_payload,pharmacyid_pharmacy) values (1,3000,100,25,50,20,1);
insert into Scooter(id_scooter,max_battery_capacity,state_of_charge,poweroutput,weight,max_payload,pharmacyid_pharmacy) values (2,3000,100,25,50,20,1);
insert into Scooter(id_scooter,max_battery_capacity,state_of_charge,poweroutput,weight,max_payload,pharmacyid_pharmacy) values (3,3000,100,25,50,20,1);
insert into Scooter(id_scooter,max_battery_capacity,state_of_charge,poweroutput,weight,max_payload,pharmacyid_pharmacy) values (4,3000,100,25,50,20,1);
insert into Scooter(id_scooter,max_battery_capacity,state_of_charge,poweroutput,weight,max_payload,pharmacyid_pharmacy) values (5,3000,100,25,50,20,2);
insert into Scooter(id_scooter,max_battery_capacity,state_of_charge,poweroutput,weight,max_payload,pharmacyid_pharmacy) values (6,3000,100,25,50,20,2);
insert into Scooter(id_scooter,max_battery_capacity,state_of_charge,poweroutput,weight,max_payload,pharmacyid_pharmacy) values (7,3000,100,25,50,20,2);

insert into Drone(id_drone,max_battery_capacity,state_of_charge,poweroutput,weight,max_payload,pharmacyid_pharmacy) values (1,500,100,25,5,5,1);
insert into Drone(id_drone,max_battery_capacity,state_of_charge,poweroutput,weight,max_payload,pharmacyid_pharmacy) values (2,500,100,25,5,5,1);
insert into Drone(id_drone,max_battery_capacity,state_of_charge,poweroutput,weight,max_payload,pharmacyid_pharmacy) values (3,500,100,25,5,5,2);
insert into Drone(id_drone,max_battery_capacity,state_of_charge,poweroutput,weight,max_payload,pharmacyid_pharmacy) values (4,500,100,25,5,5,2);

insert into platformuser (user_email,password,usertypeid_user_type) values ('courier1@gmail.com', 'courier1', 3);
insert into courier (name_courier,niss,nif_courier,weight,scooterid_scooter,pharmacyid_pharmacy,useruser_email) values ('courier1',1111,555555551,75,1,1,'courier1@gmail.com');
insert into platformuser (user_email,password,usertypeid_user_type) values ('courier2@gmail.com', 'courier2', 3);
insert into courier (name_courier,niss,nif_courier,weight,scooterid_scooter,pharmacyid_pharmacy,useruser_email) values ('courier2',2222,555555552,70,2,1,'courier2@gmail.com');
insert into platformuser (user_email,password,usertypeid_user_type) values ('courier3@gmail.com', 'courier3', 3);
insert into courier (name_courier,niss,nif_courier,weight,scooterid_scooter,pharmacyid_pharmacy,useruser_email) values ('courier3',3333,555555553,72,3,1,'courier3@gmail.com');
insert into platformuser (user_email,password,usertypeid_user_type) values ('courier4@gmail.com', 'courier4', 3);
insert into courier (name_courier,niss,nif_courier,weight,scooterid_scooter,pharmacyid_pharmacy,useruser_email) values ('courier4',4444,555555554,75,4,1,'courier4@gmail.com');
insert into platformuser (user_email,password,usertypeid_user_type) values ('courier5@gmail.com', 'courier5', 3);
insert into courier (name_courier,niss,nif_courier,weight,scooterid_scooter,pharmacyid_pharmacy,useruser_email) values ('courier5',5555,555555555,75,5,2,'courier5@gmail.com');
insert into platformuser (user_email,password,usertypeid_user_type) values ('courier6@gmail.com', 'courier6', 3);
insert into courier (name_courier,niss,nif_courier,weight,scooterid_scooter,pharmacyid_pharmacy,useruser_email) values ('courier6',6666,555555556,82,6,2,'courier6@gmail.com');
insert into platformuser (user_email,password,usertypeid_user_type) values ('courier7@gmail.com', 'courier7', 3);
insert into courier (name_courier,niss,nif_courier,weight,scooterid_scooter,pharmacyid_pharmacy,useruser_email) values ('courier7',7777,555555557,85,7,2,'courier7@gmail.com');

insert into deliveryrun (courierid_courier,delivery_run_date) values (1,TO_DATE('2020/12/31 9:30:00', 'YYYY/MM/DD HH:MI:SS'));
insert into deliveryrunline (clientorderid_order, deliveryrunid_delivery_run) values (7,1);
insert into deliveryrunline (clientorderid_order, deliveryrunid_delivery_run) values (8,1);


insert into deliveryrun (courierid_courier,delivery_run_date) values (2,TO_DATE('2020/12/31 9:40:00', 'YYYY/MM/DD HH:MI:SS'));
insert into deliveryrunline (clientorderid_order, deliveryrunid_delivery_run) values (7,2);
insert into deliveryrunline (clientorderid_order, deliveryrunid_delivery_run) values (8,2);

insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);

insert into parkingslot (pharmacyid_pharmacy) values (2);
insert into parkingslot (pharmacyid_pharmacy) values (2);
insert into parkingslot (pharmacyid_pharmacy) values (2);
insert into parkingslot (pharmacyid_pharmacy) values (2);
insert into parkingslot (pharmacyid_pharmacy) values (2);
insert into parkingslot (pharmacyid_pharmacy) values (2);
insert into parkingslot (pharmacyid_pharmacy) values (2);
insert into parkingslot (pharmacyid_pharmacy) values (2);
insert into parkingslot (pharmacyid_pharmacy) values (2);
insert into parkingslot (pharmacyid_pharmacy) values (2);

insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,1);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (0,2,2);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,3);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (0,1,4);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,5);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (0,1,6);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,7);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (0,2,8);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,9);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,10);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,11);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,12);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,13);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,14);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,15);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,16);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,17);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,18);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,19);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,20);

insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,21);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (0,1,22);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,23);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (0,1,24);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,25);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (0,1,26);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,27);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (0,2,28);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,29);
insert into parkingslotsscooters (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (0,1,30);

insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);
insert into parkingslot (pharmacyid_pharmacy) values (1);

insert into parkingslot (pharmacyid_pharmacy) values (2);
insert into parkingslot (pharmacyid_pharmacy) values (2);
insert into parkingslot (pharmacyid_pharmacy) values (2);
insert into parkingslot (pharmacyid_pharmacy) values (2);
insert into parkingslot (pharmacyid_pharmacy) values (2);

insert into parkingslotdrones (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,31);
insert into parkingslotdrones (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,32);
insert into parkingslotdrones (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,33);
insert into parkingslotdrones (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,34);
insert into parkingslotdrones (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,35);
insert into parkingslotdrones (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,36);
insert into parkingslotdrones (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,37);
insert into parkingslotdrones (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,38);
insert into parkingslotdrones (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (1,1,39);
insert into parkingslotdrones (has_charging_capabilities,state_of_use_typeid_state_of_use,parkingslotid_parkingslot) values (0,1,40);
/
commit;





-- Correr 5 vezes no m�nimo!
DELETE FROM usertype;
DELETE FROM address;
DELETE FROM administrator;
DELETE FROM client;
DELETE FROM clientorder;
DELETE FROM courier;
DELETE FROM courierscooter;
DELETE FROM creditcard;
DELETE FROM deliveryrun;
DELETE FROM deliveryrunline;
DELETE FROM deliveryrunregistry;
DELETE FROM drone;
DELETE FROM invoice;
DELETE FROM invoiceline;
DELETE FROM orderline;
DELETE FROM parkingslot;
DELETE FROM parkingslotdrones;
DELETE FROM parkingslotdronesusage;
DELETE FROM parkingslotscootersusage;
DELETE FROM parkingslotsscooters;
DELETE FROM pharmacy;
DELETE FROM platformuser;
DELETE FROM postalcode;
DELETE FROM product;
DELETE FROM product_type;
DELETE FROM scooter;
DELETE FROM sailors;
DELETE FROM state_of_use_type;
DELETE FROM state_order;
DELETE FROM stock;
DELETE FROM stockline;
