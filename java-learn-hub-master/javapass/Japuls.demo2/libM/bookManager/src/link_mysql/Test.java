package link_mysql;

public class Test {
    public static boolean isEmpty(String str){
        if(str==null || "".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断是否不是空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str){
        if(str!=null && !"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }







}
