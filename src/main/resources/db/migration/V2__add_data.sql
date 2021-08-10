insert into first(id,first_field) values
(1001,'first1'),
(1002,'first2'),
(1003,'first3');

insert into second(id,second_field,fk_first,fk_second) values
(2001,'second01',null,null),
(2002,'second02',null,null),

(2003,'second03',null,2001),
(2004,'second04',null,2002),

(2101,'second11',1001,2003),
(2102,'second12',1001,2004),

(2201,'second21',1002,2003),
(2202,'second22',1002,2004),

(2301,'second31',1003,2003),
(2302,'second32',1003,2004);

insert into third(id,third_field,fk_first,fk_second) values
(3111,'third111',1001,2101),
(3112,'third112',1001,2101),
(3121,'third121',1001,2102),
(3122,'third122',1001,2102),

(3211,'third211',1002,2201),
(3212,'third212',1002,2201),
(3221,'third221',1002,2202),
(3222,'third222',1002,2202),

(3311,'third311',1003,2301),
(3312,'third312',1003,2301),
(3321,'third321',1003,2302),
(3322,'third322',1003,2302),

(3001,'third001',null,2001),
(3002,'third002',null,2002);