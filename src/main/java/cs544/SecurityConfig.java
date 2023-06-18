//package cs544;
//
//
//import cs544.Service.CustomUserDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig{
//    @Autowired
//    private CustomUserDetailService customUserDetailService;
//
////    @Bean
////    public AuthenticationManager authenticationManagerBean() throws Exception{
////        return new AuthenticationManager() {
////
////    }
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder authenticationMangerBuilder) throws Exception {
////        authenticationMangerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
////    }
////@Bean
////public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////    http.authorizeHttpRequests(auth -> auth.requestMatchers("/signup").permitAll()
////                    .requestMatchers(HttpMethod.POST,"/signup").permitAll()
////            );
////    return http.build();
////}
//
//    protected void configure(HttpSecurity http) throws Exception{
//        http.csrf().disable().authorizeHttpRequests().anyRequest().authenticated().and().httpBasic();
//    }
//
//    protected void configure(AuthenticationManagerBuilder auth) throws  Exception{
//       auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//  }
//}
