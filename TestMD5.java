import org.springframework.util.DigestUtils;

public class TestMD5 {
    public static void main(String[] args) {
        String password = "123456";
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println("加密前: " + password);
        System.out.println("加密后: " + encryptedPassword);
        System.out.println("预期值: e10adc3949ba59abbe56e057f20f883e");
        System.out.println("是否匹配: " + encryptedPassword.equals("e10adc3949ba59abbe56e057f20f883e"));
    }
}