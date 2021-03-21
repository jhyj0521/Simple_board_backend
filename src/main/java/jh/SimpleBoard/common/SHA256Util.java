package jh.SimpleBoard.common;

import jh.SimpleBoard.domain.Member;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SHA256Util {

    /**
     * 난수 SALT를 발생시킴
     * @return
     */
    public static String generateSalt() {

        Random random = new Random();

        byte[] salt = new byte[8];
        random.nextBytes(salt);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < salt.length; i++) {
            // byte 값을 Hex 값으로 바꾸기.
            sb.append(String.format("%02x",salt[i]));
        }

        return sb.toString();
    }

    /**
     * 비밀번호와 salt를 조합해 암호화 처리
     * @param pwd - 비밀번호
     * @param salt - 솔트 값
     * @return
     */
    public static String getEncrypt(String pwd, String salt) {

        String result = "";

        byte[] pwdBytes = pwd.getBytes();
        byte[] saltBytes = salt.getBytes();
        byte[] bytes = new byte[pwdBytes.length + saltBytes.length];

        System.arraycopy(pwdBytes, 0, bytes, 0, pwdBytes.length);
        System.arraycopy(saltBytes, 0, bytes, pwdBytes.length, saltBytes.length);

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes);

            byte[] b = md.digest();

            StringBuffer sb = new StringBuffer();

            for(int i=0; i<b.length; i++) {
                sb.append(Integer.toString((b[i] & 0xFF) + 256, 16).substring(1));
            }

            result = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 난수 솔트를 생성하여 저장하고, 비밀번호를 암호화하여 저장한다.
     * @param member - 비밀번호를 암호화할 유저
     * @return
     */
    public static void setEncrypt (Member member) {

        String salt = generateSalt();
        member.setSalt(salt);

        String password = getEncrypt(member.getPassword(), salt);
        member.setPassword(password);
    }
}
