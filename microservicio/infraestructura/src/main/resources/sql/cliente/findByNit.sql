select
    id,
    nit,
    razon_social,
    nombre_representante,
    telefono,
    direccion
from clientes
 where nit = :nit;