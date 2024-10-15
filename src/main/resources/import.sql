INSERT INTO tb_user(first_name, last_name, email, password) VALUES('John', 'Doe', 'john@gmail.com', '$2a$12$jYoAYQUY1i.JPrPoVhrJg.30hvqgVY20bhStwsz0VJrBqTshM/y5S');

INSERT INTO tb_role(authority) VALUES('ROLE_ADMIN');
INSERT INTO tb_role(authority) VALUES('ROLE_USER');

INSERT INTO tb_user_role(user_id, role_id) VALUES(1,1);
INSERT INTO tb_user_role(user_id, role_id) VALUES(1,2);

-- Inserts Experience
INSERT INTO tb_experience(position, company, description, start_date, end_date) VALUES('Analista de desenvolvimento JR', 'Rolemar', 'Manutencao e desenvolvimento de novas features na aplicação da empresa, um e-commerce de pecas automotivas com integracão ao erp da empresa. Aplicação web desenvolvida em php e react.', '2022-06-01', '2023-12-01' );


-- Inserts Education
INSERT INTO tb_education(course_name, institution, description, certificate_url, workload, start_date, end_date) VALUES('Desenvolvedor Moderno', 'Devsuperior', 'Na jornada os seguintes cursos foram concluídos: 1. Lógica de programação; 2. Git e Github; 3. HTML e CSS; 4. Programação Moderna; 5. Banco de Dados; 6. Análise de Sistemas; 7. Ambiente de Desenvolvimento; 8. Back End; 9. JavaScript; 10. Front end.', 'https://learn.devsuperior.com/certificados/7774880', 650, '2022-09-01', '2023-09-01');

-- Inserts Skills
INSERT INTO tb_skill(name, icon_url, doc_url, level, show_as_ability) VALUES('java script', 'https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/javascript/javascript-plain.svg', 'https://docs.oracle.com/en/java/', 60, true );
INSERT INTO tb_skill(name, icon_url, doc_url, level, show_as_ability) VALUES('java', 'https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original.svg', 'https://docs.oracle.com/en/java/', 70, false );
INSERT INTO tb_skill(name, icon_url, doc_url, level, show_as_ability) VALUES('python', 'https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/python/python-original.svg', 'https://docs.oracle.com/en/java/', 50, true );

--Inserts Category
INSERT INTO tb_category(name) VALUES('front-end');
INSERT INTO tb_category(name) VALUES('back-end');
INSERT INTO tb_category(name) VALUES('devops');

--Inserts Message
INSERT INTO tb_message(name, email, subject, message, sent_at, is_read) VALUES ('John Doe', 'johndoe@example.com', 'Inquiry about product', 'I would like to know more about your product specifications.', '2024-09-24T14:30:00Z', false);
INSERT INTO tb_message (name, email, subject, message, sent_at, is_read) VALUES ('Jane Smith', 'janesmith@example.com', 'Support Request', 'I am experiencing issues with the latest update of your software.', '2024-09-23T09:15:00Z', true);
INSERT INTO tb_message (name, email, subject, message, sent_at, is_read) VALUES ('Carlos Silva', 'carlos.silva@example.com', 'Billing Question', 'Could you provide more details about the charges on my last invoice?', '2024-09-22T16:45:00Z', false);

--Inserts Projects
INSERT INTO tb_project(title, description, repository_url, live_url, created_at) VALUES('E-commerce product page', 'Successfully crafted an engaging product page featuring a dynamic lightbox gallery and seamless cart functionality, showcasing proficiency in JavaScript development.', 'https://ruderson.com.br/home', 'https://github.com/Rudersonvf/portfolio-react', '2024-09-22T16:45:00Z');

--Insert Images
INSERT INTO tb_image(url, project_id) VALUES('https://raw.githubusercontent.com/Rudersonvf/assets/main/port1.png', 1);
INSERT INTO tb_image(url, project_id) VALUES('https://raw.githubusercontent.com/Rudersonvf/assets/main/crud1.png', 1);
INSERT INTO tb_image(url, project_id) VALUES('https://raw.githubusercontent.com/Rudersonvf/assets/main/devCep.png', 1);

INSERT INTO tb_project_category(project_id, category_id) VALUES(1, 1);
INSERT INTO tb_project_category(project_id, category_id) VALUES(1, 2);

INSERT INTO tb_project_skill(project_id, skill_id) VALUES(1, 1);
INSERT INTO tb_project_skill(project_id, skill_id) VALUES(1, 2);
INSERT INTO tb_project_skill(project_id, skill_id) VALUES(1, 3);
