package com.example.auth.config;

import com.example.auth.model.Horoscope;
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
                // Burç bilgilerini ekleyelim
                horoscopeRepository.save(new Horoscope(null, "Koç", 3, 21, 4, 19));
                horoscopeRepository.save(new Horoscope(null, "Boğa", 4, 20, 5, 20));
                horoscopeRepository.save(new Horoscope(null, "İkizler", 5, 21, 6, 20));
                horoscopeRepository.save(new Horoscope(null, "Yengeç", 6, 21, 7, 22));
                horoscopeRepository.save(new Horoscope(null, "Aslan", 7, 23, 8, 22));
                horoscopeRepository.save(new Horoscope(null, "Başak", 8, 23, 9, 22));
                horoscopeRepository.save(new Horoscope(null, "Terazi", 9, 23, 10, 22));
                horoscopeRepository.save(new Horoscope(null, "Akrep", 10, 23, 11, 21));
                horoscopeRepository.save(new Horoscope(null, "Yay", 11, 22, 12, 21));
                horoscopeRepository.save(new Horoscope(null, "Oğlak", 12, 22, 1, 19));
                horoscopeRepository.save(new Horoscope(null, "Kova", 1, 20, 2, 18));
                horoscopeRepository.save(new Horoscope(null, "Balık", 2, 19, 3, 20));
                
                System.out.println("Burç verileri başarıyla yüklendi.");
            }
        };
    }
} 