create table config(
	id int(10),
	cron varchar(40),
	primary key(id)
);

insert into config(id,cron) values(1,'0 0/2 * * * ?'); //每隔2分钟执行一次