drop table Person;
drop table wage;
drop table Attend;
drop table man;


create table Person
(
  EmployeeID int not null primary key,
  Name varchar(50) not null,
  Sex varchar(50) not null,
  Date varchar(50) not null,
  City varchar(50) not null,
  Nation varchar(50) not null,
  Polity varchar(50) null,
  Culture varchar(50) null,
  Marriage varchar(50) null,
  Graduate varchar(50) null,
  Spec varchar(50) null,
  Speci varchar(50) null,
  Wtype varchar(50) null,
  Duty varchar(50) null,
  Depart varchar(50) null,
  IDcard varchar(50) null,
  Address varchar(50) null,
  Postcode varchar(50) null,
  HomePhone varchar(50) null,
  Mobile varchar(50) null,
  Email varchar(50) null,
  Resume text null,
  Photo BLOB null
);

insert into person values(1,'����','��','1981.1','�ӱ���ɽ','��','Ⱥ��','����','δ��','�ӱ�����ѧ','�����','','��','����','����Ա','�»�����46��','300006','0315-2592920','13633665689','zsanyi@163.com','130256198101010130','','');
insert into person values(2,'����','��','1981.1','�ӱ���ɽ','��','Ⱥ��','����','δ��','�ӱ�����ѧ','�����','','��','����','����Ա','�»�����46��','300006','0315-2592920','13633665689','zsanyi@163.com','130256198101010130','','');
insert into person values(3,'����','Ů','1981.1','�ӱ���ɽ','��','Ⱥ��','����','δ��','�ӱ�����ѧ','�����','','��','����','����Ա','�»�����46��','300006','0315-2592912','13635565689','lsdiyi@163.com','130254198101010530','','');
insert into person values(4,'����','��','1981.1','�ӱ���ɽ','��','Ⱥ��','����','�ѻ�','�ӱ�����ѧ','�����','','��','����','����Ա','�»�����46��','300006','0315-2592920','13633665689','zsanyi@163.com','130256198101010130','','');



create table wage
(
  EmployeeID int not null primary key,
  Name varchar(50) not null,
  Base_pay int null,
  Baseprize int null,
  Benifitprize int null,
  Insurance int null,
  Medicare int null,
  Deprivepay int null,
  Depriveprize int null,
  Depriveattend int null
);
insert into wage values(1,'����',1500,1050,600,500,400,200,200,100);
insert into wage values(2,'����',1500,1050,600,500,400,200,200,100);
insert into wage values(3,'����',1500,1050,600,500,400,200,200,100);
insert into wage values(4,'����',1500,1050,600,500,400,200,200,100);



create table Attend
(
  EmployeeID int not null,
  Name varchar(50) not null,
  Time varchar(50) not null,
  Late int null,
  Leaveearly int null,
  Jobwound int null,
  S_leave int null,
  Pa_leave int null
);
insert into Attend values(1,'����','2002.1',0,1,1,1,0);
insert into Attend values(2,'����','2002.1',0,1,1,1,0);
insert into Attend values(3,'����','2002.1',0,1,1,1,0);
insert into Attend values(4,'����','2002.1',0,1,1,1,0);




create table man
(
 mgNo varchar(50),
 password varchar(50)
);
insert into man values('wyf','hxl');












