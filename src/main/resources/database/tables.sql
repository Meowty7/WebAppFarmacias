-- we don't know how to generate root <with-no-name> (class Root) :(
create table farmacias
(
    cod_farmacia INT          not null
        constraint farmacias_cod_sucursal_pk
            primary key,
    nombre       varchar(100) not null,
    longitud     decimal(9, 6),
    latitud      decimal(8, 6)
);

create table medicamentos
(
    id_medicamento INT not null
        constraint PK_medicamentos2
            primary key,
    nom_generico   varchar(200),
    nom_comercial  varchar(100),
    precio_m       decimal(15, 2)
);

create table inventario
(
    id_medicamento INT not null
        constraint inventario_medicamentos_id_medicamento_fk
            references medicamentos
            on update cascade on delete cascade,
    cod_farmacia   INT not null
        constraint inventario_farmacias_cod_sucursal_fk
            references farmacias
            on update cascade on delete cascade,
    cantidad       INT default 0,
    constraint inventario_pk
        primary key (id_medicamento, cod_farmacia)
);

