INSERT INTO role (name) VALUES
  ('ROLE_ADMIN_1'),
  ('ROLE_ADMIN_2');

INSERT INTO user (name,pwd,surname,username) VALUES
  ('dave','$2a$10$YTv.aGJ4D0nxUVV8XKkR4.p1x73.CXn6QhWwfyu1sGOGpDZ9Mytrq','silva','dave'),
  ('nino','$2a$10$YTv.aGJ4D0nxUVV8XKkR4.p1x73.CXn6QhWwfyu1sGOGpDZ9Mytrq','nino','nino');

INSERT INTO user_roles (users_id,roles_id) VALUES
  (1,1),
  (2,2);

INSERT INTO calendar (name,role_name,owner_id) VALUES
  ('Euro 2020','ROLE_ADMIN_1',1),
  ('Wimbledon 2020','ROLE_ADMIN_1',1),
  ('JO Tokyo 2020','ROLE_ADMIN_2',2),
  ('Championnat NBA','ROLE_ADMIN_2',2);


INSERT INTO event (title, calendar_id,creator_id, description,start,end) VALUES
  ('Suisse - Italie',1,1,'Quart de finale de l euro','2020-04-19T17:29:00','2020-04-19T19:29:00'),
  ('Phase de poule',1,1,'Phase de poule de l euro','2020-04-10T00:00:00','2020-04-15T00:00:00'),

  ('Roger Federer - Lucas Pouille',2,1,'Premier tour du tournoi','2020-04-25T17:29:00','2020-04-25T19:29:00'),
  ('Nadal - Benoît Paire',2,1,'Premier tour du tournoi','2020-04-25T00:00:00','2020-04-25T00:00:00'),

  ('100 m haies',3,2,'Premier tour','2020-04-23T17:29:00','2020-04-23T19:29:00'),
  ('Lancer du javelot',3,2,'Deuxième tout','2020-04-24T00:00:00','2020-04-24T00:00:00'),

  ('Première phase',4,2,'Première phase du championnat NBA 2019/20','2020-04-01T17:29:00','2020-04-30T19:29:00'),
  ('Phase finale',4,2,'Phase final de playoff du championnat NBA 2019/20','2020-05-01T00:00:00','2020-05-30T00:00:00');
