
create table member(
	id varchar(16) not null,
	first_name varchar(128) not null,
	last_name varchar(128) not null,
	sex enum('M', 'F') not null,
	address varchar(255) not null,
	salt varchar(64) not null,
	password varchar(255) not null,
	date_created timestamp not null,
	date_updated timestamp not null,
	primary key (id)
) engine=innodb default charset=utf8;

create table book (
	id bigint not null,
	code varchar(45) not null,
	title varchar(255) not null,
	author varchar(128) not null,
	year smallint not null,
	stock smallint not null,
	primary key (id)
) engine=innodb default charset=utf8;

create table librarian (
	id int not null auto_increment,
	username varchar(128) not null,
	first_name varchar(128) not null,
	last_name varchar(128) not null,
	sex enum('M', 'F') not null,
	salt varchar(128) not null, 
	password varchar(255) not null,
	date_created timestamp not null,
	date_updated timestamp not null,
	primary key (id)
) engine=innodb default charset=utf8;

create table borrow (
	member_id varchar(16) not null,
	book_id bigint not null,
	librarian_id int not null,
	date_borrowed timestamp not null,
	date_returned timestamp,
	primary key (member_id, book_id, librarian_id, date_borrowed),
	foreign key (member_id) references member(id),
	foreign key (book_id) references book(id),
	foreign key (librarian_id) references librarian(id)
) engine=innodb default charset=utf8;

create table reserve (
	member_id varchar(16) not null,
	book_id bigint not null,
	librarian_id int not null,
	date_reserved timestamp not null,
	primary key (member_id, book_id, librarian_id, date_reserved),
	foreign key (member_id) references member(id),
	foreign key (book_id) references book(id),
	foreign key (librarian_id) references librarian(id)
) engine=innodb default charset=utf8;

create index idx_book_title on book(title);

