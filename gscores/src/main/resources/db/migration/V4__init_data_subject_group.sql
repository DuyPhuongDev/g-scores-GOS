INSERT INTO subjects (id, name) VALUES
                                (1,'Toán'), (2, 'Ngữ văn'), (3, 'Ngoại ngữ'),
                                (4, 'Vật lý'), (5, 'Hoá học'), (6, 'Sinh học'),
                                (7, 'Lịch sử'), (8, 'Địa lý'), (9, 'GDCD');


INSERT INTO exam_groups (id, group_name) VALUES
                                         (1,'A00'), (2, 'A01'), (3, 'B'), (4, 'C'), (5, 'D01'), (6, 'D07');


-- Khối A00: Toán, Vật lý, Hóa học
INSERT INTO group_subjects (group_id, subject_id) VALUES (1, 1), (1, 4), (1, 5);

-- Khối A01: Toán, Vật lý, Ngoại ngữ
INSERT INTO group_subjects (group_id, subject_id) VALUES (2, 1), (2, 4), (2, 3);

-- Khối B: Toán, Hóa học, Sinh học
INSERT INTO group_subjects (group_id, subject_id) VALUES (3, 1), (3, 5), (3, 6);

-- Khối C: Ngữ văn, Lịch sử, Địa lý
INSERT INTO group_subjects (group_id, subject_id) VALUES (4, 2), (4, 7), (4, 8);

-- Khối D01: Toán, Ngữ văn, Ngoại ngữ
INSERT INTO group_subjects (group_id, subject_id) VALUES (5, 1), (5, 2), (5, 3);

-- Khối D07: Toán, Hóa học, Ngoại ngữ
INSERT INTO group_subjects (group_id, subject_id) VALUES (6, 1), (6, 5), (6, 3);