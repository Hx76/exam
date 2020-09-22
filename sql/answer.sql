CREATE TABLE `answer` (
                          `id` int(8) NOT NULL,
                          `question_id` int(8) NOT NULL,
                          `correct_option` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`,`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;