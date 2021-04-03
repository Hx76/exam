import com.onlineexam.utils.VerificationCodeUtil;

public class Test {
    public static void main(String[] args) {
        Integer id = Integer.valueOf(VerificationCodeUtil.generateVerifyCode(7));
        System.out.println(id);
        String s = "2+2=?]";
        int len = s.length();
        System.out.println(s.substring(0,len-1));
    }
}
