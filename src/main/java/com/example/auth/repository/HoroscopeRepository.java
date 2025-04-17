package com.example.auth.repository;

import com.example.auth.entity.HoroscopeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HoroscopeRepository extends JpaRepository<HoroscopeEntity, Long> {
    @Query("SELECT h FROM HoroscopeEntity h WHERE " +
           "((h.startMonth = :month AND h.startDay <= :day) OR " +
           "(h.endMonth = :month AND h.endDay >= :day)) OR " +
           "(h.startMonth > h.endMonth AND " +
           "((h.startMonth <= :month AND :month <= 12) OR " +
           "(1 <= :month AND :month <= h.endMonth)))")
    Optional<HoroscopeEntity> findByMonthAndDay(int month, int day);
} 