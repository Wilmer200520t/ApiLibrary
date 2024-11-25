ALTER TABLE medico ADD active tinyint default 1;
UPDATE medico SET active = 1;