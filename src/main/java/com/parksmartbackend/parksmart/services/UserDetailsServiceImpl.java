package com.parksmartbackend.parksmart.services;

import com.parksmartbackend.parksmart.model.User;
import com.parksmartbackend.parksmart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    @Value("xxx")
    String webUrl;

    @Value("${purge.daysToLive}")
    String timeToLive;

    public UserDetailsServiceImpl(UserRepository userRepository,PasswordEncoder encoder) {

        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));

        return UserDetailsImpl.build(user);
    }

//    public void sendConfirmationMail(String userMail, String token) {
//
//        final SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(userMail);
//        mailMessage.setSubject("Mail Confirmation Link!");
//        //mailMessage.setFrom("<MAIL>");
//        mailMessage.setText("Kayıt olduğunuz için teşekkürler.\nKullanıcı adınız: " + userMail + "\n Aktivasyon linki: "
//                + "" + webUrl + "activation/" + token + "\n\n" + "Thank you for registering.\nYour username: "
//                + userMail + "\n Activation link: " + "" + webUrl + "activation/" + token);
//
//        emailSenderService.sendEmail(mailMessage);
//    }

//    public void sendResetPasswordMail(String userMail, String token) {
//
//        final SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(userMail);
//        mailMessage.setSubject("Reset Password Link!");
//        mailMessage.setFrom("<MAIL>");
//        mailMessage.setText("Şifre sıfırlama linkiniz:" + "\n" + webUrl + "reset/" + token + "\n\n"
//                + "Your link to reset your password:" + "\n" + webUrl + "reset/" + token);
//
//        emailSenderService.sendEmail(mailMessage);
//    }

//    public void confirmUser(String token) throws TokenNotAvailableException {
//
//        Token confirmationToken = tokenService.findByToken(token);
//        final User user = confirmationToken.getUser();
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime tokenCreationTime = confirmationToken.getCreatedDate();
//        Long hours = Duration.between(now, tokenCreationTime).getSeconds() / 3600;
//        if (hours > 24) {
//            throw new TokenNotAvailableException("token expired!!");
//        }
//
//        user.setEnabled(true);
//        userRepository.save(user);
//        tokenService.deleteConfirmationToken(confirmationToken.getId());
//
//    }

//    public void resetPassword(String token, String newPassword) throws TokenNotAvailableException {
//
//        Token confirmationToken = tokenService.findByToken(token);
//        final User user = confirmationToken.getUser();
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime tokenCreationTime = confirmationToken.getCreatedDate();
//        Long hours = Duration.between(now, tokenCreationTime).getSeconds() / 3600;
//        if (hours > 1) {
//            throw new TokenNotAvailableException("token expired!!");
//        }
//        user.setPassword(encoder.encode(newPassword));
//        userRepository.save(user);
//        tokenService.deleteConfirmationToken(confirmationToken.getId());
//
//    }

    public User getUserFrom(UserDetailsImpl userDetails) {

        return userRepository.findByEmail(userDetails.getEmail()).orElse(null);
    }

}