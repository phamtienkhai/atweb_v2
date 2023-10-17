package kma.atweb.vn.project.service;

import kma.atweb.vn.project.dao.AccountDAO;
import kma.atweb.vn.project.entity.Account;
import kma.atweb.vn.project.mail.MessageChannel;
import kma.atweb.vn.project.model.AccountInfo;
import kma.atweb.vn.project.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {
    @Autowired
    private AccountDAO accountDAO;
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    // Đổi mật khẩu
    public boolean changePassword(String userName, String newPassword) throws UsernameNotFoundException {
        Account account = accountDAO.findAccount(userName);

        if (account == null) {
            throw new UsernameNotFoundException("User " //
                    + userName + " was not found in the database");
        }

        String hashPwd = bCryptPasswordEncoder.encode(newPassword);
        account.setEncrytedPassword(hashPwd);
        return accountDAO.saveAccount(account);
    }

    // Gửi mã otp: phục vụ cho việc đổi mật khẩu (lúc đổi mật khẩu sẽ cần xác nhận otp, nếu thành công cho tiếp đổi mật khaaur)
    public boolean sendOTP(String email) {
        try {
            String otpCode = Utils.genOTP();

            Map<String, Object> mailInput = new HashMap<>();
            mailInput.put("mail_to", email);
            mailInput.put("subject", "OTP");
            mailInput.put("text", otpCode);
            MessageChannel.send(mailInput);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // Kiểm tra mã otp
    public boolean checkOTP(String otp) {
        return Utils.validOTP(otp);
    }

    // Đăng ký
    public boolean register(String userName, String password, String email, String fullName, String address, int age) {
        Account account = accountDAO.findAccount(userName);
        if(account != null) {
            return false;
        }

        account = new Account();
        account.setUserName(userName);
        account.setActive(true);
        account.setUserRole("ROLE_EMPLOYEE");
        account.setEncrytedPassword(bCryptPasswordEncoder.encode(password));
        account.setEmail(email);
        account.setFullName(fullName);
        account.setAddress(address);
        account.setAge(age);

        return accountDAO.saveAccount(account);
    }

    // Đổi thông tin tài khoản
    public boolean changeInfo(String userName, Account newAccount) throws UsernameNotFoundException {
        Account account = accountDAO.findAccount(userName);

        if (account == null) {
            throw new UsernameNotFoundException("User " //
                    + userName + " was not found in the database");
        }

        return accountDAO.saveAccount(newAccount);
    }

    // Lấy thông tin tài khoản
    public Map<String, Object> getInfo(String userName) throws UsernameNotFoundException {
        Account account = accountDAO.findAccount(userName);

        if (account == null) {
            throw new UsernameNotFoundException("User " //
                    + userName + " was not found in the database");
        }

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("fullName", account.getFullName());
        userInfo.put("email", account.getEmail());
        userInfo.put("address", account.getAddress());
        userInfo.put("age", account.getAge());
        return userInfo;
    }

    // Khóa tài khoản
    public boolean lockAccount(String userName) throws UsernameNotFoundException {
        Account account = accountDAO.findAccount(userName);

        if (account == null) {
            throw new UsernameNotFoundException("User " //
                    + userName + " was not found in the database");
        }

        if(account.isActive()) {
            account.setActive(false);
            return accountDAO.saveAccount(account);
        }
        return true;
    }

    // Mở khóa tài khoản
    public boolean unlockAccount(String userName) throws UsernameNotFoundException {
        Account account = accountDAO.findAccount(userName);

        if (account == null) {
            throw new UsernameNotFoundException("User " //
                    + userName + " was not found in the database");
        }

        if(!account.isActive()) {
            account.setActive(true);
            return accountDAO.saveAccount(account);
        }
        return true;
    }

    public List<AccountInfo> getAccounts() {
        return accountDAO.findAccounts("ROLE_EMPLOYEE");
    }
}
