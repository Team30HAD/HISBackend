package com.had.his.Security;

import com.had.his.UserDetailsService.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.SecurityMarker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private NurseDetailsService nurseDetailsService;

    private NursejwtAuthenticationFilter nursejwtAuthenticationFilter;

    private DoctorjwtAuthenticationFilter doctorjwtAuthenticationFilter;
    private DoctorDetailsService doctorDetailsService;

    private PharmacyDetailsService pharmacyDetailsService;

    private PharmacyJwtAuthenticationFilter pharmacyJwtAuthenticationFilter;

    private AdminJwtAuthenticationFilter adminJwtAuthenticationFilter;

    private AdminDetailsService adminDetailsService;

    private ReceptionistJwtAuthenticationFilter receptionistJwtAuthenticationFilter;
    private ReceptionistDetailsService receptionistDetailsService;

    public SecurityConfig(NurseDetailsService nurseDetailsService, NursejwtAuthenticationFilter nursejwtAuthenticationFilter, DoctorjwtAuthenticationFilter doctorjwtAuthenticationFilter, DoctorDetailsService doctorDetailsService, PharmacyDetailsService pharmacyDetailsService, PharmacyJwtAuthenticationFilter pharmacyJwtAuthenticationFilter, AdminJwtAuthenticationFilter adminJwtAuthenticationFilter, AdminDetailsService adminDetailsService, ReceptionistJwtAuthenticationFilter receptionistJwtAuthenticationFilter, ReceptionistDetailsService receptionistDetailsService) {
        this.nurseDetailsService = nurseDetailsService;
        this.nursejwtAuthenticationFilter = nursejwtAuthenticationFilter;
        this.doctorjwtAuthenticationFilter = doctorjwtAuthenticationFilter;
        this.doctorDetailsService = doctorDetailsService;
        this.pharmacyDetailsService = pharmacyDetailsService;
        this.pharmacyJwtAuthenticationFilter = pharmacyJwtAuthenticationFilter;
        this.adminJwtAuthenticationFilter = adminJwtAuthenticationFilter;
        this.adminDetailsService = adminDetailsService;
        this.receptionistJwtAuthenticationFilter = receptionistJwtAuthenticationFilter;
        this.receptionistDetailsService = receptionistDetailsService;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain nursesecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/nurse/**")
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/nurse/login").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/nurse/**").hasAuthority("NURSE")
                        .anyRequest().authenticated()
                ).userDetailsService(nurseDetailsService)
                .sessionManagement(ses->ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(nursejwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain doctorsecurityFilterChain(HttpSecurity http) throws Exception {


        http.csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/doctor/**")
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/doctor/login").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/doctor/**").hasAuthority("DOCTOR")
                        .anyRequest().authenticated()
                ).userDetailsService(doctorDetailsService)
                .sessionManagement(ses->ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(doctorjwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);



        return http.build();
    }

    @Bean
    @Order(3)
    public SecurityFilterChain pharmacysecurityFilterChain(HttpSecurity http) throws Exception {


        http.csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/pharmacy/**")
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/pharmacy/login").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/pharmacy/**").hasAuthority("PHARMACY")
                        .anyRequest().authenticated()
                ).userDetailsService(pharmacyDetailsService)
                .sessionManagement(ses->ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(pharmacyJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);



        return http.build();
    }
    @Bean
    @Order(4)
    public SecurityFilterChain adminsecurityFilterChain(HttpSecurity http) throws Exception {


        http.csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/admin/**")
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/admin/login").permitAll()
                        .requestMatchers("/admin/addAdmin").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                ).userDetailsService(adminDetailsService)
                .sessionManagement(ses->ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(adminJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);



        return http.build();
    }

    @Bean
    @Order(5)
    public SecurityFilterChain receptionistsecurityFilterChain(HttpSecurity http) throws Exception {


        http.csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/receptionist/**")
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/receptionist/login").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/receptionist/**").hasAuthority("RECEPTIONIST")
                        .anyRequest().authenticated()
                ).userDetailsService(receptionistDetailsService)
                .sessionManagement(ses->ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(receptionistJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);



        return http.build();
    }






    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration )
    {
        try {
            return configuration.getAuthenticationManager();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

