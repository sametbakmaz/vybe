package com.example.auth.repository;

import com.example.auth.entity.Horoscope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HoroscopeRepository extends JpaRepository<Horoscope, Long> {
    @Query("SELECT h FROM Horoscope h WHERE " +
           "((h.startMonth = :month AND h.startDay <= :day) OR " +
           "(h.endMonth = :month AND h.endDay >= :day)) OR " +
           "(h.startMonth > h.endMonth AND " +
           "((h.startMonth <= :month AND :month <= 12) OR " +
           "(1 <= :month AND :month <= h.endMonth)))")
    Optional<Horoscope> findByMonthAndDay(int month, int day);
} 