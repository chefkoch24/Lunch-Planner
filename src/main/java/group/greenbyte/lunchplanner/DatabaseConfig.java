package group.greenbyte.lunchplanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://schalter-info.de:3306/greenbyte");
        driverManagerDataSource.setUsername("greenbyte");
        driverManagerDataSource.setPassword("1qay\"WSX");
        return driverManagerDataSource;
    }
}
