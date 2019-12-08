INSERT INTO `managers` (`name`, `email`, `phone`)
VALUES ('테스트 담당자', 'test@platform.com', '');

INSERT INTO `platforms` (`title`, `manager_id`, `code`, `api_url`, `api_key`, `token`)
VALUES ('테스트', 1, 'test', 'http://localhost:8080/dcat/dataset', 'test', 'test-token');