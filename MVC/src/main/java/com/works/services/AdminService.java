package com.works.services;

import com.works.entities.Admin;
import com.works.repositoies.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    final AdminRepository adminRepository;
    final TinkEncDec tinkEncDec;
    final HttpServletRequest request;

    public boolean save(Admin admin) {
        boolean status = false;
        Optional<Admin> optionalAdmin = adminRepository.findByEmailEqualsIgnoreCase(admin.getEmail());
        if ( !optionalAdmin.isPresent() ) {
            admin.setPassword(tinkEncDec.encrypt(admin.getPassword()));
            adminRepository.save(admin);
        }
        return status;
    }


    public boolean login( Admin admin ) {
        boolean status = false;
        Optional<Admin> optionalAdmin = adminRepository.findByEmailEqualsIgnoreCase(admin.getEmail());
        if ( optionalAdmin.isPresent() ) {
            Admin adm = optionalAdmin.get();
            String dbPass = tinkEncDec.decrypt( adm.getPassword() );
            if ( dbPass.equals(admin.getPassword()) ) {
                // create session
                request.getSession().setAttribute("user", adm );
                status = true;
            }
        }
        return status;
    }

    public void logout() {
        request.getSession().removeAttribute("user");
    }

}
