CREATE TABLE `exam` (
                        `id` int(10) NOT NULL AUTO_INCREMENT,
                        `exam_serial_number` int(8) NOT NULL COMMENT '考试编号',
                        `name` varchar(255) NOT NULL COMMENT '考试名称',
                        `duration` varchar(255) NOT NULL COMMENT '考试时间',
                        `start_time` datetime(6) NOT NULL COMMENT '开始时间',
                        `creator` varchar(255) NOT NULL COMMENT '创建人',
                        `state` varchar(255) DEFAULT NULL COMMENT '状态',
                        `total_points` int(3) DEFAULT NULL COMMENT '总分',
                        `creation_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
                        `number_of_people` int(8) DEFAULT NULL COMMENT '考试人数',
                        PRIMARY KEY (`id`,`exam_serial_number`),
                        KEY `exam_serial_number` (`exam_serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;