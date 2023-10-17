package kma.atweb.vn.project.utils;

import javax.servlet.http.HttpServletRequest;

import kma.atweb.vn.project.model.CartInfo;

import com.bastiaanjansen.otp.HMACAlgorithm;
import com.bastiaanjansen.otp.TOTPGenerator;

import java.time.Duration;
import java.util.Base64;

public class Utils {

    // Products in the cart, stored in Session.
    public static CartInfo getCartInSession(HttpServletRequest request) {

        CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("myCart");


        if (cartInfo == null) {
            cartInfo = new CartInfo();

            request.getSession().setAttribute("myCart", cartInfo);
        }

        return cartInfo;
    }

    public static void removeCartInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("myCart");
    }

    public static void storeLastOrderedCartInSession(HttpServletRequest request, CartInfo cartInfo) {
        request.getSession().setAttribute("lastOrderedCart", cartInfo);
    }

    public static CartInfo getLastOrderedCartInSession(HttpServletRequest request) {
        return (CartInfo) request.getSession().getAttribute("lastOrderedCart");
    }

    public static String genOTP() {
        String base64Secret = "QVZERDdBMkdFS0VDRzVaM01CNDRYU0NQQ1pIRlZSVlM=";
        byte[] secret = Base64.getDecoder().decode(base64Secret);
        TOTPGenerator totp = new TOTPGenerator.Builder(secret).withHOTPGenerator(builder -> {
            builder.withPasswordLength(6);
            builder.withAlgorithm(HMACAlgorithm.SHA256);
        }).withPeriod(Duration.ofSeconds(600)).build();
        String otpCode = totp.now();
        return otpCode;
    }

    public static boolean validOTP(String otp) {
        String base64Secret = "QVZERDdBMkdFS0VDRzVaM01CNDRYU0NQQ1pIRlZSVlM=";
        byte[] secret = Base64.getDecoder().decode(base64Secret);
        TOTPGenerator totp = new TOTPGenerator.Builder(secret).withHOTPGenerator(builder -> {
            builder.withPasswordLength(6);
            builder.withAlgorithm(HMACAlgorithm.SHA256);
        }).withPeriod(Duration.ofSeconds(600)).build();
        boolean isValid = totp.verify(otp.toString());
        return isValid;
    }
}
