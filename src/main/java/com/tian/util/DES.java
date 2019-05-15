package com.tian.util;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.Security;

public class DES {
    /**
     * DES加密介绍 DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
     * 后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，
     * 24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现 。
     * 注意：DES加密和解密过程中，密钥长度都必须是8的倍数
     */


    //定义加密算法类型
    private  static final  String ENCRYPT_TYPE = "DES";
    //字符串默认键值
    private static String defaultKey = "";
    //加密工具
    private Cipher encryptCipher = null;
    //解密工具
    private Cipher decrpytCipher = null;


    public DES() throws Exception{
        this(defaultKey);
    }

    /**
     * 指定密钥构造方法
     *
     * @param strKey 指定的密钥
     * @throws Exception
     */
    public DES(String strKey) throws Exception{
        //添加安全算法,如果用JCE就要把它添加进去 这是java中的一个加密类
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        Key key = getKey(strKey.getBytes());

        //加密
        //调用Cipher类中的getInstance静态工厂方法得到Cipher对象 ENCRYPT_TYPE = "DES" 以DES方式进行加密
        //getInstance工厂返回的对象没有初始化，在使用前必须进行初始化
        encryptCipher = Cipher.getInstance(ENCRYPT_TYPE);
        //使用Cipher.init对getInstance工厂返回的对象进行初始化
        encryptCipher.init(Cipher.ENCRYPT_MODE,key);
        //解密

        decrpytCipher = Cipher.getInstance(ENCRYPT_TYPE);
        decrpytCipher.init(Cipher.ENCRYPT_MODE,key);


    }

    /**
     * 加密字节数组
     *
     * @param arr 需要加密的字节数组
     * @return 加密后的字节数组
     * @throws Exception
     */
    private byte[] encryptStr(byte[] arr)throws Exception{
        //调用Cipher的doFinal方法来完成单步加密
        return encryptCipher.doFinal(arr);
    }


    /**
     * 加密字符串
     *
     * @param strIn
     *            需加密的字符串
     * @return 加密后的字符串
     * @throws Exception
     */
    public String encrypt(String strIn)throws Exception{
        return StrConvertUtil.byteArrToHexStr(encryptStr(strIn.getBytes()));
    }

    /**
     * 解密字节数组
     *
     * @param arr
     *            需解密的字节数组
     * @return 解密后的字节数组
     * @throws Exception
     */
    private byte[] decryptStr(byte[] arr)throws Exception{
        return decrpytCipher.doFinal(arr);
    }

    /**
     * 解密字符串
     *
     * @param strIn
     *            需解密的字符串
     * @return 解密后的字符串
     * @throws Exception
     */
    public String decrypt(String strIn) throws Exception {

        return new String(decryptStr(StrConvertUtil.hexStrToByteArr(strIn)));
    }


    private Key getKey(byte[] arrBTmp) {
        //创建一个空的8位字节数组 （默认值为0）
        byte[] arrB = new byte[8];

        //将原始字节数组转换为8位
        for (int i = 0;i < arrBTmp.length && i <arrB.length; i++){
            arrB[i] = arrBTmp[i];
        }
        //生成密钥
        Key key = new javax.crypto.spec.SecretKeySpec(arrB,ENCRYPT_TYPE);
        return key;
    }

    public static  void main(String[] args){
        try {
            //自定义密钥
            DES des = new DES("Java");
            String src= "说相声的郭德纲kkk";

            String src5= "说相声的郭德纲kk";


            String src6 = des.encrypt(src5);

            String src1 = des.encrypt(src);
            System.out.println(src6.equals(src1));
            String src2 = des.decrypt(src1);
            System.out.println("DES加密前的字符串 "+ src + " 长度 "+ src.length());
            System.out.println("DES加密后的字符串 "+ src6 + " 长度 "+ src1.length());
            System.out.println("DES解密后的字符串 "+ src2 + " 长度 "+ src2.length());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

