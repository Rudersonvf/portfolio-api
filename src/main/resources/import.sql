INSERT INTO tb_user(first_name, last_name, email, password) VALUES('John', 'Doe', 'john@gmail.com', '$2a$12$jYoAYQUY1i.JPrPoVhrJg.30hvqgVY20bhStwsz0VJrBqTshM/y5S');

INSERT INTO tb_role(authority) VALUES('ROLE_ADMIN');
INSERT INTO tb_role(authority) VALUES('ROLE_USER');

INSERT INTO tb_user_role(user_id, role_id) VALUES(1,1);
INSERT INTO tb_user_role(user_id, role_id) VALUES(1,2);

-- Insert Experience
INSERT INTO tb_experience(position, company, description, start_date, end_date) VALUES('Analista de desenvolvimento JR', 'Rolemar', 'Manutencao e desenvolvimento de novas features na aplicação da empresa, um e-commerce de pecas automotivas com integracão ao erp da empresa. Aplicação web desenvolvida em php e react.', '2022-06-01', '2023-12-01' );


-- Insert Education
INSERT INTO tb_education(course_name, institution, description, certificate_url, workload, start_date, end_date) VALUES('Desenvolvedor Moderno', 'Devsuperior', 'Na jornada os seguintes cursos foram concluídos: 1. Lógica de programação; 2. Git e Github; 3. HTML e CSS; 4. Programação Moderna; 5. Banco de Dados; 6. Análise de Sistemas; 7. Ambiente de Desenvolvimento; 8. Back End; 9. JavaScript; 10. Front end.', 'https://learn.devsuperior.com/certificados/7774880', 650, '2022-09-01', '2023-09-01');