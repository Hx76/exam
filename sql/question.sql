CREATE TABLE `question` (
                            `id` int(11) NOT NULL,
                            `question_body` varchar(255) NOT NULL COMMENT '题干',
                            `type_id` varchar(255) NOT NULL COMMENT '类型',
                            `exam_id` int(8) NOT NULL,
                            PRIMARY KEY (`id`,`question_body`,`exam_id`) USING BTREE,
                            KEY `id` (`id`),
                            KEY `考试编号` (`exam_id`),
                            CONSTRAINT `考试编号` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;