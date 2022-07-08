package com.pultrax.reactdashfx.sale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class SaleConfig {

    @Bean
    CommandLineRunner commandLineRunner(SaleService service) {
        return args -> {
            Sale sale1 = new Sale(
                    LocalDate.of(2021, JANUARY, 15),
                    5,
                    100,
                    4
            );
            Sale sale2 = new Sale(
                    LocalDate.of(2021, JANUARY, 15),
                    3,
                    300,
                    1
            );
            Sale sale3 = new Sale(
                    LocalDate.of(2021, JANUARY, 16),
                    1,
                    200,
                    3
            );
            Sale sale4 = new Sale(
                    LocalDate.of(2021, JANUARY, 16),
                    3,
                    200,
                    4
            );
            Sale sale5 = new Sale(
                    LocalDate.of(2021, JANUARY, 17),
                    2,
                    300,
                    2
            );
            Sale sale6 = new Sale(
                    LocalDate.of(2021, JANUARY, 17),
                    4,
                    100,
                    5
            );
            Sale sale7 = new Sale(
                    LocalDate.of(2021, JANUARY, 18),
                    1,
                    300,
                    3
            );
            Sale sale8 = new Sale(
                    LocalDate.of(2021, JANUARY, 18),
                    2,
                    200,
                    1
            );
            Sale sale9 = new Sale(
                    LocalDate.of(2021, JANUARY, 19),
                    4,
                    100,
                    8
            );
            Sale sale10 = new Sale(
                    LocalDate.of(2021, JANUARY, 20),
                    5,
                    300,
                    1
            );

            Sale sale11 = new Sale(
                    LocalDate.of(2022, JANUARY, 15),
                    5,
                    100,
                    4
            );
            Sale sale12 = new Sale(
                    LocalDate.of(2022, JANUARY, 15),
                    3,
                    300,
                    1
            );
            Sale sale13 = new Sale(
                    LocalDate.of(2022, JANUARY, 16),
                    1,
                    200,
                    3
            );
            Sale sale14 = new Sale(
                    LocalDate.of(2022, JANUARY, 16),
                    3,
                    200,
                    4
            );
            Sale sale15 = new Sale(
                    LocalDate.of(2022, JANUARY, 17),
                    2,
                    300,
                    2
            );
            Sale sale16 = new Sale(
                    LocalDate.of(2022, JANUARY, 17),
                    4,
                    100,
                    5
            );
            Sale sale17 = new Sale(
                    LocalDate.of(2022, JANUARY, 18),
                    1,
                    300,
                    3
            );
            Sale sale18 = new Sale(
                    LocalDate.of(2022, JANUARY, 18),
                    2,
                    200,
                    1
            );
            Sale sale19 = new Sale(
                    LocalDate.of(2022, JANUARY, 19),
                    4,
                    100,
                    8
            );
            Sale sale20 = new Sale(
                    LocalDate.of(2022, JANUARY, 20),
                    5,
                    300,
                    1
            );
            service.addNewSale(
                    List.of(
                            sale1, sale2, sale3, sale4, sale5, sale6, sale7, sale8, sale9, sale10,
                            sale11, sale12, sale13, sale14, sale15, sale16, sale17, sale18, sale19, sale20
                    )
            );
        };
    }
}
