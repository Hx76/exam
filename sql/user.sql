CREATE TABLE `user` (
                        `id` int(255) NOT NULL AUTO_INCREMENT,
                        `email` varchar(255) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `username` varchar(255) DEFAULT NULL,
                        `class_id` int(11) DEFAULT NULL,
                        PRIMARY KEY (`id`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
# 加密
HEX(AES_ENCRYPT(username,'hx76'));
