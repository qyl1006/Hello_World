import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class TestMD5 {
    @Test
    public void testLogin(){
        Md5Hash md5Hash = new Md5Hash("1");
        System.out.println(md5Hash);

        //加盐
        System.out.println(new Md5Hash("666", "zhangsan"));

        //设置散列次数
        System.out.println(new SimpleHash("md5", "1", "admin",2));
    }
}
