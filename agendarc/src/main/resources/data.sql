INSERT INTO role (name) VALUES
  ('ROLE_ADMIN_1'),
  ('ROLE_ADMIN_2');

INSERT INTO user (name,pwd,surname,username) VALUES
  ('dave','$2a$10$YTv.aGJ4D0nxUVV8XKkR4.p1x73.CXn6QhWwfyu1sGOGpDZ9Mytrq','silva','dave'),
  ('nino','$2a$10$YTv.aGJ4D0nxUVV8XKkR4.p1x73.CXn6QhWwfyu1sGOGpDZ9Mytrq','nino','nino');

INSERT INTO user_roles (users_id,roles_id) VALUES
  (1,1),
  (1,2);

INSERT INTO calendar (name,role_name,owner_id) VALUES
  ('Euro 2020','ROLE_ADMIN_1',1),
  ('JO Tokyo','ROLE_ADMIN_2',1);


INSERT INTO event (title, calendar_id,creator_id, description,start,end) VALUES
  ('Suisse - Italie',1,1,'Quart de finale de l euro','2020-04-19T17:29:00','2020-04-19T19:29:00'),
  ('Phase de poule',1,1,'Phase de poule de l euro','2020-04-10T00:00:00','2020-04-15T00:00:00');
