package my.study.hello.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import my.study.hello.springboot.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile", "/error/**").permitAll() // add "/profile"
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
}
//@RequiredArgsConstructor
//@EnableWebSecurity // activate 'Spring Security configurations'
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final CustomOAuth2UserService customOAuth2UserService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable().headers().frameOptions().disable() // for using a h2-console UI
//                .and()
//                    .authorizeRequests() // is the start point that controls authorization options of each URLs
//                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
//                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
//                    /* [Note]
//                     * antMatches : is a option to manage authorizations of each URLs and HTTP methods
//                     * .permitAll() gives "/", "/css/**" (and so on) to the authorization about whole access auth.
//                     * .hasRole() gives "/api/v1/**" to the authorization about to access by only User who have "ROLE-USER" auth.
//                     */
//                    .anyRequest().authenticated()
//                    /* [Note]
//                     * anyRequest : all URLS except above URLs
//                     * in here, I added authenticated() - only for users who have been logined.
//                     */
//                .and()
//                    .logout()
//                        .logoutSuccessUrl("/") // is the start point that controls a logout function. if success to logout, it redirects to "/"
//                .and()
//                    .oauth2Login() // is the start point that controls a Oauth2 login function.
//                        .userInfoEndpoint() // controls to get user info after to success Oauth2 login
//                            .userService(customOAuth2UserService); // UserService interface to process after login
//    }
//}
