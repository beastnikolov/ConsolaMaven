create table idioma(
idi_id int auto_increment PRIMARY KEY,
idi_cod varchar(3) not null ,
UNIQUE (idi_cod)
);


create table literal(
	lit_id int auto_increment PRIMARY KEY,
	idi_cod varchar(3) not null, 
	lit_clau varchar(30) not null, 
	lit_text varchar(300) not null,
	UNIQUE (lit_clau,idi_cod),
    FOREIGN KEY (idi_cod) REFERENCES idioma (idi_cod) ON UPDATE RESTRICT ON DELETE CASCADE
);

SELECT * from idioma;
SELECT * from literal;

SELECT lit_text FROM literal WHERE lit_clau = 'DIR_NOTFOUND' AND idi_cod = 'ESP';

SET GLOBAL time_zone ="+01:00";

DELETE from literal where lit_clau = "DIRECTORINOTROBAT";
INSERT INTO idioma (idi_cod) VALUES("ESP");
INSERT INTO idioma (idi_cod) VALUES("ENG");

INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ESP","DIR_NOTFOUND","Directorio no encontrado");
INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ENG","DIR_NOTFOUND","Directory not found");

INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ESP","LASTDIR_NOTFOUND","El ultimo directorio visitado no se puede encontrar");
INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ENG","LASTDIR_NOTFOUND","The last visited directory could not be found");

INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ESP","HISTORY_NOTFOUND","No existe ningún historial de navegación");
INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ENG","HISTORY_NOTFOUND","No browsing history was found");

INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ESP","DIR_EMPTY","Directorio sin elementos");
INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ENG","DIR_EMPTY","Directory is empty");

INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ESP","PARENT_NOTFOUND","Directorio padre no encontrado");
INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ENG","PARENT_NOTFOUND","Parent directory not found");

INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ESP","DIR","Directorio");
INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ENG","DIR","Directory");

INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ESP","CREATED_SUCCESS","creado correctamente");
INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ENG","CREATED_SUCCESS","created successfully");

INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ESP","ALREADY_EXISTS","ya existe");
INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ENG","ALREADY_EXISTS","already exists");

INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ESP","FILE","Archivo");
INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ENG","FILE","File");

INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ESP","DEL_SUCCESS","eliminado correctamente");
INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ENG","DEL_SUCCESS","deleted successfully");

INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ESP","NO_EXIST","no existe");
INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ENG","NO_EXIST","doesn't exist");

INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ESP","LOG_ON","Logs activados");
INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ENG","LOG_ON","Logs activated");

INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ESP","LOG_OFF","Logs desactivados");
INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ENG","LOG_OFF","Logs are deactivated");

INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ESP","FILE_EMPTY","Fichero vacio");
INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ENG","FILE_EMPTY","File is empty");

INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ESP","FILE_EXISTS","ERROR: El archivo ya existe");
INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ENG","FILE_EXISTS","ERROR: File already exists");

INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ESP","EXIT","Saliendo de la consola");
INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ENG","EXIT","Logging off from console");

INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ESP","LOG_CLEANED","El log se ha limpiado correctamente");
INSERT INTO literal (idi_cod,lit_clau,lit_text) VALUES ("ENG","LOG_CLEANED","Log has been cleared successfully");


create table log(
	log_id int auto_increment PRIMARY KEY,
	log_texte varchar(200) not null,
	log_data DATETIME DEFAULT CURRENT_TIMESTAMP
);

SELECT * from log;

DELETE FROM  log;

INSERT into log (log_texte) VALUES ('Sudando voy');

