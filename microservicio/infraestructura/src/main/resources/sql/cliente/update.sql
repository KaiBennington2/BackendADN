update clientes
set nit = :nitCustomer,
	razon_social = :companyName,
	nombre_representante = :representantName,
	telefono = :phone,
	direccion = :adress,
	fecha_modificacion = :modifiedDate
where id = :id;