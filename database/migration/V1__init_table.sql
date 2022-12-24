/*
 * Created on Sat Dec 24 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */

CREATE TABLE account (
    id INT PRIMARY KEY AUTO_INCREMENT,

    email VARCHAR(64) NOT NULL UNIQUE,
    credential VARCHAR(128),

    created_at BIGINT NOT NULL,
    activated_at BIGINT,
    updated_at BIGINT NOT NULL,

    suspended TINYINT NOT NULL
);

CREATE TABLE redirection (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    account_id INT NOT NULL,

    name VARCHAR(16) NOT NULL UNIQUE,
    url VARCHAR(512) NOT NULL,

    created_at BIGINT NOT NULL,
    updated_at BIGINT NOT NULL,
    
    expire_at BIGINT,
    visit_limit BIGINT,
    
    redirection_page TINYINT NOT NULL,
    user_disabled TINYINT NOT NULL
);

CREATE TABLE visitlog (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    redirection_id BIGINT NOT NULL,

    visitor_address VARCHAR(64) NOT NULL,

    visited_at BIGINT NOT NULL
);