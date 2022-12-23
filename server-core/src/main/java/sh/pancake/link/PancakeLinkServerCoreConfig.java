/*
 * Created on Fri Dec 23 2022
 *
 * Copyright (c) storycraft. Licensed under the GNU General Public License v3.
 */
package sh.pancake.link;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ImportResource("classpath:mybatis.xml")
public class PancakeLinkServerCoreConfig {
    @Getter
    @Setter
    private DataSource dataSource;
}
