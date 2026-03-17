INSERT INTO rol (rol) VALUES
    ('ADMINISTRADOR'),
    ('EMPLEADO');

INSERT INTO usuario (nombre, apellido, email, password, activo, rol_id) VALUES
    ('Juan', 'Perez', 'juan@gmail.com', '123456', TRUE, 1),
    ('Maria', 'Lopez', 'maria@gmail.com', '123456', TRUE, 2),
    ('Carlos', 'Gomez', 'carlos@gmail.com', '123456', TRUE, 2);

INSERT INTO bodega (nombre, ubicacion, capacidad, activo, encargado_id) VALUES
    ('Bodega Central', 'Bogotá', 1000, TRUE, 1),
    ('Bodega Norte', 'Medellín', 800, TRUE, 2);

INSERT INTO producto (nombre, descripcion, precio, stock, categoria) VALUES
    ('Laptop', 'Laptop HP', 2500.00, 50, 'Tecnología'),
    ('Mouse', 'Mouse inalámbrico', 50.00, 200, 'Accesorios'),
    ('Teclado', 'Teclado mecánico', 120.00, 150, 'Accesorios');

INSERT INTO inventario (stock, bodega_id, producto_id) VALUES
    (30, 1, 1),
    (100, 1, 2),
    (70, 2, 3);

INSERT INTO movimientos (tipo, fecha, estado, usuario_id, bodega_origen_id, bodega_destino_id) VALUES
    ('ENTRADA', NOW(), 'COMPLETADO', 1, NULL, 1),
    ('SALIDA', NOW(), 'COMPLETADO', 2, 1, NULL),
    ('TRANSFERENCIA', NOW(), 'PENDIENTE', 3, 1, 2);

INSERT INTO detalle_movimiento (cantidad, precio_unitario, movimiento_id, producto_id) VALUES
    (10, 2500.00, 1, 1),
    (5, 50.00, 2, 2),
    (20, 120.00, 3, 3);

INSERT INTO auditoria (operacion, entidad, valores_anteriores, valores_nuevos, origen, fecha_hora, usuario_id) VALUES
    ('INSERTAR', 'producto', 'N/A', 'Laptop creada', 'API', NOW(), 1),
    ('ACTUALIZAR', 'inventario', 'stock: 20', 'stock: 30', 'WEB', NOW(), 2),
    ('ELIMINAR', 'usuario', 'Carlos Gomez', 'N/A', 'ADMIN', NOW(), 1);