--Popula tabela Salesman
INSERT INTO salesman (full_name) VALUES ('Rafael'), ('Fernando'), ('Vinicius'), ('Alan'), ('Kleber'), ('Romário'), ('Ronaldo');
--Popula
INSERT INTO Product (product_name, volume, minimum_temperature, max_temperature, category, salesman_id) VALUES ('Banana', 10, 0, 15, 'FRESH', 1),
                                                                                                            ('Maca', 15, 0, 15,'FRESH',1 ),
                                                                                                            ('Laranja', 25, 10, 20,'FRESH',1 ),
                                                                                                            ('Melancia', 50, 15, 25,'FRESH',1 ),
                                                                                                            ('Carne', 25, -15, 5,'FROZEN_FOOD',2 ),
                                                                                                            ('Pizza', 45, -10, 0,'FROZEN_FOOD',2 ),
                                                                                                            ('Filé de Peito', 30, -10, 5,'FROZEN_FOOD',2 ),
                                                                                                            ('Filé Mignon', 30, -20, 5,'FROZEN_FOOD',2 ),
                                                                                                            ('Iogurte', 6, 7, 15,'REFRIGERATED',3),
                                                                                                            ('Requeijão', 10, 7, 15,'REFRIGERATED',3),
                                                                                                            ('Danone', 3, 5, 15,'REFRIGERATED',3),
                                                                                                            ('Refrigerante', 3, 5, 15,'REFRIGERATED',3),
                                                                                                            ('Banana', 10, 0, 15, 'FRESH', 4),
                                                                                                            ('Maca', 15, 0, 15,'FRESH',4 ),
                                                                                                            ('Laranja', 25, 10, 20,'FRESH',4 ),
                                                                                                            ('Melancia', 50, 15, 25,'FRESH',4 ),
                                                                                                            ('Carne', 25, -15, 5,'FROZEN_FOOD',5 ),
                                                                                                            ('Pizza', 45, -10, 0,'FROZEN_FOOD',5 ),
                                                                                                            ('Filé de Peito', 30, -10, 5,'FROZEN_FOOD',5 ),
                                                                                                            ('Filé Mignon', 30, -20, 5,'FROZEN_FOOD',5 ),
                                                                                                            ('Iogurte', 6, 7, 15,'REFRIGERATED',6),
                                                                                                            ('Requeijão', 10, 7, 15,'REFRIGERATED',6),
                                                                                                            ('Danone', 3, 5, 15,'REFRIGERATED',6),
                                                                                                            ('Refrigerante', 10, 5, 15,'REFRIGERATED',6),
                                                                                                            ('Mozzarela', 100, 10, 15,'FRESH',7),
                                                                                                            ('Presunto', 100, 10, 15,'FRESH',7),
                                                                                                            ('Peito de Peru', 100, 10, 15,'FRESH',7),
                                                                                                            ('Mortadela', 100, 10, 15,'FRESH',7);

INSERT INTO batch_stock (initial_quantity, current_quantity, price, due_date, manufacturing_date, manufacturing_time,
                         product_id)
VALUES ( 400, 400, 10, '2022/01/15','2021/01/10' ,'1999-01-08 23:00:00', 1),
       (1000, 1000, 20, '2022/01/16', '2022/01/13' ,'1999-01-08 14:30:00', 3),
       (2000, 2000, 30, '2022/05/15', '2022/05/10', '1999-01-08 14:30:00', 2),
       (250, 250, 40, '2022/05/21', '2022/05/10', '1999-01-08 17:45:00', 5),
       (750, 250, 50, '2022/07/03', '2022/07/01', '1999-01-08 14:35:00', 7);
