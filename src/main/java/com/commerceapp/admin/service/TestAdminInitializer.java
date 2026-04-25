package com.commerceapp.admin.service;

import com.commerceapp.admin.entity.Admin;
import com.commerceapp.admin.repository.AdminRepository;
import com.commerceapp.common.config.PasswordEncoder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Component
@Profile({"local", "test"})
@RequiredArgsConstructor
public class TestAdminInitializer {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initTestAdmins() {
        log.info("슈퍼관리자 기능 테스트용 운영 관리자 계정 10개 생성을 시작합니다.");

        String encodedPassword = passwordEncoder.encode("12345678");

        for (int i = 1; i <= 10; i++) {
            String email = "testadmin" + i + "@gmail.com";

            Admin testAdmin = new Admin(
                    "테스트관리자" + i,
                    email,
                    encodedPassword,
                    "010-9999-" + String.format("%04d", i),
                    "운영"
            );


            if (i <= 2) {
                testAdmin.activate(LocalDateTime.now());
                log.debug("활성 상태 계정 세팅: {}", email);
            } else {
                log.debug("승인 대기 상태 계정 세팅: {}", email);
            }

            adminRepository.save(testAdmin);
        }

        log.info("테스트용 운영 관리자 계정 생성 완료 (활성: 2, 승인대기: 8)");
    }
}
