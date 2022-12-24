/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import lombok.Data;

@Configuration
@Data
@ImportResource("classpath:database.xml")
public class PancakeLinkDatabaseConfig {
    private String driverClassName;

    private String url;

    private String username;
    private String password;

    @Bean
    public DataSource dataSource() throws Exception {
        Class.forName(driverClassName);
        return new DriverManagerDataSource(url, username, password);
    }
}
