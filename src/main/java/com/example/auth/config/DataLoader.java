package com.example.auth.config;

import com.example.auth.entity.HoroscopeEntity;
import com.example.auth.repository.HoroscopeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner initDatabase(HoroscopeRepository horoscopeRepository) {
        return args -> {
            if (horoscopeRepository.count() == 0) {
                horoscopeRepository.save(createHoroscope("Koç", 3, 21, 4, 19));
                horoscopeRepository.save(createHoroscope("Boğa", 4, 20, 5, 20));
                horoscopeRepository.save(createHoroscope("İkizler", 5, 21, 6, 20));
                horoscopeRepository.save(createHoroscope("Yengeç", 6, 21, 7, 22));
                horoscopeRepository.save(createHoroscope("Aslan", 7, 23, 8, 22));
                horoscopeRepository.save(createHoroscope("Başak", 8, 23, 9, 22));
                horoscopeRepository.save(createHoroscope("Terazi", 9, 23, 10, 22));
                horoscopeRepository.save(createHoroscope("Akrep", 10, 23, 11, 21));
                horoscopeRepository.save(createHoroscope("Yay", 11, 22, 12, 21));
                horoscopeRepository.save(createHoroscope("Oğlak", 12, 22, 1, 19));
                horoscopeRepository.save(createHoroscope("Kova", 1, 20, 2, 18));
                horoscopeRepository.save(createHoroscope("Balık", 2, 19, 3, 20));

                System.out.println("Burç verileri başarıyla yüklendi.");
            }
        };
    }

    private HoroscopeEntity createHoroscope(String name, int startMonth, int startDay, int endMonth, int endDay) {
        HoroscopeEntity horoscopeEntity = new HoroscopeEntity();
        horoscopeEntity.setName(name);
        horoscopeEntity.setStartMonth(startMonth);
        horoscopeEntity.setStartDay(startDay);
        horoscopeEntity.setEndMonth(endMonth);
        horoscopeEntity.setEndDay(endDay);
        return horoscopeEntity;
    }
}