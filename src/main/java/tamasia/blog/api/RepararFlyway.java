package tamasia.blog.api;

import org.flywaydb.core.Flyway;

public class RepararFlyway {

    public static void main(String[] args) {

        Flyway flyway = Flyway.configure()
                .dataSource(
                    "jdbc:mysql://localhost:3306/blogappdb?serverTimezone=America/Sao_Paulo",
                    "root",
                    "15051709"
                 
                )
                .locations("classpath:db/migration")
                .load();

        flyway.repair();

        System.out.println("Reparo do Flyway concluído!");
    }
}
