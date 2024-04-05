INSERT INTO users (id, email, first_name, last_name, password, phone_number, points, username)
VALUES
    (1, 'admin@test.com', 'Admin', 'Adminov', 'a88d78aa482512691254a2177fc435ac9d6f6a62a7d12da7fcf2560f9333c5a36c859249a9fd5ea3a551b9f0cc3430af', '0899445566', 0, 'admin4eto'),
    (2, 'user@test.com', 'User', 'Userov', '65596bc1a3e2e3060b10064988170d2733b914e0d1bfcacaa46f5584a3489eee71d0aeaa383817575431e81a5308a234', '0899443322', 0, 'user4eto');


INSERT INTO roles (`id`, `role`)
VALUES
    (1, 'ADMIN'),
    (2, 'USER');

INSERT INTO users_roles(`user_id`, `role_id`)
VALUES
    (1, 1),
    (1, 2),
    (2, 2);
