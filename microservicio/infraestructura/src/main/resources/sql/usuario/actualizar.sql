update usuarios
set nombre = :nombre,
	clave = :clave,
	fecha_creacion = :fechaCreacion
where id = :id