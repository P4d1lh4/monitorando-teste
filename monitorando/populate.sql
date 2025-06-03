-- Popula tabela users (corrigido com surname)
INSERT INTO users (id, email, name, surname, password, role, active)
VALUES
    (1, 'xavier@monitorando.com', 'Professor', 'Xavier', 'senha123', 'PROFESSOR', false),
    (2, 'jean@monitorando.com', 'Jean', 'Grey', 'senha123', 'STUDENT', false),
    (3, 'scott@monitorando.com', 'Scott', 'Summers', 'senha123', 'STUDENT', false),
    (4, 'ororo@monitorando.com', 'Ororo', 'Munroe', 'senha123', 'MONITOR', false);

-- Popula tabela students
INSERT INTO students (id)
VALUES
    (2),
    (3),
    (4);

-- Popula tabela disciplines
INSERT INTO disciplines (id, name, code)
VALUES
    (1, 'Álgebra Linear', 'adibnei'),
    (2, 'Estrutura de Dados', 'dfiwbn');

-- Popula tabela monitors
INSERT INTO monitors (id, discipline_id)
VALUES
    (4, 1);

-- Popula tabela student_discipline (relacionamento many-to-many)
INSERT INTO student_discipline (student_id, discipline_id)
VALUES
    (2, 1),
    (3, 2),
    (4, 1);

