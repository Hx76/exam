CREATE TABLE `options` (
                           `id` int(11) NOT NULL,
                           `option` varchar(255) NOT NULL COMMENT '选项',
                           `option_number` varchar(4) NOT NULL COMMENT '选项编号',
                           `question_id` int(8) NOT NULL COMMENT '题目编号',
                           PRIMARY KEY (`id`,`option`,`question_id`),
                           KEY `题目编号` (`question_id`),
                           CONSTRAINT `题目编号` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;