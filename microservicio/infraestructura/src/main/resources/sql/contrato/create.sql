insert into contratos
(nit_cliente, duracion_contrato_meses, tipo_moneda, trm_aplicada, paquete, fecha_instalacion, fecha_corte)
values (:cliente.nit, :tiempoContratoMeses, :tipoMoneda, :trmAplicada, :paquete.nombre, :fechaInstalacion, :fechaCorte);