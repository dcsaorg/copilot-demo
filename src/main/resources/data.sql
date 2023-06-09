-- generate 5 random users for user table

INSERT INTO "user" ("id", email, "password", first_name, last_name, created_at, updated_at) 
SELECT uuid_generate_v4(), 
       'user' || i || '@example.com', 
       'password' || i || '@', 
       'first_name' || i, 
       'last_name' || i, 
       now(), 
       now()
FROM generate_series(1, 5) AS t(i);


