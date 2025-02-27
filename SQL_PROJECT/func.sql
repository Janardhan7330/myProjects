# AGGRIGATE FUNCTIONS

select count(order_id) from order_details;
select count(order_id) as COUNT_ORDERID from order_details;
select sum(order_id+ORDER_QTY) as ord_id_add_qyt from order_details where order_id=201;
select max(order_qty)MAXI from order_details;
select min(order_id)AS MINS from order_details;
select avg(total_amount) AS AVT_TOTAL from order_details;

# SCALAR FUNCTIONS

select ucase(customer_name) upcase from customer;
select lcase(customer_name) as lowercase from customer;
select mid(customer_name,3,9) from customer;
select format(customer_id,2)as formatt from payment;
select round(avg(order_qty))as avg_order_qty from orders;  


alter table customer add order_date date unique check(order_date>20210801);

update customer set order_date=20210802 where customer_id=102;
alter table customer drop order_date;
alter table customer add order_date date default(20210801);

# ORDER BY & GROUP BY
select customer_name,customer_id from customer order by customer_id asc;
select customer_name,customer_id from customer order by customer_id desc;
select count(order_qty),order_qty from order_details group by order_qty;






