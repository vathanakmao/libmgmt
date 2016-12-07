
create table member(
	id varchar(16) not null,
	first_name varchar(128) not null,
	last_name varchar(128) not null,
	sex enum('M', 'F') not null,
	date_created timestamp not null,
	date_updated timestamp not null,
	primary key (id)
) engine=innodb default charset=utf8;

create table book (
	id bigint not null,
	ddc varchar(45) not null,
	title varchar(255) not null,
	author varchar(128) not null,
	year_published smallint not null,
	primary key (id)
) engine=innodb default charset=utf8;

create table librarian (
	id int not null,
	first_name varchar(128) not null,
	last_name varchar(128) not null,
	sex enum('M', 'F') not null,
	date_created timestamp not null,
	date_updated timestamp not null,
	primary key (id)
) engine=innodb default charset=utf8;

create table borrow (
	member_id varchar(16) not null,
	book_id bigint not null,
	date_borrowed timestamp not null,
	date_returned timestamp,
	primary key (member_id, book_id, date_borrowed),
	foreign key (member_id) references member(id),
	foreign key (book_id) references book(id)
) engine=innodb default charset=utf8;

create table reserve (
	member_id bigint not null,
	book_id bigint not null,
	date_reserved timestamp not null,
	primary key (member_id, book_id, date_reserved),
	foreign key (member_id) references member(member_id),
	foreign key (book_id) references book(bookd_id)
) engine=innodb default charset=utf8;

create index idx_book_title on book(title);

