---- Creación Procedimiento Almacenado ----

CREATE OR REPLACE PROCEDURE take_random_pizza_order(
    IN id_customer VARCHAR(15),
    IN method CHAR(1),
    OUT order_taken BOOLEAN
)
LANGUAGE plpgsql
AS $$
DECLARE
id_random_pizza INT;
    price_random_pizza DECIMAL(5,2);
    price_with_discount DECIMAL(5,2);
    with_errors BOOLEAN DEFAULT FALSE;
    last_order_id INT;
    new_id_item INT;
BEGIN
    BEGIN
        SELECT id_pizza, price
        INTO id_random_pizza, price_random_pizza
        FROM pizza
        WHERE available
        ORDER BY RANDOM()
        LIMIT 1;

        -- Verificar que price_random_pizza no sea NULL
        IF price_random_pizza IS NULL THEN
                    RAISE EXCEPTION 'No se encontró una pizza disponible';
        END IF;

        price_with_discount := price_random_pizza - (price_random_pizza * 0.20);

        -- Verificar que price_with_discount no sea NULL
        IF price_with_discount IS NULL THEN
            RAISE EXCEPTION 'price_with_discount no puede ser NULL';
        END IF;

        RAISE NOTICE 'Insertando orden con descuento: %', price_with_discount;

        BEGIN
            -- Insertar la orden principal en pizza_order
            INSERT INTO pizza_order (id_customer, date, total, method, additional_notes)
            VALUES (id_customer, CURRENT_DATE, price_with_discount, method, '20% OFF PIZZA RANDOM PROMOTION')
                RETURNING id_order INTO last_order_id;

            RAISE NOTICE 'Último ID insertado: %', last_order_id;

            -- Generar un nuevo id_item único usando la secuencia
            new_id_item := nextval('order_item_id_item_seq');

            -- Insertar el item en order_item con el nuevo id_item
            INSERT INTO order_item (id_item, id_order, id_pizza, quantity, price)
            VALUES (new_id_item, last_order_id, id_random_pizza, 1, price_random_pizza);

        EXCEPTION WHEN OTHERS THEN
                    RAISE NOTICE 'Error en la inserción de la orden o items: %', SQLERRM;
                    with_errors := TRUE;
        END;

        IF with_errors THEN
            order_taken := FALSE;
        ELSE
            order_taken := TRUE;
        END IF;

    EXCEPTION WHEN OTHERS THEN
            RAISE NOTICE 'Error en la ejecución del procedimiento: %', SQLERRM;
            with_errors := TRUE;
    END;

    IF with_errors THEN
            order_taken := FALSE;
    END IF;
END;
$$;

---- Ejecutar Procedimiento Almacenado ----

DO $$
DECLARE
    result BOOLEAN;
BEGIN
    CALL take_random_pizza_order('863264988', 'D', result);
    RAISE NOTICE 'Resultado (order_taken): %', result;
END;
$$;