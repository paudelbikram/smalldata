SELECT datname FROM pg_database order by datname;

SELECT * FROM information_schema.tables;

SELECT * FROM information_schema.tables
where table_schema = 'public';


create table event_users
(
	id bigint generated always as identity,
	email varchar(256) not null,
	first_name varchar(256) not null,
	last_name varchar(256) not null,
	PRIMARY KEY (id)
);


SELECT
   table_name,
   column_name,
   data_type
FROM
   information_schema.columns
WHERE
   table_name = 'event_users';


INSERT INTO event_users (email, first_name, last_name)
VALUES ('ramu@techpasya.com', 'Ram', 'Bhandari');

INSERT INTO event_users (email, first_name, last_name)
VALUES ('hari@techpasya.com', 'Hari', 'Thapa'),
('shyam@techpasya.com', 'Shyam', 'Gurung'),
('rajesh@techpasya.com', 'Rajesh', 'Chaudhary');

Select * from event_users;



CREATE Table events
(
	id bigint generated always as identity,
	event_name varchar(256) not null,
	event_time timestamp not null,
	event_location varchar(2048) not null,
	primary key(id)

)
Select * from events;

INSERT INTO events (event_name, event_time, event_location)
VALUES ('Coffee With Friends', '2021-11-19 14:00:00', '1234 Main Street, Edmond, OK 73034');

INSERT INTO events (event_name, event_time, event_location)
VALUES ('Fundraising For Street Children', '2022-01-19 14:00:00', '24 greenroad Street, Edmond, OK 73034')
, ('Awareness Against Phone Scam', '2022-01-10 10:30:00', '34 greenleaf street, Edmond, OK 73034');

CREATE Table event_participants
(
	id bigint generated always as identity,
	event_id bigint not null,
	event_user_id bigint not null,
	primary key(id),
	constraint fk_event_users
		FOREIGN KEY(event_user_id)
			REFERENCES event_users (id),
	constraint fk_events
		FOREIGN KEY(event_id)
			REFERENCES events (id)
);


CREATE Index idx_event_participants_event_id
on event_participants (event_id);

CREATE Index idx_event_participants_event_user_id
on event_participants (event_user_id);

select * from event_users;

select * from event_participants ORDER BY event_id desc;
INSERT INTO event_participants (event_id, event_user_id)
values (1, 1), (2, 1), (3, 1), (2, 2), (3, 3), (1, 4), (2, 4), (3, 4);


-- Running Queries Now
SELECT event_id, COUNT(*) FROM event_participants
GROUP BY event_id
HAVING COUNT(*) < 3
ORDER BY Count(*) DESC LIMIT 10;

SELECT * FROM events;
-- Updating Row
UPDATE Events SET event_time = '2022-02-01 15:45:00'
WHERE id = 1;

SELECT * FROM event_participants;
-- Deleting Row
DELETE FROM events where id = 14;

