package cn.qzwxixi.utils;

/**
 * @version 1.0
 * @Author qin
 * @Date 2022/10/14 14:41
 * 冒泡排序,选择排序，插入排序
 */
public class mappao {
    //1.确定结构：静态方法<=(int [] arr,String method)==>arr返回排序后数组
    //2.主函数

    static boolean paduan(String s){
        if(s.equals("S")==true) {
            return true;
        }
        return false;
    }
    static int [] MaoSort(int arr[],String s){
        if(paduan(s)){
            for(int i=0;i<arr.length-1;i++){
                //遍历次数为n,即0到n-1，保证每个数都被遍历
                for(int j=0;j<arr.length-i-1;i++){
                    //又因为要从左往右泡泡过程中要根据当前下表来算，因此
                    if(arr[j]<arr[j+1]){
                        int temp=arr[j];
                        arr[j]=arr[j+1];
                        arr[j+1]=temp;
                    }
                }
            }
            return arr;
        }else {
            for(int i=0;i<arr.length-1;i++){
                //遍历次数为n,即0到n-1，保证每个数都被遍历
                for(int j=0;j<arr.length-i-1;i++){
                    //又因为要从左往右泡泡过程中要根据当前下表来算，因此
                    if(arr[j]>arr[j+1]){
                        int temp=arr[j];
                        arr[j]=arr[j+1];
                        arr[j+1]=temp;
                    }
                }
            }
        }
        return null;
    }
    static int[] XunZeSort(int arr[],String s){
        return null;
    }

    static int[] ChaRu(int arr[],String s){
        return null;
    }

    public static void main(String[] args) {
        int arr[] = {1,8,3,2,33,2}; //3.带输入数组
        int newarr[]=(MaoSort(arr,"S")); //4.待接收数组
        for(int i=0;i<newarr.length;i++){
            System.out.print(newarr[i]);
        }

    }

}
